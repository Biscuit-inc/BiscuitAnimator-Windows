package a;

import java.awt.BorderLayout;
import java.awt.Rectangle;
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
    JPanel window = new JPanel();
    JButton cap;
    Rectangle rcap;
    private int width = 210;
    private int height = 280;
    public static JFrame f = new JFrame();
    protected int fpsselection = 1;
    Toolbar toolbar = new Toolbar();

    //handles the JFrame
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
        f.setTitle("00002-A");
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
        rcap = new Rectangle((width / 2) - 40, 80, 80, 25);
        cap.setBounds(rcap);
        window.add(cap);
    }

    public static void main(String args[]) {
        Controls controls = new Controls();
        Camera camera = new Camera();
    }
}