package lesson4;

import java.util.Random;
import java.util.Scanner;

public class FourthApp {

    public static char[][] map;
    public static final int SIZE = 9;
    public static final int DOTS_TO_WIN = 5;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static final Scanner sc = new Scanner(System.in);
    public static final Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Human is winner!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Tie.");
                break;
            }
            aiTurn();
            System.out.println("Computer: ");
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Computer is winner!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Tie.");
                break;
            }
        }
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    private static boolean checkWin(char symb) {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == symb) {
                counter++;
                if (counter == DOTS_TO_WIN) return true;
            }
            else counter = 0;
        }
        //--------------------------------------------------
        counter = 0;
        for (int i = 0, j = SIZE - 1; i < SIZE; i++, j--) {
            if (map[i][j] == symb) {
                counter++;
                if (counter == DOTS_TO_WIN) return true;
            }
            else counter = 0;
        }
        //---------------------------------------------------
        for (int i = 0; i < SIZE; i++) {
            counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb) {
                    counter++;
                    if (counter == DOTS_TO_WIN) return true;
                }
                else counter = 0;
            }
        }
        //---------------------------------------------------
        for (int j = 0; j < SIZE; j++) {
            counter = 0;
            for (int i = 0; i < SIZE; i++) {
                if (map[i][j] == symb) {
                    counter++;
                    if (counter == DOTS_TO_WIN) return true;
                }
                else counter = 0;
            }
        }
        return false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.print("Enter the coordinates in X Y format: ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[x][y] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x, y;

        if (!checkFirstDiagonal(DOTS_TO_WIN - 1)) {
            if (!checkSecondDiagonal(DOTS_TO_WIN - 1)) {
                if (!checkRows(DOTS_TO_WIN - 1)) {
                    if (!checkColumns(DOTS_TO_WIN - 1)) {
                        if (!checkFirstDiagonal(DOTS_TO_WIN - 2)) {
                            if (!checkSecondDiagonal(DOTS_TO_WIN - 2)) {
                                if (!checkRows(DOTS_TO_WIN - 2)) {
                                    if (!checkColumns(DOTS_TO_WIN - 2)){
                                        do {
                                            x = rand.nextInt(SIZE);
                                            y = rand.nextInt(SIZE);
                                        } while (!isCellValid(x, y));
                                        map[x][y] = DOT_O;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    //--AI--------------------------------------------------------
    public static boolean checkFirstDiagonal(int mH) {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == DOT_X) {
                counter++;
                if (counter == mH) {
                    if (i - mH >= 0 && map[i - mH][i - mH] == DOT_EMPTY) {
                        map[i - mH][i - mH] = DOT_X;
                        if (checkWin(DOT_X)) {
                            map[i - mH][i - mH] = DOT_O;
                            return true;
                        }
                        else map[i - mH][i - mH] = DOT_EMPTY;
                    }
                    if (i + 1 < SIZE && map[i + 1][i + 1] == DOT_EMPTY) {
                        map[i + 1][i + 1] = DOT_X;
                        if (checkWin(DOT_X)) {
                            map[i + 1][i + 1] = DOT_O;
                            return true;
                        }
                        else map[i + 1][i + 1] = DOT_EMPTY;
                    }
                }
            }
            else counter = 0;
        }
        return false;
    }

    public static boolean checkSecondDiagonal(int mH) {
        int counter = 0;
        for (int i = 0, j = SIZE - 1; i < SIZE; i++, j--) {
            if (map[i][j] == DOT_X) {
                counter++;
                if (counter == mH) {
                    if ((i - mH >= 0) && (j + mH < SIZE) && (map[i - mH][j + mH] == DOT_EMPTY)) {
                        map[i - mH][j + mH] = DOT_X;
                        if (checkWin(DOT_X)) {
                            map[i - mH][j + mH] = DOT_O;
                            return true;
                        }
                        else map[i - mH][j + mH] = DOT_EMPTY;
                    }
                    if ((i + 1 < SIZE) && (j - 1 >= 0) && (map[i + 1][j - 1] == DOT_EMPTY)) {
                        map[i + 1][j - 1] = DOT_X;
                        if (checkWin(DOT_X)) {
                            map[i + 1][j - 1] = DOT_O;
                            return true;
                        }
                        else map[i + 1][j - 1] = DOT_EMPTY;
                    }
                }
            }
            else counter = 0;
        }
        return false;
    }

    public static boolean checkRows(int mH) {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_X) {
                    counter++;
                    if (counter == mH) {
                        if ((j - mH >= 0) && (map[i][j - mH] == DOT_EMPTY)) {
                            map[i][j - mH] = DOT_X;
                            if (checkWin(DOT_X)) {
                                map[i][j - mH] = DOT_O;
                                return true;
                            }
                            else map[i][j - mH] = DOT_EMPTY;
                        }
                        if ((j + 1 < SIZE) && (map[i][j + 1] == DOT_EMPTY)) {
                            map[i][j + 1] = DOT_X;
                            if (checkWin(DOT_X)) {
                                map[i][j + 1] = DOT_O;
                                return true;
                            }
                            else map[i][j + 1] = DOT_EMPTY;
                        }
                    }
                } else counter = 0;
            }
        }
        return false;
    }

    public static boolean checkColumns(int mH) {
        int counter = 0;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if (map[i][j] == DOT_X) {
                    counter++;
                    if (counter == mH) {
                        if ((i - mH >= 0) && (map[i - mH][j] == DOT_EMPTY)) {
                            map[i - mH][j] = DOT_X;
                            if (checkWin(DOT_X)) {
                                map[i - mH][j] = DOT_O;
                                return true;
                            }
                            else map[i - mH][j] = DOT_EMPTY;
                        }
                        if ((i + 1 < SIZE) && (map[i + 1][j] == DOT_EMPTY)) {
                            map[i + 1][j] = DOT_X;
                            if (checkWin(DOT_X)) {
                                map[i + 1][j] = DOT_O;
                                return true;
                            }
                            else map[i + 1][j] = DOT_EMPTY;
                        }
                    }
                } else counter = 0;
            }
        }
        return false;
    }



}
