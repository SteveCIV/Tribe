package tribe;

/**
 *
 * @author conor
 */
public class Member {
    private String name;
    private Coordinate cord;
    private double stregth;
    private final int born;
    
    Member() {
        this.cord = new Coordinate(-1, -1);
        this.stregth = 0.0;
        this.born = -1;
    }
    
    Member(int x, int y) {
        this.cord = new Coordinate(x, y);
        this.stregth = 0.0;
        this.born = -1;
    }
    
    Member(int x, int y, int yearBorn) {
        this.cord = new Coordinate(x, y);
        stregth = randomStregth();
        this.born = yearBorn;
    }
    
    public void move(int direction) {
        switch(direction) {
            case 0:
                moveNorth();
                break;
            case 1:
                moveEast();
                break;
            case 2:
                moveSouth();
                break;
            case 3:
                moveWest();
                break;
            case 4:
                break;
        }
    }

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
    
    // sets stregth as random 0.0 to 1.0
    public double randomStregth() {
        return Math.random();
    }
    // sets stregth
    public void setStregth(double str) {
        this.stregth = str;
    }
    
    // return stregth
    public double getStregth() {
        return stregth;
    }
    
    // return age
    public int getAge() {
        return born;
    }
    
    // sets location in space
    public void setCords(int x, int y) {
        this.cord = new Coordinate(x, y);
    }
    
    // returns location in space
    public Coordinate getCords() {
        return cord;
    }
}
