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
    public Acre[][] map = new Acre[WIDTH][HEIGHT];
    
    
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
                    map[j][i] = new Acre(i, j, isPassable);
                }
                i++;
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    
    // return Acre at given (x, y)
    public Acre getAcre(int x, int y) {
        Acre acre = map[x][y];
        return acre;
    }
}
