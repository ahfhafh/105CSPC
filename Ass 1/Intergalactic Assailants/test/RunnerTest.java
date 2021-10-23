import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RunnerTest {

    static private JFXPanel fxPanel;

    @BeforeAll
    static void initJavaFx() {
        fxPanel = new JFXPanel();
    }
    
    @Test
    void testShowRestartOptionScreen() {

    }
    
    @Test
    void testCreatePlayer() throws FileNotFoundException {
        ImageView playerImage = Runner.createPlayer();
        Image image = playerImage.getImage();

        assertNotNull(image);
        assertFalse(image.errorProperty().get());
    }
    
    @Test
    void testCreateScoreLabel() throws FileNotFoundException { 
        Label label = Runner.createScoreLabel(5);
        
        assertEquals(label, label);
    }

    @Test
    void testCreateBackgroundImage() throws FileNotFoundException {

    }

    @Test
    void testGameOverTitle() throws FileNotFoundException {

    }

    @Test
    void testCreateResumeButtonImage() throws FileNotFoundException {

    }

    @Test
    void testCreateRestartButtonImage() throws FileNotFoundException {

    }

    @Test
    void testCreateSaveButtonImage() throws FileNotFoundException {

    }

    @Test
    void testCheckRestartOnKeyHandle() {

    }

}
