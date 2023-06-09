package org.asia.game.result;

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
            case FILE -> new JsonFileRepository();
           // case IN_MEMORY -> new InMemoryRepository();
            default -> new InMemoryRepository();
        };
    }


}
