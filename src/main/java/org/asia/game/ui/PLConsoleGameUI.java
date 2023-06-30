package org.asia.game.ui;

import org.asia.game.Config;
import org.asia.game.GameState;
import org.asia.game.result.GameResult;

import java.util.List;

public class PLConsoleGameUI implements GameUI {

    private static final String INSERT_USER_INPUT = ", podaj liczbę jajek >>> ";
    private static final String GAME_DESCRIPTION = """
                        
            Witaj w grze!
                        
            Oto zasady:
            Gra generuje losową liczbę jajek zniesionych przez kurę (od 1 do %s), którą musisz odgadnąć.
            Otrzymujesz punkty, jeśli poprawnie odgadniesz liczbę złożonych jajek (5 punktów za każdą poprawną odpowiedź).
            Dodatkowo 2 zwycięstwa z rzędu dają dodatkowe 10 punktów, 3 zwycięstwa z rzędu dają 100 punktów
            a 4 poprawne odpowiedzi dają 1000 dodatkowych punktów!
                        
            Powodzenia :)
            """.formatted(Config.MAX_VALUE_DRAWING);
    private static final String INSERT_USER_NAME = "Jak masz na imię? ";
    private static final String DRAWN_NUMBER = "Kura zniosła %s jajek w tej rundzie";
    private static final String POINTS = "Gratulacje %s, zdobyłeś/-aś %s punktów!";
    private static final String GREETING_USER =
            "Witaj %s! Zgadnij ile jajek zniosła kura?";
    private static final String ROUND = "Runda ";
    private static final String INPUT_NUMBERS = "Wprowadź liczbę!";
    private static final String PLAY_AGAIN = "Chcesz zagrać jeszcze raz? Jeśli TAK wpisz 'y' lub 'n' jeśli NIE";
    private static final String INPUT_PLAY_GAME_RESPONSE = "Wpisz 'y' lub 'n'!";
    private static final String PREVIOUS_RESULTS = "Oto lista wyników prprzednich gier: ";
    private static final String START_GAME = "Zaczynajmy!";

    @Override
    public void printInsertInputMessage() {
        System.out.print(INSERT_USER_INPUT);
    }
    @Override
    public void printGameDescriptionMessage() {
        System.out.println(GAME_DESCRIPTION);
    }
    @Override
    public void printInsertUserNameMessage() {
        System.out.println(INSERT_USER_NAME);
    }
    @Override
    public void printDrawnNumber(int number) {
        System.out.println(DRAWN_NUMBER.formatted(number));
    }
    @Override
    public void printCollectedPointsMessage(String userName, int points) {
        System.out.print(POINTS.formatted(userName, points));
    }
    @Override
    public void printDottedLine() {
        System.out.println("* ".repeat(50));
    }
    @Override
    public void printEmptyRow() {
        System.out.println();
    }
    @Override
    public void printGreetingUserMessage(GameState gameState) {
        System.out.println(GREETING_USER.formatted(gameState.getUserName()));
    }
    @Override
    public void printRoundMessage(int round) {
        System.out.print(ROUND + round);
    }
    @Override
    public void printOnlyNumbersMessage() {
        System.out.println(INPUT_NUMBERS);
    }
    @Override
    public void printPlayAgainMessage() {
        System.out.println(PLAY_AGAIN);
    }
    @Override
    public void printResponseWarningExceptionMessage() {
        System.out.println(INPUT_PLAY_GAME_RESPONSE);
    }
    @Override
    public void printPreviousGameResults() {
        System.out.print(PREVIOUS_RESULTS);
    }
    @Override
    public void printLetsStart() {
        System.out.println(START_GAME);
    }
    @Override
    public void printGameResultJsonFile(List<GameResult> gameResults) {
        for (var result : gameResults)
        {
            System.out.println(result.toString());
        }
    }
}
