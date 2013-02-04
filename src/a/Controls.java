package a;

import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
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
    JButton cap;
    Rectangle rcap;
    private int width = 320;
    private int height = 280;
    public static final JFrame f = new JFrame();
    Toolbar toolbar = new Toolbar();
    private int framename = 0;

    //handles the JFrame and Main Content
    public Controls() {

        window.add(toolbar.toolBar, BorderLayout.NORTH);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.getContentPane().add(window);
        f.setTitle("Pre-Alpha-002-A - ");
        f.setSize(width, height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(true);
        f.setVisible(true);
        window.setLayout(null);

        drawButtons();
        f.repaint();
    }

    //Draws the buttons and adds functions to them
    private void drawButtons() {
        cap = new JButton("Capture");
        rcap = new Rectangle((width / 2) - 50, 80, 80, 25);
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
        Camera camera = new Camera();
    }
}