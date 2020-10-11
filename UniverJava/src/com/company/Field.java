package com.company;


// Игровое поле.
public class Field {

    // Размерность игрового поля.
    public int size;

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public int[][] getFieldArray() { return fieldArray; }

    public void setFieldArray(int[][] fieldArray) { this.fieldArray = fieldArray; }

    // Чем заполнены ячейки
    public int[][] fieldArray;

    public Field() {
    }

    // Инициализация игрового поля. (нули это пустые клетки).
    public void InitializeField(int size) {
        setSize(size);
        setFieldArray(new int [size][size]);
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                getFieldArray()[i][j] = 0;
            }
        }
        System.out.println("Поле " + getSize() + "x" + getSize() + " инициализировано.");
        ShowField();
    }

    // Вывести поле на экран.
    public void ShowField() {
        for (int k = 0; k < getSize() * 2 - 1; k++)
            System.out.print("=");
        System.out.println();

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                System.out.print(getFieldArray()[i][j] + " ");
            }
            System.out.println();
        }

        for (int k = 0; k < getSize() * 2 - 1; k++)
            System.out.print("=");
        System.out.println();
    }

}
