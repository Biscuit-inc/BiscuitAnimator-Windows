package a;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;

public class Camera extends JPanel implements Runnable {

    private JSplitPane split;
    private JScrollPane menu;
    private JScrollPane image;
    Frame frame = new Frame();
    public static final CanvasFrame canvas = new CanvasFrame("");
    IplImage imgcheck = frame.frame();

    public Camera() {
        setLayout(new BorderLayout());
        creaPanelSup();
        creaPanelInf();
        split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        add(split, BorderLayout.CENTER);
        split.setRightComponent(menu);
        split.setLeftComponent(image);
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        error_Check();
        while (true) {
            try {
                System.setProperty("sun.java2d.opengl", "True");
                IplImage img = frame.frame();
                canvas.showImage(img);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void stateChanged(ChangeEvent evt) {
        if (evt.getSource() == menu.getViewport()) {
            Point point = menu.getViewport().getViewPosition();
            Dimension dimmenu = menu.getViewport().getViewSize();
            Dimension dimimage = image.getViewport().getViewSize();
            float escaleX = 1;
            float escaleY = 1;
            if(dimmenu.width<dimimage.width) {

            }
        }
    }

    private void error_Check() {
        if (imgcheck == null) {
            NoCam_Error noCam_Error = new NoCam_Error();
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}