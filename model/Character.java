package model;
import controller.MazeControls;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;


/**
 * This class will represent the controllable character
 * that the user will operate.
 * @author David Hoang
 * @author Faith Capito.
 * @version Fall 2023.
 */


public class Character implements Serializable {
    /**
     * This will be the current position of character.
     */
    private Point myCurrentPosition;


    /**
     * This field will be the bounds for which rows the user can enter.
     */
    private final int myMaxRows;
    /**
     * This field will be the bounds for which columns the user can enter.
     *
     */
    private final  int myMaxCols;




    /**
     * The starting X coordinate of where the user spawned within the room.
     */

    private final int myStartX;

    /**
     * The starting Y coordinate of where the user spawned within the room.
     */

    private final int myStartY;

    /**
     * The speed at which the character moves between each movement.
     */
    private final int mySpeed = 16;






    /**
     * The direction that the character is facing.
     */
    private String myDirection;

    /**
     * This method initializes the start position.
     * @param theStartX coordinate
     * @param theStartY coordinate
     * @param theMaxRows boundaries
     * @param theMaxCols boundaries
     */
    public Character(final int theStartX, final int theStartY,
                     final int theMaxRows, final int theMaxCols) {
        myDirection = "down";
        myStartX = theStartX;
        myStartY = theStartY;
        myCurrentPosition = new Point(theStartX, theStartY);
        myMaxRows = theMaxRows;
        myMaxCols = theMaxCols;
    }


    /**
     * The direction which the character is facing
     * @return a String that represents which direction the character is facing.
     */
    public String getMyDirection() {
        return myDirection;
    }

    /**
     * Resets the character's position to where they spawned.
     */
    public void resetToMiddle() {
        myCurrentPosition = new Point(myStartX,  myStartY);
    }

    /**
     * Moves the user up if it is within the boundaries.
     */
    public void moveUp() {
        final double newY = myCurrentPosition.getY() - mySpeed;
        // Check if the new Y-coordinate is above the top boundary
        if (newY >= 0) {
            myDirection = "up";
            myCurrentPosition.setLocation(myCurrentPosition.getX(), newY);
        } else {
            // If already at or above the top boundary, set Y-coordinate to 0
            myCurrentPosition.setLocation(myCurrentPosition.getX(), 0);
        }

    }

    /**
     * Method to move the user down if it is within the boundaries.
     */
    public void moveDown() {
        final double newY = myCurrentPosition.getY() + mySpeed;
        // Check if the new Y-coordinate is below the bottom boundary
        if (newY < myMaxCols - MazeControls.MY_TILE_SIZE) {
            myDirection = "down";
            myCurrentPosition.setLocation(myCurrentPosition.getX(), newY);

        } else {
            // If already at or below the bottom boundary, set Y-coordinate to bottom boundary
            myCurrentPosition.setLocation(myCurrentPosition.getX(),
                    myMaxCols - MazeControls.MY_TILE_SIZE);
        }

    }

    /**
     * Method to move the user right if it is within the boundaries.
     */
    public void moveRight() {
        final double newX = myCurrentPosition.getX() + mySpeed;
        // Check if the new X-coordinate is beyond the right boundary
        if (newX < myMaxRows - MazeControls.MY_TILE_SIZE) {
            myDirection = "right";
            myCurrentPosition.setLocation(newX, myCurrentPosition.getY());
        } else {
            // If already at or beyond the right boundary, set X-coordinate to right boundary
            myCurrentPosition.setLocation(myMaxRows - MazeControls.MY_TILE_SIZE,
                    myCurrentPosition.getY());
        }

    }


    /**
     * Method to move the user left if it is within the boundaries.
     */
    public void moveLeft() {
        final double newX = myCurrentPosition.getX() - mySpeed;
        // Check if the new X-coordinate is beyond the left boundary
        if (newX >= 0) {
            myDirection = "left";
            myCurrentPosition.setLocation(newX, myCurrentPosition.getY());
        } else {
            // If already at or beyond the left boundary, set X-coordinate to 0
            myCurrentPosition.setLocation(0, myCurrentPosition.getY());
        }

    }






    /**
     * Method to return the current position of the character.
     * @return a point object that represents the current position.
     */
    public Point getCurrentPosition() {
        return new Point(myCurrentPosition);
    }


}