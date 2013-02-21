package a;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import edsdk.utils.CanonCamera;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
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
    private TextArea scriptfield = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
    JSlider exp, red, green, blue;
    Timer timer;
    Frame frame = new Frame();
    JPanel window = new JPanel();
    JPanel script = new JPanel();
    JLabel renderlabel, lexp, lred, lgreen, lblue, timeline, scripteditor;
    JTabbedPane tabs = new JTabbedPane(JTabbedPane.RIGHT);
    Font font = new Font("Courier New", Font.PLAIN, 12);
    JButton cap;
    Rectangle rcap;
    private int width = 1440;
    private int height = 870;
    public static final JFrame f = new JFrame();
    Toolbar toolbar = new Toolbar();
    protected static int framename = 0;

    //handles the JFrame and Main Content
    public Controls() {

        tabs.add("Frame Grabber", window);
        tabs.add("Script Editor", script);
        f.add(tabs);

        System.setProperty("sun.java2d.opengl", "True");
        f.setJMenuBar(toolbar.toolBar);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.setTitle("Pre-Alpha-002-A");
        f.setSize(width, height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //camera.endLiveView();
                //camera.closeSession();
                CanonCamera.close();
                System.exit(0);
            }
        });
        f.setLocationRelativeTo(null);
        f.setResizable(true);
        f.setVisible(true);
        window.setLayout(null);

        sliderMethod();
        timeLine();
        labels();
        eventAdder();
        frameRender();
        drawButtons();
        scriptEditor();
        f.repaint();
    }

    private void sound() {
        //Shutter release
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("resources/sounds/350d-shutter.wav"));
            Clip shutter = AudioSystem.getClip();
            shutter.open(audio);
            shutter.start();
        } catch (UnsupportedAudioFileException uae) {
            System.out.println(uae);
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (LineUnavailableException lua) {
            System.out.println(lua);
        }
    }

    //Script Editor
    private void scriptEditor() {
        scriptfield.setEditable(true);
        scriptfield.setFont(font);
        scriptfield.setPreferredSize(new Dimension(800, 700));
        scriptfield.setCursor(null);
        script.add(scriptfield);
    }

    //TimeLine
    private void timeLine() {
        timeline = new JLabel();
        timeline.setBounds(0, 600, 1310, 200);
        timeline.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        timeline.setToolTipText("Movie timeline");
        timeline.setLayout(new GridLayout(3, 3));
        window.add(timeline);
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
        red.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        red.setBounds(10, 50, 255, 50);
        red.setMajorTickSpacing(10);
        red.setMinorTickSpacing(5);
        red.setPaintTicks(true);
        red.setToolTipText("Adjust the red levels of the image");
        window.add(red);

        //green
        green = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        green.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        green.setBounds(10, 120, 255, 50);
        green.setMajorTickSpacing(10);
        green.setMinorTickSpacing(5);
        green.setPaintTicks(true);
        green.setToolTipText("Adjust the green levels of the image");
        window.add(green);

        //Blue
        blue = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        blue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        blue.setBounds(10, 190, 255, 50);
        blue.setMajorTickSpacing(10);
        blue.setMinorTickSpacing(5);
        blue.setPaintTicks(true);
        blue.setToolTipText("Adjust the blue levels of the image");
        window.add(blue);

        //Exposure
        exp = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        exp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        ImageIcon render = new ImageIcon(frame.frame().getBufferedImage());
        renderlabel = new JLabel(render);
        renderlabel.setBounds((width / 2) - 320, 50, 640, 480);
        renderlabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        renderlabel.setToolTipText("Live camera feed");
        renderlabel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        window.add(renderlabel);
        renderlabel.revalidate();
        renderlabel.repaint();
    }

    //Draws the buttons and adds functions to them
    private void drawButtons() {
        //ImageIcon capimage = new ImageIcon("resources/images/capture.gif");
        cap = new JButton("Capture");
        cap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rcap = new Rectangle((width / 2) - 50, 540, 80, 25);
        cap.setBounds(rcap);
        cap.setToolTipText("Capture Frame");
        window.add(cap);

        cap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    opencv_core.IplImage img = frame.frame();
                    if (img != null) {
                        sound();
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
        // Camera camera = new Camera();
    }
}