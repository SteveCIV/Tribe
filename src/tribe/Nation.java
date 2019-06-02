package tribe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author conor
 */
public class Nation {
    public ArrayList<Member> members;
    private int nationPop;
    public double nationAvgStr;
    
    Nation() {
        this.members = new ArrayList();
        this.nationPop = 0;
        this.nationAvgStr = -1;
    }
    
    Nation(int pop, int year) {
        this.members = new ArrayList();
        this.nationPop = 0;
        this.nationAvgStr = -1;
    }
    
    public int getNationPop() {
        return nationPop;
    }
    
    // adds member at given (x, y) and birth year 
    // unsafe method! does not check if square is a valid place for member to exist
    public void addMember(int x, int y, int yearBorn) {
        Member newMember = new Member(x, y, yearBorn);
        members.add(newMember);
        nationPop++;
    }
    
    // moves every member of tribe to valid location
    public void randMoveAll(Map land) {
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
            
            if(tempTile.getPassable()) {
                m.move(rr);
            } else {
                // reroll directoin of travel
            }
        }
    }
    
//    public void removeMember(int x, int y) {
//        for(Member m : members) {
//            m.
//        }
//    }
}
