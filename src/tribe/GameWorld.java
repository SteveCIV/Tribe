package tribe;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    
    public void generateNewYear() {
        civ.randMoveAllNation(land);
        setWorldOlder();
    }
    
    // sets land as given txt file
    public void setLand(Map land) throws FileNotFoundException {
        land = new Map();
    }
    
    // returns land
    public Map getLand() {
        return land;
    }
    
    // sets new civilization
    public void setCiv(Civilization civ) {
        this.civ = civ;
    }
    
    // returns civilization
    public Civilization getCiv() {
        return civ;
    }
    
    // creates a new nation of given size
    public void setNewNation(int pop, int year) {
        Nation n = new Nation();
        //ArrayList<Member> n;
        for(int i = 0; i < pop; i++) {
            Random r1 = new Random();
            int rX = r1.nextInt(300);
            Random r2 = new Random();
            int rY = r2.nextInt(164);
            
            Tile tempTile = land.getTile(rX, rY);
            if(!tempTile.getPassable()) {
                i--;
            } else {
                n.addMember(rX, rY, year);
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
