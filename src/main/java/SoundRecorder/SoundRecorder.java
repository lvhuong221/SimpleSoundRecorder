package SoundRecorder;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SoundRecorder {
    //Path of output
    File waveFile;

    //Format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    // the line from which data is captured
    TargetDataLine line;

    //Stop flag to stop recording
    boolean stopFlag;
    AudioFormat getAudioFormat(){
        float sampleRate = 16000;
        int sampleSizeInBits = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format =
                new AudioFormat(sampleRate,sampleSizeInBits,channel,signed, bigEndian);
        return format;
    }
    /*
    Capture the sound and save to wav file
     */
    void start(){
        try{
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            //check is system support the data line
            if(!AudioSystem.isLineSupported(info)){
                System.out.println("Line is not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();// start capturing
            System.out.println("Start capturing...");
            AudioInputStream ais = new AudioInputStream(line);
            System.out.println("Start recording...");
            //Write to file
            String name = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss'.wav'").format(new Date());
            waveFile = new File("./Output/Recording_"+name);
            AudioSystem.write(ais, fileType, waveFile);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void finish(){
        line.stop();
        line.close();
        if(!line.isOpen())
            System.out.println("Finished!");
    }
}