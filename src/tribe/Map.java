package tribe;

import java.util.Random;

/**
 *
 * @author conor
 */
public class Map {
    private Acre[][] map = new Acre[Tribe.WIDTH][Tribe.HEIGHT];
    private double regrowRate; // TODO: will be limited from 0.0 to 1.0
    private double regrowValue;
    private static final Random r = new Random();
    
    public Map() {
        fillMap();
    }
    
    public Map(double regrowR, double regrowV) {
        fillMap();
        this.regrowRate = regrowR;
        this.regrowValue = regrowV;
    }
    
    // fills map from char[][]
    public void fillMap() {
        MapGenerator mapG = new MapGenerator();
        char[][] charMap;
        charMap = mapG.getCharMap();
        
        for(int i = 0; i < charMap.length; i++) {
            for(int j = 0; j < charMap[0].length; j++) {
                
                // initialise new Acre and sets values
                Acre a = new Acre(i, j);
                switch(charMap[i][j]) {
                    case 'w':
                        a.setPassable(true);
                        a.setFood(1.0);
                        break;
                    case ' ':
                        a.setPassable(false);
                        a.setFood(0.0);
                }
                setAcre(a, i, j);
            }
        }
    }
    
    public void terrainRegrowth() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j].getPassable()) {
                    if(map[i][j].getFood() < 1.0) {
                        if(r.nextDouble() <= regrowRate) {
                            map[i][j].changeFood(regrowValue);
                        }
                    }
                }
            }
        }
    }
    
    // GETTERS && SETTERS
    // sets acre at given (x, y)
    public void setAcre(Acre a, int x, int y) {
        map[x][y] = a;
    }
    
    // sets acre at given Cord
    public void setAcre(Acre a, Coordinate c) {
        map[c.getX()][c.getY()] = a;
    }
    
    // return Acre at given (x, y)
    public Acre getAcre(int x, int y) {
        Acre acre = map[x][y];
        return acre;
    }
    
    // returns Acre at given Cord
    public Acre getAcre(Coordinate c) {
        Acre acre = map[c.getX()][c.getY()];
        return acre;
    }
    
    public void setRegrowRate(double rR) {
        this.regrowRate = rR;
    }
    
    public double getRegrowRate() {
        return regrowRate;
    }
    
    public void setRegrowValue(double rV) {
        this.regrowValue = rV;
    }
    
    public double getRegrowValue() {
        return regrowValue;
    }
}
