package org.asia.game;

import org.asia.game.result.GameResultRepositoryFactory;
import org.asia.game.ui.ConsoleGameUIFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.asia.game.Config.LANGUAGE_KEY;
import static org.asia.game.Config.REPOSITORY_DESTINATION_KEY;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var repoFactory = new GameResultRepositoryFactory(loadDestinationFromArgs(args));
        var uiFactory = new ConsoleGameUIFactory(loadLanguageFromArgs(args));
        var gameUI = uiFactory.newGameUI();
        var inputSystem = new InputSystem(gameUI);
        GameLoop gameLoop = new GameLoop(repoFactory.newRepository(), gameUI, inputSystem);
        boolean playingGame = true;
        log.info("Starting game with args = {}", Arrays.stream(args).toList());
        gameLoop.playIntro();

        while (playingGame) {
            playingGame = gameLoop.playGame();
        }
    }

    private static GameResultRepositoryFactory.Destination loadDestinationFromArgs(String[] args) {
        for (String arg : args) {
            if (arg.contains(REPOSITORY_DESTINATION_KEY)) {
                var value= arg.substring(REPOSITORY_DESTINATION_KEY.length() + 1);
                try {
                    return GameResultRepositoryFactory.Destination.valueOf(value);
                } catch (IllegalArgumentException illegalArgumentException) {
                    log.warn("Invalid arguments passed. Default value is 'IN_MEMORY'");
                }
            }
        }
        return GameResultRepositoryFactory.Destination.IN_MEMORY;
    }

    private static ConsoleGameUIFactory.Language loadLanguageFromArgs(String[] args) {
        for (String arg : args) {
            if (arg.contains(LANGUAGE_KEY)) {
                var value= arg.substring(LANGUAGE_KEY.length() + 1);
                try {
                    return ConsoleGameUIFactory.Language.valueOf(value);
                } catch (IllegalArgumentException illegalArgumentException) {
                    log.warn("Invalid arguments passed. Default value is 'EN'");
                }
            }
        }
        return ConsoleGameUIFactory.Language.EN;
    }
}
