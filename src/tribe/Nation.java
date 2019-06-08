package tribe;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author conor
 */
public class Nation {
    private ArrayList<Member> members;
    private ArrayList<Coordinate> occTiles;
    Random r = new Random();
    public final Color NCOLOR = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    
    Nation() {
        this.members = new ArrayList();
        this.occTiles = new ArrayList();
    }
    
    Nation(int pop, int year) {
        this.members = new ArrayList();
        this.occTiles = new ArrayList();
    }
    
    Nation(ArrayList<Member> m) {
        this.members = m;
        this.occTiles = new ArrayList();
    }
    
    // moves every member of tribe to valid location
    // inefficient method! copies an entire map and barely uses it
    public void randMoveAllMember(Map land) {
        for(Member m : members) {
            
            // collider member cords
            Coordinate mCord = new Coordinate(m.getCords().getX(), m.getCords().getY());
            
            // collider acre
            Acre a = land.getAcre(mCord.getX(), mCord.getY());
            
            // collider
            Tile collider = new Tile(m, a);
            
            // finds part of collidee
            Acre aMove = land.getAcre(mCord.getX(), mCord.getY());
            Member mMove = new Member(mCord, m.getBorn());
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
            Member testMove = null;
            for(Coordinate c : occTiles) {
                if(mMove.getCords().getX() == c.getX() && mMove.getCords().getY() == c.getY()) {
                    //testMove = GameWorld.findMember(c, civ);
                }
            }
            Tile collidee = new Tile(testMove, aMove);
            
            // moves member if movement is valid
            TileCollisionManager canCollide = new TileCollisionManager(collider, collidee);
            boolean validMove = canCollide.memberToTileCollide();
            if(validMove) {
                a.changeFood(-0.1);
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

    // adds member a given (x, y) and birth year 
    // unsafe method! does not check if square is a valid place for member to exist
    public void addMember(Coordinate c, int yearBorn) {
        Member newMember = new Member(c, yearBorn);
        addTile(c);
        members.add(newMember);
    }
    
    // returns popNation
    public int getNationPop() {
        return members.size();
    }
    
    public ArrayList<Coordinate> getOccTiles() {
        return occTiles;
    }
    
    public void addTile(Coordinate c) {
        occTiles.add(c);
    }
}
