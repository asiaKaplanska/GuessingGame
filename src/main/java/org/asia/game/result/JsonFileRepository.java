package org.asia.game.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

class JsonFileRepository implements GameResultRepository {

    private final Path repositoryFilePath;

    JsonFileRepository(Path repositoryFilePath) {
        this.repositoryFilePath = repositoryFilePath;
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

        try {
            String jsonFile = loadResultsFile();

            var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            List<GameResult> deserializedFile = mapper.readValue(jsonFile, new TypeReference<>() {
            });
            System.out.println(deserializedFile.toString());
            return deserializedFile;
        } catch (JsonProcessingException | GameRepositoryProcessingException exception) {
            throw new GameRepositoryProcessingException("Json deserialization failed", exception);
        }
    }

    private String loadResultsFile() throws GameRepositoryProcessingException {

        try {
            InputStream inputStream = new FileInputStream(repositoryFilePath.toFile());
            var inputStreamReader = new InputStreamReader(inputStream);
            var bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
            return bufferedReader.lines().collect(Collectors.joining());
        } catch (Exception e) {
            throw new GameRepositoryProcessingException("Loading Json file failed", e);
        }
    }

    private void saveGameResultToFile(String gameResult) throws GameRepositoryProcessingException {

        try {
            checkOrCreateDirectory();
            checkOrCreateFile();
            OutputStreamWriter outputStreamWriter;
            FileOutputStream fileOutputStream =
                    new FileOutputStream(repositoryFilePath.toFile(), true);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("\n");
            outputStreamWriter.write(gameResult);
            outputStreamWriter.close();
        } catch (Exception e) {
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
