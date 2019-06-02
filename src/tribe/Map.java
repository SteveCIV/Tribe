package tribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author conor
 */
public class Map {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 164;
    public Tile[][] map = new Tile[WIDTH][HEIGHT];
    
    
    public Map() throws FileNotFoundException {
        fillMap();
    }
    
    // fills map from premade text file
    public void fillMap() {
        try {
            File source = new File("C:/Users/conor/Documents/NetBeansProjects/CS141/Tribe/src/maps/earth300x164.txt");
            Scanner scr = new Scanner(source);
            
            int i = 0;
            while (scr.hasNextLine()) {
                String line = scr.nextLine();
                //System.out.println("next line " + line);
                for (int j = 0; j < line.length(); j++) {
                    
                    // idk why tf this works
                    boolean isPassable = true;
                    switch (line.charAt(j)) {
                        case 'W':
                            isPassable = true;
                        case 'f':
                            isPassable = false;
                    }
                    map[j][i] = new Tile(i, j, isPassable);
                }
                i++;
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    
    // return Tile at given (x, y)
    public Tile getTile(int x, int y) {
        Tile tempTile = map[x][y];
        return tempTile;
    }
}
