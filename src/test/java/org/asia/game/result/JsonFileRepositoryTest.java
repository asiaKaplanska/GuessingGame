package org.asia.game.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.Files.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonFileRepositoryTest {

    @ParameterizedTest
    @DisplayName("Should correctly add new game result to new list of results [positive]")
    @MethodSource("getGameResultsParameters")
    void saveGameResult_positive_newOnlyOneEntry(GameResult gameResult) throws GameRepositoryProcessingException, IOException {
        //given
        Path tempPath = createTempFile("test", ".json");
        var sut = new JsonFileRepository(tempPath);

        //when
        sut.saveGameResult(gameResult);

        //then
        assertTrue(sut.getAllGameResults().contains(gameResult));
    }

    private static Stream<Arguments> getGameResultsParameters() {
        return Stream.of(
                Arguments.of(new GameResult("Player", 0, LocalDateTime.now())),
                Arguments.of(new GameResult("Asia", 50, LocalDateTime.now())),
                Arguments.of(new GameResult("abc_1", 5000, LocalDateTime.now())),
                Arguments.of(new GameResult("QWERTY", -2, LocalDateTime.now())));
    }

    @Test
    @DisplayName("Should correctly add new game results to existing list of results [positive]")
    void saveGameResult_positive_existingEntries() throws GameRepositoryProcessingException, IOException {
        //given
        Path tempPath = createTempFile("test", ".json");
        var sut = new JsonFileRepository(tempPath);
        var gameResult = new GameResult("Player", 0, LocalDateTime.now());
        var secondGameResult = new GameResult("abc_1", 5000, LocalDateTime.now());
        var thirdGameResult = new GameResult("QWERTY", -2, LocalDateTime.now());

        List<GameResult> gameResultList = new ArrayList<>();
        gameResultList.add(gameResult);
        gameResultList.add(secondGameResult);
        gameResultList.add(thirdGameResult);

        //when
        sut.saveGameResult(gameResult);
        sut.saveGameResult(secondGameResult);
        sut.saveGameResult(thirdGameResult);

        //then
        assertTrue(sut.getAllGameResults().containsAll(gameResultList));
    }

    @Test
    @DisplayName("Should correctly throw GameRepositoryProcessingException due to null path set [negative]")
    void saveGameResult_negative_nullPath() {
        assertThrows(
                GameRepositoryProcessingException.class,
                () ->  new JsonFileRepository(null));
    }

    @Test
    @DisplayName("Should correctly throw GameRepositoryProcessingException due to invalid path set [negative]")
    void saveGameResult_negative_invalidPath() throws GameRepositoryProcessingException, IOException {
        //given
        var gameResult = new GameResult("Player", 123, LocalDateTime.now());
        Path tempPath = createTempDirectory("test");
        var sut = new JsonFileRepository(tempPath);

        //then
        assertThrows(
                GameRepositoryProcessingException.class,
                () -> sut.saveGameResult(gameResult));
    }
}