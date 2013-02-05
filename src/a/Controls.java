package a;

import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Christopher Williams
 */
public class Controls {

    //Variables
    Frame frame = new Frame();
    JPanel window = new JPanel();
    JLabel renderlabel;
    JButton cap;
    Rectangle rcap;
    private int width = 1280;
    private int height = 720;
    public static final JFrame f = new JFrame();
    Toolbar toolbar = new Toolbar();
    private int framename = 0;

    //handles the JFrame and Main Content
    public Controls() {

        System.setProperty("sun.java2d.opengl", "True");
        window.add(toolbar.toolBar, BorderLayout.WEST);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.getContentPane().add(window);
        f.setTitle("Pre-Alpha-002-A");
        f.setSize(width, height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
        window.setLayout(null);

        frameRender();
        drawButtons();
        f.repaint();
    }

    private void imageUpdate() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // renderlabel.repaint();
        }
    }

    //renders buffered image from frame
    private void frameRender() {
            ImageIcon render = new ImageIcon(frame.frame().getBufferedImage());
            renderlabel = new JLabel(render);
            renderlabel.setBounds((width / 2) - 320, 50, 640, 480);
            window.add(renderlabel);
            //imageUpdate();
    }

    //Draws the buttons and adds functions to them
    private void drawButtons() {
        cap = new JButton("Capture");
        rcap = new Rectangle((width / 2) - 50, 540, 80, 25);
        cap.setBounds(rcap);
        window.add(cap);

        cap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    opencv_core.IplImage img = frame.frame();
                    if (img != null) {
                        cvSaveImage(Save_Algorithm.imgdir + "\\image_" + framename + ".jpg", img);
                        System.out.println("Frame Captured at... " + Save_as.pathname);
                        framename++;
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String args[]) {
        Controls controls = new Controls();
        //Camera camera = new Camera();
    }
}