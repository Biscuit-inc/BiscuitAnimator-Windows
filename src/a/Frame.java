/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;


/**
 *
 * @author Christopher Williams
 */
//Not working at all
public class Frame {

    final OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);

    public opencv_core.IplImage Frame() {
        try {
            grabber.start();
            opencv_core.IplImage img = grabber.grab();
            if (img != null) {
                return img;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
