/*
 * TriviaMaze
 * Fall 2023
 */
package model;

import controller.MazeControls;
import controller.PropertyChangedEnabledMazeControls;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Maze class contains data that will be responsible for current data of game.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class Maze implements PropertyChangedEnabledMazeControls {

    /**
     * Constant to use for assigning right door in rooms.
     */
    private static final String RIGHT_DOOR = "Right";

    /**
     * Constant to use for assigning left door in rooms.
     */
    private static final String LEFT_DOOR = "Left";

    /**
     * Constant to use for assigning top door in rooms.
     */
    private static final String TOP_DOOR = "Top";

    /**
     * Constant to use for assigning bottom door in rooms.
     */
    private static final String BOTTOM_DOOR = "Bottom";

    /**
     * Constant to use for reaching the furthest rooms in perimeter of maze.
     */
    private static final int ENDPOINT = 3;


    /**
     * The room that Character is currently in.
     */
    private Room myCurrentRoom;

    /**
     * 2D Array of all rooms within this Maze.
     */
    private Room[][] myRooms;

    /**
     * Width of Maze rooms.
     */
    private int myWidth;

    /**
     * Height of Maze rooms.
     */
    private int myHeight;

    /**
     * Number of correct answers that the current Character has answered.
     */
    private static int myCorrectAnswers;

    /**
     * Character that is in Maze.
     */
    private Character myCharacter;

    /**
     * Signals change from the model to the view.
     */
    private PropertyChangeSupport myPcs;

    /**
     * Constructor for new game of Maze, creating starting point of a Character and Rooms.
     */
    public Maze(final int theWidth, final int theHeight) {
        super();

        // Calculate the initial position for the Character to be in the middle of the screen.
        // since it is represented within the top left corner of a pixel, you have to subtract
        // the tile size.
        final int startX = (MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE) / 2;
        final int startY = (MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE) / 2;

        // Instantiate the Character with the calculated initial position.
        myCharacter = new Character(startX, startY, MazeControls.MY_SCREEN_WIDTH,
                MazeControls.MY_SCREEN_HEIGHT);


        myWidth = theWidth;
        myHeight = theHeight;
        myPcs = new PropertyChangeSupport(this);
        createMaze();


    }

    /**
     * Fills the maze with Rooms and sets initial game values.
     */
    public void createMaze() {

        myCorrectAnswers = 0;
        myRooms = new Room[myWidth][myHeight];

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                myRooms[i][j] = new Room();
            }
        }

        createRooms();
        assignDoors();
        myCurrentRoom = myRooms[0][0];
        myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);


    }

    /**
     * Creates appropriate directional doors for each room, with perimeter rooms only
     * containing doors leading to rooms adjacent to it.
     */
    private void createRooms() {

        setTopRow();
        setLeftCol();
        setBottomRow();
        setRightCol();

        for (int i = 1; i < myWidth - 1; i++) {
            for (int j = 1; j < myHeight - 1; j++) {
                myRooms[i][j].setDoor(RIGHT_DOOR);
                myRooms[i][j].setDoor(LEFT_DOOR);
                myRooms[i][j].setDoor(TOP_DOOR);
                myRooms[i][j].setDoor(BOTTOM_DOOR);
            }
        }

    }
    /**
     * Print information about the doors of the current room.
     */
    public void printCurrentRoomDoors() {
        System.out.println("Current Room Doors:");
        System.out.println("Left Door: " + (myCurrentRoom.getLeftDoor() != null ? "Exists" : "Not Exists"));
        System.out.println("Right Door: " + (myCurrentRoom.getRightDoor() != null ? "Exists" : "Not Exists"));
        System.out.println("Top Door: " + (myCurrentRoom.getTopDoor() != null ? "Exists" : "Not Exists"));
        System.out.println("Bottom Door: " + (myCurrentRoom.getBottomDoor() != null ? "Exists" : "Not Exists"));
    }

    /**
     * Sets the doors of top row in maze.
     */
    private void setTopRow() {

        // assigning outside the loop since it only has to be done once.
        myRooms[0][0].setDoor(RIGHT_DOOR);
        myRooms[0][0].setDoor(BOTTOM_DOOR);
        myRooms[0][ENDPOINT].setDoor(LEFT_DOOR);
        myRooms[0][ENDPOINT].setDoor(BOTTOM_DOOR);

        for (int i = 1; i < myWidth - 1; i++) {
            myRooms[0][i].setDoor(LEFT_DOOR);
            myRooms[0][i].setDoor(RIGHT_DOOR);
            myRooms[0][i].setDoor(BOTTOM_DOOR);
        }

    }

    /**
     * Sets the doors of left column in maze.
     */
    private void setLeftCol() {

        // sets [3, 0]
        // assigning outside the loop since it only has to be done once.
        myRooms[ENDPOINT][0].setDoor(RIGHT_DOOR);
        myRooms[ENDPOINT][0].setDoor(TOP_DOOR);

        for (int i = 1; i < myHeight - 1; i++) {
            myRooms[i][0].setDoor(TOP_DOOR);
            myRooms[i][0].setDoor(RIGHT_DOOR);
            myRooms[i][0].setDoor(BOTTOM_DOOR);
        }


    }

    /**
     * Sets the doors of bottom row in maze.
     */
    private void setBottomRow() {

        // sets [3, 3]
        // assigning outside the loop since it only has to be done once.
        myRooms[ENDPOINT][ENDPOINT].setDoor(LEFT_DOOR);
        myRooms[ENDPOINT][ENDPOINT].setDoor(TOP_DOOR);

        for (int i = 1; i < myWidth - 1; i++) {
            myRooms[ENDPOINT][i].setDoor(TOP_DOOR);
            myRooms[ENDPOINT][i].setDoor(RIGHT_DOOR);
            myRooms[ENDPOINT][i].setDoor(LEFT_DOOR);
        }

    }

    /**
     * Sets the doors of right column in maze.
     */
    private void setRightCol() {

//        myRooms[ENDPOINT][1].setDoor();
        for (int i = 1; i < myWidth - 1; i++) {
            myRooms[i][ENDPOINT].setDoor(TOP_DOOR);
            myRooms[i][ENDPOINT].setDoor(BOTTOM_DOOR);
            myRooms[i][ENDPOINT].setDoor(LEFT_DOOR);
        }

    }

    /**
     * Create door objects in myRooms to reference different directional
     * doors that the Character traverses through.
     */
    private void assignDoors() {

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {

                // assigns all rooms that have both left and top doors.
                if (j > 0 && i > 0) {
                    myRooms[i][j].assignLeftDoor(myRooms[i][j - 1].getRightDoor());
                    myRooms[i][j].assignTopDoor(myRooms[i - 1][j].getBottomDoor());
                }

                // assigns all rooms that have both right and bottom doors.
                if (j < myHeight - 2 && i < myWidth - 2) {
                    myRooms[i][j].assignRightDoor(myRooms[i][j + 1].getLeftDoor());
                    myRooms[i][j].assignBottomDoor(myRooms[i + 1][j].getTopDoor());
                }

                // assigns left col top doors
                if (i > 0 && j == 0) {
                    myRooms[i][j].assignTopDoor(myRooms[i - 1][j].getBottomDoor());

                }

                // assigns top row left doors
                if (i == 0 && j > 0) {
                    myRooms[i][j].assignLeftDoor(myRooms[i][j - 1].getRightDoor());

                }

                // assigns right doors of last row.
                if (i == ENDPOINT && j < myHeight - 2) {
                    myRooms[i][j].assignRightDoor(myRooms[i][j + 1].getLeftDoor());
                }

                // assigns bottom doors of right column.
                if (i < myWidth - 2 && j == ENDPOINT) {
                    myRooms[i][j].assignBottomDoor(myRooms[i + 1][j].getTopDoor());
                }

            }
        }

    }

    /**
     * Move method to place Character into a different position.
     * @param theInput
     */
    public void move(final String theInput) {

    }

    /**
     * Returns information about the current room Character is in.
     * @return String info about current room.
     */
    public String getCurrentRoomInfo() {

        final String info = "This is information";

        return info;
    }

    /**
     * Evaluates the answer given by the Character to a trivia Question.
     */
    public boolean answerQuestion(final String theInput) {

        final boolean validity = false;

        return validity;
    }

    /**
     * Evaluates if Character can move in Maze.
     * @return boolean if Character can move.
     */
    public boolean canMove() {

        // TODO: Evaluate nearby cells to see if traversable.

        final boolean move = true;

        return move;
    }

    /**
     * Door holding current Question to be locked if answered incorrectly.
     */
    private void lockDoor(final Door theDoor) {

    }

    /**
     * Evaluates current game and determines whether it is won or lost.
     */
    public boolean isGameLost() {

        final boolean game = true;

        return game;
    }

    @Override
    public void newGame() {
        // Calculate the initial position for the Character to be in the middle of the screen.
        // since it is represented within the top left corner of a pixel, you have to subtract
        // the tile size.
        final int startX = (MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE) / 2;
        final int startY = (MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE) / 2;

        // Instantiate the Character with the calculated initial position.
        myCharacter = new Character(startX, startY, MazeControls.MY_SCREEN_WIDTH,
                MazeControls.MY_SCREEN_HEIGHT);

        myCorrectAnswers = 0;
        createMaze();
        // Print the door status
        printMazeDoorStatus();
        myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);
        myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);

    }

    @Override
    public void moveDown() {

        if (canMove()) {
            myCharacter.moveDown();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE,
                    null, myCharacter);
        }

    }

    @Override
    public void moveUp() {

        if (canMove()) {
            myCharacter.moveUp();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);
        }

    }

    @Override
    public void moveLeft() {

        if (canMove()) {
            myCharacter.moveLeft();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE,
                    null, myCharacter);
        }

    }

    @Override
    public void moveRight() {

        if (canMove()) {
            myCharacter.moveRight();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE,
                    null, myCharacter);
        }

    }

    /**
     * Prints the door status of each room in the maze.
     */
    public void printMazeDoorStatus() {
        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                System.out.println("Door status for Room [" + i + "][" + j + "]:");
                System.out.println("Left Door: " + (myRooms[i][j].getLeftDoor() != null ? "Exists" : "Not Exists"));
                System.out.println("Right Door: " + (myRooms[i][j].getRightDoor() != null ? "Exists" : "Not Exists"));
                System.out.println("Top Door: " + (myRooms[i][j].getTopDoor() != null ? "Exists" : "Not Exists"));
                System.out.println("Bottom Door: " + (myRooms[i][j].getBottomDoor() != null ? "Exists" : "Not Exists"));
                System.out.println("------------------------");
            }
        }
    }
    @Override
    public void pauseGame() {

    }
    /**
     * adds an object as a listener to the propertyChangeSupport object.
     * @param theListener The PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * adds an object as a listener to the propertyChangeSupport object.
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    /**
     * removes an object as a listener to the propertyChangeSupport object.
     * @param theListener The PropertyChangeListener to be removed
     */
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    /**
     * removes an object as a listener to the propertyChangeSupport object.
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }
}
