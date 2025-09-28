import javax.sound.sampled.*;
import java.io.File;

public class Audio {
    static Clip clip;

    public Audio(String audioPath) {
        try {
            File file = new File(audioPath);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clip1(){
        new Audio("homeMusic.wav");
    }

    public static void clip2(){
        new Audio("tickingClock.wav");
    }

    public static void stopClip() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}