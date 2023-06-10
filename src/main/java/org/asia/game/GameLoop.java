package org.asia.game;

import org.asia.game.result.GameRepositoryProcessingException;
import org.asia.game.result.GameResult;
import org.asia.game.result.GameResultRepository;

import java.time.LocalDateTime;
import java.util.Objects;

public class GameLoop {

    private GameState gameState;
    private GameUI gameUI = new GameUI();
    private InputSystem inputSystem = InputSystem.getInstance();
    private NumberGenerator numberGenerator = new NumberGenerator();
    private ScoreSystem scoreSystem = new ScoreSystem();
    private final GameResultRepository gameResultRepository;

    public GameLoop(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
    }

    public void playIntro() {

        gameState = new GameState();
        gameUI.printGameDescriptionMessage();
        gameUI.printDottedLine();
        gameUI.printEmptyRow();

        gameUI.printInsertUserNameMessage();
        gameState.setUserName(inputSystem.getUserName());
        gameUI.printGreetingUserMessage(gameState);
        gameUI.printLetsStart();
    }

    private void printAllPreviousResults() {

        try {
            var listOfPreviousResults = gameResultRepository.getAllGameResults();
            gameUI.printGameResultJsonFile(listOfPreviousResults);
        } catch (GameRepositoryProcessingException exception) {
            gameUI.printListNotExist();
        }
    }

    public boolean playGame() {

        playGameRound();
        playSummary();
        return shouldPlayAgain();
    }

    private void playGameRound() {

        for (int i = 0; i < Config.GAMES_ITERATION; i++) {

            gameUI.printRoundMessage(i + 1);
            gameUI.printInsertInputMessage();
            var userInput = inputSystem.getUserIntegerInput();
            var generatedNumber = numberGenerator.drawRandomNumber();
            gameUI.printDrawnNumber(generatedNumber);
            gameUI.printEmptyRow();

            scoreSystem.addBasicScore(userInput, generatedNumber, gameState);
            scoreSystem.addBonusScore(gameState);
        }
    }

    private void playSummary() {

        gameUI.printDottedLine();
        gameUI.printEmptyRow();

        gameUI.printCollectedPointsMessage(gameState.getUserName(), gameState.getUserScore());
        gameUI.printEmptyRow();
        GameResult gameResult = new GameResult(gameState.getUserName(), gameState.getUserScore(), LocalDateTime.now());
        saveResult(gameResult);

        gameUI.printEmptyRow();

        gameUI.printPreviousGameResults();
        printAllPreviousResults();
        gameUI.printEmptyRow();
    }

    private boolean shouldPlayAgain() {

        gameUI.printPlayAgainMessage();
        var userInput = inputSystem.getUserPlayingDecision();
        return Objects.equals(userInput, Config.YES_RESPONSE);
    }

    private void saveResult(GameResult gameResult) {

        try {
            gameResultRepository.saveGameResult(gameResult);
        } catch (GameRepositoryProcessingException e) {
            gameUI.printSavingFailed();
        }

    }
}
