/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha;

import java.awt.Choice;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Christopher Williams
 */
//Not Functional Yet
public class VideoSettings extends JFrame {

    private JLabel lfps;
    private JButton ok;
    JPanel settings = new JPanel();
    private int width = 640;
    private int height = 480;
    private Choice fps = new Choice();
    private Rectangle rok, rfps;

    public VideoSettings() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUndecorated(false);
        getContentPane().add(settings);
        setTitle("Settings");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        settings.setLayout(null);

        JLabel();
        drawbuttons();
        repaint();
    }

    private void JLabel() {
        lfps = new JLabel("FPS");
        lfps.setBounds(30, 10, 80, 25);
        settings.add(lfps);
    }

    private void drawbuttons() {

        ok = new JButton("Save");
        rok = new Rectangle((width / 2) - 50, 400, 80, 25);
        ok.setBounds(rok);
        settings.add(ok);

        rfps = new Rectangle(30, 40, 80, 25);
        fps.setBounds(rfps);
        fps.add("10");
        fps.add("12");
        fps.add("15");
        fps.add("24");
        fps.add("30");
        fps.select(2);
        settings.add(fps);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                int fpsselection = fps.getSelectedIndex();
                int f = 0;

                if (fpsselection == 0) {
                    f = 10;
                }

                if (fpsselection == 1 || fpsselection == -1) {
                    f = 12;
                }

                if (fpsselection == 2) {
                    f = 15;
                }

                if (fpsselection == 3) {
                    f = 24;
                }

                if (fpsselection == 4) {
                    f = 30;
                }

                Configure.saveConfig("fps", f);
                dispose();
            }
        });
    }
}
