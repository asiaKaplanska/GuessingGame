package org.asia.game.result;

import java.util.ArrayList;
import java.util.List;

class InMemoryRepository implements GameResultRepository{

    private final List<GameResult> results = new ArrayList<>();
    @Override
    public void saveGameResult(GameResult gameResult) {
        //ToDo: implement me
    }

    @Override
    public List<GameResult> getAllGameResults() {
        //ToDo: implement me
        return null;
    }
}
