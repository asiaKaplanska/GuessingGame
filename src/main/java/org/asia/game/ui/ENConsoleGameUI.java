package org.asia.game.ui;

import org.asia.game.Config;
import org.asia.game.GameState;
import org.asia.game.result.GameResult;

import java.util.List;

class ENConsoleGameUI implements GameUI {

    private static final String INSERT_USER_INPUT = ", enter the number of eggs >>> ";
    private static final String GAME_DESCRIPTION = """
                        
            Welcome to the game!
                        
            Here are the rules:
            The game generates a random number of eggs laid by the hen (from 1 to %s) that you have to guess.
            You get points if you correctly guess the number of eggs laid (5 points for each correct answer).
            In addition, 2 wins in a row give you an additional 10 points, 3 wins in a row give you 100 points
            and 4 correct answers give you 1000 extra points!
                        
            Good luck :)
            """.formatted(Config.MAX_VALUE_DRAWING);
    private static final String INSERT_USER_NAME = "What's Your name? ";
    private static final String DRAWN_NUMBER = "The hen has laid %s eggs this round";
    private static final String POINTS = "Congratulations %s, you have scored %s points in this game!";
    private static final String GREETING_USER =
            "Hello %s! Guess how many eggs the hen has laid?";
    private static final String ROUND = "Round ";
    private static final String INPUT_NUMBERS = "You can only enter numbers!";
    private static final String PLAY_AGAIN = "Want to play again? If YES choose 'y' or 'n' if NO";
    private static final String INPUT_PLAY_GAME_RESPONSE = "You can only enter 'y' or 'n'!";
    private static final String PREVIOUS_RESULTS = "Here are all the results of previous games: ";
    private static final String START_GAME = "Let's start!";

    @Override
    public void printInsertInputMessage() {
        System.out.print(INSERT_USER_INPUT);
    }
    @Override
    public void printGameDescriptionMessage() {
        System.out.println(GAME_DESCRIPTION);
    }
    @Override
    public void printInsertUserNameMessage() {
        System.out.println(INSERT_USER_NAME);
    }
    @Override
    public void printDrawnNumber(int number) {
        System.out.println(DRAWN_NUMBER.formatted(number));
    }
    @Override
    public void printCollectedPointsMessage(String userName, int points) {
        System.out.print(POINTS.formatted(userName, points));
    }
    @Override
    public void printDottedLine() {
        System.out.println("* ".repeat(50));
    }
    @Override
    public void printEmptyRow() {
        System.out.println();
    }
    @Override
    public void printGreetingUserMessage(GameState gameState) {
        System.out.println(GREETING_USER.formatted(gameState.getUserName()));
    }
    @Override
    public void printRoundMessage(int round) {
        System.out.print(ROUND + round);
    }
    @Override
    public void printOnlyNumbersMessage() {
        System.out.println(INPUT_NUMBERS);
    }
    @Override
    public void printPlayAgainMessage() {
        System.out.println(PLAY_AGAIN);
    }
    @Override
    public void printResponseWarningExceptionMessage() {
        System.out.println(INPUT_PLAY_GAME_RESPONSE);
    }
    @Override
    public void printPreviousGameResults() {
        System.out.print(PREVIOUS_RESULTS);
    }
    @Override
    public void printLetsStart() {
        System.out.println(START_GAME);
    }
    @Override
    public void printGameResultJsonFile(List<GameResult> gameResults) {
        for (var result : gameResults)
        {
            System.out.println(result.toString());
        }
    }
}
