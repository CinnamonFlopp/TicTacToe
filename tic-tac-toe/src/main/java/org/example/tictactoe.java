package org.example;

import java.util.Random;
import java.util.Scanner;
public class tictactoe {
    public static int size = 5;
    public static int win = 4;



    public static final char PLAYER = 'O';
    public static  int Px;
    public static  int Py;
    public static final char AI = 'X';
    public static final char EMPTY = '*';

    public static char[][] map;

    public static Random rnd = new Random();
    public static Scanner sc = new Scanner(System.in);

    public static void initMap()
    {
        map = new char [size][size];
        for (int i = 0;i < size; i++)
        {
            for (int a = 0; a < size; a++)
            {
                map[i][a] = EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isMapFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == EMPTY) return false;
            }
        }
        System.out.println("Ничья");
        return true;
    }

    public static boolean isCellValid(int x, int y)
    {
        if (x < 0 || x >= size || y < 0 || y >= size)
        {
            return false;
        }
        if (map[x][y] == EMPTY) return true;
        return false;
    }
    public static void humanTurn()
    {
        int x,y;

        do {
            System.out.println("Введите координаты");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x,y));
        map[x][y] = PLAYER;
        Px = x;
        Py = y;

    }

    public static int PxL = 0;
    public static int PyL = 0;
    public static void AiTurn()
    {
        int x, y;
        PxL = Px;
        PyL = Py;
        x = Px + 1;
        y = Py + 1;
        if (rnd.nextInt(100) > 50)
        {
            do {

                if (isCellValid(x,y) == true)
                {
                    break;
                }
                x = Px - 1;
                if (isCellValid(x,y) == true)
                {
                    break;
                }
                y = Py - 1;
                if (isCellValid(x,y) == true)
                {
                    break;
                }
            } while (!isCellValid(x, y));
        }
        if (rnd.nextInt(100) < 50) {
            do {

                y = Py - 1;
                if (isCellValid(x, y) == true) {
                    break;
                }
                x = Px - 1;
                if (isCellValid(x, y) == true) {
                    break;
                }
            } while (!isCellValid(x, y));
        }


        map[x][y] = AI;
    }

    public static boolean checkWin(char symb) {
        int hor, ver;
        for (int i = 0; i < size; i++) {
            hor = 0;
            ver = 0;
            for (int j = 0; j < size; j++) {
                if (map[i][j] == symb) {
                    hor++;
                } else if (map[i][j] != symb && hor < win) {
                    hor = 0;
                }
                if (map[i][j] == symb) {
                    ver++;
                } else if (map[i][j] != symb && ver < win) {
                    ver = 0;
                }
            }
            if (hor >= win || ver >= win) {
                return true;
            }
        }
        int diag;
        for (int i = 0; i < size; i++) {
            diag = 0;
            for (int j = 0; j < size; j++) {
                int k = i + j;
                if (k < size) {
                    if (map[k][i] == symb) {
                        diag++;
                    }
                    if (map[k][i] != symb && diag < win) {
                        diag = 0;
                    }
                }
                if (diag >= win) {
                    return true;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            diag = 0;
            for (int j = 0; j < size; j++) {
                int k = i + j;
                if (k < size) {
                    if (map[i][k] == symb) {
                        diag++;
                    }
                    if (map[i][k] != symb && diag < win) {
                            diag = 0;
                        }
                    }
                    if (diag >= win) {
                        return true;
                    }
                }
            }
            for (int j = 1; j < size; j++) {
                diag = 0;
                for (int i = 0; i < size; i++) {
                    int k = (size - 1) - j - i;
                    if (k >= 0) {
                        if (map[i][k] == symb) {
                            diag++;
                        } else if (map[i][k] != symb && diag < win) {
                            diag = 0;
                        }
                    }
                    if (diag >= win) {
                        return true;
                    }
                }
            }
            return false;
        }




    public static void main(String[] args){
        initMap();
        printMap();

        while (true)
        {
            humanTurn();
            printMap();
            if (checkWin(PLAYER))
            {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull() == true)
            {
                System.out.println("Ничья");
                break;
            }
            AiTurn();
            printMap();
            if (checkWin(AI))
            {
                System.out.println("Победил бот");
                break;
            }
            if (isMapFull() == true)
            {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }
}
