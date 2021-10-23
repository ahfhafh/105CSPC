import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GUITest { 
    
    @Test
    void testCreateBackgroundImage() throws FileNotFoundException {
        ImageView backgroundImage = GUI.createBackgroundImage();
        Image image = backgroundImage.getImage();
        
        assertNotNull(image);
        assertFalse(image.errorProperty().get());
    }

    @Test
    void testCreateTitleImage() throws FileNotFoundException {
        ImageView titleImage = GUI.createTitleImage();
        Image image = titleImage.getImage();
        
        assertNotNull(image);
        assertFalse(image.errorProperty().get());
    }

    @Test
    void testCreatePlayButtonImage() throws FileNotFoundException {
        ImageView playButtonImage = GUI.createPlayButtonImage();
        Image image = playButtonImage.getImage();

        assertNotNull(image);
        assertFalse(image.errorProperty().get());
    }

    @Test
    void testCreateExitButtonImage() throws FileNotFoundException {
        ImageView exitButtonImage = GUI.createExitButtonImage();
        Image image = exitButtonImage.getImage();

        assertNotNull(image);
        assertFalse(image.errorProperty().get());
    }

    @Test
    void testCreateLoadButtonImage() throws FileNotFoundException {
        ImageView loadButtonImage = GUI.createLoadButtonImage();
        Image image = loadButtonImage.getImage();

        assertNotNull(image);
        assertFalse(image.errorProperty().get());
    }

    @Test
    void testCreateMoonIcon() throws FileNotFoundException {
        Image loadButtonImage = GUI.createMoonIcon();

        assertNotNull(loadButtonImage);
        assertFalse(loadButtonImage.errorProperty().get());
    }


}
