/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Christopher Williams
 */
public class audioRecorder extends JPanel {

    AudioFormat audioFormat;
    TargetDataLine targetDataLine;
    final JButton captureBtn = new JButton("Capture");
    final JButton stopBtn = new JButton("Stop");
    final JPanel btnPanel = new JPanel();
    final ButtonGroup btnGroup = new ButtonGroup();
    final JRadioButton aifcBtn = new JRadioButton("AIFC");
    final JRadioButton aiffBtn = new JRadioButton("AIFF");
    final JRadioButton auBtn = new JRadioButton("AU", true);
    final JRadioButton sndBtn = new JRadioButton("SND");
    final JRadioButton waveBtn = new JRadioButton("WAVE");

    public audioRecorder() {

        //Register anonymous listeners
        captureBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(
                            ActionEvent e) {
                        captureBtn.setEnabled(false);
                        stopBtn.setEnabled(true);
                        //Capture input data from the
                        // microphone until the Stop button is
                        // clicked.
                        captureAudio();
                    }//end actionPerformed
                }//end ActionListener
                );//end addActionListener()

        stopBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(
                            ActionEvent e) {
                        captureBtn.setEnabled(true);
                        stopBtn.setEnabled(false);
                        //Terminate the capturing of input data
                        // from the microphone.
                        targetDataLine.stop();
                        targetDataLine.close();
                    }//end actionPerformed
                }//end ActionListener
                );//end addActionListener()

//        //Include the radio buttons in a group
//        btnGroup.add(aifcBtn);
//        btnGroup.add(aiffBtn);
//        btnGroup.add(auBtn);
//        btnGroup.add(sndBtn);
//        btnGroup.add(waveBtn);
//
//        //Add the radio buttons to the JPanel
//        btnPanel.add(aifcBtn);
//        btnPanel.add(aiffBtn);
//        btnPanel.add(auBtn);
//        btnPanel.add(sndBtn);
//        btnPanel.add(waveBtn);

    }//end constructor

    //This method captures audio input from a
    // microphone and saves it in an audio file.
    private void captureAudio() {
        try {
            //Get things set up for capture
            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo =
                    new DataLine.Info(
                    TargetDataLine.class,
                    audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);

            //Create a thread to capture the microphone
            // data into an audio file and start the
            // thread running.  It will run until the
            // Stop button is clicked.  This method
            // will return after starting the thread.
            new CaptureThread().start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }//end catch
    }//end captureAudio method

    //This method creates and returns an
    // AudioFormat object for a given set of format
    // parameters.  If these parameters don't work
    // well for you, try some of the other
    // allowable parameter values, which are shown
    // in comments following the declarations.
    private AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        //8,16
        int channels = 1;
        //1,2
        boolean signed = true;
        //true,false
        boolean bigEndian = false;
        //true,false
        return new AudioFormat(sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }

//Inner class to capture data from microphone
// and write it to an output audio file.
    class CaptureThread extends Thread {

        public void run() {
            AudioFileFormat.Type fileType = null;
            File audioFile = null;

            //Set the file type and the file extension
            // based on the selected radio button.
            if (aifcBtn.isSelected()) {
                fileType = AudioFileFormat.Type.AIFC;
                audioFile = new File("junk.aifc");
            } else if (aiffBtn.isSelected()) {
                fileType = AudioFileFormat.Type.AIFF;
                audioFile = new File("junk.aif");
            } else if (auBtn.isSelected()) {
                fileType = AudioFileFormat.Type.AU;
                audioFile = new File("junk.au");
            } else if (sndBtn.isSelected()) {
                fileType = AudioFileFormat.Type.SND;
                audioFile = new File("junk.snd");
            } else if (waveBtn.isSelected()) {
                fileType = AudioFileFormat.Type.WAVE;
                audioFile = new File("junk.wav");
            }

            try {
                targetDataLine.open(audioFormat);
                targetDataLine.start();
                AudioSystem.write(
                        new AudioInputStream(targetDataLine),
                        fileType,
                        audioFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}