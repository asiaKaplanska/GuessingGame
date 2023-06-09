package org.asia.game;

import java.util.Objects;
import java.util.Scanner;

public final class InputSystem {

    private static InputSystem instance;
    private Scanner scanner = new Scanner(System.in);
    private GameUI gameUI = new GameUI();

    private InputSystem() {
    }

    public static InputSystem getInstance() {

        if (instance == null) {
            instance = new InputSystem();
        }
        return instance;
    }

    public int getUserIntegerInput() {

        boolean success = false;
        String userInputAsString;
        var userInputAsInteger = 0;

        do {
            userInputAsString = scanner.next();
            try {
                userInputAsInteger = Integer.parseInt(userInputAsString);
                success = true;
            } catch (NumberFormatException nfe) {
                gameUI.printOnlyNumbersMessage();
            }
        } while (!success);

       return userInputAsInteger;
    }


    public String getUserName() {

        return scanner.nextLine();
    }

    public String getUserPlayingDecision() {

        boolean success = false;
        String userInputDecision;

        do {
            userInputDecision = scanner.next();
            if ((Objects.equals(userInputDecision, Config.YES_RESPONSE.toLowerCase())) ||
                    (Objects.equals(userInputDecision, Config.NO_RESPONSE.toLowerCase()))) {
                success = true;
            }
               gameUI.printResponseWarningExceptionMessage();
        } while (!success);

        return userInputDecision;
    }


}
