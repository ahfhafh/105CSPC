import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;

/**
 * Main menu screen for the game 19.03.15
 */

public class GUI extends Application {

    private static String filePath = System.getProperty("user.dir");
    private static int aEnemiesKilled, aEnemyMovementSpeed, aEnemyProjectileSpeed, aWavesKilled, aScore;
    static Music backgroundMusic;

    @Override
    public void start(Stage stage) throws Exception {
        playBackgroundmsc();

        ImageView backgroundImage = createBackgroundImage();
        ImageView titleImage = createTitleImage();
        ImageView instructionsImage = createControlsImage();
        ImageView playButtonImage = createPlayButtonImage();
        ImageView exitButtonImage = createExitButtonImage();
        ImageView loadButtonImage = createLoadButtonImage();
        Image moonIcon = createMoonIcon();

        Button play_button = createPlayButton(stage, playButtonImage);
        Button exit_button = createExitButton(stage, exitButtonImage);
        Button load_button = createLoadButton(stage, loadButtonImage);

        Pane root = new Pane();
        root.getChildren().addAll(backgroundImage, titleImage, play_button, exit_button, load_button, loadButtonImage, instructionsImage);
        Scene scene = new Scene(root, 1300, 730);
        stage.setTitle("Intergalactic Assailants");
        stage.getIcons().add(moonIcon);
        stage.setScene(scene);
        stage.show();
    }

    public static void btnClickSound() {
        String btnClickSound = filePath + "\\SFX\\casual-death-loose.wav";
        MediaPlayer playbtnClickSound = new MediaPlayer(new Media(new File(btnClickSound).toURI().toString()));
        playbtnClickSound.play();
    }

    public static void playBackgroundmsc() {
        backgroundMusic = Music.createMusic("\\SFX\\8_Bit_March.mp3");
        backgroundMusic.startMusic();
    }
    

    protected static ImageView createBackgroundImage() throws FileNotFoundException {
        FileInputStream backgroundFile = new FileInputStream(filePath + "\\Textures\\space.png");
        Image backgroundImg = new Image(backgroundFile, 1920, 1080, false, true);
        ImageView backgroundImage = new ImageView();

        backgroundImage.setImage(backgroundImg);
        backgroundImage.setLayoutX(0);
        backgroundImage.setLayoutY(0);
        backgroundImage.setFitHeight(1080);
        backgroundImage.setFitWidth(1300);
        backgroundImage.setPreserveRatio(true);

        return backgroundImage;
    }

    protected static ImageView createTitleImage() throws FileNotFoundException {
        FileInputStream titleFile = new FileInputStream(filePath + "\\Textures\\title.png");
        Image titleImg = new Image(titleFile, 777, 174, false, true);
        ImageView titleImage = new ImageView();

        titleImage.setImage(titleImg);
        titleImage.setLayoutX(250);
        titleImage.setLayoutY(180);

        return titleImage;
    }

    protected static ImageView createControlsImage() throws FileNotFoundException {
        FileInputStream controlsFile = new FileInputStream(filePath + "\\Textures\\commands.png");
        Image controlsImg = new Image(controlsFile, 500, 232, false, true);
        ImageView instructionsImage = new ImageView();
        
        instructionsImage.setImage(controlsImg);
        instructionsImage.setLayoutX(800);
        instructionsImage.setLayoutY(430);

        return instructionsImage;
    }

    protected static ImageView createPlayButtonImage() throws FileNotFoundException {
        FileInputStream playButtonFile = new FileInputStream(filePath + "\\Textures\\start.png");
        Image playButtonImg = new Image(playButtonFile, 269, 84, false, true);
        ImageView playButtonImage = new ImageView();

        playButtonImage.setImage(playButtonImg);
        playButtonImage.setLayoutX(500);
        playButtonImage.setLayoutY(400);

        return playButtonImage;
    }

    protected static ImageView createExitButtonImage() throws FileNotFoundException {
        FileInputStream exitButtonFile = new FileInputStream(filePath + "\\Textures\\exit.png");
        Image exitButtonImg = new Image(exitButtonFile, 267, 82, false, true);
        ImageView exitButtonImage = new ImageView();

        exitButtonImage.setImage(exitButtonImg);
        exitButtonImage.setLayoutX(500);
        exitButtonImage.setLayoutY(500);
        
        return exitButtonImage;
    }

    protected static ImageView createLoadButtonImage() throws FileNotFoundException {
        FileInputStream loadButtonFile = new FileInputStream(filePath + "\\Textures\\load.png");
        Image loadButtonImg = new Image(loadButtonFile, 269, 84, false, true);
        ImageView loadButtonImage = new ImageView();

        loadButtonImage.setImage(loadButtonImg);
        loadButtonImage.setLayoutX(500);
        loadButtonImage.setLayoutY(600);

        return loadButtonImage;

    }

    protected static Image createMoonIcon() throws FileNotFoundException {
        FileInputStream moonIconFile = new FileInputStream(filePath + "\\Textures\\moon.png");
        Image moonImg = new Image(moonIconFile, 50, 50, false, true);

        return moonImg;
    }

    private Button createPlayButton(Stage stage, ImageView playButtonImage) {
        // Create play button
        Button play_button = new Button();
        play_button.setGraphic(playButtonImage);
        play_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        play_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent start) {
                try {
                    Runner.startGame(stage, 0, 5, 9, 20, 0, 0);
                    btnClickSound();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        play_button.setLayoutX(500);
        play_button.setLayoutY(400);

        return play_button;
    }

    private Button createExitButton(Stage stage, ImageView exitButtonImage) {
        Button exit_button = new Button();
        exit_button.setGraphic(exitButtonImage);
        exit_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        exit_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent exit) {
                System.exit(0);
            }
        });

        exit_button.setLayoutX(500);
        exit_button.setLayoutY(500);
        
        return exit_button;
    }

    private Button createLoadButton(Stage stage, ImageView loadButtonImage) {
        Button load_button = new Button();
        load_button.setGraphic(loadButtonImage);
        load_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        load_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent save) {
                try {
                    FileReader freader = new FileReader(filePath + "\\savegame.txt");
                    BufferedReader breader = new BufferedReader(freader);
                    for (int i = 1; i < 7; i++) {
                        if (i == 1)
                            aScore = Integer.parseInt(breader.readLine());
                        if (i == 2)
                            aEnemyMovementSpeed = Integer.parseInt(breader.readLine());
                        if (i == 3)
                            aEnemyProjectileSpeed = Integer.parseInt(breader.readLine());
                        if (i == 5)
                            aWavesKilled = Integer.parseInt(breader.readLine());
                        if (i == 6)
                            aEnemiesKilled = Integer.parseInt(breader.readLine());
                    }
                    breader.close();
                    Runner.startGame(stage, aScore, aEnemyMovementSpeed, aEnemyProjectileSpeed,
                            ((20 + (2 * aWavesKilled)) - aEnemiesKilled), aWavesKilled, aEnemiesKilled);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                btnClickSound();
            }
        });

        load_button.setLayoutX(500);
        load_button.setLayoutY(600);

        return load_button;
    }

}