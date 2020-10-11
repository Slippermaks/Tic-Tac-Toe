package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Игрок.
public class Player {

    private int symbol;

    private String playerName;

    private int x, y;

    public Player(){
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public int getSymbol() { return symbol; }

    public void setSymbol(int symbol) { this.symbol = symbol; }

    public String getPlayerName() { return playerName; }

    public void setPlayerName(String playerName) { this.playerName = playerName; }

    // Ход игрока.
    public void Turn() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите ячейку поля корректно");
        System.out.print("строка: ");
        setX(Integer.parseInt(in.readLine()) - 1);
        System.out.print("столбец: ");
        setY(Integer.parseInt(in.readLine()) - 1);
    }

    // Инициализация игрока.
    public void InitializePlayer(String playerName, int symbol) {
        setPlayerName(playerName);
        setSymbol(symbol);
    }
}
