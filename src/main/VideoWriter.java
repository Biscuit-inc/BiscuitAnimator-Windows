/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.googlecode.javacv.FFmpegFrameRecorder;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author Christopher Williams
 */
public class VideoWriter {

    opencv_core.IplImage image = cvLoadImage("C:/Users/rick/Desktop/test/images/image_0.jpg"); //test path
    private int CODEC_ID_H263;
    private int PIX_FMT_YUV420P;

    public VideoWriter() {
        writer();
    }

    private void writer() {
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("C:/Users/rick/Desktop/test/images/test.avi", 256, 256); //test path
        try {
            recorder.setCodecID(CODEC_ID_H263);
            recorder.setFormat("avi");
            recorder.setPixelFormat(PIX_FMT_YUV420P);
            recorder.start();
            for (int i = 0; i < 10; i++) {
                recorder.record(image);
            }
            recorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
