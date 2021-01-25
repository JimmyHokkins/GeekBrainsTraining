package lesson4;

import java.util.Random;
import java.util.Scanner;

public class FourthApp {

    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static int xComp;
    public static int yComp;

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
        if (!checkWinIfOneMoveHuman()) {
            if (!blockingHumanMove()) {
                do {
                    xComp = rand.nextInt(SIZE);
                    yComp = rand.nextInt(SIZE);
                } while (!isCellValid(xComp, yComp));
            }
        }
        map[xComp][yComp] = DOT_O;
    }


    //--AI--------------------------------------------------------

    public static boolean checkLinePossibleWin(char[] line) {
        int counter = 0;
        char[] save = new char[line.length];
        for (int i = 0; i < line.length; i++) {
            save[i] = line[i];
            if (line[i] == DOT_EMPTY) line[i] = DOT_X;
        }
        for (int j : line) {
            if (j == DOT_X) {
                counter++;
                if (counter == DOTS_TO_WIN) {
                    System.arraycopy(save, 0, line, 0, line.length);
                    return true;
                }
            }
            else counter = 0;
        }
        System.arraycopy(save, 0, line, 0, line.length);
        return false;
    }

    public static boolean checkWinIfOneMoveHuman() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_X;
                    if (checkWin(DOT_X)) {
                        xComp = i;
                        yComp = j;
                        return true;
                    }
                    else map[i][j] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    public static boolean blockingHumanMove() {
        char[] lineFirstDiagonal = new char[SIZE];
        char[] lineSecondDiagonal = new char[SIZE];
        char[] lineRow = new char[SIZE];
        char[] lineColumn = new char[SIZE];
        int move;

        for (int i = 0; i < SIZE; i++) {
            lineFirstDiagonal[i] = map[i][i];
        }
        for (int i = 0, j = SIZE - 1; i < SIZE; i++, j--) {
            lineSecondDiagonal[i] = map[i][j];
        }

        for (int t = 3; t > 1; t--) {
            if (checkLinePossibleWin(lineFirstDiagonal)) {
                move = smartCompMove(lineFirstDiagonal, t);
                if (move != -1) {
                    xComp = move;
                    yComp = move;
                    return true;
                }
            }
            //--------------------------------------------------------
            if (checkLinePossibleWin(lineSecondDiagonal)) {
                move = smartCompMove(lineSecondDiagonal, t);
                if (move != -1) {
                    xComp = move;
                    yComp = SIZE - 1 - move;
                    return true;
                }
            }
            //---------------------------------------------------------

            for (int i = 0; i < SIZE; i++) {
                System.arraycopy(map[i], 0, lineRow, 0, SIZE);
                if (checkLinePossibleWin(lineRow)) {
                    move = smartCompMove(lineRow, t);
                    if (move != -1) {
                        xComp = i;
                        yComp = move;
                        return true;
                    }
                }
            }
            // ---------------------------------------------------------
            for (int j = 0; j < SIZE; j++) {
                for (int i = 0; i < SIZE; i++) {
                    lineColumn[i] = map[i][j];
                }
                if (checkLinePossibleWin(lineColumn)) {
                    move = smartCompMove(lineColumn, t);
                    if (move != -1) {
                        xComp = move;
                        yComp = j;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int smartCompMove(char[] line, int countEmpty) {
        int emptyCounter = 0;
        int humanCounter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (line[i] == DOT_EMPTY) emptyCounter++;
            if (line[i] == DOT_X) humanCounter++;
        }
        if (emptyCounter == countEmpty && humanCounter == 2) {
            if (line[0] == DOT_EMPTY && line[SIZE - 1] == DOT_EMPTY) {
                for (int i = 0; i < SIZE; i++) {
                    if (i > 0 && i < SIZE - 1 && line[i] == DOT_EMPTY &&
                            line[i - 1] == DOT_X && line[i + 1] == DOT_X) {
                        return i;
                    }
                }
                for (int i = 0; i < SIZE; i++) {
                    if (i > 0 && i < SIZE - 1 && line[i] == DOT_EMPTY &&
                            ((line[i - 1] == DOT_EMPTY && line[i + 1] == DOT_X) ||
                                    (line[i - 1] == DOT_X && line[i + 1] == DOT_EMPTY))) {
                        return i;
                    }
                }
            }
            for (int i = 0; i < SIZE; i++) {
                if (i > 0 && i < SIZE - 1 && line[i] == DOT_EMPTY &&
                        ((line[i - 1] == DOT_EMPTY && line[i + 1] == DOT_X) ||
                                (line[i - 1] == DOT_X && line[i + 1] == DOT_EMPTY))) {
                    return i;
                }
            }
            for (int i = 0; i < SIZE; i++) {
                if (line[i] == DOT_EMPTY) return i;
            }
        }
        return -1;
    }

}
