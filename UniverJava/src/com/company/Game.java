package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Игра.
public class Game {

    private Field field;

    private Player player1;
    private Player player2;

    private int turnNumber = 0;

    public int getTurnNumber() { return turnNumber; }

    public void setTurnNumber(int turnNumber) { this.turnNumber = turnNumber; }

    public Field getField() { return field; }

    public void setField(Field field) { this.field = field; }

    public Player getPlayer1() { return player1; }

    public void setPlayer1(Player player1) { this.player1 = player1; }

    public Player getPlayer2() { return player2; }

    public void setPlayer2(Player player2) { this.player2 = player2; }

    public Game() {
    }

    // Проверить, можно ли походить в эту ячейку.
    public boolean isCellAvailable(int x, int y) {
        if (x < 0 || y < 0 || x >= getField().getSize() || y >= getField().getSize()) { // Проверить, не хотят ли поставить за границу поля
            System.out.println("Введённый номер ячейки выходит за рамки, введите заново");
            return false;
        }
        return getField().getFieldArray()[x][y] == 0; // Проверить ячейку на пустоту.
    }

    // Проверка на выигрыш
    public boolean checkWin(int symbolInCell) {
        // Проверка подряд идущих знаков по вертикали и горизонтали
        boolean isWinning = false;

        // Проверка по горизонтали.
        for (int i = 0; i < getField().getSize() - 1; i++) {
            for (int j = 0; j < getField().getSize() - 1; j++) {
                if ((getField().getFieldArray()[i][j] == getField().getFieldArray()[i][j + 1]) && (getField().getFieldArray()[i][j] == symbolInCell))
                    isWinning = true;
                else {
                    isWinning = false;
                    break;
                }
            }
            if (isWinning) {
                //System.out.println("горизонталь");
                return true;
            }
        }

        // Проверка по вертикали.
        isWinning = false;
        for (int i = 0; i < getField().getSize() - 1; i++) {
            for (int j = 0; j < getField().getSize() - 1; j++) {
                if ((getField().getFieldArray()[j][i] == getField().getFieldArray()[j + 1][i]) && (getField().getFieldArray()[j][i] == symbolInCell))
                    isWinning = true;
                else {
                    isWinning = false;
                    break;
                }
            }
            if (isWinning) {
                //System.out.println("вертикаль");
                return true;
            }
        }

        // Проверка главной диагонали
        for (int i = 0; i < getField().getSize() - 1; i++) {
            for (int j = i; j < getField().getSize() - 1;) {
                if (getField().getFieldArray()[i][j] == getField().getFieldArray()[i + 1][j + 1] && getField().getFieldArray()[i][j] == symbolInCell) {
                    isWinning = true;
                    break;
                }
                else {
                    isWinning = false;
                    break;
                }
            }
            if (isWinning) {
                continue;
            } else break;
        }
        if (isWinning) {
            //System.out.println("главная диагональ");
            return true;
        }

        // Проверка побочной диагонали
        for (int i = 0; i < getField().getSize() - 1; i++) {
            for (int j = getField().getSize() - 1 - i; j > 0;) {
                if ((getField().getFieldArray()[i][j] == getField().getFieldArray()[i + 1][j - 1]) && (getField().getFieldArray()[i][j] == symbolInCell)) {
                    isWinning = true;
                    break;
                }
                else {
                    isWinning = false;
                    break;
                }
            }
            if (isWinning) {
                continue;
            } else break;
        }
        if (isWinning) {
            //System.out.println("побочная диагональ");
            return true;
        }
        return false;
    }

    // Пустая ли ячейка
    public boolean isFieldFull() {
        for (int i = 0; i < getField().getFieldArray().length; i++) {
            for (int j = 0; j < getField().getFieldArray()[0].length; j++) {
                if (getField().getFieldArray()[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    // Начало игры.
    public void startGame() throws IOException {
        while (true) {
            // Ход первого игрока.
            setTurnNumber(getTurnNumber()+1);
            System.out.println("Ход игрока " + getPlayer1().getPlayerName());
            do {
                getPlayer1().turn();
            } while (!isCellAvailable(getPlayer1().getX(), getPlayer1().getY()));
            getField().getFieldArray()[getPlayer1().getX()][getPlayer1().getY()] = getPlayer1().getSymbol();
            showTurnNumber();
            getField().showField();
            if (checkWin(getPlayer1().getSymbol())) {
                System.out.println(getPlayer1().getPlayerName() + " ВЫИГРАЛ!!!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("НИЧЬЯ. ПОПРОБУЙТЕ СНОВА");
                break;
            }

            // Ход второго игрока.
            setTurnNumber(getTurnNumber()+1);
            System.out.println("Ход игрока " + getPlayer2().getPlayerName());
            do {
                getPlayer2().turn();
            } while (!isCellAvailable(getPlayer2().getX(), getPlayer2().getY()));
            getField().getFieldArray()[getPlayer2().getX()][getPlayer2().getY()] = getPlayer2().getSymbol();
            getField().showField();
            if (checkWin(getPlayer2().getSymbol())) {
                System.out.println(getPlayer2().getPlayerName() + " ВЫИГРАЛ!!!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("НИЧЬЯ. ПОПРОБУЙТЕ СНОВА");
                break;
            }
        }
        System.out.println("Сыграть заново? (+ ДА / - НЕТ)");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        if (in.readLine().equals("+")) {
            System.out.println("НОВАЯ ИГРА");
            initializeGame(field, player1, player2);
            startGame();
        }
    }

    // Показать, какой сейчас по счёту ход.
    private void showTurnNumber() {
        for (int k = 0; k < getField().getSize() * 2 - 1; k++){
            System.out.print("_");
        }
        System.out.println();
        System.out.println("ХОД № " + getTurnNumber());
    }

    // Инициализация игры.
    public void initializeGame(Field field, Player player1, Player player2) throws IOException {
        // Инициализация игрового поля.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите размерность игрового поля: ");
        field.initializeField(Integer.parseInt(in.readLine()));
        setField(field);

        // Инициализация игрока 1
        System.out.print("Игрок 1, введите ваше имя: ");
        String p1Name = in.readLine();
        System.out.println("Символ - то, чем будете рисовать на поле. (пока что только цифру)");
        System.out.print(p1Name + ", введите свой символ: ");
        int p1Symbol = Integer.parseInt(in.readLine());
        player1.initializePlayer(p1Name, p1Symbol);
        setPlayer1(player1);

        // Инициализация игрока 2
        System.out.print("Игрок 2, введите ваше имя: ");
        String p2Name = in.readLine();
        System.out.println("Символ - то, чем будете рисовать на поле.");
        System.out.print(p2Name + ", введите свой символ: ");
        int p2Symbol = Integer.parseInt(in.readLine());
        player2.initializePlayer(p2Name, p2Symbol);
        setPlayer2(player2);
    }

}
