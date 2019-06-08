package tribe;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 *
 * @author conor
 */
public class GameWorld {
    private final Map land;
    private ArrayList<Civilization> civs;
    private static ArrayList<Coordinate> occTiles;
    private int worldAge;
    
    public GameWorld() throws FileNotFoundException {
        this.land = new Map();
        this.civs = new ArrayList();
        this.occTiles = new ArrayList();
        this.worldAge = 0;
    }
    
    // moves civilizatoin, setWorldOlder
    public void generateNewYear() {
        for(Civilization c : civs) {
            c.randMoveAllNation(land, civs, occTiles);
        }
        setWorldOlder();
    }
    
    public static Member findMember(Coordinate cord, ArrayList<Civilization> civs) {
        Member mem = null;
        for(Civilization c : civs) {
            for(Nation n : c.getNationList()) {
                mem = n.findMember(cord, n.getMemberList());
            }
        }
        return mem;
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
        Civilization c = new Civilization();
        for(int i = 0; i < popNat; i++) {
            Nation n = new Nation();
            for(int j = 0; j < popMem; j++) {
                Random r1 = new Random();
                int rX = r1.nextInt(Tribe.WIDTH);
                Random r2 = new Random();
                int rY = r2.nextInt(Tribe.HEIGHT);
                Coordinate rC = new Coordinate(rX, rY);

                // create collider
                Member m = new Member(rC, year);
                Tile collider = new Tile(m);

                // find tile
                Acre aMove = land.getAcre(rX, rY);

                // find member, test if occupied by any member in any civs
                Member mMove = new Member(rC);
                Member testMove = Civilization.findMember(mMove.getCords(), c.getNationList());

                // create collidee
                Tile collidee = new Tile(testMove, aMove);
                TileCollisionManager canCollide = new TileCollisionManager(collider, collidee);
                if(!canCollide.memberToTileCollide()) {
                    j--;
                } else {
                    n.addMember(rC, year);
                }
            }
            c.addNation(n);
        }
        civs.add(c);
    }
    
    // worldAge++
    private void setWorldOlder() {
        worldAge++;
    }
    
    // returns worldAge
    public int getWorldAge() {
        return worldAge;
    }
    
    public ArrayList<Coordinate> getOccTiles() {
        return occTiles;
    }
    
    // adds cords to occupied tile list
    public void addTiles(Coordinate c) {
        occTiles.add(c);
    }
}
