/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Christopher Williams
 */
public class Save_Algorithm {

    public static File imgdir = new File(Save_as.pathname + "\\images");
    File dir = new File(Save_as.pathname);

    public Save_Algorithm() throws IOException {
        dir.mkdir();
        imgdir.mkdir();
    }
}