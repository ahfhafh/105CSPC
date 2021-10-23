import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
    private static String filePath = System.getProperty("user.dir");
    
    // Background Music
    private final int maxVolume = 100;
    private final int volume = 40;
    private final float log1 = (float) (Math.log(maxVolume - volume) / Math.log(maxVolume));
    private MediaPlayer music;

    private Music(Media musicMedia) {
        music = new MediaPlayer(musicMedia);
        music.setVolume(1 - log1);
    }

    public static Music createMusic(String musicPath) {
        String musicFile = filePath + musicPath;
        Media musicMedia = new Media(new File(musicFile).toURI().toString());
        return new Music(musicMedia);
    }

    public float getlog1() {
        return this.log1;
    }

    public MediaPlayer getMediaPlayer() {
        return this.music;
    }
    
    public void stopMusic() {
        music.stop();
    }

    public void startMusic() {
        music.play();
        music.seek(music.getStartTime());
    }


}
