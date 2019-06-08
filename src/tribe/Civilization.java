package tribe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author conor
 */
public class Civilization {
    private ArrayList<Nation> nations;
    private int popNation;
    private int popCiv;
    
    public Civilization() {
        this.nations = new ArrayList();
        this.popNation = nations.size();
        this.popCiv = 0;
    }
    
    public Civilization(int popNation, int popMember) {
        this.nations = new ArrayList();
        this.popNation = nations.size();
        this.popCiv = 0;
    }
    
    public void addNation(Nation newNation) {
        nations.add(newNation);
        setPopNationLarger();
    }
    
    // moves all nations
    public void randMoveAllNation(Map land) {
        for(Nation n : nations) {
            n.randMoveAllMember(land);
        }
    }
    
    // creates a new nation of given size and adds to nation list
//    public void addNation(int pop, int year) {
//        Nation n = new Nation();
//        for (int i = 0; i < pop; i++) {
//            Random r1 = new Random();
//            int rX = r1.nextInt(Tribe.WIDTH);
//            Random r2 = new Random();
//            int rY = r2.nextInt(Tribe.HEIGHT);
//            Coordinate rC = new Coordinate(rX, rY);
//
//            // create collider
//            Member m = new Member(rC, year);
//            Tile collider = new Tile(m);
//
//            // find tile
//            Acre aMove = land.getAcre(rX, rY);
//
//            // find member, test if occupied 
//            Member mMove = new Member(rC);
//            Member testMove = Nation.findMember(mMove.getCords(), n.getMemberList());
//
//            // create collidee
//            Tile collidee = new Tile(testMove, aMove);
//            TileCollisionManager canCollide = new TileCollisionManager(collider, collidee);
//            if (!canCollide.memberToTileCollide()) {
//                i--;
//            } else {
//                n.addMember(rC, year);
//            }
//        }
//        civ.addNation(n);
//    }
    
    public static Member findMember(Coordinate c, ArrayList<Nation> nations) {
        for(Nation n : nations) {
            if(n.findMember(c, n.getMemberList()) != null) {
                return n.findMember(c, n.getMemberList());
            }
        }
        return null;
    }
    
    // SETTERS && GETTERS
    // set nation list
    public void setNationList(ArrayList<Nation> nations) {
        this.nations = nations;
    }
    
    // returns nation list
    public ArrayList<Nation> getNationList() {
        return nations;
    }
    
    // popNation++ && popNation--
    private void setPopNationLarger() {
        popNation++;
    }
    private void setPopNationSmaller() {
        popNation--;
    }
    
    // returns popNation
    public int getPopNation() {
        popNation = nations.size();
        return popNation;
    }
    
    // returns popWorld
    public int getPopCiv() {
        int tempPop = 0;
        for(Nation n : nations) {
            tempPop += n.getNationPop();
        }
        popCiv = tempPop;
        return popCiv;
    }
}
