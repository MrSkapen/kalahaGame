package interfaces;

public interface GameStateObserver {

    /**
     * Metoda umożliwia przekazanie stanu gry do jej obserwatora.
     *
     * @param state aktualny stan gry
     */
    public void currentState(KalahaState state);
}