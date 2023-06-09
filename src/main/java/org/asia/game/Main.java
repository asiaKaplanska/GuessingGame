package org.asia.game;

import org.asia.game.result.GameResultRepositoryFactory;

public class Main {

    public static void main(String[] args) {

        var repoFactory = new GameResultRepositoryFactory(GameResultRepositoryFactory.Destination.IN_MEMORY);
        //ToDo: add args and add new class with main config
        GameLoop gameLoop = new GameLoop(repoFactory.newRepository());
        boolean playingGame = true;
        gameLoop.playIntro();

        while (playingGame) {
            playingGame = gameLoop.playGame();
        }
    }
}
