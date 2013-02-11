package a;

import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Christopher Williams
 */
public class Controls {

    //Variables
    JSlider exp, red, green, blue;
    JLabel lexp, lred, lgreen, lblue;
    Timer timer;
    Frame frame = new Frame();
    JPanel window = new JPanel();
    JLabel renderlabel;
    JButton cap;
    Rectangle rcap;
    private int width = 1380;
    private int height = 800;
    public static final JFrame f = new JFrame();
    Toolbar toolbar = new Toolbar();
    protected static int framename = 0;

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
      //  f.setUndecorated(true);
        f.setResizable(true);
        f.setVisible(true);
        window.setLayout(null);

        sliderMethod();
        labels();
        eventAdder();
        frameRender();
        drawButtons();
        f.repaint();
    }

    //Creates Labels for levels
    private void labels() {

        //Red Label
        lred = new JLabel("Red: 0");
        lred.setBounds(10, 100, 90, 10);
        window.add(lred);

        //Green Label
        lgreen = new JLabel("Green: 0");
        lgreen.setBounds(10, 170, 90, 10);
        window.add(lgreen);

        //Blue Label
        lblue = new JLabel("Blue: 0");
        lblue.setBounds(10, 240, 90, 10);
        window.add(lblue);

        //Exposure Label
        lexp = new JLabel("Exposure: 0");
        lexp.setBounds(10, 320, 90, 10);
        window.add(lexp);
    }

    //Creates Levels sliders
    private void sliderMethod() {

        //Red
        red = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        red.setBounds(10, 50, 255, 50);
        red.setMajorTickSpacing(10);
        red.setMinorTickSpacing(5);
        red.setPaintTicks(true);
        red.setToolTipText("Adjust the red levels of the image");
        window.add(red);

        //green
        green = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        green.setBounds(10, 120, 255, 50);
        green.setMajorTickSpacing(10);
        green.setMinorTickSpacing(5);
        green.setPaintTicks(true);
        green.setToolTipText("Adjust the green levels of the image");
        window.add(green);

        //Blue
        blue = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        blue.setBounds(10, 190, 255, 50);
        blue.setMajorTickSpacing(10);
        blue.setMinorTickSpacing(5);
        blue.setPaintTicks(true);
        blue.setToolTipText("Adjust the blue levels of the image");
        window.add(blue);

        //Exposure
        exp = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        exp.setBounds(10, 260, 255, 50);
        exp.setMajorTickSpacing(10);
        exp.setMinorTickSpacing(5);
        exp.setPaintTicks(true);
        exp.setToolTipText("Adjust the exposure of the image");
        window.add(exp);
    }

    //Attaches events to the sliders
    private void eventAdder() {
        event e = new event();
        exp.addChangeListener(e);
        red.addChangeListener(e);
        green.addChangeListener(e);
        blue.addChangeListener(e);
    }

    //Updates levels Labels
    public class event implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
            int exposure = exp.getValue();
            int redlevel = red.getValue();
            int bluelevel = blue.getValue();
            int greenlevel = green.getValue();

            lgreen.setText("Green: " + greenlevel);
            lblue.setText("Blue: " + bluelevel);
            lred.setText("Red: " + redlevel);
            lexp.setText("Exposure: " + exposure);
        }
    }

    //renders buffered image from frame
    private void frameRender() {
//        ActionListener actionListener = new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
        ImageIcon render = new ImageIcon(frame.frame().getBufferedImage());
        renderlabel = new JLabel(render);
        renderlabel.setBounds((width / 2) - 320, 50, 640, 480);
        renderlabel.setBorder(BorderFactory.createLineBorder(Color.lightGray,3));
        renderlabel.setToolTipText("Live camera feed");
        window.add(renderlabel);
        renderlabel.revalidate();
        renderlabel.repaint();
//            }
//        };
//        timer = new Timer(100, actionListener);
//        timer.setInitialDelay(1000);
//        timer.start();
    }

    //Draws the buttons and adds functions to them
    private void drawButtons() {
        cap = new JButton("Capture");
        rcap = new Rectangle((width / 2) - 50, 540, 80, 25);
        cap.setBounds(rcap);
        cap.setToolTipText("Capture Frame");
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
        Save_as save = new Save_as();
        //Camera camera = new Camera();
    }
}