package a;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Camera implements Runnable {

    public Camera() {
        CanvasFrame canvas = new CanvasFrame("Cam");
    canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    Frame frame = new Frame();
    while(true){
        try {
            IplImage img = frame.Frame();
            canvas.showImage(img);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}