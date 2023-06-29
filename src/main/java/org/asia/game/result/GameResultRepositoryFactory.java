package org.asia.game.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.asia.game.Config.FILE_PATH;

public class GameResultRepositoryFactory {
    private final Destination destination;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public enum Destination {
        IN_MEMORY,
        FILE
    }

    public GameResultRepositoryFactory(Destination destination) {
        if (destination != null) {
            this.destination = destination;
        } else
            throw new IllegalArgumentException("Destination cannot be null");
    }

    public GameResultRepository newRepository() {
        return switch (destination) {
            case FILE -> {
                try {
                    yield new JsonFileRepository(FILE_PATH);
                } catch (GameRepositoryProcessingException e) {
                    log.warn("Cannot create Json file repository. Returning default");
                    yield new InMemoryRepository();
                }
            }
            default -> new InMemoryRepository();
        };
    }
}
