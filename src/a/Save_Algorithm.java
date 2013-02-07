/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Christopher Williams
 */
public class Save_Algorithm {

    Properties prop = new Properties();
    String path = Save_as.pathname + "project.animation";
    public static File imgdir = new File(Save_as.pathname + "\\images");
    File dir = new File(Save_as.pathname);

    public Save_Algorithm() throws IOException {
        dir.mkdir();
        imgdir.mkdir();
    }

    //Create Project File
    public void projectFile(String key, int value) {
        try {
            File file = new File(path);
            boolean exist = file.exists();
            if (!exist) {
                file.createNewFile();
            }
            OutputStream write = new FileOutputStream(path);
            prop.setProperty(key, Integer.toString(value));
            prop.storeToXML(write, "Project Specific Settings");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
        }
    }
}