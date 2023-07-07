package org.asia.game.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleGameUIFactory {
    private final Language language;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public enum Language {
        EN,
        PL
    }

    public ConsoleGameUIFactory(Language language) {
        if (language != null) {
            this.language = language;
        } else
            throw new IllegalArgumentException("Language cannot be null");
    }

    public GameUI newGameUI() {

        return switch (language) {
            case PL -> new ConsoleUI(new PLBaseConsoleGameTexts());
            default -> new ConsoleUI(new ENBaseConsoleGameTexts());
        };
    }
}
