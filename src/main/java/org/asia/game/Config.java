package org.asia.game;

import org.asia.game.ui.ConsoleGameUIFactory;

import java.nio.file.Path;

public class Config {

    public static final int MIN_VALUE_DRAWING = 1;
    public static final int MAX_VALUE_DRAWING = 10;

    public static final int BASIC_WIN_POINTS = 5;
    public static final int TWO_WINS_BONUS = 10;
    public static final int THREE_WINS_BONUS = 100;
    public static final int FOUR_WINS_BONUS = 1000;

    public static final int GAMES_ITERATION = 4;

    public static final int DOUBLE_WINS = 2;
    public static final int TRIPLE_WINS = 3;
    public static final int QUADRUPLE_WINS = 4;

    public static final String YES_RESPONSE = "y";
    public static final String NO_RESPONSE = "n";

    public static final String DIRECTORY_PATH = System.getProperty("user.home") + "/GameResults/";
    public static final Path FILE_PATH = Path.of(DIRECTORY_PATH + "myFile.json");

    public final static String REPOSITORY_DESTINATION_KEY = "RepositoryDestination";

    public static final ConsoleGameUIFactory.Language LANGUAGE = ConsoleGameUIFactory.Language.EN;
}
