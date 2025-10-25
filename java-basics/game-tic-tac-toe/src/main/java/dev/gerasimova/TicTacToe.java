package dev.gerasimova;

import java.util.Scanner;
/**
 * Класс для игры в Крестики-нолики.
 * Управляет игровым полем, игроками и очередностью ходов.
 */
public class TicTacToe {

    private char[][] table;
    private Player player1;
    private Player player2;

    /**
     * Создает новую игру Крестики-нолики.
     * Инициализирует игровое поле и игроков.
     */
    public TicTacToe() {
        createTable();
        createPlayers();
    }
    /**
     * Инициализирует игровое поле 3x3.
     * Заполняет поле пустыми клетками ('.').
     */
    public void createTable() {
        this.table = new char[][]{
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}
        };
    }
    /**
     * Создает двух игроков для игры.
     * Игрок 1: 'X', Игрок 2: 'O'.
     */
    public void createPlayers() {
        this.player1 = new Player("Maria", 'X', GameStatus.WAITING_TURN);
        this.player2 = new Player("Alex", 'O', GameStatus.WAITING_TURN);
    }

    /**
     * Определяет, кто ходит первым.
     * @param scanner объект Scanner для ввода выбора
     */
    public void chooseFirstPlayer(Scanner scanner) {
        System.out.println("Выберите кто ходит первым? (1 - крестик,2 - нолик)");
        int temp = scanner.nextInt();
        scanner.nextLine();

        if (temp == 1) {
            player1.setStatus(GameStatus.MAKING_MOVE);
            System.out.println("Крестик ходит первым!");
        }
        else {
            player2.setStatus(GameStatus.MAKING_MOVE);
            System.out.println("Нолик ходит первым!");
        }
    }
    /**
     * Выполняет ход игрока на указанные координаты.
     * @param waitingPlayer игрок, который ожидает хода
     * @param movePlayer игрок, который делает ход
     * @param x координата X (строка)
     * @param y координата Y (столбец)
     */
    public void makeMove(Player waitingPlayer, Player movePlayer, int x, int y) {
        table[x][y] = movePlayer.getSymbol();
        movePlayer.setStatus(GameStatus.WAITING_TURN);
        waitingPlayer.setStatus(GameStatus.MAKING_MOVE);
    }

    /**
     * Выводит текущее состояние игрового поля в консоль.
     */
    public void printTable () {
        System.out.println("  0 1 2");
        for (int k = 0; k < 3; k++) {
            System.out.print(k + " ");
            for (int s = 0; s < 3; s++) {
                System.out.print(table[k][s] + " ");
            }
            System.out.println();
        }
    }
    /**
     * Считывает и проверяет координату из потока ввода.
     * Гарантирует, что введено число в диапазоне 0-2.
     *
     * @param scanner объект Scanner для ввода
     * @param coordinateName название координаты для сообщения ("X", "Y")
     * @return валидная координата (0, 1 или 2)
     */
    public static int inputCoordinate (Scanner scanner, String coordinateName) {
        while (true) {
            System.out.println("Введите координату " + coordinateName + ": (0-2)");
            String input = scanner.nextLine();

            try {
                int coordinate = Integer.parseInt(input);
                if (coordinate >= 0 && coordinate < 3) {
                    return coordinate;
                } else {
                    System.out.println("Ошибка: координата должна быть от 0 до 2!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: '" + input + "' не является числом!");
            }
        }
    }
    /**
     * Запускает игру.
     * Управляет очередностью ходов и проверкой условий.
     * @param scanner объект Scanner для ввода координат
     */
    public void start (Scanner scanner) {
        System.out.println("Добро пожаловать в игру!");
        chooseFirstPlayer(scanner);

        int movesCount = 0;
       while (movesCount < 9) {
           printTable();
           int x = inputCoordinate(scanner, "X");
           int y = inputCoordinate(scanner, "Y");

           if (table[x][y] != '.') {
               System.out.println("Клетка уже занята!");
               continue;
           }

           if (player1.getStatus() == GameStatus.MAKING_MOVE) {
               makeMove(player2, player1, x,y);
           }
           else {
               makeMove(player1, player2, x,y);
           }

           movesCount++;
       }
        printTable();
        System.out.println("Игра завершена!");
    }
}
