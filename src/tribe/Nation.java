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
    private final Civilization parent;
    private static final Random r = new Random();
    public final Color NCOLOR = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    
    Nation(Civilization parent) {
        this.members = new ArrayList();
        this.parent = parent;
    }
    
    Nation(int pop, int year, Civilization parent) {
        this.members = new ArrayList();
        this.parent = parent;
    }
    
    Nation(ArrayList<Member> m, Civilization parent) {
        this.members = m;
        this.parent = parent;
    }
    
    // returns a center point that all initial nation members spawn around
    // inefficient method! copies an entire map and barely uses it
    public static Coordinate findValidCenter(Map land, int spawnRadius) {
        boolean validCenter = false;
        Coordinate center = new Coordinate();
        while(!validCenter) {
            center = Coordinate.randomCoordinate(Tribe.WIDTH, Tribe.HEIGHT);
            int totalTiles = 0;
            int passableTiles = 0;

            // if not enough surrounding land is passable reroll center
            try {
                for(int x = center.getX() - spawnRadius; x < center.getX() + spawnRadius; x++) {
                    for(int y = center.getY() - spawnRadius; y < center.getY() + spawnRadius; y++) {
                        if(land.getAcre(x, y).getPassable()) {
                            passableTiles++;
                        }
                        totalTiles++;
                    }
                }
            } catch(Exception e) {}
            System.out.println("Passable: " + passableTiles + " Total Tiles: " + totalTiles);
            if(passableTiles > totalTiles * 0.75) {
                validCenter = true;
            }
        }
        return center;
    }
    
    // moves every member of tribe to valid location
    // inefficient method! copies an entire map and barely uses it
    public void randMoveAllMember(Map land) {
        ArrayList<Member> members = (ArrayList<Member>)this.members.clone();
        for(Member m : members) {
            
            // collider acre
            Coordinate mCord = new Coordinate(m.getCords().getX(), m.getCords().getY());
            Acre a = land.getAcre(mCord.getX(), mCord.getY());
            
            // collider
            Tile collider = new Tile(m, a);
            
            // finds part of collidee
            Acre aMove = land.getAcre(mCord.getX(), mCord.getY());
            Member mMove = new Member(mCord, m.getBorn(), this);
            switch (r.nextInt(5)) {
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
            Member testMove = findMember(aMove.getCords());
            Tile collidee = new Tile(testMove, aMove);
            
            // moves member if movement is valid
            TileCollisionManager collision = new TileCollisionManager(collider, collidee);
            collision.memberToTileCollide();
            m = collision.getCollider().getMember();
        }
    }
    
    // attempts to find member given cords
    public Member findMember(Coordinate c) {
        for(Member m : members) {
            if(m.getCords().getX() == c.getX() && m.getCords().getY() == c.getY()) {
                return m;
            }
        }
        return parent.findMember(c);
    }
    
    // adds member a given (x, y) and birth year 
    // unsafe method! does not check if square is a valid place for member to exist
    public void memberBorn(Coordinate c, int yearBorn) {
        Member newMember = new Member(c, yearBorn, this);
        members.add(newMember);
    }
    
    // attempts to delete member given cords
    public void memberKilled(Coordinate c) {
        for(Member m : members) {
            if(m.getCords().getX() == c.getX() && m.getCords().getY() == c.getY()) {
                members.remove(m);
                if(members.isEmpty()) {
                    parent.removeNation(this);
                }
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
    
    // gets parent
    public Civilization getParent() {
        return parent;
    }
    
    // returns popNation
    public int getNationPop() {
        return members.size();
    }
    
    public static String toStringMemberList(ArrayList<Member> mList) {
        String output = "";
        for(Member m : mList) {
            output += "(" + m.getCords().getX() + ", " + m.getCords().getY() + ")";
        }
        return output;
    }
}
