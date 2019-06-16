package tribe;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javafx.stage.Stage;

/**
 *
 * @author conor
 */
public class SaveGameWorld {
    public static void SaveGameWorld(String fileName, GameWorld gameWorld, Stage window) {
        GameWorld gw = gameWorld;
        String fn = fileName;
        try { 
            FileOutputStream saveFile = new FileOutputStream("C:\\Users\\conor\\Documents\\NetBeansProjects\\CS141\\Tribe\\src\\savedGames\\" + fn);
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(gw);
            save.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.close();
    }
}
