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
import javafx.scene.text.Font;

import java.io.*;
import java.util.*;

/**
 * Main menu screen for the game 19.03.15
 */

public class GUI extends Application {

    private static String filePath = System.getProperty("user.dir");
    private static int aEnemiesToSpawn, aEnemiesKilled, aEnemyMovementSpeed, aEnemyProjectileSpeed, aWavesKilled,
            aScore;

    // Background Music
    int maxVolume = 100;
    int volume = 40;
    static String Backgroundmsc = filePath + "\\SFX\\8_Bit_March.mp3";
    static Media Backgroundsnd = new Media(new File(Backgroundmsc).toURI().toString());
    static MediaPlayer playBackgroundmsc = new MediaPlayer(Backgroundsnd);
    float log1 = (float) (Math.log(maxVolume - volume) / Math.log(maxVolume));

    /**
     * method that makes sound when the button is clicked
     */
    public static void btnClickSound() {
        String btnClickSound = filePath + "\\SFX\\casual-death-loose.wav";
        MediaPlayer playbtnClickSound = new MediaPlayer(new Media(new File(btnClickSound).toURI().toString()));
        playbtnClickSound.play();
    }

    /**
     * method that stops background music
     */
    public static void stopBackgroundMusic() {
        playBackgroundmsc.stop();
    }

    /**
     * starts background music
     */
    public static void startBackgroundMusic() {
        playBackgroundmsc.play();
    }

    @Override
    public void start(Stage stage) throws Exception {

        playBackgroundmsc.setOnEndOfMedia(new Runnable() {
            public void run() {
                playBackgroundmsc.stop();
                playBackgroundmsc.play();
            }
        });
        playBackgroundmsc.setVolume(1 - log1);
        startBackgroundMusic();

        Pane root = new Pane();

        FileInputStream titleBg = new FileInputStream(filePath + "\\Textures\\space.png");
        Image titleScreen = new Image(titleBg, 1920, 1080, false, true);
        FileInputStream titleText = new FileInputStream(filePath + "\\Textures\\title.png");
        Image title = new Image(titleText, 777, 174, false, true);
        FileInputStream commands = new FileInputStream(filePath + "\\Textures\\commands.png");
        Image controls = new Image(commands, 500, 232, false, true);
        FileInputStream playPath = new FileInputStream(filePath + "\\Textures\\start.png");
        Image playButton = new Image(playPath, 269, 84, false, true);
        FileInputStream exitPath = new FileInputStream(filePath + "\\Textures\\exit.png");
        Image exitButton = new Image(exitPath, 267, 82, false, true);
        FileInputStream scorePath = new FileInputStream(filePath + "\\Textures\\score.png");
        Image score = new Image(scorePath, 186, 44, false, true);
        FileInputStream icon = new FileInputStream(filePath + "\\Textures\\moon.png");
        Image moon = new Image(icon, 50, 50, false, true);

        ImageView scoreNode = new ImageView();
        scoreNode.setImage(score);

        ImageView imageView = new ImageView();
        imageView.setImage(titleScreen);

        ImageView titleNode = new ImageView();
        titleNode.setImage(title);

        ImageView instructions = new ImageView();
        instructions.setImage(controls);

        Button play_button = new Button();
        ImageView playButtonNode = new ImageView();
        playButtonNode.setImage(playButton);
        play_button.setGraphic(playButtonNode);
        play_button
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
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

        Button exit_button = new Button();
        ImageView exitButtonNode = new ImageView();
        exitButtonNode.setImage(exitButton);
        exit_button.setGraphic(exitButtonNode);
        exit_button
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        exit_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent exit) {
                System.exit(0);
            }
        });
        // Create load button
        FileInputStream loadPath = new FileInputStream(filePath + "\\Textures\\load.png");
        Image loadButton = new Image(loadPath, 269, 84, false, true);
        Button load_button = new Button();
        ImageView loadButtonNode = new ImageView();
        loadButtonNode.setImage(loadButton);
        load_button.setGraphic(loadButtonNode);
        load_button
                .setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
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
                        if (i == 4)
                            aEnemiesToSpawn = Integer.parseInt(breader.readLine());
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
        loadButtonNode.setLayoutX(500);
        loadButtonNode.setLayoutY(600);

        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        imageView.setFitHeight(1080);
        imageView.setFitWidth(1300);
        imageView.setPreserveRatio(true);

        titleNode.setLayoutX(250);
        titleNode.setLayoutY(180);

        instructions.setLayoutX(800);
        instructions.setLayoutY(430);

        play_button.setLayoutX(500);
        play_button.setLayoutY(400);
        playButtonNode.setLayoutX(500);
        playButtonNode.setLayoutY(400);

        exit_button.setLayoutX(500);
        exit_button.setLayoutY(500);
        exitButtonNode.setLayoutX(500);
        exitButtonNode.setLayoutY(500);

        scoreNode.setLayoutX(950);
        scoreNode.setLayoutY(620);

        root.getChildren().addAll(imageView, titleNode, play_button, exit_button, load_button, loadButtonNode,
                instructions);
        Scene scene = new Scene(root, 1300, 730);
        stage.setTitle("Intergalactic Assailants");
        stage.getIcons().add(moon);
        stage.setScene(scene);
        stage.show();
    }

}