package com.example;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ScoreBoardTest 
{
    private static final String TEAM_1_NAME = "Mexico";
    private static final String TEAM_2_NAME = "Canada";
    private static final String TEAM_3_NAME = "Spain";
    private static final String TEAM_4_NAME = "Brazil";
    private static final String TEAM_5_NAME = "Germany";
    private static final String TEAM_6_NAME = "France";
    private static final String TEAM_7_NAME = "Uruguay";
    private static final String TEAM_8_NAME = "Italy";
    private static final String TEAM_9_NAME = "Argentina";
    private static final String TEAM_10_NAME = "Australia";

    @Test
    public void createScoreBoard()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        assertEquals(0, scoreBoard.getGames().size());
    }

    @Test
    public void startOneGame()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        Game game1 = scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        assertEquals(1, scoreBoard.getGames().size());
        assertEquals(0, game1.getHomeTeamResult());
        assertEquals(0, game1.getAwayTeamResult());
        assertEquals(0, game1.getTotalResult());
        assertEquals(TEAM_1_NAME, game1.getHomeTeamName());
        assertEquals(TEAM_2_NAME, game1.getAwayTeamName());
    }

    @Test
    public void scoredHomeTeam()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        Game game1 = scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        game1.homeTeamScored();

        assertEquals(1, scoreBoard.getGames().size());
        assertEquals(1, game1.getHomeTeamResult());
        assertEquals(0, game1.getAwayTeamResult());
        assertEquals(1, game1.getTotalResult());
    }


    @Test
    public void scoreAwayTeam()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        Game game1 = scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        game1.awayTeamScored();

        assertEquals(1, scoreBoard.getGames().size());
        assertEquals(0, game1.getHomeTeamResult());
        assertEquals(1, game1.getAwayTeamResult());
        assertEquals(1, game1.getTotalResult());
    }

    @Test
    public void scoredBothTeam()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        Game game1 = scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        game1.awayTeamScored();
        game1.homeTeamScored();

        assertEquals(1, scoreBoard.getGames().size());
        assertEquals(1, game1.getHomeTeamResult());
        assertEquals(1, game1.getAwayTeamResult());
        assertEquals(2, game1.getTotalResult());
    }

    @Test
    public void scoredBothTeamFewGoals()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        Game game1 = scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        game1.awayTeamScored();
        game1.homeTeamScored();
        game1.awayTeamScored();
        game1.awayTeamScored();
        game1.awayTeamScored();
        game1.homeTeamScored();

        assertEquals(1, scoreBoard.getGames().size());
        assertEquals(2, game1.getHomeTeamResult());
        assertEquals(4, game1.getAwayTeamResult());
        assertEquals(6, game1.getTotalResult());
    }

    @Test
    public void createTwoGames()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        Game game1 = scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        game1.awayTeamScored();
        game1.homeTeamScored();
        game1.awayTeamScored();
        assertEquals(1, scoreBoard.getGames().size());
        assertEquals(1, game1.getHomeTeamResult());
        assertEquals(2, game1.getAwayTeamResult());
        assertEquals(3, game1.getTotalResult());

        Game game2 = scoreBoard.startGame(TEAM_3_NAME, TEAM_4_NAME);
        game2.awayTeamScored();
        game2.homeTeamScored();
        game2.awayTeamScored();
        game2.homeTeamScored();

        assertEquals(2, scoreBoard.getGames().size());
        assertEquals(2, game2.getHomeTeamResult());
        assertEquals(2, game2.getAwayTeamResult());
        assertEquals(4, game2.getTotalResult());
    }

    @Test
    public void finishGame()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        Game game2 = scoreBoard.startGame(TEAM_3_NAME, TEAM_4_NAME);
        scoreBoard.finishGame(game2);

        assertEquals(1, scoreBoard.getGames().size());
    }

    @Test
    public void createFewGames()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        scoreBoard.startGame(TEAM_3_NAME, TEAM_4_NAME);
        scoreBoard.startGame(TEAM_5_NAME, TEAM_6_NAME);
        scoreBoard.startGame(TEAM_7_NAME, TEAM_8_NAME);
        scoreBoard.startGame(TEAM_9_NAME, TEAM_10_NAME);

        assertEquals(5, scoreBoard.getGames().size());
    }

    @Test
    public void finishAllGames()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        Game game1 = scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        Game game2 = scoreBoard.startGame(TEAM_3_NAME, TEAM_4_NAME);
        Game game3 = scoreBoard.startGame(TEAM_5_NAME, TEAM_6_NAME);
        Game game4 = scoreBoard.startGame(TEAM_7_NAME, TEAM_8_NAME);
        Game game5 = scoreBoard.startGame(TEAM_9_NAME, TEAM_10_NAME);

        scoreBoard.finishGame(game5);
        scoreBoard.finishGame(game1);
        scoreBoard.finishGame(game3);
        scoreBoard.finishGame(game2);
        scoreBoard.finishGame(game4);

        assertEquals(0, scoreBoard.getGames().size());
    }

    @Test
    public void showScoreTableInCorrectOrder()
    {
        ScoreBoard scoreBoard = new ScoreBoard();

        Game game1 = scoreBoard.startGame(TEAM_1_NAME, TEAM_2_NAME);
        for (int i = 0; i < 5; i++) {
            game1.awayTeamScored();
        }
        
        Game game2 = scoreBoard.startGame(TEAM_3_NAME, TEAM_4_NAME);
        for (int i = 0; i < 10; i++) {
            game2.homeTeamScored();
        }
        for (int i = 0; i < 2; i++) {
            game2.awayTeamScored();
        }
        
        Game game3 = scoreBoard.startGame(TEAM_5_NAME, TEAM_6_NAME);
        for (int i = 0; i < 2; i++) {
            game3.awayTeamScored();
            game3.homeTeamScored();
        }
        
        Game game4 = scoreBoard.startGame(TEAM_7_NAME, TEAM_8_NAME);
        for (int i = 0; i < 6; i++) {
            game4.awayTeamScored();
            game4.homeTeamScored();
        }
        
        Game game5 = scoreBoard.startGame(TEAM_9_NAME, TEAM_10_NAME);
        for (int i = 0; i < 3; i++) {
            game5.homeTeamScored();
        }
        for (int i = 0; i < 1; i++) {
            game5.awayTeamScored();
        }
        
        List<Game> games = scoreBoard.showScoreTable();
        assertEquals(TEAM_7_NAME, games.get(0).getHomeTeamName());
        assertEquals(6,  games.get(0).getHomeTeamResult());
        assertEquals(TEAM_8_NAME, games.get(0).getAwayTeamName());
        assertEquals(6,  games.get(0).getAwayTeamResult());

        assertEquals(TEAM_3_NAME, games.get(1).getHomeTeamName());
        assertEquals(10,  games.get(1).getHomeTeamResult());
        assertEquals(TEAM_4_NAME, games.get(1).getAwayTeamName());
        assertEquals(2,  games.get(1).getAwayTeamResult());

        assertEquals(TEAM_1_NAME, games.get(2).getHomeTeamName());
        assertEquals(0,  games.get(2).getHomeTeamResult());
        assertEquals(TEAM_2_NAME, games.get(2).getAwayTeamName());
        assertEquals(5,  games.get(2).getAwayTeamResult());


        assertEquals(TEAM_9_NAME, games.get(3).getHomeTeamName());
        assertEquals(3,  games.get(3).getHomeTeamResult());
        assertEquals(TEAM_10_NAME, games.get(3).getAwayTeamName());
        assertEquals(1,  games.get(3).getAwayTeamResult());

        assertEquals(TEAM_5_NAME, games.get(4).getHomeTeamName());
        assertEquals(2,  games.get(4).getHomeTeamResult());
        assertEquals(TEAM_6_NAME, games.get(4).getAwayTeamName());
        assertEquals(2,  games.get(4).getAwayTeamResult());
    }
}   
