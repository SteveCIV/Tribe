package tribe;

import java.io.Serializable;

/**
 *
 * @author conor
 */
public class Member implements Serializable {
    private Coordinate cord;
    private final Nation parent;
    private double satiation;
    private final int born;
    
    Member(Nation parent) {
        this.cord = new Coordinate(-1, -1);
        this.parent = parent;
        this.satiation = 0.0;
        this.born = -1;
    }
    
    Member(Coordinate c, Nation parent) {
        this.cord = new Coordinate(c.getX(), c.getY());
        this.parent = parent;
        this.satiation = 0.0;
        this.born = -1;
    }
    
    Member(Coordinate c, int yearBorn, Nation parent) {
        this.cord = new Coordinate(c.getX(), c.getY());
        this.parent = parent;
        this.satiation = 0.0;
        this.born = yearBorn;
    }
    
    public void memberDeath() {
        parent.memberKilled(cord);
    }

    // moves member in a cardinal direction
    public void moveNorth() {
        cord.setY(cord.getY() - 1);
    }
    public void moveEast() {
        cord.setX(cord.getX() + 1);
    }
    public void moveSouth() {
        cord.setY(cord.getY() + 1);
    }
    public void moveWest() {
        cord.setX(cord.getX() - 1);
    }
    
    public void changeSatiation(double satiation) {
        this.satiation += satiation;
    }
    
    // SETTERS && GETTERS
    // sets location from (x, y)
    public void setCords(int x, int y) {
        this.cord = new Coordinate(x, y);
    }
    
    // sets location from Coordinate
    public void setCords(Coordinate c) {
        this.cord = c;
    }
    
    // returns cord
    public Coordinate getCords() {
        return cord;
    }
    
    // gets parent
    public Nation getParent() {
        return parent;
    }

    // sets satiation
    private void setSatiation(double sat) {
        this.satiation = sat;
    }

    // returns satiation
    public double getSatiation() {
        return satiation;
    }

    // returns age
    public int getAge(int worldAge) {
        int age = worldAge - born;
        return age;
    }
    
    // returns born
    public int getBorn() {
        return born;
    }
}
