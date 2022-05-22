package interfaces;

import java.util.List;

public interface KalahaState {

    public static enum GameStates {
        INITAL_STATE,
        AFTER_PLAYER1_TURN,
        AFTER_PLAYER2_TURN,
        END_OF_GAME
    }

    public static enum GameResults {
        PLAYER1_WON,
        PLAYER2_WON,
        DRAW,
        UNKNOWN
    }

    /**
     * Metoda zwraca listę zawierającą ilość kamieni w poszczególnych dołkach (łącznie z bazowymi).
     * Numeracja dołków od 0. Widok danych z punktu widzenia gracza pierwszego.

     * @return ilość kamieni w dołkach
     */
    public List<Integer> getPitsState();

    /**
     * Metoda zwraca stan gry.
     * @return aktualny stan gry
     */
    public GameStates getGameState();

    /**
     * Metoda zwraca rezultat gry. Do czasu zakończenia gry stan UNKNOWN.
     * @return wynik gry
     */
    public GameResults getGameResult();
}