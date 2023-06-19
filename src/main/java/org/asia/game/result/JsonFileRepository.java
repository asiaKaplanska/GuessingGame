package org.asia.game.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.*;

class JsonFileRepository implements GameResultRepository {

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final Path repositoryFilePath;

    JsonFileRepository(Path repositoryFilePath) throws GameRepositoryProcessingException {
        if (repositoryFilePath != null) {
            this.repositoryFilePath = repositoryFilePath;
        } else throw new GameRepositoryProcessingException(
                "Repository file paths cannot be null", new IllegalArgumentException());
    }

    @Override
    public void saveGameResult(GameResult gameResult) throws GameRepositoryProcessingException {
        saveGameResultToFile(convertToJson(gameResult));
    }

    @Override
    public List<GameResult> getAllGameResults() throws GameRepositoryProcessingException {
        return convertFromJson(loadResultsFile());
    }

    private List<GameResult> convertFromJson(List<String> json) throws GameRepositoryProcessingException {
        var gameResults = new ArrayList<GameResult>();
        try {
            for (var r : json) {
                gameResults.add(MAPPER.readValue(r, GameResult.class));
            }
        } catch (JsonProcessingException exception) {
            throw new GameRepositoryProcessingException("Converting from json failed", exception);
        }
        return gameResults;
    }

    private String convertToJson(GameResult gameResult) throws GameRepositoryProcessingException {
        try {
            return MAPPER.writeValueAsString(gameResult);
        } catch (JsonProcessingException e) {
            throw new GameRepositoryProcessingException("Converting to json failed", e);
        }
    }

    private List<String> loadResultsFile() throws GameRepositoryProcessingException {
        try {
            return readAllLines(repositoryFilePath, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new GameRepositoryProcessingException("Loading Json file failed", e);
        }
    }

    private void saveGameResultToFile(String gameResult) throws GameRepositoryProcessingException {
        checkOrCreateDirectory();
        checkOrCreateFile();

        byte[] bytes = gameResult.getBytes(StandardCharsets.UTF_8);
        try (var outputStream = new FileOutputStream(repositoryFilePath.toFile(), true)) {
            outputStream.write(bytes);
            outputStream.write(LINE_SEPARATOR.getBytes());
        } catch (IOException e) {
            throw new GameRepositoryProcessingException("Saving Json file failed", e);
        }
    }

    private void checkOrCreateDirectory() throws GameRepositoryProcessingException {
        var parentPath = repositoryFilePath.getParent();

        if (!exists(parentPath)) {
            try {
                createDirectories(parentPath);
            } catch (IOException e) {
                throw new GameRepositoryProcessingException("Creating new directory failed", e);
            }
        }
    }

    private void checkOrCreateFile() throws GameRepositoryProcessingException {
        if (!exists(repositoryFilePath)) {
            try {
                createFile(repositoryFilePath);
            } catch (IOException e) {
                throw new GameRepositoryProcessingException("Creating new file failed", e);
            }
        }
    }
}
