public class Projectile extends Player {
    // Instance Variable
    private boolean projectileMovingUp;

    /**
     * Constructor that takes in the dimensions and position of the projectial as
     * well as the direction it is moving in
     * 
     * @param x
     * @param y
     * @param w
     * @param h
     * @param state
     */
    public Projectile(int x, int y, int w, int h, boolean state) {
        super(x, y, w, h);
        this.projectileMovingUp = state;
    }

    /**
     * Returns a boolean of whether the projectile is moving up or not
     * 
     * @return
     */
    public boolean getProjectileMovingUp() {
        return projectileMovingUp;
    }

    /**
     * Sets the speed at which the projectile is moving
     * 
     * @param speed
     */
    public void setProjectileMovingSpeed(int speed) {
        if (projectileMovingUp == true)
            moveUp(speed);
        if (projectileMovingUp == false)
            moveDown(speed);
    }
}