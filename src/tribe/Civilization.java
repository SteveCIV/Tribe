package tribe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author conor
 */
public class Civilization {
    private ArrayList<Nation> nations;
    private final GameWorld parent;
    private int popCiv;
    
    public Civilization(GameWorld gw) {
        this.nations = new ArrayList();
        this.parent = gw;
        this.popCiv = 0;
    }
    
    public Civilization(int popNation, int popMember, GameWorld parent) {
        this.nations = new ArrayList();
        this.parent = parent;
        this.popCiv = 0;
    }
    
    public Civilization(ArrayList<Nation> nats, GameWorld parent) {
        this.nations = nats;
        this.parent = parent;
        this.popCiv = 0;
    }
    
    // moves all nations
    public void randMoveAllNation(Map land) {
        ArrayList<Nation> nations = (ArrayList<Nation>)this.nations.clone();
        for(Nation n : nations) {
            n.randMoveAllMember(land);
        }
    }
    
    public Member findMember(Coordinate c) {
        for(Nation n : nations) {
            for(Member m : n.getMemberList()) {
                if(m.getCords().getX() == c.getX() && m.getCords().getY() == c.getY()) {
                    return m;
                }
            }
        }
        return parent.findMember(c);
    }
    
    // creates a new nation of given member size and adds to nation list
    // untested!
    public void addNation(int pop, int year, Map land) {
        Nation n = new Nation(this);
        for (int i = 0; i < pop; i++) {
            Coordinate rC = Coordinate.randomCoordinate(Tribe.WIDTH, Tribe.HEIGHT);

            // create collider
            Member m = new Member(rC, year, n);
            Tile collider = new Tile(m);

            // find tile
            Acre aMove = land.getAcre(rC.getX(), rC.getY());

            // find member, test if occupied 
            Member mMove = new Member(rC, n);
            Member testMove = findMember(mMove.getCords());

            // create collidee
            Tile collidee = new Tile(testMove, aMove);
            TileCollisionManager collision = new TileCollisionManager(collider, collidee);
            
            if(!collision.memberToTileCollide()) {
                i--;
            } else {
                n.memberBorn(rC, year);
            }
        }
        nations.add(n);
    }

    // adds new nation and adds to nations
    public void addNation(Nation newNation) {
        nations.add(newNation);
    }
    
    // removes given nation from nations
    public void removeNation(Nation n) {
        nations.remove(n);
        if(nations.isEmpty()) {
            parent.removeCiv(this);
        }
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
    
    // gets parent
    public GameWorld getParent() {
        return parent;
    }
    
    // returns popCiv
    public int getPopCiv() {
        for (Nation n : nations) {
            popCiv += n.getNationPop();
        }
        return popCiv;
    }
}
