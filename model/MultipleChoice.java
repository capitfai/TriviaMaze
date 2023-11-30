package model;

/**
 * Multiple Choice is a subclass of Question object that will be used to display
 * through doors in maze.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class MultipleChoice extends AbstractQuestion {

    /**
     * Question's text.
     */
    private static String myQuestion;

    /**
     * Text of correct answer to this Question.
     */
    private static String myAnswer;

    /**
     * Text of incorrect second option.
     */
    private static String mySecondOption;

    /**
     * Text of incorrect third option.
     */
    private static String myThirdOption;

    /**
     * Text of incorrect fourth option.
     */
    private static String myFourthOption;

    /**
     * Multiple choice constructor calls super to Question and takes parameters based
     * on the information pertaining to this specific type of question.
     * @param theQuestion String text of the question to be displayed.
     * @param theAnswer String text of the answer to be displayed.
     * @param theSecondOption String text of the second, incorrect option to be displayed.
     * @param theThirdOption String text of the third, incorrect option to be displayed.
     * @param theFourthOption String text of the fourth, incorrect option to be displayed.
     */
    public MultipleChoice(final String theQuestion, final String theAnswer,
                          final String theSecondOption, final String theThirdOption,
                          final String theFourthOption) {
        super();

        myQuestion = theQuestion;
        myAnswer = theAnswer;
        mySecondOption = theSecondOption;
        myThirdOption = theThirdOption;
        myFourthOption = theFourthOption;
    }

    /**
     * Returns the text of the question that will be displayed in view.
     * @return String question text.
     */
    public String getQuestionText() {
        return myQuestion;
    }

    /**
     * Returns the correct answer for the question that will be displayed in view.
     * @return String correct answer text.
     */
    public String getAnswerText() {
        return myAnswer;
    }

    /**
     * Returns an incorrect option used for multiple choice in view.
     * @return String second incorrect option.
     */
    public String getSecondOption() {
        return mySecondOption;
    }

    /**
     * Returns an incorrect option used for multiple choice in view.
     * @return String third incorrect option.
     */
    public String getThirdOption() {
        return myThirdOption;
    }


    /**
     * Returns an incorrect option used for multiple choice in view.
     * @return String fourth incorrect option.
     */
    public String getFourthOption() {
        return myFourthOption;
    }

}
