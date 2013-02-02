/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Christopher Williams
 */
//Not working at all
public class Frame {

    Controls con = new Controls();
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

    public opencv_core.IplImage listener() {
        con.cap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    grabber.start();
                    opencv_core.IplImage img = grabber.grab();
                    if (img != null) {
                        cvSaveImage(img);
                        System.out.println("Frame Captured...");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void cvSaveImage(IplImage img) {
                throw new UnsupportedOperationException("Not yet implemented");
            }
        });
        return null;
    }
}
