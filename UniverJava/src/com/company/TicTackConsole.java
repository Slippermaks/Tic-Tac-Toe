package com.company;

// Пока не разбирался с MVC.
public class TicTackConsole implements Render {


    @Override
    public void setMsg(String str) {
        System.out.println(str);
    }

    @Override
    public int getMsg() {
        // считывает данные с консоли, преобразует в число и возвращает
        return 0;
    }
}
