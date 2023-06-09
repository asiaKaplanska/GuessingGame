package org.asia.game;

public class ScoreSystem {

    public void addBasicScore(int userNumber, int drawNumber, GameState gameState) {

        if (userNumber == drawNumber) {
            gameState.addToUserScore(Config.BASIC_WIN_POINTS);
            gameState.increaseWinnings();
        } else {
            gameState.setWinnings(0);
        }
    }

    public void addBonusScore(GameState gameState) {

        switch (gameState.getWinnings()) {
            case Config.DOUBLE_WINS -> gameState.addToUserScore(Config.TWO_WINS_BONUS);
            case Config.TRIPLE_WINS -> gameState.addToUserScore(Config.THREE_WINS_BONUS);
            case Config.QUADRUPLE_WINS -> gameState.addToUserScore(Config.FOUR_WINS_BONUS);
            default -> {}
        }
    }
}
