package tribe;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 *
 * @author conor
 */
public class MapGenerator {
    private char[][] charMap = new char[300][164];
    
    public MapGenerator() {
        imageToMap();
        charMap = getCharMap();
    }
    
    public void imageToMap() {
        try {
            // finds image
            File file = new File("C:/Users/conor/Documents/NetBeansProjects/CS141/Tribe/src/imageMap/Earth300x164.png");
            BufferedImage image = ImageIO.read(file);


            // loops every pixel in image and prints to txt file
            for(int i = 0; i < image.getHeight(); i++) {
                String row = "";
                for (int j = 0; j < image.getWidth(); j++) {
                    char c;
                    int clr = image.getRGB(j, i);
                    int r = (clr & 0x00ff0000) >> 16;
                    int g = (clr & 0x0000ff00) >> 8;
                    int b = clr & 0x000000ff;

                    // tests if pixel is black or white or other
                    if (r == 255 && g == 255 && b == 255) {
                        c = ' ';
                        row += ' ';
                    } else if (r == 0 && g == 0 && b == 0) {
                        c = 'w';
                        row += 'w';
                    } else {
                        c = '?';
                        row += '?';
                    }
                    setChar(c, j, i);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found e throw");
        } catch(IOException e) {
            System.out.println("IO e thrown");
        }
    }
    
    // GETTRS && SETTERS
    // sets char at (x, y)
    public void setChar(char c, int x, int y) {
        charMap[x][y] = c;
    }
    
    // returns char at (x, y)
    public char getChar(int x, int y) {
        return charMap[x][y];
    }
    
    // sets charMap
    public void setCharMap(char[][] charMap) {
        this.charMap = charMap;
    }
    
    // returns charMap
    public char[][] getCharMap() {
        return charMap;
    }
}
