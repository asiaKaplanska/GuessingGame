package org.asia.game;

public class GameState {

    private String userName;
    private int winnings;
    private int userScore;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWinnings() {
        return winnings;
    }

    public void setWinnings(int winnings) {
        this.winnings = winnings;
    }

    public void increaseWinnings() {
        winnings++;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public void addToUserScore(int points) {
        userScore += points;
    }
}
