package tribe;

import java.util.Random;

/**
 *
 * @author conor
 */
public class Map {
    private Acre[][] map = new Acre[Tribe.WIDTH][Tribe.HEIGHT];
    private double regrowRate;
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
                        if(adjacentFood(i, j)) {
                            if(r.nextDouble() <= regrowRate) {
                                map[i][j].changeFood(regrowValue);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private boolean adjacentFood(int x, int y) {
        if(map[x][y - 1].getFood() > 0) {
            return true;
        } else if(map[x + 1][y].getFood() > 0) {
            return true;
        } else if(map[x][y + 1].getFood() > 0) {
            return true;
        } else if(map[x - 1][y].getFood() > 0) {
            return true;
        } else {
            return false;
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
        Acre acre = new Acre();
        try {
            acre = map[x][y];
        } catch(Exception e) {}
        return acre;
    }
    
    // returns Acre at given Cord
    public Acre getAcre(Coordinate c) {
        Acre acre = new Acre();
        try {
        acre = map[c.getX()][c.getY()];
        } catch(Exception e) {}
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
