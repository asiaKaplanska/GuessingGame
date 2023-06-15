package org.asia.game.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class JsonFileRepository implements GameResultRepository {

    private final Path repositoryFilePath;

    JsonFileRepository(Path repositoryFilePath) throws GameRepositoryProcessingException {
        if (repositoryFilePath != null) {
            this.repositoryFilePath = repositoryFilePath;
        } else throw new GameRepositoryProcessingException(
                "Repository file paths cannot be null", new IllegalArgumentException());
    }

    @Override
    public void saveGameResult(GameResult gameResult) throws GameRepositoryProcessingException {

        var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            var json = mapper.writeValueAsString(gameResult);
            System.out.println(json);
            saveGameResultToFile(json);
        } catch (JsonProcessingException e) {
            throw new GameRepositoryProcessingException("Json serialization failed", e);
        }
    }

    @Override
    public List<GameResult> getAllGameResults() throws GameRepositoryProcessingException {

        var gameResults = new ArrayList<GameResult>();
        try {
            var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            var records = loadResultsFile().split(System.lineSeparator());

            for (var r : records) {
                gameResults.add(mapper.readValue(r, GameResult.class));
            }
        } catch (JsonProcessingException exception) {
            throw new GameRepositoryProcessingException("Json deserialization failed", exception);
        }
        return gameResults;
    }

    private String loadResultsFile() throws GameRepositoryProcessingException {

        try (var reader = Files.newBufferedReader(repositoryFilePath)) {
            return readAllLines(reader);
        } catch (Exception e) {
            throw new GameRepositoryProcessingException("Loading Json file failed", e);
        }
    }

    private String readAllLines(BufferedReader bufferedReader) throws IOException {

        var content = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }

    private void saveGameResultToFile(String gameResult) throws GameRepositoryProcessingException {

        checkOrCreateDirectory();
        checkOrCreateFile();

        byte[] bytes = gameResult.getBytes();
        try (var outputStream = new FileOutputStream(repositoryFilePath.toFile(), true)) {
            outputStream.write(bytes);
            outputStream.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            throw new GameRepositoryProcessingException("Saving Json file failed", e);
        }
    }

    private void checkOrCreateDirectory() throws GameRepositoryProcessingException {

        var parentPath = repositoryFilePath.getParent();

        if (!Files.exists(parentPath)) {
            try {
                Files.createDirectory(parentPath);
            } catch (IOException e) {
                throw new GameRepositoryProcessingException("Creating new directory failed", e);
            }
        }
    }

    private void checkOrCreateFile() throws GameRepositoryProcessingException {

        if (!Files.exists(repositoryFilePath)) {
            try {
                Files.createFile(repositoryFilePath);
            } catch (IOException e) {
                throw new GameRepositoryProcessingException("Creating new file failed", e);
            }
        }
    }
}
