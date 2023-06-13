package org.asia.game.result;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

class InMemoryRepositoryTest {

    InMemoryRepository inMemoryRepository = new InMemoryRepository();

    @ParameterizedTest
    @DisplayName("Should correctly add new game result to list of results [positive]")
    @MethodSource("getGameResultsParameters")
    void saveGameResult(GameResult gameResult) {

        //when
        inMemoryRepository.saveGameResult(gameResult);

        //then
        Assertions.assertTrue(inMemoryRepository.getAllGameResults().contains(gameResult));
    }

    private static Stream<Arguments> getGameResultsParameters() {

        return Stream.of(
                Arguments.of(new GameResult("Player", 0, LocalDateTime.now())),
                Arguments.of(new GameResult("Asia", 50, LocalDateTime.now())),
                Arguments.of(new GameResult("abc_1", 5000, LocalDateTime.now())),
                Arguments.of(new GameResult("QWERTY", -2, LocalDateTime.now())));
    }
}