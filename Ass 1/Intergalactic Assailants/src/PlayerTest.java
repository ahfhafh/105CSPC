/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hrithvik
 */
public class PlayerTest {
    public static final String CLASSNAME = "Player";
    public static final String FILENAME = CLASSNAME + ".java";
    
    private void testInterface() 
    {
            String[] instanceVars = {"int x_coordinate", "int y_coordinate", "int width", "int height", "boolean live"};

//		assertTrue("Class should not have the default constructor.", noDefaultConstructor());

    }

    /**
     * Test of getX_Coordinate method, of class Player.
     */
    @Test
    public void testGetX_Coordinate() {
        System.out.println("getX_Coordinate");
        Player instance = new Player(50, 30, 30, 50);
        int expResult = 50;
        int result = instance.getX_Coordinate();
        assertEquals("Expected Result:",expResult, result);
    }

    /**
     * Test of getY_Coordinate method, of class Player.
     */
    @Test
    public void testGetY_Coordinate() {
        System.out.println("getY_Coordinate");
        Player instance = new Player(50, 30, 30, 50);
        int expResult = 30;
        int result = instance.getY_Coordinate();
        assertEquals("Expected Result:",expResult, result);
    }

    /**
     * Test of getWidth method, of class Player.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Player instance = new Player(50, 30, 30, 50);;
        int expResult = 30;
        int result = instance.getWidth();
        assertEquals("Expected Result",expResult, result);
    }

    /**
     * Test of getHeight method, of class Player.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Player instance = new Player(50, 30, 30, 50);
        int expResult = 50;
        int result = instance.getHeight();
        assertEquals("Expected Result",expResult, result);
    }

    /**
     * Test of getLive method, of class Player.
     */
    @Test
    public void testGetLive() {
        System.out.println("getLive");
        Player instance = new Player(50, 30, 30, 50);;
        boolean expResult = true;
        boolean result = instance.getLive();
        assertEquals("Expected Result:",expResult, result);
    }

    /**
     * Test of setXCoordinate method, of class Player.
     */
    @Test
    public void testSetXCoordinate() {
        System.out.println("setXCoordinate");
        int a = 80;
        Player instance = new Player(50, 30, 30, 50);;
        instance.setXCoordinate(a);
        assertEquals("Expected Result:",80, instance.getX_Coordinate());
    }

    /**
     * Test of setYCoordinate method, of class Player.
     */
    @Test
    public void testSetYCoordinate() {
        System.out.println("setYCoordinate");
        int a = 60;
        Player instance = new Player(30,40,40,60);
        instance.setYCoordinate(a);
        assertEquals("Expected Result:",60, instance.getY_Coordinate());
    }

    /**
     * Test of setLive method, of class Player.
     */
    @Test
    public void testSetLive() {
        System.out.println("setLive");
        boolean state = false;
        Player instance = new Player(50, 60, 50, 60);
        instance.setLive(state);
        assertEquals("Expected Result:", false, instance.getLive());
    }

    /**
     * Test of setWidth method, of class Player.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        int aWidth = 10;
        Player instance = new Player(20,60,60,70);
        instance.setWidth(aWidth);
        assertEquals("Expected Result:",10, instance.getWidth());
    }

    /**
     * Test of setHeight method, of class Player.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        int aHeight = 20;
        Player instance = new Player(10, 40, 50, 40);
        instance.setHeight(aHeight);
        assertEquals("Expected Result:",20, instance.getHeight());
    }

    /**
     * Test of moveRight method, of class Player.
     */
    @Test
    public void testMoveRight() {
        System.out.println("moveRight");
        int distance = 10;
        Player instance = new Player(20,20,30,40);
        instance.moveRight(distance);
        assertEquals("Expected Result:",30, instance.getX_Coordinate());
    }

    /**
     * Test of moveLeft method, of class Player.
     */
    @Test
    public void testMoveLeft() {
        System.out.println("moveLeft");
        int distance = 10;
        Player instance = new Player(20, 30, 40, 50);
        instance.moveLeft(distance);
        assertEquals("Expected Result:",10, instance.getX_Coordinate());
    }

    /**
     * Test of moveDown method, of class Player.
     */
    @Test
    public void testMoveDown() {
        System.out.println("moveDown");
        int distance = 10;
        Player instance = new Player(20, 40, 50, 60);
        instance.moveDown(distance);
        assertEquals("Expected Result:",50, instance.getY_Coordinate());
    }

    /**
     * Test of moveUp method, of class Player.
     */
    @Test
    public void testMoveUp() {
        System.out.println("moveUp");
        int distance = 10;
        Player instance = new Player(20, 50, 60, 70);
        instance.moveUp(distance);
        assertEquals("Expected Result:",40, instance.getY_Coordinate());
    }

    /**
     * Test of getUnitHitBox method, of class Player.
     */
    @Test
    public void testGetUnitHitBox() {
        System.out.println("getUnitHitBox");
        Player instance = new Player(20, 30, 40, 50);
        Rectangle expResult = new Rectangle(20, 30, 40, 50);
        Rectangle result = instance.getUnitHitBox();
        assertEquals("Expected Result:", expResult, result);
    }
    
}
