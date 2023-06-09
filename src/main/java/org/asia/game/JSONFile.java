package org.asia.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class JSONFile {

    GameUI gameUI = new GameUI();

    public <T> void serialise(T object) {

        var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            var json = mapper.writeValueAsString(object);
            gameUI.printSerialisedJson(json);
            saveGameResultToFile(json);
        } catch (JsonProcessingException ignored) {}
    }

    public void deserialize() {

        String jsonFile = loadResultsFile();
        try {
            var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            List<GameResult> deserializedFile = mapper.readValue(jsonFile, new TypeReference<>() {});
            gameUI.printDeserializedFile(deserializedFile.toString());
        } catch (JsonProcessingException ignored) {}
    }


    public String loadResultsFile() {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/myFile.json");
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
        String line = bufferedReader.readLine();
        while (line != null) {
            System.out.println(line);
            line = bufferedReader.readLine();}
        } catch (Exception ignored) {}

        return bufferedReader.lines().collect(Collectors.joining());
    }

    public void saveGameResultToFile(String gameResult) {

        try {
            OutputStreamWriter outputStreamWriter;
            FileOutputStream fileOutputStream =
                         new FileOutputStream(getClass().getClassLoader().getResource("json/myFile.json").getPath(), true);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("\n");
            outputStreamWriter.write(gameResult);
            outputStreamWriter.close();
        } catch (Exception ignored) {}
    }


}
