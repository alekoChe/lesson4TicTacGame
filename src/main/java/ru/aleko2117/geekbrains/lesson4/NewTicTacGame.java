package ru.aleko2117.geekbrains.lesson4;

import java.util.Random;
import java.util.Scanner;

public class NewTicTacGame {
    private static Scanner scanner; // объявляем сканнер
    private static Random random;

    private static char [][] map;
    private static final int SIZE = 6;
    private static final int DOTS_TO_WIN = 4;

    private static final char FIELD_EMPTY = '_';
    private static final char ELEMENT_X = 'X';
    private static final char ELEMENT_0 = '0';

    public static void main(String[] args) {

        scanner = new Scanner(System.in);  // инициализируем сканнер
        random = new Random();

        initMap();
        printMap();

        while (true) {
            playerTurn();
            printMap();
            if (checkWinHorizontal(ELEMENT_X) || checkingWinVertical(ELEMENT_X) ||
            checkingWinDiagonalLeftRightDown(ELEMENT_X) || checkingWinDiagonalLeftRightUp(ELEMENT_X)) {
                System.out.println("Игра окончена! Победил игрок!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Игра окончена! Ничья!");
                break;
            }
            aiTurn();
            printMap();
            if (checkWinHorizontal(ELEMENT_0) || checkingWinVertical(ELEMENT_0) ||
                    checkingWinDiagonalLeftRightDown(ELEMENT_0) || checkingWinDiagonalLeftRightUp(ELEMENT_0)) {
                System.out.println("Игра окончена! Победил AI!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Игра окончена! Ничья!");
                break;
            }
        }
    }

    public static boolean checkWinHorizontal(char element) {
        int shift = SIZE - DOTS_TO_WIN + 1; // что бы не сместиться за пределы поля
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < shift; j++) {
                int counterHorizontal = 0;
                for (int n = 0; n < DOTS_TO_WIN; n++) { // проверка по горизонтали
                    if (map[i][j + n] == element) {
                        counterHorizontal += 1;
                    }
                }
                if (counterHorizontal == DOTS_TO_WIN) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkingWinVertical(char element) {
        int shift = SIZE - DOTS_TO_WIN + 1;
        for (int i = 0; i < shift; i++) {
            for (int j = 0; j < SIZE; j++) {
                int counterVertical = 0;
                for (int n = 0; n < DOTS_TO_WIN; n++) { // проверка по вертикали
                    if (map[i + n][j] == element) {
                        counterVertical += 1;
                    }
                }
                if (counterVertical == DOTS_TO_WIN) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkingWinDiagonalLeftRightDown(char element) {
        int shift = SIZE - DOTS_TO_WIN + 1;
        for (int i = 0; i < shift; i++) {
            for (int j = 0; j < shift; j++) {
                int counterDiagonalLeftRightDown = 0;
                for (int n = 0; n < DOTS_TO_WIN; n++) { // проверка по диагонали слева направо вниз
                    if (map[i + n][j + n] == element) {
                        counterDiagonalLeftRightDown += 1;
                    }
                }
                if (counterDiagonalLeftRightDown == DOTS_TO_WIN) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkingWinDiagonalLeftRightUp(char element) {
        int shift = SIZE - DOTS_TO_WIN + 1;
        for (int i = shift; i < SIZE; i++) {
            for (int j = 0; j < shift; j++) {
                int counterDiagonal = 0;
                for (int n = 0; n < DOTS_TO_WIN; n++) {  // проверка по диагонали слева направо вверх
                    if (map[i - n][j + n] == element) {
                        counterDiagonal += 1;
                    }
                }
                if (counterDiagonal == DOTS_TO_WIN) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++ ) {
                if (map[i][j] == FIELD_EMPTY) {
                    return false;
                }
            }
        } return true;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isMoveValid(x, y));

        map[x][y] = ELEMENT_0;
        System.out.println("AI сходил в поле [ " + (x + 1) + ", " + (y + 1) + " ]") ;
    }

    public static void playerTurn() {

        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isMoveValid(x, y));

        map[x][y] = ELEMENT_X;
    }

    public static  boolean isMoveValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[x][y] == FIELD_EMPTY; /////////////////////
    }

    public static void printMap() {
        for (int n = 0; n <= SIZE; n++) {  // отпечатываем цифры 1, 2, 3 по горизонтали
            System.out.print(n + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " "); // отпечатываем цифры 1, 2, 3 по вертикали
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j ++) {
                map[i][j] = FIELD_EMPTY;
            }
        }
    }
}
