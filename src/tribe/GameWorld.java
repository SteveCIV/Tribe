package tribe;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Random;
import static tribe.Tribe.gw;

/**
 *
 * @author conor
 */
public class GameWorld {
    private final Map land;
    private final ArrayList<Civilization> civs;
    private final ArrayList<Coordinate> occTiles;
    private final Tree society;
    private int worldAge;
    
    public GameWorld() {
        land = new Map();
        civs = new ArrayList();
        occTiles = new ArrayList();
        society = new Tree();
        worldAge = 0;
    }
    
    // moves civilizatoin, setWorldOlder
    public void generateNewYear() {
        for(Civilization c : civs) {
            c.randMoveAllNation(land, occTiles, society);
        }
        setWorldOlder();
    }
    
    // GETTERS && SETTERS
    // sets land from given txt file
    public void setLand(Map land) throws FileNotFoundException {
        land = new Map();
    }
    
    // returns land
    public Map getLand() {
        return land;
    }
    
    // sets civilization
    public void setCiv(Civilization civ) {
        this.civs.add(civ);
    }
    
    // returns civilization
    public ArrayList<Civilization> getCivList() {
        return civs;
    }
    
    // creates a new civilization of given nation size and member size and adds to civilization list
    public void addCiv(int popNat, int popMem, int year) {
        // sets gw as parent of gw
        Civilization c = new Civilization(this);
        TNode cNode = new TNode();
        cNode.setData(c);
        society.setBase(new TNode(gw));
        cNode.setParent(society.getBase());
        
        // for given nation size
        for(int i = 0; i < popNat; i++) {
            
            // sets civilization as parent of nation
            Nation n = new Nation(c);
            TNode nNode = new TNode();
            nNode.setData(n);
            nNode.setParent(cNode);
            
            // for given member size
            for(int j = 0; j < popMem; j++) {
                
                // gets random coordinate
                Random r1 = new Random();
                int rX = r1.nextInt(Tribe.WIDTH);
                Random r2 = new Random();
                int rY = r2.nextInt(Tribe.HEIGHT);
                Coordinate rC = new Coordinate(rX, rY);

                // create collider
                Member m = new Member(rC, year, n);
                Tile collider = new Tile(m);
                
                // set nation as parent of member
                TNode mNode = new TNode();
                mNode.setData(m);
                mNode.setParent(nNode);
                m.setParent(n);

                // find tile
                Acre aMove = land.getAcre(rX, rY);

                // find member, test if occupied by any member in any civs
                Member mMove = new Member(rC, n);
                Member testMove = Civilization.findMember(mMove.getCords(), c.getNationList());

                // create collidee
                Tile collidee = new Tile(testMove, aMove);
                TileCollisionManager canCollide = new TileCollisionManager(collider, collidee);
                if(!canCollide.memberToTileCollide()) {
                    j--;
                } else {
                    n.addMember(rC, year);
                }
                nNode.setChildren(n.getMemberList());
                n.setParent(c);
            }
                        
            // sets nation MemberList as child of civilization
            c.addNation(n);
            cNode.setChildren(c.getNationList());
            c.setParent(gw);
        }
        
        // sets civilization NationList as child of society
        civs.add(c);
        society.getBase().setChildren(civs);
    }
    
    public void addOccTiles(Coordinate cord) {
        occTiles.add(cord);
    }
    
    public ArrayList<Coordinate> getOccTiles() {
        return occTiles;
    }
    
    // worldAge++
    private void setWorldOlder() {
        worldAge++;
    }
    
    // returns worldAge
    public int getWorldAge() {
        return worldAge;
    }
}
