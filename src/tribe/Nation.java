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
    public void randMoveAllMember(Map land) {
        for(Member m : members) {
            Random r = new Random();
            int rr = r.nextInt(5);
            
            Tile tempTile = land.getTile(m.getCords().getX(), m.getCords().getY());
            switch(rr) {
                case 0:
                    tempTile = land.getTile(m.getCords().getX(), m.getCords().getY() - 1);
                    break;
                case 1:
                    tempTile = land.getTile(m.getCords().getX() + 1, m.getCords().getY());
                    break;
                case 2:
                    tempTile = land.getTile(m.getCords().getX(), m.getCords().getY() + 1);
                    break;
                case 3:
                    tempTile = land.getTile(m.getCords().getX() - 1, m.getCords().getY());
                    break;
                case 4:
                    break;
            }
            
            if(tempTile.getPassable() /*&& no other members are in this spot*/) {
                m.move(rr);
            } else {
                // reroll directoin of travel
            }
        }
    }
    
    // SETTERS && GETTERS
    // sets nation
    public void setMemberList(ArrayList<Member> n) {
        this.members = n;
    }
    
    // returns memberlist
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
