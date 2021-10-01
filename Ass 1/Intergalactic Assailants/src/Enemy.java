public class Enemy extends Player {
    // New instance variables
    private boolean enemyMovementRight;

    /**
     * Constructor that takes in the enemy's x and y coordinate and the height and
     * width
     * 
     * @param x
     * @param y
     * @param h
     * @param w
     */
    public Enemy(int x, int y, int h, int w) {
        super(x, y, h, w);
        this.enemyMovementRight = true;
    }

    /**
     * Getter method that returns if the enemy is supposed to move right or not
     * 
     * @return enemyMovementRight
     */
    public boolean getEnemyMovementRight() {
        return enemyMovementRight;
    }

    /**
     * Setter method that sets which direction the enemy is to move in
     * 
     * @param state
     */
    public void setEnemyMovementRight(boolean state) {
        enemyMovementRight = state;
    }

    /**
     * Sets the enemy's movement speed and direction
     * 
     * @param enemySpeed
     */
    public void enemyMovement(int enemySpeed) {
        if (getEnemyMovementRight() == true) {
            if (getX_Coordinate() >= 930) {
                moveDown(50);
                setEnemyMovementRight(false);
            }
            moveRight(enemySpeed);
        } else {
            if (getX_Coordinate() <= 0) {
                moveDown(50);
                setEnemyMovementRight(true);
            }
            moveLeft(enemySpeed);
        }
    }
}