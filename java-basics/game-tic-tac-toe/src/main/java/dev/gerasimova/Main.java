package dev.gerasimova;

import java.util.Scanner;
/**
 * Игра крестики-нолики. Работа с двумерными массивами.
 * Основная задача: реализовать механику смены ходов и корректный вывод поля.
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TicTacToe game = new TicTacToe();
        game.start(scanner);

        scanner.close();
    }
}