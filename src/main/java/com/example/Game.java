package com.example;

import java.time.Instant;
import java.util.UUID;

public class Game {
    private UUID gameId;
    private UUID boardId;
    private Instant startedAt;
    private Instant finishedAt;
    private String homeTeamName;
    private int homeTeamResult;
    private String awayTeamName;
    private int awayTeamResult;
    private int totalResult;

    public static Game startGame(
            UUID boardId,
            String homeTeamName,
            String awayTeamName) {
        Game match = new Game();

        match.gameId = UUID.randomUUID();
        match.boardId = boardId;
        match.homeTeamName = homeTeamName;
        match.awayTeamName = awayTeamName;
        match.homeTeamResult = 0;
        match.awayTeamResult = 0;
        match.totalResult = 0;
        match.startedAt = Instant.now();

        return match;
    }

    public void homeTeamScored() {
        this.homeTeamResult++;
        this.totalResult++;
    }

    public void awayTeamScored() {
        this.awayTeamResult++;
        this.totalResult++;
    }

    protected void finishGame() {
        this.finishedAt = Instant.now();
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public int getHomeTeamResult() {
        return this.homeTeamResult;
    }

    public int getAwayTeamResult() {
        return this.awayTeamResult;
    }

    public String getHomeTeamName() {
        return this.homeTeamName;
    }

    public String getAwayTeamName() {
        return this.awayTeamName;
    }
}