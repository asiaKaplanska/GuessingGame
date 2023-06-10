package org.asia.game.result;

import java.util.List;

public interface GameResultRepository {

    void saveGameResult(GameResult gameResult) throws GameRepositoryProcessingException;

    List<GameResult> getAllGameResults() throws GameRepositoryProcessingException;
}
