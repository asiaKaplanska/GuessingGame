package org.asia.game.ui;

import org.asia.game.GameState;
import org.asia.game.result.GameResult;

import java.util.List;

public interface GameUI {

    void printInsertInputMessage();
    void printGameDescriptionMessage();
    void printInsertUserNameMessage();
    void printDrawnNumber(int number);
    void printCollectedPointsMessage(String userName, int points);
    void printDottedLine();
    void printEmptyRow();
    void printGreetingUserMessage(GameState gameState);
    void printRoundMessage(int round);
    void printOnlyNumbersMessage();
    void printPlayAgainMessage();
    void printResponseWarningExceptionMessage();
    void printPreviousGameResults();
    void printLetsStart();
    void printGameResultJsonFile(List<GameResult> gameResults);
}
