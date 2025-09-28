import javax.sound.sampled.*;
import java.io.File;
public class Audio {
    static Clip clip;

    public Audio(String audioPath, boolean loop) {
        try {
                    File file = new File(audioPath);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                    clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    if (loop) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public static void stopClip() {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
            }
        }