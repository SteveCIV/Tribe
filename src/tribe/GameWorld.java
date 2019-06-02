package tribe;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 *
 * @author conor
 */
public class GameWorld {
    private final Map land;
    private Nation nation; // will be an array list
    private int worldAge;
    
    public GameWorld() throws FileNotFoundException {
        land = new Map();
        nation = new Nation();
        worldAge = 0;
    }
    
    public void generateNewYear() {
        int pop = nation.getNationPop();
        nation.randMoveAll(land);
        setWorldOlder();
    }
    
    // sets land as given txt file
    public void setLand(Map land) throws FileNotFoundException {
        land = new Map();
    }
    
    // gives land
    public Map getLand() {
        return land;
    }
    
    // creates a new nation of given size
    public void setNewNation(int pop, int year) {
        for(int i = 0; i < pop; i++) {
            Random r1 = new Random();
            int rX = r1.nextInt(300);
            Random r2 = new Random();
            int rY = r2.nextInt(164);
            
            Tile tempTile = land.getTile(rX, rY);
            if(!tempTile.getPassable()) {
                i--;
            } else {
                nation.addMember(rX, rY, year);
            }
        }
    }
    
    // returns a nation
    public Nation getNation() {
        return nation;
    }
    
    // worldAge++
    public void setWorldOlder() {
        worldAge++;
    }
    
    //return worldAge
    public int getWorldAge() {
        return worldAge;
    }
}
