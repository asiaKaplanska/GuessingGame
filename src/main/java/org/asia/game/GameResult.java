package org.asia.game;

import java.time.LocalDateTime;

public record GameResult(String playerName, int playerScore, LocalDateTime dateOfGame) {

}
