import java.util.*;
import javafx.geometry.Insets;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.*;

public abstract class Runner extends Application {

    // Instance Variables
    private static Pane root = new Pane();
    private static Scene scene = new Scene(root, 1000, 1000);
    private static Player player = new Player(500, 800, 30, 30);
    private static boolean movingRight, movingLeft, fireShot;
    private static boolean gamePause = false;
    private static boolean gameRestart = false;
    private static int enemiesToSpawn, enemiesKilled, enemyMovementSpeed, enemyProjectileSpeed, wavesKilled, score;
    private static Label theScore;
    private static String filePath = System.getProperty("user.dir");
    private static Music loserMusic, fireProjectileSound;
    private static final double OFF_SCREEN = 1500;

    // R.N.G.
    private static Random randomNumber = new Random();

    // Arraylists
    private static ArrayList<Projectile> bullets = new ArrayList<Projectile>();
    private static ArrayList<ImageView> theBullets = new ArrayList<ImageView>();

    private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private static ArrayList<ImageView> theEnemies = new ArrayList<ImageView>();

    private static ArrayList<Projectile> enemyBullets = new ArrayList<Projectile>();
    private static ArrayList<ImageView> theEnemyBullets = new ArrayList<ImageView>();

    // Main game/GUI
    /**
     * Starts the game
     * 
     * @param primaryStage
     * @param aScore
     * @param theEnemyMovementSpeed
     * @param theEnemyProjectileSpeed
     * @param theEnemiesToSpawn
     * @param numWavesKilled
     * @param numEnemiesKilled
     * @throws FileNotFoundException
     */
    public static void startGame(Stage primaryStage, int aScore, int theEnemyMovementSpeed, int theEnemyProjectileSpeed,
            int theEnemiesToSpawn, int numWavesKilled, int numEnemiesKilled) throws FileNotFoundException {

        loserMusic = Music.createMusic("\\SFX\\Naruto - Sadness and Sorrow 8 Bit.Mp3");
        fireProjectileSound = Music.createMusic("\\SFX\\pew.wav");

        // Sets up non-enemy/bullet spites and other images

        ImageView playerImage = createPlayer();
        ImageView theBG = createBackgroundImage();
        ImageView gameOver = createGameOverTitle();
        ImageView scoreText = createScoreText();
        ImageView resumeButtonNode = createResumeButtonImage();
        ImageView restartButtonNode = createRestartButtonImage();
        ImageView saveButtonNode = createSaveButtonImage();

        theScore = createScoreLabel(aScore);

        Button resume_button = createResumeButton(resumeButtonNode);
        Button save_button = createSaveButton(saveButtonNode);
        Button restart_button = createRestartButton(restartButtonNode);

        // Sets up main controls
        // Player holds a and d to move left and right respectivly
        // Player taps then releases space to shoot
        // Uses R.N.G to determine if enemies shoot

        enemyMovementSpeed = theEnemyMovementSpeed;
        enemyProjectileSpeed = theEnemyProjectileSpeed;
        enemiesToSpawn = 20 + (numWavesKilled * 2);
        wavesKilled = numWavesKilled;
        enemiesKilled = numEnemiesKilled;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case D:
                        movingRight = true;
                        spawnWaves();
                        enemyChanceToShoot();
                        checkRestartOnKeyHandle(playerImage);
                        break;

                    case A:
                        movingLeft = true;
                        spawnWaves();
                        enemyChanceToShoot();
                        checkRestartOnKeyHandle(playerImage);
                        break;

                    case B:
                        spawnWaves();
                        fireShot = true;
                        if (player.getLive() == true && gamePause == false)
                            try {
                                shootEnemyProjectile();
                                shootEnemyProjectile();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        checkRestartOnKeyHandle(playerImage);
                        break;

                    case ESCAPE:
                        break;
                        
                    default:
                        break;
                }

            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case D:
                        movingRight = false;
                        spawnWaves();
                        enemyChanceToShoot();
                        checkRestartOnKeyHandle(playerImage);
                        break;

                    case A:
                        movingLeft = false;
                        spawnWaves();
                        enemyChanceToShoot();
                        checkRestartOnKeyHandle(playerImage);
                        break;

                    case B:
                        spawnWaves();
                        if (fireShot && player.getLive() && gamePause == false)
                            try {
                                shootProjectile();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        fireShot = false;
                        break;

                    case ESCAPE:
                        if (player.getLive()) {
                            showRestartOptionScreen(resume_button, resumeButtonNode, restart_button, restartButtonNode, save_button, saveButtonNode);
                        }
                        break;
                        
                    default:
                        break;
                }
            }
        });
        // Sets up Stage and spawns in enemies
       
        root.getChildren().addAll(theBG, resume_button, resumeButtonNode, restart_button, restartButtonNode, gameOver, scoreText, playerImage, theScore, save_button, saveButtonNode);
        spawnEnemies(theEnemiesToSpawn);

        primaryStage.setTitle("Intergalactic Assailants");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Animation Timers
        // Each timer controls a diffrent game aspect
        // Handles Player movement based on player input
        AnimationTimer timerPlayer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (gamePause == false && player.getLive()) {

                    save_button.setLayoutX(OFF_SCREEN);
                    save_button.setLayoutY(OFF_SCREEN);
                    saveButtonNode.setLayoutX(OFF_SCREEN);
                    saveButtonNode.setLayoutY(OFF_SCREEN);

                    restart_button.setLayoutX(OFF_SCREEN);
                    restart_button.setLayoutY(OFF_SCREEN);
                    restartButtonNode.setLayoutX(OFF_SCREEN);
                    restartButtonNode.setLayoutY(OFF_SCREEN);
                }

                if (movingLeft) {
                    if (player.getLive() && gamePause == false) {
                        if (player.getX_Coordinate() > 5) {
                            player.moveLeft(5);
                            playerImage.setLayoutX(player.getX_Coordinate());
                        }
                    }
                }

                if (movingRight) {
                    if (player.getLive() && gamePause == false) {
                        if (player.getX_Coordinate() < 950) {
                            player.moveRight(5);
                            playerImage.setLayoutX(player.getX_Coordinate());
                        }
                    }
                }
            }
        };
        // Moves and despawns Enemies based on life state
        AnimationTimer timerEnemy = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (gameRestart) {
                    gameOver.setLayoutX(OFF_SCREEN);
                    gameOver.setLayoutY(OFF_SCREEN);
                }

                if (player.getLive()) {
                    for (int i = 0; i < enemies.size(); i++) {

                        if (enemies.get(i).getLive() && player.getLive() && gamePause == false) {
                            enemies.get(i).setEnemyMovement(enemyMovementSpeed);
                            theEnemies.get(i).setLayoutX(enemies.get(i).getX_Coordinate());
                            theEnemies.get(i).setLayoutY(enemies.get(i).getY_Coordinate());
                            if (enemies.get(i).getUnitHitBox().intersects(player.getUnitHitBox())) {
                                player.setLive(false);

                                restart_button.setLayoutX(500 - (269 / 2));
                                restart_button.setLayoutY(750 - (269 / 2));
                                restartButtonNode.setLayoutX(500 - (269 / 2));
                                restartButtonNode.setLayoutY(750 - (269 / 2));
                            }
                        }
                        // Relocates enemies after death to avoid collosion with invisible, dead enemies
                        // Removes enemies from list to increase fire rate of remaining enemies
                        // Checks if all enemies have died, ends game if they have
                        if (player.getLive() == false) {
                            gameOver.setLayoutX(500 - (375 / 2));
                            gameOver.setLayoutY(500 - (190 / 2));
                            GUI.backgroundMusic.stopMusic();
                            loserMusic.startMusic();
                        }
                        if (enemies.get(i).getLive() == false) {
                            enemies.get(i).setXCoordinate(1100);
                            enemies.get(i).setYCoordinate(1100);
                            root.getChildren().remove(theEnemies.get(i));
                            theEnemies.remove(theEnemies.get(i));
                            enemies.remove(enemies.get(i));

                            enemiesKilled++;

                            score += 100;
                            String aScoreChange = score + "";
                            theScore.setText(aScoreChange);
                        }
                    }
                }
            }
        };
        // Moves Projectiles and checks for collisions with enemy
        AnimationTimer timerBullets = new AnimationTimer() {

            @Override
            public void handle(long now) {
                for (int i = 0; i < bullets.size(); i++) {
                    // Collision for projectiles
                    if (bullets.get(i).getLive() == true && bullets.get(i).getY_Coordinate() < 1100) {
                        bullets.get(i).setProjectileMovingSpeed(7);
                        theBullets.get(i).setLayoutY(bullets.get(i).getY_Coordinate());
                    }
                    // Collision for enemies
                    for (int e = 0; e < enemies.size(); e++) {
                        if (bullets.get(i).getUnitHitBox().intersects(enemies.get(e).getUnitHitBox())
                                && bullets.get(i).getLive() == true && gamePause == false) {
                            enemies.get(e).setLive(false);
                            bullets.get(i).setLive(false);
                            root.getChildren().remove(theBullets.get(i));
                        }
                    }
                }
            }
        };
        // Moves Enemy Projectiles
        AnimationTimer timerEnemyBullets = new AnimationTimer() {

            @Override
            public void handle(long now) {
                for (int i = 0; i < enemyBullets.size(); i++) {

                    if (enemyBullets.get(i).getLive() == true && enemyBullets.get(i).getY_Coordinate() > 0) {
                        enemyBullets.get(i).setProjectileMovingSpeed(enemyProjectileSpeed);
                        theEnemyBullets.get(i).setLayoutY(enemyBullets.get(i).getY_Coordinate());
                    }

                    if (enemyBullets.get(i).getUnitHitBox().intersects(player.getUnitHitBox()) && gamePause == false) {
                        player.setLive(false);
                        root.getChildren().remove(playerImage);
                        gameOver.setLayoutX(500 - (375 / 2));
                        gameOver.setLayoutY(500 - (190 / 2));
                        GUI.backgroundMusic.stopMusic();
                        loserMusic.startMusic();

                        restart_button.setLayoutX(500 - (269 / 2));
                        restart_button.setLayoutY(750 - (269 / 2));
                        restartButtonNode.setLayoutX(500 - (269 / 2));
                        restartButtonNode.setLayoutY(750 - (269 / 2));
                    }
                }
            }
        };

        timerPlayer.start();
        timerEnemy.start();
        timerBullets.start();
        timerEnemyBullets.start();
    }

    protected static void showRestartOptionScreen(Button resume_button, ImageView resumeButtonNode, Button restart_button, ImageView restartButtonNode, Button save_button, ImageView saveButtonNode) {
        gamePause = true;

        resume_button.setLayoutX(500 - (269 / 2));
        resume_button.setLayoutY(500 - (269 / 2));
        resumeButtonNode.setLayoutX(500 - (269 / 2));
        resumeButtonNode.setLayoutY(500 - (269 / 2));

        restart_button.setLayoutX(500 - (269 / 2));
        restart_button.setLayoutY(600 - (269 / 2));
        restartButtonNode.setLayoutX(500 - (269 / 2));
        restartButtonNode.setLayoutY(600 - (269 / 2));

        save_button.setLayoutX(500 - (269 / 2));
        save_button.setLayoutY(700 - (269 / 2));
        saveButtonNode.setLayoutX(500 - (269 / 2));
        saveButtonNode.setLayoutY(700 - (269 / 2));
    }

    protected static ImageView createPlayer() throws FileNotFoundException {
        // Sets up Player Sprite
        FileInputStream playerImgFile = new FileInputStream(filePath + "\\Textures\\player.png");
        Image playerSprite = new Image(playerImgFile, 50, 50, false, true);
        ImageView playerImage = new ImageView();
        playerImage.setImage(playerSprite);
        playerImage.setLayoutY(player.getY_Coordinate());
        playerImage.setLayoutX(player.getX_Coordinate());
        
        return playerImage;
    }

    protected static ImageView createBackgroundImage() throws FileNotFoundException {
        // Sets up Backround image
        FileInputStream bgPath = new FileInputStream(filePath + "\\Textures\\bg.png");
        Image bg = new Image(bgPath, 1000, 1000, false, true);
        ImageView theBG = new ImageView();
        theBG.setImage(bg);

        return theBG;
    }

    protected static ImageView createGameOverTitle() throws FileNotFoundException {
        // Sets up Game Over
        FileInputStream goPath = new FileInputStream(filePath + "\\Textures\\game over.png");
        Image go = new Image(goPath, 375, 190, false, true);
        ImageView gameOver = new ImageView();
        gameOver.setImage(go);
        gameOver.setLayoutX(OFF_SCREEN);
        gameOver.setLayoutY(OFF_SCREEN);
        
        return gameOver;
    }
    
    protected static ImageView createScoreText() throws FileNotFoundException {
        // Sets up Score
        FileInputStream scorePath = new FileInputStream(filePath + "\\Textures\\score.png");
        Image scoreI = new Image(scorePath, 186, 44, false, true);
        ImageView scoreText = new ImageView();
        scoreText.setImage(scoreI);
        scoreText.setLayoutX(0);
        scoreText.setLayoutY(950);
        
        return scoreText;
    }

    protected static ImageView createResumeButtonImage() throws FileNotFoundException {
        // Creates resume button Image
        FileInputStream resumePath = new FileInputStream(filePath + "\\Textures\\resume.png");
        Image resumeButton = new Image(resumePath, 269, 84, false, true);
        ImageView resumeButtonNode = new ImageView();
        resumeButtonNode.setImage(resumeButton);
        resumeButtonNode.setLayoutX(OFF_SCREEN);
        resumeButtonNode.setLayoutY(OFF_SCREEN);
        
        return resumeButtonNode;
    }

    protected static ImageView createRestartButtonImage() throws FileNotFoundException {
        // Creates restart button
        FileInputStream restartPath = new FileInputStream(filePath + "\\Textures\\play again.png");
        Image restartButton = new Image(restartPath, 269, 84, false, true);
        ImageView restartButtonNode = new ImageView();
        restartButtonNode.setImage(restartButton);
        restartButtonNode.setLayoutX(OFF_SCREEN);
        restartButtonNode.setLayoutY(OFF_SCREEN);

        return restartButtonNode;
    }

    protected static ImageView createSaveButtonImage() throws FileNotFoundException {

        // Creates save button
        FileInputStream savePath = new FileInputStream(filePath + "\\Textures\\save.png");
        Image saveButton = new Image(savePath, 269, 84, false, true);
        
        ImageView saveButtonNode = new ImageView();
        saveButtonNode.setImage(saveButton);
        
        saveButtonNode.setLayoutX(OFF_SCREEN);
        saveButtonNode.setLayoutY(OFF_SCREEN);

        return saveButtonNode;
    }

    private static Button createResumeButton(ImageView resumeButtonNode) {
        Button resume_button = new Button();
        resume_button.setGraphic(resumeButtonNode);
        resume_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        resume_button.setLayoutX(OFF_SCREEN);
        resume_button.setLayoutY(OFF_SCREEN);

        resume_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent resume) {
                gamePause = false;

                resume_button.setLayoutX(OFF_SCREEN);
                resume_button.setLayoutY(OFF_SCREEN);
                resumeButtonNode.setLayoutX(OFF_SCREEN);
                resumeButtonNode.setLayoutY(OFF_SCREEN);

                GUI.btnClickSound();
            }
        });
        
        return resume_button;
    }

    private static Button createRestartButton(ImageView restartButtonNode) {
        Button restart_button = new Button();
        restart_button.setGraphic(restartButtonNode);
        restart_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        restart_button.setLayoutX(OFF_SCREEN);
        restart_button.setLayoutY(OFF_SCREEN);

        restart_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent restart) {

                root.getChildren().removeAll(bullets);
                root.getChildren().removeAll(theBullets);
                root.getChildren().removeAll(enemies);
                root.getChildren().removeAll(theEnemies);
                root.getChildren().removeAll(enemyBullets);
                root.getChildren().removeAll(theEnemyBullets);

                bullets.clear();
                theBullets.clear();
                enemies.clear();
                theEnemies.clear();
                enemyBullets.clear();
                theEnemyBullets.clear();

                score = 0;
                String aScoreChange = score + "";
                theScore.setText(aScoreChange);
                enemyMovementSpeed = 5;
                enemyProjectileSpeed = 9;
                enemiesToSpawn = 20;
                wavesKilled = 0;
                enemiesKilled = 0;

                try {
                    spawnEnemies(enemiesToSpawn);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (player.getLive() == false)
                    gameRestart = true;
                player.setLive(true);

                loserMusic.stopMusic();
                GUI.backgroundMusic.startMusic();
                GUI.btnClickSound();

                restart_button.setLayoutX(OFF_SCREEN);
                restart_button.setLayoutY(OFF_SCREEN);
                restartButtonNode.setLayoutX(OFF_SCREEN);
                restartButtonNode.setLayoutY(OFF_SCREEN);
            }
        });

        return restart_button;
    }

    private static Button createSaveButton(ImageView saveButtonNode) {
        Button save_button = new Button();
        save_button.setGraphic(saveButtonNode);
        save_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        save_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent save) {
                if (enemiesKilled == enemiesToSpawn)
                    enemiesKilled = 0;
                try {
                    FileWriter savefwriter = new FileWriter(filePath + "\\savegame.txt", false);
                    BufferedWriter savebwriter = new BufferedWriter(savefwriter);
                    savebwriter.write(Integer.toString(score));
                    savebwriter.newLine();
                    savebwriter.write(Integer.toString(enemyMovementSpeed));
                    savebwriter.newLine();
                    savebwriter.write(Integer.toString(enemyProjectileSpeed));
                    savebwriter.newLine();
                    savebwriter.write(Integer.toString(enemiesToSpawn));
                    savebwriter.newLine();
                    savebwriter.write(Integer.toString(wavesKilled));
                    savebwriter.newLine();
                    savebwriter.write(Integer.toString(enemiesKilled));
                    savebwriter.close();
                    FileWriter scorefwriter = new FileWriter(filePath + "\\scoreboard.txt", true);
                    BufferedWriter scorebwriter = new BufferedWriter(scorefwriter);
                    scorebwriter.write(Integer.toString(score));
                    scorebwriter.newLine();
                    scorebwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GUI.btnClickSound();
            }
        });
        save_button.setLayoutX(OFF_SCREEN);
        save_button.setLayoutY(OFF_SCREEN);

        return save_button;
    }

    protected static Label createScoreLabel(int aScore) throws FileNotFoundException {
        // Sets up Score label
        String scoreString = aScore + "";
        Label theScore = new Label(scoreString);
        FileInputStream fontStream = new FileInputStream(filePath + "\\Textures\\04B_30__.TTF");
        Font f = Font.loadFont(fontStream, 34);
        theScore.setFont(f);
        theScore.setTextFill(Color.rgb(255, 193, 170));
        theScore.setLayoutX(200);
        theScore.setLayoutY(955);

        return theScore;
    }

    // Methods for Spawning game elements
    /**
     * Method that spawns enemies
     * 
     * @param numberOfEnemies
     * @throws FileNotFoundException
     */

    private static void spawnEnemies(int numberOfEnemies) throws FileNotFoundException {
        // Used to seperate Enemy Spawns
        int x = 0;
        int y = 0;
        for (int i = 0; i < numberOfEnemies; i++) {
            final boolean reachedEndOfScreen = (50 + (x * 100)) == 950;
            // Checks if spawns have reached the outer limit of the game board
            if (reachedEndOfScreen) {
                x = 0;
                y++;
            }
            Enemy enemy = new Enemy((50 + (x * 100)), (50 + (y * 50)), 50, 50);
            // Enemy moves left or right based on y value
            if (y % 2 != 0)
                enemy.setEnemyMovementRight(false);
            // Enemy sprite setup
            FileInputStream enemyPath = new FileInputStream(filePath + "\\Textures\\ufo.png");
            Image enemySprite = new Image(enemyPath, 50, 50, false, true);
            ImageView theEnemy = new ImageView();
            theEnemy.setImage(enemySprite);
            // Sets Layout and adds enemy and sprite to the scene and corrseponding arrays
            theEnemy.setLayoutX(enemy.getX_Coordinate());
            theEnemy.setLayoutY(enemy.getY_Coordinate());

            enemies.add(enemy);
            theEnemies.add(theEnemy);
            root.getChildren().add(theEnemy);

            x++;
        }
    }

    /**
     * Method that allows the player to shoot
     * 
     * @throws FileNotFoundException
     */
    private static void shootProjectile() throws FileNotFoundException {
        // Creates Projectile
        Projectile bullet = new Projectile(player.getX_Coordinate(), player.getY_Coordinate(), 20, 40, true);
        // Creates Procetile sprite
        FileInputStream bulletPath = new FileInputStream(filePath + "\\Textures\\missle.png");
        Image bulletSprite = new Image(bulletPath, 20, 50, false, true);
        ImageView theBullet = new ImageView();
        theBullet.setImage(bulletSprite);
        // Spawns Projectile @ player location
        theBullet.setLayoutX(bullet.getX_Coordinate() + 15);
        theBullet.setLayoutY(bullet.getY_Coordinate());
        // adds projectile and sprite to scene and arraylists
        bullets.add(bullet);
        theBullets.add(theBullet);
        root.getChildren().add(theBullet);
        // SFX
        fireProjectileSound.startMusic();
    }

    /**
     * Method that allows the enemies to shoot
     * 
     * @throws FileNotFoundException
     */
    public static void shootEnemyProjectile() throws FileNotFoundException {
        // One enemy will randomly shoot
        Enemy enemyBullet = enemies.get(randomNumber.nextInt(enemies.size()));
        // Creates Projectile
        Projectile bullet = new Projectile(enemyBullet.getX_Coordinate(), enemyBullet.getY_Coordinate(), 20, 40, false);
        // Creates Procetile sprite
        FileInputStream bulletPath = new FileInputStream(filePath + "\\Textures\\pew.png");
        Image bulletSprite = new Image(bulletPath, 20, 50, false, true);
        ImageView theBullet = new ImageView();
        theBullet.setImage(bulletSprite);
        // Spawns Projectile @ enemy location
        theBullet.setLayoutX(bullet.getX_Coordinate());
        theBullet.setLayoutY(bullet.getY_Coordinate());
        // adds projectile and sprite to scene and arraylists
        enemyBullets.add(bullet);
        theEnemyBullets.add(theBullet);
        root.getChildren().add(theBullet);
    }

    /**
     * Spawns new waves after one has been cleared
     */
    public static void spawnWaves() {
        // Spawns new Waves of enemies
        if (enemiesKilled == enemiesToSpawn) {
            wavesKilled++;
            enemiesKilled = 0;
            // increases amount of enemies by 2 per wave killed
            if (enemiesToSpawn < 40)
                enemiesToSpawn += 2;
            // increases speed of enemies every other wave
            if (wavesKilled % 2 == 0)
                enemyMovementSpeed++;
            // increases projectile speed every 4th wave killed
            if (wavesKilled % 4 == 0)
                enemyProjectileSpeed++;
            try {
                spawnEnemies(enemiesToSpawn);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void enemyChanceToShoot() {
        int random = randomNumber.nextInt(2);
        if (random == 0 && player.getLive() && gamePause == false)
            try {
                shootEnemyProjectile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }

    protected static void checkRestartOnKeyHandle(ImageView playerImage) {
        if (gameRestart) {
            root.getChildren().add(playerImage);
            gameRestart = false;
        }
    }
}