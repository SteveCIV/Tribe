package tribe;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author conor
 */
public class GameWorld implements Serializable {
    private final Map land;
    private final ArrayList<Civilization> civs;
    private int worldAge;
    public int popGw;
    
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
        civs.add(c);

        // for given nation size
        for (int i = 0; i < popNat; i++) {

            // sets civilization as parent of nation
            Nation n = new Nation(c);
            c.addNation(n);
            
            int sR = Nation.spawnRadius(popMem);
            Coordinate center = findValidNationCenter(sR);
            
            // for given member size
            for (int j = 0; j < popMem; j++) {
                Coordinate rC = Coordinate.randomCoordinateRange(center.getX() - sR, center.getY() - sR, center.getX() + sR, center.getY() + sR);

                // create collider
                Member m = new Member(rC, year, n);
                Tile collider = new Tile(m);

                // find tile
                Acre aMove = land.getAcre(rC.getX(), rC.getY());

                // find member, test if occupied by any member in any civs
                Member mMove = findMember(m.getCords());

                // create collidee
                Tile collidee = new Tile(mMove, aMove);
                TileCollisionManager collision = new TileCollisionManager(collider, collidee);
                
                if(collision.memberToTileCollide()) {
                    n.memberBorn(rC, year);
                } else {
                    j--;
                }
            }
        }
    }
    
    // adds new civilization to civs
    public void addCiv(Civilization c) {
        civs.add(c);
    }
    
    // returns a center point that all initial nation members spawn around
    public Coordinate findValidNationCenter(int spawnRadius) {
        Coordinate center;
        while(true) {
            center = Coordinate.randomCoordinate(Tribe.WIDTH, Tribe.HEIGHT);
            if(!land.getAcre(center.getX(), center.getY()).getPassable()) {
                continue;
            }
            int totalTiles = 0;
            int passableTiles = 0;

            // if not enough surrounding land is passable reroll center
            try {
                for(int x = center.getX() - spawnRadius; x < center.getX() + spawnRadius; x++) {
                    for(int y = center.getY() - spawnRadius; y < center.getY() + spawnRadius; y++) {
                        if(land.getAcre(x, y).getPassable() && findMember(x, y) == null) {
                            passableTiles++;
                        }
                        totalTiles++;
                    }
                }
            } catch (Exception e) {}
            if(passableTiles > totalTiles * 0.75) {
                return center;
            }
        }
    }
    
    // removes civ from civs
    public void removeCiv(Civilization c) {
        civs.remove(c);
        System.out.println();
    }
    
    public void updateLand(double regrowRate, double regrowValue) {
        land.setRegrowRate(regrowRate);
        land.setRegrowValue(regrowValue);
    }
    
    public Member findMember(int x, int y) {
        Coordinate cord = new Coordinate(x, y);
        return findMember(cord);
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
    public int getPop() {
        for(Civilization c : civs) {
            popGw += c.getPopCiv();
        }
        return popGw;
    }
}
