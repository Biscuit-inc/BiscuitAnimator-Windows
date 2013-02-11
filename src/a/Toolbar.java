/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author Christopher Williams
 */
public class Toolbar {

    final JToolBar toolBar = new JToolBar();
    //Settings
    JMenuItem vidsets = new JMenuItem("Video Settings");
    JMenuItem cam = new JMenuItem("Camera Settings");
    //File
    JMenuItem openfile = new JMenuItem("Open Project");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem saveas = new JMenuItem("Save_as");
    JMenuItem exit = new JMenuItem("exit");
    JMenuItem export = new JMenuItem("Export");
    //Tools
    JMenuItem capwin = new JMenuItem("Capture Window");
    JMenuItem photo = new JMenuItem("Image Editor");
    JMenuItem video = new JMenuItem("Video Editor");
    //Help
    JMenuItem about = new JMenuItem("About...");
    JMenuItem mshelp = new JMenuItem("Help");

    public Toolbar() {
        toolBar.add(createMoreButton());
        toolBar.add(createPrograms());
        toolBar.add(createSettings());
        toolBar.add(createHelp());
        toolBar.setFloatable(false);

        actionMethod();
    }

    //Settings menu
    private AbstractButton createSettings() {
        final JToggleButton helpButton = new JToggleButton("Settings");
        helpButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    createSettingsMenu((JComponent) e.getSource(), helpButton);
                }
            }
        });
        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(SwingConstants.LEADING);
        return helpButton;
    }

    //Help menu
    private AbstractButton createHelp() {
        final JToggleButton helpButton = new JToggleButton("Help");
        helpButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    createHelpMenu((JComponent) e.getSource(), helpButton);
                }
            }
        });
        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(SwingConstants.LEADING);
        return helpButton;
    }

    //File menu
    private AbstractButton createMoreButton() {
        final JToggleButton moreButton = new JToggleButton("File");
        moreButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    createAndShowMenu((JComponent) e.getSource(), moreButton);
                }
            }
        });
        moreButton.setFocusable(false);
        moreButton.setHorizontalTextPosition(SwingConstants.LEADING);
        return moreButton;
    }

    //Tools menu
    private AbstractButton createPrograms() {
        final JToggleButton programs = new JToggleButton("Tools");
        programs.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    createProgramsMenu((JComponent) e.getSource(), programs);
                }
            }
        });
        programs.setFocusable(false);
        programs.setHorizontalTextPosition(SwingConstants.LEADING);
        return programs;
    }

    //Help menu list
    private void createHelpMenu(final JComponent component, final AbstractButton moreButton) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(about);
        menu.add(mshelp);

        menu.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                moreButton.setSelected(false);
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                moreButton.setSelected(false);
            }
        });

        menu.show(component, 0, component.getHeight());
    }

    //Settings menu list
    private void createSettingsMenu(final JComponent component, final AbstractButton moreButton) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(vidsets);

        menu.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                moreButton.setSelected(false);
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                moreButton.setSelected(false);
            }
        });

        menu.show(component, 0, component.getHeight());
    }

    //File menu list
    private void createAndShowMenu(final JComponent component, final AbstractButton moreButton) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(openfile);
        menu.add(save);
        menu.add(saveas);
        menu.add(export);
        menu.add(exit);

        menu.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                moreButton.setSelected(false);
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                moreButton.setSelected(false);
            }
        });

        menu.show(component, 0, component.getHeight());
    }

    //Programs menu list
    private void createProgramsMenu(final JComponent component, final AbstractButton programs) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(capwin);
        menu.add(photo);
        menu.add(video);

        menu.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                programs.setSelected(false);
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                programs.setSelected(false);
            }
        });

        menu.show(component, 0, component.getHeight());
    }

    //Actions performed when clicked
    private void actionMethod() {

        openfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //add TODO code
            }
        });

        capwin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Camera cam = new Camera();
            }
        });

        vidsets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                VideoSettings vs = new VideoSettings();
            }
        });

        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //add TODO code
            }
        });

        mshelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Help help = new Help();
            }
        });

        photo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Photo_editor pe = new Photo_editor();
            }
        });

        export.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                VideoWriter vidwriter = new VideoWriter(); //not working
            }
        });

        saveas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionevent) {
                Save_as save_as = new Save_as();
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Save_Algorithm sa = new Save_Algorithm();
                    sa.projectFile("Last Captured Picture", Controls.framename);
                } catch (IOException ex) {
                    Logger.getLogger(Toolbar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Camera.canvas.dispose();
                Controls.f.dispose();
            }
        });

        video.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Runtime runtime = Runtime.getRuntime();
                    //need to build a video editor
                    runtime.exec("");
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });
    }
}