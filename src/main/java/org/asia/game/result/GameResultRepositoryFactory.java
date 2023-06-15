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

        if (destination != null) {
            this.destination = destination;
        } else throw new IllegalArgumentException();
    }

    public GameResultRepository newRepository() {

        return switch (destination) {
            case FILE -> {
                try {
                    yield new JsonFileRepository(Path.of(Config.FILE_PATH));
                } catch (GameRepositoryProcessingException e) {
                    System.out.println("Cannot create Json file repository. Returning default");
                    yield new InMemoryRepository();
                }
            }
            default -> new InMemoryRepository();
        };
    }


}
