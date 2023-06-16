package org.asia.game.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.*;

class JsonFileRepository implements GameResultRepository {

    private final Path repositoryFilePath;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final String lineSeparator = System.lineSeparator();

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
                gameResults.add(mapper.readValue(r, GameResult.class));
            }
        } catch (JsonProcessingException exception) {
            throw new GameRepositoryProcessingException("Converting from json failed", exception);
        }
        return gameResults;
    }

    private String convertToJson(GameResult gameResult) throws GameRepositoryProcessingException {
        try {
            return mapper.writeValueAsString(gameResult);
        } catch (JsonProcessingException e) {
            throw new GameRepositoryProcessingException("Converting to json failed", e);
        }
    }

    private List<String> loadResultsFile() throws GameRepositoryProcessingException {
        try {
            return Files.readAllLines(repositoryFilePath);
        } catch (Exception e) {
            throw new GameRepositoryProcessingException("Loading Json file failed", e);
        }
    }

    private void saveGameResultToFile(String gameResult) throws GameRepositoryProcessingException {
        checkOrCreateDirectory();
        checkOrCreateFile();

        byte[] bytes = gameResult.getBytes();
        try (var outputStream = new FileOutputStream(repositoryFilePath.toFile(), true)) {
            outputStream.write(bytes);
            outputStream.write(lineSeparator.getBytes());
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
