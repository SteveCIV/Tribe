package tribe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author conor
 */
public class GameWorld {
    private final Map land;
    private final ArrayList<Civilization> civs;
    private int worldAge;
    public int popGw;
    private static final Random r = new Random();
    
    public GameWorld() {
        land = new Map();
        civs = new ArrayList();
        worldAge = 0;
        popGw = 0;
    }
    
    // moves all members in gw and worldAge++
    public void generateNewYear() {
        ArrayList<Civilization> civs = (ArrayList<Civilization>)this.civs.clone();
        for(Civilization c : civs) {
            c.randMoveAllNation(land);
        }
        land.terrainRegrowth();
        setWorldOlder();
    }
    
    // creates a new civilization of given nation size and member size and adds to civilization list
    public void addCiv(int popNat, int popMem, int year) {
        // sets gw as parent of civilization
        Civilization c = new Civilization(this);

        // for given nation size
        for (int i = 0; i < popNat; i++) {

            // sets civilization as parent of nation
            Nation n = new Nation(c);

            // for given member size
            for (int j = 0; j < popMem; j++) {

                // gets random coordinate
                int rX = r.nextInt(Tribe.WIDTH);
                int rY = r.nextInt(Tribe.HEIGHT);
                Coordinate rC = new Coordinate(rX, rY);

                // create collider
                Member m = new Member(rC, year, n);
                Tile collider = new Tile(m);

                // find tile
                Acre aMove = land.getAcre(rX, rY);

                // find member, test if occupied by any member in any civs
                Member mMove = new Member(rC, n);
                Member testMove = findMember(mMove.getCords());

                // create collidee
                Tile collidee = new Tile(testMove, aMove);
                TileCollisionManager canCollide = new TileCollisionManager(collider, collidee);
                if(!canCollide.memberToTileCollide()) {
                    j--;
                } else {
                    n.memberBorn(rC, year);
                }
            }
            c.addNation(n);
        }
        civs.add(c);
    }
    
    // adds new civilization to civs
    public void addCiv(Civilization c) {
        civs.add(c);
    }
    
    // removes civ from civs
    public void removeCiv(Civilization c) {
        civs.remove(c);
        System.out.println();
    }
    
    public Member findMember(Coordinate cord) {
        for(Civilization c : civs) {
            for(Nation n : c.getNationList()) {
                for(Member m : n.getMemberList()) {
                    if(m.getCords().getX() == cord.getX() && m.getCords().getY() == cord.getY()) {
                        return m;
                    }
                }
            }
        }
        return null;
    }
    
    // GETTERS && SETTERS
    // sets land from given txt file
    public void setLand(Map land) {
        land = new Map();
    }
    
    // returns land
    public Map getLand() {
        return land;
    }
    
    // sets civilization
    public void setCivList(ArrayList<Civilization> civ) {
        while(civs != null) {
            civs.remove(0);
        }
        for(Civilization c : civ) {
            civs.add(c);
        }
    }
    
    // returns civilization
    public ArrayList<Civilization> getCivList() {
        return civs;
    }
    
    // worldAge++
    private void setWorldOlder() {
        worldAge++;
    }
    
    // returns worldAge
    public int getWorldAge() {
        return worldAge;
    }
    
    // returns popGw
    public int getPopGw() {
        for(Civilization c : civs) {
            popGw += c.getPopCiv();
        }
        return popGw;
    }
}
