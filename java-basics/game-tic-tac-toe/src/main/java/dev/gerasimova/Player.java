package dev.gerasimova;

/**
 * Класс, представляющий игрока в Крестики-нолики.
 * Содержит информацию об имени игрока, его символе (X или O) и текущем статусе хода.
 * <p></p>
 * Поля:
 * - name: имя игрока
 * - symbol: символ игрока (X или O)
 * - status: текущий статус хода игрока
 */
public class Player {
    private String name;
    private char symbol;
    private GameStatus status;

    public Player(String name, char symbol, GameStatus status) {
        this.name = name;
        this.symbol = symbol;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
