import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer.Status;


public class MusicTest {

    private static JFXPanel fxPanel;
    private static Music test_music;

    @BeforeAll
    static void initJavaFx() {
        fxPanel = new JFXPanel();
        test_music = Music.createMusic("\\SFX\\pew.wav");
    }

    @RepeatedTest(5)
    void testStartofMusic() throws InterruptedException {
        test_music.startMusic();
        boolean playing = test_music.getMediaPlayer().getStatus().equals(Status.PLAYING);
        assertEquals(true, playing);
    }

    @Test
    void testEndofMusic() {
        test_music.stopMusic();
        boolean playing = test_music.getMediaPlayer().getStatus().equals(Status.PLAYING);
        assertEquals(false, playing);
    }

    @Test
    void testGetlog1() {

    }

    @Test
    void testGetMediaPlayer() {

    }
    
}
