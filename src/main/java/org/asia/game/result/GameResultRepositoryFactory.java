package org.asia.game.result;

import org.asia.game.Config;

import java.nio.file.Path;

public class GameResultRepositoryFactory {

    private final Destination destination;

    public enum Destination {
        IN_MEMORY,
        FILE
    }

    public GameResultRepositoryFactory(Destination destination) {
        this.destination = destination;
    }

    public GameResultRepository newRepository() {

        return switch (destination) {
            case FILE -> new JsonFileRepository(Path.of(Config.FILE_PATH));
            default -> new InMemoryRepository();
        };
    }


}
