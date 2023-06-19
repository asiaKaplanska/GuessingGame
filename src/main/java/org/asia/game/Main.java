package org.asia.game;

import org.asia.game.result.GameResultRepositoryFactory;

import static org.asia.game.Config.REPOSITORY_DESTINATION_KEY;

public class Main {

    public static void main(String[] args) {

        var repoFactory = new GameResultRepositoryFactory(loadDestinationFromArgs(args));
        GameLoop gameLoop = new GameLoop(repoFactory.newRepository());
        boolean playingGame = true;
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
                    System.out.println("Invalid arguments passed. Default value is 'IN_MEMORY'");
                }
            }
        }

        return GameResultRepositoryFactory.Destination.IN_MEMORY;
    }
}
