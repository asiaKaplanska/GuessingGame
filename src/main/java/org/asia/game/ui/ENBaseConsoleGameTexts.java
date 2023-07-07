package org.asia.game.ui;

import org.asia.game.Config;

class ENBaseConsoleGameTexts extends BaseConsoleTexts {

    private final String insertUserInput = ", enter the number of eggs >>> ";
    private final String gameDescription = """
                        
            Welcome to the game!
                        
            Here are the rules:
            The game generates a random number of eggs laid by the hen (from 1 to %s) that you have to guess.
            You get points if you correctly guess the number of eggs laid (5 points for each correct answer).
            In addition, 2 wins in a row give you an additional 10 points, 3 wins in a row give you 100 points
            and 4 correct answers give you 1000 extra points!
                        
            Good luck :)
            """.formatted(Config.MAX_VALUE_DRAWING);
    private final String insertUserName = "What's Your name? ";
    private final String drawnNumber = "The hen has laid %s eggs this round";
    private final String points = "Congratulations %s, you have scored %s points in this game!";
    private final String greetingUser =
            "Hello %s! Guess how many eggs the hen has laid?";
    private final String round = "Round ";
    private final String inputNumbers = "You can only enter numbers!";
    private final String playAgain = "Want to play again? If YES choose 'y' or 'n' if NO";
    private final String inputPlayGameResponse = "You can only enter 'y' or 'n'!";
    private final String previousResults = "Here are all the results of previous games: ";
    private final String startGame = "Let's start!";

    @Override
    public String getInsertUserInput() {
        return insertUserInput;
    }

    @Override
    public String getGameDescription() {
        return gameDescription;
    }

    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    @Override
    public String getDrawnNumber() {
        return drawnNumber;
    }

    @Override
    public String getPoints() {
        return points;
    }

    @Override
    public String getGreetingUser() {
        return greetingUser;
    }

    @Override
    public String getRound() {
        return round;
    }

    @Override
    public String getInputNumbers() {
        return inputNumbers;
    }

    @Override
    public String getPlayAgain() {
        return playAgain;
    }

    @Override
    public String getInputPlayGameResponse() {
        return inputPlayGameResponse;
    }

    @Override
    public String getPreviousResults() {
        return previousResults;
    }

    @Override
    public String getStartGame() {
        return startGame;
    }
}
