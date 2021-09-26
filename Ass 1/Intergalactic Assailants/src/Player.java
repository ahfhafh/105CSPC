import java.awt.Rectangle;

public class Player {
    // The Players instance Variables
    private int x_coordinate;
    private int y_coordinate;
    private int width;
    private int height;
    private boolean live;

    /**
     * Player constructor that takes in the x and y coordinates as well as the width
     * and height of the player
     * 
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public Player(int x, int y, int w, int h) {
        this.x_coordinate = x;
        this.y_coordinate = y;
        this.width = w;
        this.height = h;
        this.live = true;
    }

    // Getters
    /**
     * Returns the player's x-coordinate
     * 
     * @return x_coordinate
     */
    public int getX_Coordinate() {
        return x_coordinate;
    }

    /**
     * Returns the player's y-coordinate
     * 
     * @return
     */
    public int getY_Coordinate() {
        return y_coordinate;
    }

    /**
     * Returns the player's width
     * 
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the player's height
     * 
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the life of the player
     * 
     * @return
     */
    public boolean getLive() {
        return live;
    }

    // Setters
    /**
     * Sets the player's x-coordinate
     * 
     * @param a
     */
    public void setXCoordinate(int a) {
        x_coordinate = a;
    }

    /**
     * Sets the player's y coordinate
     * 
     * @param a
     */
    public void setYCoordinate(int a) {
        y_coordinate = a;
    }

    /**
     * Sets the player's state
     * 
     * @param state
     */
    public void setLive(boolean state) {
        if (live != state)
            live = state;
    }

    /**
     * Sets the player's width
     * 
     * @param aWidth
     */
    public void setWidth(int aWidth) {
        width = aWidth;
    }

    /**
     * Sets the player's height
     * 
     * @param aHeight
     */
    public void setHeight(int aHeight) {
        height = aHeight;
    }

    // Movement
    /**
     * Moves the player to the right
     * 
     * @param distance
     */
    public void moveRight(int distance) {
        x_coordinate += distance;
    }

    /**
     * Moves the player to the left
     * 
     * @param distance
     */
    public void moveLeft(int distance) {
        x_coordinate -= distance;
    }

    /**
     * Moves the object down
     * 
     * @param distance
     */
    public void moveDown(int distance) {
        y_coordinate += distance;
    }

    /**
     * Moves the object up
     * 
     * @param distance
     */
    public void moveUp(int distance) {
        y_coordinate -= distance;
    }

    // Hitbox
    /**
     * Returns the position and dimensions of the unit's hitbox
     * 
     * @return
     */
    public Rectangle getUnitHitBox() {
        return new Rectangle(x_coordinate, y_coordinate, width, height);
    }
}