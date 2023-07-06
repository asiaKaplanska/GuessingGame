package org.asia.game.ui;

import org.asia.game.Config;

class PLConsoleGameTexts extends ConsoleTexts{

    private final String insertUserInput = ", podaj liczbę jajek >>> ";
    private final String gameDescription = """
                        
            Witaj w grze!
                        
            Oto zasady:
            Gra generuje losową liczbę jajek zniesionych przez kurę (od 1 do %s), którą musisz odgadnąć.
            Otrzymujesz punkty, jeśli poprawnie odgadniesz liczbę złożonych jajek (5 punktów za każdą poprawną odpowiedź).
            Dodatkowo 2 zwycięstwa z rzędu dają dodatkowe 10 punktów, 3 zwycięstwa z rzędu dają 100 punktów
            a 4 poprawne odpowiedzi dają 1000 dodatkowych punktów!
                        
            Powodzenia :)
            """.formatted(Config.MAX_VALUE_DRAWING);
    private final String insertUserName = "Jak masz na imię? ";
    private final String drawnNumber = "Kura zniosła %s jajek w tej rundzie";
    private final String points = "Gratulacje %s, zdobyłeś/-aś %s punktów!";
    private final String greetingUser =
            "Witaj %s! Zgadnij ile jajek zniosła kura?";
    private final String round = "Runda ";
    private final String inputNumbers = "Wprowadź liczbę!";
    private final String playAgain = "Chcesz zagrać jeszcze raz? Jeśli TAK wpisz 'y' lub 'n' jeśli NIE";
    private final String inputPlayGameResponse = "Wpisz 'y' lub 'n'!";
    private final String previousResults = "Oto lista poprzednich wyników: ";
    private final String startGame = "Zaczynajmy!";

    @Override
    public String getInsertUserInput() {
        return insertUserInput;
    }

    @Override
    public String getGameDescription() {
        return gameDescription;
    }

    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    @Override
    public String getDrawnNumber() {
        return drawnNumber;
    }

    @Override
    public String getPoints() {
        return points;
    }

    @Override
    public String getGreetingUser() {
        return greetingUser;
    }

    @Override
    public String getRound() {
        return round;
    }

    @Override
    public String getInputNumbers() {
        return inputNumbers;
    }

    @Override
    public String getPlayAgain() {
        return playAgain;
    }

    @Override
    public String getInputPlayGameResponse() {
        return inputPlayGameResponse;
    }

    @Override
    public String getPreviousResults() {
        return previousResults;
    }

    @Override
    public String getStartGame() {
        return startGame;
    }
}
