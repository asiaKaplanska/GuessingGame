package org.asia.game.result;

import org.asia.game.GameUI;

import java.util.ArrayList;
import java.util.List;

class InMemoryRepository implements GameResultRepository{

    GameUI gameUI = new GameUI();
    private final List<GameResult> results = new ArrayList<>();

    @Override
    public void saveGameResult(GameResult gameResult) {

        results.add(gameResult);
    }

    @Override
    public List<GameResult> getAllGameResults() {

        for (GameResult element : results) {
            gameUI.printGameResultInMemory(element.playerName(), element.playerScore(), element.dateOfGame());
        }
        return results;
    }
}
