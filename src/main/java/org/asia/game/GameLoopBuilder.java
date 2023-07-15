package org.asia.game;

import org.asia.game.result.GameResultRepository;
import org.asia.game.ui.GameUI;

import java.util.Objects;
import java.util.stream.Stream;

public class GameLoopBuilder {

    private GameUI gameUI;
    private InputSystem inputSystem;
    private NumberGenerator numberGenerator;
    private ScoreSystem scoreSystem;
    private GameResultRepository gameResultRepository;

    public GameLoopBuilder setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
        return this;
    }

    public GameLoopBuilder setInputSystem(InputSystem inputSystem) {
        this.inputSystem = inputSystem;
        return this;
    }

    public GameLoopBuilder setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        return this;
    }

    public GameLoopBuilder setScoreSystem(ScoreSystem scoreSystem) {
        this.scoreSystem = scoreSystem;
        return this;
    }

    public GameLoopBuilder setGameResultRepository(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
        return this;
    }

    public GameLoop build() {
        if (Stream.of(gameResultRepository, gameUI, inputSystem, numberGenerator, scoreSystem).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("All GameLoop parameters have to be initialized");
        }
            return new GameLoop(gameResultRepository, gameUI, inputSystem, numberGenerator, scoreSystem);
    }
}
