package tribe;

import java.util.Random;

/**
 *
 * @author conor
 */
public class Coordinate {
    private int x, y;
    private static final Random r = new Random();
    
    Coordinate() {
        this.x = 0;
        this.y = 0;
    }
    
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public static Coordinate randomCoordinate(int x, int y) {
        return new Coordinate((int)(Math.random() * x), (int)(Math.random() * y));
    }
    
    public static Coordinate randomCoordinateRange(int xMin, int yMin, int xMax, int yMax) {
        if(xMin >= xMax || yMin >= yMax) {
            System.out.println("Max must be greater than Min");
        }
        return new Coordinate(r.nextInt(xMax - xMin) + xMin, r.nextInt(yMax - yMin) + yMin);
    }
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
