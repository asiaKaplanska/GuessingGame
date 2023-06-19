package org.asia.game.result;

import java.time.LocalDateTime;

public record GameResult(String playerName, int playerScore, LocalDateTime dateOfGame) {

}
