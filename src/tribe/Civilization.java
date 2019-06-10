package tribe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author conor
 */
public class Civilization {
    private ArrayList<Nation> nations;
    private ArrayList<Coordinate> occTiles;
    private GameWorld parent;
    private int popCiv;
    
    public Civilization(GameWorld gw) {
        this.nations = new ArrayList();
        this.occTiles = new ArrayList();
        this.parent = gw;
        this.popCiv = 0;
    }
    
    public Civilization(int popNation, int popMember, GameWorld gw) {
        this.nations = new ArrayList();
        this.occTiles = new ArrayList();
        this.parent = gw;
        this.popCiv = 0;
    }
    
    // moves all nations
    public void randMoveAllNation(Map land, ArrayList<Coordinate> occTiles, Tree society) {
        for(Nation n : nations) {
            n.randMoveAllMember(land, occTiles, society);
        }
    }
    
    public static Member findMember(Coordinate c, ArrayList<Nation> nations) {
        for(Nation n : nations) {
            Member m = n.findMember(c, n.getMemberList());
            if(m != null) {
                return m;
            }
        }
        return null;
    }
    
    // SETTERS && GETTERS && ADDERS
    // set nation list
    public void setNationList(ArrayList<Nation> nations) {
        this.nations = nations;
    }
    
    // returns nation list
    public ArrayList<Nation> getNationList() {
        return nations;
    }
    
    // creates a new nation of given size and adds to nation list
    // untested!
    public void addNation(int pop, int year, Map land) {
        Nation n = new Nation(this);
        for (int i = 0; i < pop; i++) {
            Random r1 = new Random();
            int rX = r1.nextInt(Tribe.WIDTH);
            Random r2 = new Random();
            int rY = r2.nextInt(Tribe.HEIGHT);
            Coordinate rC = new Coordinate(rX, rY);

            // create collider
            Member m = new Member(rC, year, n);
            Tile collider = new Tile(m);

            // find tile
            Acre aMove = land.getAcre(rX, rY);

            // find member, test if occupied 
            Member mMove = new Member(rC, n);
            Member testMove = Nation.findMember(mMove.getCords(), n.getMemberList());

            // create collidee
            Tile collidee = new Tile(testMove, aMove);
            TileCollisionManager canCollide = new TileCollisionManager(collider, collidee);
            if (!canCollide.memberToTileCollide()) {
                i--;
            } else {
                n.addMember(rC, year);
            }
        }
        nations.add(n);
    } 
    
    // adds new nation and adds to nation list
    public void addNation(Nation newNation) {
        nations.add(newNation);
    }
    
    // returns popCiv
    public int getPopCiv() {
        int tempPop = 0;
        for(Nation n : nations) {
            tempPop += n.getNationPop();
        }
        popCiv = tempPop;
        return popCiv;
    }
    
    public ArrayList<Coordinate> getOccTiles() {
        return occTiles;
    }
    
    // adds cords to occupied tile list
    public void addOccTile(Coordinate cord) {
        occTiles.add(cord);
        parent.addOccTiles(cord);

    }
    
    // sets parent
    public void setParent(GameWorld gw) {
        parent = gw;
    }
    // gets parent
    public GameWorld getParent() {
        return parent;
    }
}
