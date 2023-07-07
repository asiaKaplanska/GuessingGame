package org.asia.game;

import org.asia.game.result.GameRepositoryProcessingException;
import org.asia.game.result.GameResult;
import org.asia.game.result.GameResultRepository;
import org.asia.game.ui.GameUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Objects;

public class GameLoop {

    private GameState gameState;
    private final GameUI gameUI;
    private InputSystem inputSystem;
    private NumberGenerator numberGenerator = new NumberGenerator();
    private ScoreSystem scoreSystem = new ScoreSystem();
    private final GameResultRepository gameResultRepository;
    private static final Logger log = LoggerFactory.getLogger(GameLoop.class);

    public GameLoop(GameResultRepository gameResultRepository, GameUI gameUI, InputSystem inputSystem) {
        this.gameResultRepository = gameResultRepository;
        this.gameUI = gameUI;
        this.inputSystem = inputSystem;
    }

    public void playIntro() {
        gameState = new GameState();
        gameUI.printGameDescriptionMessage();
        gameUI.printDottedLine();
        gameUI.printEmptyRow();

        gameUI.printInsertUserNameMessage();
        gameState.setUserName(inputSystem.getUserName());
        log.info("Username is set to {}", gameState.getUserName());
        gameUI.printGreetingUserMessage(gameState);
        gameUI.printLetsStart();
    }

    private void printAllPreviousResults() {
        try {
            gameUI.printGameResultJsonFile(gameResultRepository.getAllGameResults());
        } catch (GameRepositoryProcessingException exception) {
            log.warn("List of results doesn't exist");
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
            log.info("{}. round user number: {} and generated number: {}", i + 1, userInput, generatedNumber);
            gameUI.printEmptyRow();

            scoreSystem.addBasicScore(userInput, generatedNumber, gameState);
            scoreSystem.addBonusScore(gameState);
            log.info("Current user score: {}", gameState.getUserScore());
        }
    }

    private void playSummary() {
        gameUI.printDottedLine();
        gameUI.printEmptyRow();

        gameUI.printCollectedPointsMessage(gameState.getUserName(), gameState.getUserScore());
        gameUI.printEmptyRow();
        GameResult gameResult = new GameResult(gameState.getUserName(), gameState.getUserScore(), LocalDateTime.now());
        saveResult(gameResult);
        log.info("Player {} has scored a total of {} points", gameResult.playerName(), gameResult.playerScore());

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
            log.warn("Saving failed");
        }
    }
}
