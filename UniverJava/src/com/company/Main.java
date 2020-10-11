package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //init(); start(); это логика игры в классе Game

        Game game = new Game();
        Field field = new Field();
        Player player1 = new Player();
        Player player2 = new Player();
        game.initializeGame(field, player1, player2);
        game.startGame();

    }
}
