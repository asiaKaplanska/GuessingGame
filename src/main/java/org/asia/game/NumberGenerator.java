package org.asia.game;

import java.util.Random;

public class NumberGenerator {

    private Random random = new Random();

    public int drawRandomNumber() {

        return random.nextInt(Config.MIN_VALUE_DRAWING, Config.MAX_VALUE_DRAWING);
    }



}
