/*
 * TriviaMaze
 * Fall 2023
 */
package model;

import controller.Question;
import java.io.Serializable;

/**
 * Class will contain information of current Door that the character is at.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class Door implements Serializable {

    /**
     * Whether Door is locked or not.
     */
    private boolean myLockedStatus;


    /**
     * Whether the question of the door has been correctly answered or has.
     */
    private boolean myQuestionHasBeenAnsweredCorrectly;

    /**
     * Whether the question  has been not prompted or has.
     */
    private boolean myQuestionHasNotBeenPrompted;

    /**
     * Question associated with current Door.
     */
    private Question myQuestion;


    /**
     * Constructor for the door.
     *
     */
    public Door() {
        myQuestionHasNotBeenPrompted = true;
        myQuestionHasBeenAnsweredCorrectly = false;

    }

    /**
     * returns the question of the door.
     * @return a question object.
     */
    public Question getMyQuestion() {

        return myQuestion;
    }

    /**
     * Takes a Question from the database to assign to this door(we set it like this).
     * Otherwise, assigns a question based on the parameter.
     * @param theQuestion the question object you are setting for the door.
     */
    public void setQuestion(final Question theQuestion) {
        myQuestion = theQuestion;
    }



    /**
     * Returns a boolean if the door's Question has not been prompted or has.
     * @return true if the door's question has not been prompted, false if it
     * has been prompted.
     */
    public boolean hasMyQuestionBeenNotPrompted() {
        return myQuestionHasNotBeenPrompted;
    }

    /**
     * Sets the status if the door's question has not been prompted or has.
     * @param theStatus if true, it will set door's myQuestionHasNotBeenPrompted status
     *                  to true, false will set myQuestionHasNotBeenPrompted status to false.
     */
    public void setMyQuestionNotPromptedStatus(final boolean theStatus) {
        myQuestionHasNotBeenPrompted = theStatus;
    }


    /**
     *  Returns if the door's questions has been answered correclty or not.
     * @return  true if the question is answered correctly, false otherwise
     */
    public boolean hasMyQuestionBeenAnsweredCorrectly() {
        return myQuestionHasBeenAnsweredCorrectly;
    }

    /**
     * Sets the question status if the door has been answered correctly or not.
     * @param theStatus of the question to be set to.
     */
    public void setMyQuestionHasBeenAnsweredCorrectlyStatus(final boolean theStatus) {
        myQuestionHasBeenAnsweredCorrectly = theStatus;
    }


    /**
     * Locks Door if associated Question is answered incorrectly.
     */
    public void lock() {
        myLockedStatus = true;
    }





    /**
     * Returns door's locked status.
     * @return myLockedStatus if Door is locked or not.
     */
    public boolean isLocked() {
        return myLockedStatus;
    }

}
