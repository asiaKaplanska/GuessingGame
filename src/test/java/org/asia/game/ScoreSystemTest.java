package org.asia.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScoreSystemTest {

    ScoreSystem scoreSystem;
    @Mock
    GameState gameState;

    @BeforeEach
    public void initEach() {
        scoreSystem = new ScoreSystem();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should correctly set number of winnings to 0 after drawing wrong number [negative]")
    void addBasicScore_negative() {

        //when
        scoreSystem.addBasicScore(1, 2, gameState);

        //then
        //score system should set winnings on game state to 0
        verify(gameState).setWinnings(0);
        verify(gameState, never()).addToUserScore(Config.BASIC_WIN_POINTS);
    }

//    @Test
//    @DisplayName("Should correctly add basic points to user score after drawing correct number [positive]")
//    void addBasicScore_positive() {
//
//        //when
//        scoreSystem.addBasicScore(1, 1, gameState);
//
//        //then
//        //score system should add to score basic win points and increase winnings
//        verify(gameState).addToUserScore(Config.BASIC_WIN_POINTS);
//        verify(gameState).increaseWinnings();
//    }

    @ParameterizedTest
    @DisplayName("Should correctly add basic points to user score after drawing correct number [positive]")
    @MethodSource("getDrawingParameters")
    void addBasicScore(int userNumber, int drawNumber) {

        //when
        scoreSystem.addBasicScore(userNumber, drawNumber, gameState);

        //then
        //score system should add to score basic win points and increase winnings
        verify(gameState).addToUserScore(Config.BASIC_WIN_POINTS);
        verify(gameState).increaseWinnings();
    }

    private static Stream<Arguments> getDrawingParameters() {

        return Stream.of(
                Arguments.of(Config.MIN_VALUE_DRAWING, Config.MIN_VALUE_DRAWING),
                Arguments.of(Config.MAX_VALUE_DRAWING, Config.MAX_VALUE_DRAWING),
                Arguments.of(2, 2),
                Arguments.of(9, 9),
                Arguments.of(5, 5));
    }

    @Test
    @DisplayName("Should correctly add double wins bonus points to user score [positive]")
    void addBonusScore_doubleWins() {

        //when
        //set mock state to winnings == 2
        when(gameState.getWinnings()).thenReturn(2);
        scoreSystem.addBonusScore(gameState);

        //then
        verify(gameState).addToUserScore(Config.TWO_WINS_BONUS);
    }

    @Test
    @DisplayName("Should correctly add triple wins bonus points to user score [positive]")
    void addBonusScore_tripleWins() {

        //when
        //set mock state to winnings == 3
        when(gameState.getWinnings()).thenReturn(3);
        scoreSystem.addBonusScore(gameState);

        //then
        verify(gameState).addToUserScore(Config.THREE_WINS_BONUS);
    }

    @Test
    @DisplayName("Should correctly add quadruple wins bonus points to user score [positive]")
    void addBonusScore_quadrupleWins() {

        //when
        //set mock state to winnings == 4
        when(gameState.getWinnings()).thenReturn(4);
        scoreSystem.addBonusScore(gameState);

        //then
        verify(gameState).addToUserScore(Config.FOUR_WINS_BONUS);
    }

    @Test
    @DisplayName("Should correctly add no wins bonus points to user score after no winnings [negative]")
    void addBonusScore_negative() {

        //when
        //set mock state to winnings == 0
        when(gameState.getWinnings()).thenReturn(0);
        scoreSystem.addBonusScore(gameState);

        //then
        verify(gameState, never()).addToUserScore(any(Integer.class));
    }
}