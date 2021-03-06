package tribe;

import java.io.Serializable;

/**
 *
 * @author conor
 */
public class Acre implements Serializable {
    private Coordinate cord;
    private double food;
    private boolean passable;
    
    public Acre() {
        this.cord = new Coordinate(-1, -1);
        this.food = 0.0;
        this.passable = false;
    }
    
    public Acre(int x, int y) {
        this.cord = new Coordinate(x, y);
        this.food = 0.0;
        this.passable = false;
    }
    
    public Acre(int x, int y, boolean passable) {
        this.cord = new Coordinate(x, y);
        this.food = 0.0;
        this.passable = passable;
    }
    
    public void changeFood(double c) {
        this.food = food + c;
    }
    
    // GETTERS && SETTERS
    // returns food
    public double getFood() {
        return food;
    }
    
    // sets food
    public void setFood(double food) {
        this.food = food;
    }
    
    // returns passable
    public boolean getPassable() {
        return passable;
    }
    
    // sets passable
    public void setPassable(boolean passable) {
        this.passable = passable;
    }
    
    // sets cords
    public void setCords(int x, int y) {
        cord = new Coordinate(x, y);
    }
    
    // returns cords
    public Coordinate getCords() {
        return cord;
    }
}
