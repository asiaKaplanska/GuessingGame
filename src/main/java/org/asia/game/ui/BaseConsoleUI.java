package org.asia.game.ui;

import org.asia.game.GameState;
import org.asia.game.result.GameResult;

import java.util.List;

public class BaseConsoleUI implements GameUI {

    private final ConsoleTexts consoleTexts;

    public BaseConsoleUI(ConsoleTexts consoleTexts) {
        this.consoleTexts = consoleTexts;
    }

    @Override
    public void printInsertInputMessage() {
        System.out.print(consoleTexts.getInsertUserInput());
    }
    @Override
    public void printGameDescriptionMessage() {
        System.out.println(consoleTexts.getGameDescription());
    }
    @Override
    public void printInsertUserNameMessage() {
        System.out.println(consoleTexts.getInsertUserName());
    }
    @Override
    public void printDrawnNumber(int number) {
        System.out.println(consoleTexts.getDrawnNumber().formatted(number));
    }
    @Override
    public void printCollectedPointsMessage(String userName, int points) {
        System.out.print(consoleTexts.getPoints().formatted(userName, points));
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
        System.out.println(consoleTexts.getGreetingUser().formatted(gameState.getUserName()));
    }
    @Override
    public void printRoundMessage(int round) {
        System.out.print(consoleTexts.getRound() + round);
    }
    @Override
    public void printOnlyNumbersMessage() {
        System.out.println(consoleTexts.getInputNumbers());
    }
    @Override
    public void printPlayAgainMessage() {
        System.out.println(consoleTexts.getPlayAgain());
    }
    @Override
    public void printResponseWarningExceptionMessage() {
        System.out.println(consoleTexts.getInputPlayGameResponse());
    }
    @Override
    public void printPreviousGameResults() {
        System.out.print(consoleTexts.getPreviousResults());
    }
    @Override
    public void printLetsStart() {
        System.out.println(consoleTexts.getStartGame());
    }
    @Override
    public void printGameResultJsonFile(List<GameResult> gameResults) {
        for (var result : gameResults)
        {
            System.out.println(result.toString());
        }
    }
}
