package org.asia.game.ui;

import org.asia.game.GameState;
import org.asia.game.result.GameResult;

import java.util.List;

 class ConsoleUI implements GameUI {

    private final BaseConsoleTexts baseConsoleTexts;

    public ConsoleUI(BaseConsoleTexts baseConsoleTexts) {
        this.baseConsoleTexts = baseConsoleTexts;
    }

    @Override
    public void printInsertInputMessage() {
        System.out.print(baseConsoleTexts.getInsertUserInput());
    }
    @Override
    public void printGameDescriptionMessage() {
        System.out.println(baseConsoleTexts.getGameDescription());
    }
    @Override
    public void printInsertUserNameMessage() {
        System.out.println(baseConsoleTexts.getInsertUserName());
    }
    @Override
    public void printDrawnNumber(int number) {
        System.out.println(baseConsoleTexts.getDrawnNumber().formatted(number));
    }
    @Override
    public void printCollectedPointsMessage(String userName, int points) {
        System.out.print(baseConsoleTexts.getPoints().formatted(userName, points));
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
        System.out.println(baseConsoleTexts.getGreetingUser().formatted(gameState.getUserName()));
    }
    @Override
    public void printRoundMessage(int round) {
        System.out.print(baseConsoleTexts.getRound() + round);
    }
    @Override
    public void printOnlyNumbersMessage() {
        System.out.println(baseConsoleTexts.getInputNumbers());
    }
    @Override
    public void printPlayAgainMessage() {
        System.out.println(baseConsoleTexts.getPlayAgain());
    }
    @Override
    public void printResponseWarningExceptionMessage() {
        System.out.println(baseConsoleTexts.getInputPlayGameResponse());
    }
    @Override
    public void printPreviousGameResults() {
        System.out.print(baseConsoleTexts.getPreviousResults());
    }
    @Override
    public void printLetsStart() {
        System.out.println(baseConsoleTexts.getStartGame());
    }
    @Override
    public void printGameResultJsonFile(List<GameResult> gameResults) {
        for (var result : gameResults)
        {
            System.out.println(result.toString());
        }
    }
}
