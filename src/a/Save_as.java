/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Christopher Williams
 */
public class Save_as extends JFrame {

    private JTextField filename = new JTextField(), dir = new JTextField();
    private JButton open = new JButton("Open"), save = new JButton("Save");
    JFileChooser c = new JFileChooser();
    public static String pathname;
    private String lastframenum = "Last Captured Picture";
    private int width = 300;
    private int height = 130;

    public Save_as() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Save as");
        JPanel p = new JPanel();
        p.add(open);
        p.add(save);
        Container cp = getContentPane();
        cp.add(p, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);
        cp.add(p, BorderLayout.NORTH);

        openL();
    }

    private void openL() {

        //Saves path and creates folder
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ab) {
                try {
                    Save_Algorithm savealg = new Save_Algorithm();
                    savealg.projectFile(lastframenum, WIDTH);
                } catch (RuntimeException e) {
                    throw e;
                } catch (IOException ex) {
                    Logger.getLogger(Save_as.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });

        //JFileChooser opener
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                int rVal = c.showOpenDialog(Save_as.this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    filename.setText(c.getSelectedFile().getName());
                    dir.setText(c.getCurrentDirectory().toString());
                    String filedir = (c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName() + "\\");
                    System.out.println(filedir);
                    pathname = filedir;
                }
                if (rVal == JFileChooser.CANCEL_OPTION) {
                    filename.setText("You pressed cancel");
                    dir.setText("");
                }
            }
        });
    }
}