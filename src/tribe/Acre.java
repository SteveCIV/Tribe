package tribe;

/**
 *
 * @author conor
 */
public class Acre {
    private Coordinate cord;
    private double food;
    private boolean passable;
    
    public Acre() {
        cord = new Coordinate(0, 0);
        food = 0.0;
        passable = false;
    }
    
    public Acre(int x, int y) {
        cord = new Coordinate(x, y);
        this.food = 0.0;
        this.passable = false;
    }
    
    public Acre(int x, int y, boolean passable) {
        cord = new Coordinate(x, y);
        this.food = 0.0;
        this.passable = passable;
    }
    
    // returns food
    public double getFood() {
        return food;
    }
    
    // sets new food value
    public void setFood(double food) {
        this.food = food;
    }
    
    // returns passable
    public boolean getPassable() {
        return passable;
    }
    
    // sets new passable value
    public void setPassable(boolean passable) {
        this.passable = passable;
    }
    
    // sets location of Acre on map
    public void setCords(int x, int y) {
        cord = new Coordinate(x, y);
    }
    
    // returns location of Acre on map
    public Coordinate getCords() {
        return cord;
    }
}