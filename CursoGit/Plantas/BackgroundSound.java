package Plantas;



/**
 *
 * @author aaron
 */
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.net.URL;

public class BackgroundSound {
    public Clip clip;

    public BackgroundSound(String sonido) {
        try {
            URL url = getClass().getResource(sonido);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
           
        } catch (Exception e) {
            //e.printStackTrace();

        }
    }

    public void stop() {
        clip.stop();
    }
}