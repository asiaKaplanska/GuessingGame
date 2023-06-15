package org.asia.game.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonFileRepositoryTest {

JsonFileRepository jsonFileRepository;

    @ParameterizedTest
    @DisplayName("Should correctly add new game result to list of results [positive]")
    @MethodSource("getGameResultsParameters")
    void saveGameResult(GameResult gameResult) throws GameRepositoryProcessingException, IOException {

        //given
        Path tempPath = Files.createTempFile("test", ".json");
        jsonFileRepository = new JsonFileRepository(tempPath);

        //when
        jsonFileRepository.saveGameResult(gameResult);

        //then
        assertTrue(jsonFileRepository.getAllGameResults().contains(gameResult));
    }

    private static Stream<Arguments> getGameResultsParameters() {

        return Stream.of(
                Arguments.of(new GameResult("Player", 0, LocalDateTime.now())),
                Arguments.of(new GameResult("Asia", 50, LocalDateTime.now())),
                Arguments.of(new GameResult("abc_1", 5000, LocalDateTime.now())),
                Arguments.of(new GameResult("QWERTY", -2, LocalDateTime.now())));
    }

    @Test
    @DisplayName("Should correctly throw GameRepositoryProcessingException due to null path set [negative]")
    void saveGameResult_negative_nullPath() {

        assertThrows(
                GameRepositoryProcessingException.class,
                () -> jsonFileRepository = new JsonFileRepository(null));
    }

    @Test
    @DisplayName("Should correctly throw GameRepositoryProcessingException due to invalid path set [negative]")
    void saveGameResult_negative_invalidPath() throws GameRepositoryProcessingException, IOException {

        //given
        var gameResult = new GameResult("Player", 123, LocalDateTime.now());
        Path tempPath = Files.createTempDirectory("test");
        jsonFileRepository = new JsonFileRepository(tempPath);

        //then
        assertThrows(
                GameRepositoryProcessingException.class,
                () -> jsonFileRepository.saveGameResult(gameResult));
    }
}