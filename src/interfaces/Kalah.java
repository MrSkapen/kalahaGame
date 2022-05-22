package interfaces;

public interface Kalah {

    /**
     * Metoda ustala wariant gry.
     *
     * @param houses liczba dołków dla każdego z graczy
     * @param seeds  początkowa liczba kamieni w dołkach gracza
     */
    public void setVariant(int houses, int seeds);

    /**
     * Metoda pozwala na rejestracje gracza. Kolejność rejestracji jest znacząca.
     * Pierwsza rejestracja to gracz 1. Kolejna (2, 3, itd.) rejestracja gracz 2.
     *
     * @param player obiekt reprezentujący gracza
     */
    public void registerPlayer(KalahPlayer player);

    /**
     * Metoda umożliwia dodanie obserwatorów gry. Obserwatorów może być więcej niż
     * jeden.
     *
     * @param observer obserwator gry
     */
    public void addObserver(GameStateObserver observer);

    /**
     * Metoda zleca rozpoczęcie gry. Przed jej wykonaniem zostanie ustawiony wariant
     * gry, obiekty-gracze i obiekt (obiekty) obserwujące grę.
     */
    public void startGame();
}