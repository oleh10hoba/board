package com.example;

import java.util.*;

public class ScoreBoard {
    private UUID boardId;
    private List<Game> games;

    public ScoreBoard() {
        this.boardId = UUID.randomUUID();
        this.games = new ArrayList<>();
    }

    public Game startGame(
        String nameHomeTeam,
        String nameAwayTeam
    ) {
        Game game = Game.startGame(
                boardId,
                nameHomeTeam,
                nameAwayTeam);
        games.add(game);

        return game;
    }

    public void finishGame(
           Game game
    ) {
        game.finishGame();
        games.remove(game);
    }

    public List<Game> showScoreTable() {
        return games
                .stream()
                .sorted((g1, g2)  -> comparatorByGame(g1, g2))
                .toList();
    }

    public void printScoreTable() {
        List<Game> games = showScoreTable();
        int i = 0;
        for(Game game : games) {
            i++;
            System.out.println(i + ". " + game.getHomeTeamName() + " " + game.getHomeTeamResult() 
                + " - " + game.getAwayTeamName() + " " + game.getAwayTeamResult());
        }
    }
    
    public int comparatorByGame(Game game1, Game game2) {
        int result = Integer.compare(game2.getTotalResult(), game1.getTotalResult());
        if (result == 0) {
            return game2.getStartedAt().compareTo(game1.getStartedAt());
        }
        return result;
    }

    public List<Game> getGames() {
        return this.games;
    }
}