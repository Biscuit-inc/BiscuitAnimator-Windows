/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha;

import com.googlecode.javacv.FFmpegFrameRecorder;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Christopher Williams
 */
public class VideoWriter {

    /**
     * Not working it keeps crashing I've contacted the lead dev on javacv and
     * we're doing some trouble shooting
     */
    opencv_core.IplImage image = cvLoadImage("imgs/image_0.jpg"); //test path
    ImageIcon[] images = new ImageIcon[10000];
    private int CODEC_ID_H263;
    private int PIX_FMT_YUV420P;

    public VideoWriter() {
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("test.avi", 256, 256); //test path
        try {
            recorder.setFormat("avi");
            recorder.setPixelFormat(PIX_FMT_YUV420P);
            recorder.start();
            for (int i = 0; i < Controls.framename; i++) {
                images[i] = new ImageIcon(Save_Algorithm.imgdir + "\\image_" + i + ".tiff");
                recorder.record(image);
            }
            recorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}