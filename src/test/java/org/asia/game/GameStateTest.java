package org.asia.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameStateTest {

    GameState gameState = new GameState();

    @ParameterizedTest
    @DisplayName("Should correctly add points to user score [positive]")
    @MethodSource("getInitialAndAdditionalScore")
    void addToUserScore(int initialPoints, int additionalPoints, int score) {

        //when
        gameState.setUserScore(initialPoints);
        gameState.addToUserScore(additionalPoints);

        //then
        assertEquals(score, gameState.getUserScore());
    }

    private static Stream<Arguments> getInitialAndAdditionalScore() {

        return Stream.of(
                Arguments.of(0, Config.BASIC_WIN_POINTS, Config.BASIC_WIN_POINTS),
                Arguments.of(10, Config.BASIC_WIN_POINTS, (10 + Config.BASIC_WIN_POINTS)),
                Arguments.of(0, Config.TWO_WINS_BONUS, Config.TWO_WINS_BONUS),
                Arguments.of(5, Config.THREE_WINS_BONUS, (5 + Config.THREE_WINS_BONUS)),
                Arguments.of(-2, Config.FOUR_WINS_BONUS, (Config.FOUR_WINS_BONUS - 2)),
                Arguments.of(0, -1234458, -1234458));
    }
}