package org.asia.game.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.asia.game.GameUI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.asia.game.Config.DIRECTORY_PATH;
import static org.asia.game.Config.FILE_PATH;

class JsonFileRepository implements GameResultRepository {

    GameUI gameUI = new GameUI();

    @Override
    public void saveGameResult(GameResult gameResult) {

        var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            var json = mapper.writeValueAsString(gameResult);
            gameUI.printSerialisedJson(json);
            saveGameResultToFile(json);
        } catch (JsonProcessingException ignored) {}
    }

    @Override
    public List<GameResult> getAllGameResults() {

        try {
        String jsonFile = loadResultsFile();

        var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<GameResult> deserializedFile = mapper.readValue(jsonFile, new TypeReference<>() {});
        gameUI.printDeserializedFile(deserializedFile.toString());
        return deserializedFile;
        } catch (FileNotFoundException | JsonProcessingException exception) {
            return new ArrayList<>();
        }
    }

    private String loadResultsFile() throws FileNotFoundException {

        InputStream inputStream = new FileInputStream(FILE_PATH);
        var inputStreamReader = new InputStreamReader(inputStream);
        var bufferedReader = new BufferedReader(inputStreamReader);

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();}
        } catch (Exception ignored) {}

        return bufferedReader.lines().collect(Collectors.joining());
    }

    private void saveGameResultToFile(String gameResult) {

        try {
            checkOrCreateDirectory(DIRECTORY_PATH);
            checkOrCreateFile(FILE_PATH);
            OutputStreamWriter outputStreamWriter;
            FileOutputStream fileOutputStream =
                    new FileOutputStream(FILE_PATH, true);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("\n");
            outputStreamWriter.write(gameResult);
            outputStreamWriter.close();
        } catch (Exception ignored) {}
    }

    private void createJsonFile(String path) {

        Path newFilePath = Paths.get(path);
        try {
            Files.createFile(newFilePath);
        } catch (IOException e) {}
    }

    private void createDirectoryForJsonFile(String path) {

        Path newDirectoryPath = Paths.get(path);
        try {
            Files.createDirectory(newDirectoryPath);
        } catch (IOException e) {}
    }

    private void checkOrCreateDirectory(String path) {

        Path directoryPath = Paths.get(path);
        if (!Files.exists(directoryPath)) {
            createDirectoryForJsonFile(path);
        }
    }

    private void checkOrCreateFile(String fileWithPath) {

        File file = new File(fileWithPath);
        if (!file.exists()) {
            createJsonFile(fileWithPath);
        }
    }
}
