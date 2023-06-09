package org.asia.game;

public class Main {

    public static void main(String[] args) {

        GameLoop gameLoop = new GameLoop();
        boolean playingGame = true;
        gameLoop.playIntro();

        while (playingGame) {
            playingGame = gameLoop.playGame();
        }
    }
}
