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
    private ArrayList<Civilization> civ;
    private ArrayList<Coordinate> occTiles;
    private int worldAge;
    
    public GameWorld() throws FileNotFoundException {
        land = new Map();
        civ = new ArrayList();
        occTiles = new ArrayList();
        worldAge = 0;
    }
    
    // moves civilizatoin, setWorldOlder
    public void generateNewYear() {
        for(Civilization c : civ) {
            c.randMoveAllNation(land);
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
        this.civ.add(civ);
    }
    
    // returns civilization
    public ArrayList<Civilization> getCivList() {
        return civ;
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

                // find member, test if occupied by any member in any civ
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
        civ.add(c);
    }
    
    public void addTile(Coordinate c) {
        occTiles.add(c);
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
