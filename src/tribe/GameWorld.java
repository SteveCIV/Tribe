package tribe;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 *
 * @author conor
 */
public class GameWorld {
    private final Map land;
    private Civilization civ;
    private int worldAge;
    
    public GameWorld() throws FileNotFoundException {
        land = new Map();
        civ = new Civilization();
        worldAge = 0;
    }
    
    // moves civilizatoin, setWorldOlder
    public void generateNewYear() {
        civ.randMoveAllNation(land);
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
        this.civ = civ;
    }
    
    // returns civilization
    public Civilization getCiv() {
        return civ;
    }
    
    // creates a new nation of given size and adds to nation list
    // bug! doesn't check for overlapping membres 
    public void setNewNation(int pop, int year) {
        Nation n = new Nation();
        for(int i = 0; i < pop; i++) {
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
            
            // find member, test if occupied 
            Member mMove = new Member(rC);
            Member testMove = Nation.findMember(mMove.getCords(), n.getMemberList());
            
            // create collidee
            Tile collidee = new Tile(testMove, aMove);
            TileCollisionManager canCollide = new TileCollisionManager(collider, collidee);
            if(!canCollide.memberToTileCollide()) {
                i--;
            } else {
                n.addMember(rC, year);
            }
        }
        civ.addNation(n);
    }
    
    // worldAge++
    public void setWorldOlder() {
        worldAge++;
    }
    
    // returns worldAge
    public int getWorldAge() {
        return worldAge;
    }
}
