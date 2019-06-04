package tribe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author conor
 */
public class Nation {
    private ArrayList<Member> members;
    private int popNation;
    private double nationAvgStr;
    
    Nation() {
        this.members = new ArrayList();
        this.popNation = members.size();
        this.nationAvgStr = -1;
    }
    
    Nation(int pop, int year) {
        this.members = new ArrayList();
        this.popNation = members.size();
        this.nationAvgStr = -1;
    }
    
    Nation(ArrayList<Member> m) {
        this.members = m;
        this.popNation = members.size();
        this.nationAvgStr = -1;
    }
    
    // adds member a given (x, y) and birth year 
    // unsafe method! does not check if square is a valid place for member to exist
    public void addMember(int x, int y, int yearBorn) {
        Member newMember = new Member(x, y, yearBorn);
        members.add(newMember);
        setNationPopLarger();
    }
    
    // moves every member of tribe to valid location
    // inefficient method! copies an entire map and barely uses it
    public void randMoveAllMember(Map land) {
        for(Member m : members) {
            
            // members original cords
            Coordinate mCord = new Coordinate(m.getCords().getX(), m.getCords().getY());
            
            // collider
            Tile collider = new Tile(m, land.getAcre(mCord.getX(), mCord.getY()));
            
            // finds part of collidee
            Acre aMove = land.getAcre(mCord.getX(), mCord.getY());
            Member mMove = new Member(mCord.getX(), mCord.getY(), m.getBorn());
            Random r = new Random();
            int rr = r.nextInt(5);
            switch (rr) {
                case 0:
                    mMove.moveNorth();
                    aMove = land.getAcre(mCord.getX(), mCord.getY() - 1);
                    break;
                case 1:
                    mMove.moveEast();
                    aMove = land.getAcre(mCord.getX() + 1, mCord.getY());
                    break;
                case 2:
                    mMove.moveSouth();
                    aMove = land.getAcre(mCord.getX(), mCord.getY() + 1);
                    break;
                case 3:
                    mMove.moveWest();
                    aMove = land.getAcre(mCord.getX() - 1, mCord.getY());
                    break;
                case 4:
                    break;
            }
            
            // collidee
            Member testMove = findMember(mMove.getCords(), members);
            Tile collidee = new Tile(testMove, aMove);
            
            // moves member if movement is valid
            TileCollisionManager canCollide = new TileCollisionManager(collider, collidee);
            boolean validMove = canCollide.memberToTileCollide();
            if(validMove) {
                land.getAcre(mCord.getX(), mCord.getY()).setFood(nationAvgStr);
                m.setCords(mMove.getCords().getX(), mMove.getCords().getY());
            }
        }
    }
    
    // attempts to find member given cords
    public static Member findMember(Coordinate c, ArrayList<Member> n) {
        for(Member m : n) {
            if(m.getCords().getX() == c.getX() && m.getCords().getY() == c.getY()) {
                return m;
            }
        }
        return null;
    }
    
    // attempts to delete member given cords
    // untested!
    public void deleteMember(Coordinate c) {
        for(Member m : members) {
            if(m.getCords().getX() == c.getX() && m.getCords().getY() == c.getY()) {
                members.remove(m);
                break;
            }
        }
    }
    
    // SETTERS && GETTERS
    // sets member list
    public void setMemberList(ArrayList<Member> n) {
        this.members = n;
    }
    
    // returns member list
    public ArrayList<Member> getMemberList() {
        return members;
    }
    
    // popNation++ && popNation--
    private void setNationPopLarger() {
        popNation++;
    }
    private void setNationPopSmaller() {
        popNation--;
    }
    
    // returns popNation
    public int getNationPop() {
        popNation = members.size();
        return popNation;
    }
}
