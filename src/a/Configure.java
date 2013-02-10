/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Christopher Williams
 */
public class Configure {

    //Properitys
    Properties prop = new Properties();
    //String variable for the XML path
    String path = "settings/config.xml";
    //Controls con = new Controls();

    public void saveConfig(String key, int value) {
        try {
            File file = new File(path);
            boolean exist = file.exists();
            if (!exist) {
                file.createNewFile();
            }
            OutputStream write = new FileOutputStream(path);
            prop.setProperty(key, Integer.toString(value));
            prop.storeToXML(write, "Frames per Second");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
        }
    }

    //Loads the XML when you start each time
    public void loadConfig(String path) {
        try {
            InputStream read = new FileInputStream(path);
            prop.loadFromXML(read);
            //add stuff here
            read.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void setFPS(int FPS) {
        if (FPS == 15) {
            // con.fpsselection = 0;
        }

        if (FPS == 24) {
            //  con.fpsselection = 1;
        }

        if (FPS == 30) {
            //  con.fpsselection = 2;
        }
    }
}
