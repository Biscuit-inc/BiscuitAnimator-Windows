package a;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static edsdk.a.EdSdkLibrary.kEdsPropID_BatteryLevel;
import edsdk.utils.CanonCamera;
import edsdk.utils.commands.ShootTask;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private JSlider exp, red, green, blue;
    long canonbatterylg = camera.getProperty(kEdsPropID_BatteryLevel);
    private Timer timer;
    private Frame frame = new Frame();
    private static JPanel window = new JPanel();
    private JPanel script = new JPanel();
    private static JLabel renderCanon, renderWebcam, lexp, lred, lgreen, lblue, timeline, canonBattery;
    private JTabbedPane tabs = new JTabbedPane(JTabbedPane.RIGHT);
    private Font font = new Font("Courier New", Font.PLAIN, 12);
    private JButton cap;
    private Rectangle rcap;
    private static int width = 1660;
    private int height = 1440;
    public static final JFrame f = new JFrame();
    private Toolbar toolbar = new Toolbar();
    protected static int framename = 0;
    //final File fil = new File(Save_Algorithm.imgdir);
    private static CanonCamera camera = new CanonCamera();
    static boolean canon = true;
    static boolean webcam = true;

    public static void main(String args[]) throws InterruptedException {
        camera.openSession();
        camera.beginLiveView();
        new Controls();
        new Save_as();
        renderCanon = new JLabel();

        //Renders image from canon DSLR
        while (canon = true) {
            try {
                Thread.sleep(50);
                BufferedImage image = camera.downloadLiveView();
                if (image != null) {
                    renderCanon.setIcon(new ImageIcon(image));
                    renderCanon.setBounds((width / 2) - 528, 10, 1056, 704);
                    renderCanon.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
                    renderCanon.setToolTipText("Live Canon DSLR feed");
                    renderCanon.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    window.add(renderCanon);
                    image.flush();
                }
//                else {
//                    camera.endLiveView();
//                    camera.closeSession();
//                    canon = false;
//                    break;
//                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Controls.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                camera.endLiveView();
                camera.closeSession();
                CanonCamera.close();
                try {
                    frame.grabber.stop();
                } catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(Controls.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        f.setLocationRelativeTo(null);
        f.setResizable(true);
        f.setVisible(true);
        window.setLayout(null);

        //Method init
        canonCameraInfo();
        sliderMethod();
        timeLine();
        labels();
        eventAdder();
        frameRender();
        drawButtons();
        scriptEditor();
        f.repaint();
    }

    private void canonCameraInfo() {
        canonBattery = new JLabel("Battery " + camera.getProperty(kEdsPropID_BatteryLevel));
        canonBattery.setBounds(1400, 10, 100, 10);
        canonBattery.setFont(font);
        window.add(canonBattery);
    }

    //NOT WORKING
    private void checkCapDevices() {

        if (camera.downloadLiveView() != null) {
            canon = true;
        }

        if (frame.frame() != null) {
            webcam = true;
        }
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
        timeline.setBounds(0, 800, 1500, 200);
        timeline.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        timeline.setToolTipText("Movie timeline");
        timeline.setLayout(new GridLayout(3, 3));
        window.add(timeline);
    }

    //Creates Labels for levels
    private void labels() {

        //Red Label
        lred = new JLabel("Red: 0");
        lred.setBounds(10, 60, 90, 10);
        window.add(lred);

        //Green Label
        lgreen = new JLabel("Green: 0");
        lgreen.setBounds(10, 130, 90, 10);
        window.add(lgreen);

        //Blue Label
        lblue = new JLabel("Blue: 0");
        lblue.setBounds(10, 200, 90, 10);
        window.add(lblue);

        //Exposure Label
        lexp = new JLabel("Exposure: 0");
        lexp.setBounds(10, 280, 90, 10);
        window.add(lexp);
    }

    //Creates Levels sliders
    private void sliderMethod() {

        //Red
        red = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        red.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        red.setBounds(10, 10, 255, 50);
        red.setMajorTickSpacing(10);
        red.setMinorTickSpacing(5);
        red.setPaintTicks(true);
        red.setToolTipText("Adjust the red levels of the image");
        window.add(red);

        //green
        green = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        green.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        green.setBounds(10, 80, 255, 50);
        green.setMajorTickSpacing(10);
        green.setMinorTickSpacing(5);
        green.setPaintTicks(true);
        green.setToolTipText("Adjust the green levels of the image");
        window.add(green);

        //Blue
        blue = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        blue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        blue.setBounds(10, 150, 255, 50);
        blue.setMajorTickSpacing(10);
        blue.setMinorTickSpacing(5);
        blue.setPaintTicks(true);
        blue.setToolTipText("Adjust the blue levels of the image");
        window.add(blue);

        //Exposure
        exp = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        exp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exp.setBounds(10, 220, 255, 50);
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

    //renders buffered image from webcam !!NOT WORKING!!
    private void frameRender() {
//        if (webcam = true) {
//            ImageIcon render = new ImageIcon(frame.frame().getBufferedImage());
//            renderWebcam = new JLabel(render);
//            renderWebcam.setBounds((width / 2) - 320, 50, 640, 480);
//            renderWebcam.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
//            renderWebcam.setToolTipText("Live webcam feed");
//            renderWebcam.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
//            window.add(renderWebcam);
//            renderWebcam.revalidate();
//            renderWebcam.repaint();
//        } else {
//            webcam = false;
//            try {
//                frame.grabber.stop();
//            } catch (FrameGrabber.Exception ex) {
//                Logger.getLogger(Controls.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public File filename() {
        return new File(Save_Algorithm.imgdir + new SimpleDateFormat("yyyy\\MM\\dd\\HH-mm-ss").format(new Date()) + ".tiff");
    }

    //Draws the buttons and adds functions to them
    private void drawButtons() {
        //ImageIcon capimage = new ImageIcon("resources/images/capture.gif");
        cap = new JButton("Capture");
        cap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rcap = new Rectangle((width / 2) - 50, (height / 2), 80, 25);
        cap.setBounds(rcap);
        cap.setToolTipText("Capture Frame");
        window.add(cap);

        cap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    //opencv_core.IplImage img = frame.frame();
                    if (camera != null) {
                        sound();
                        // cvSaveImage(Save_Algorithm.imgdir + "\\image_" + framename + ".jpg", img);
                        camera.execute(new ShootTask(filename()));
                        System.out.println("Frame Captured at... " + Save_as.pathname);
                        // framename++;
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}