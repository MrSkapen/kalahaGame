package interfaces;

import java.util.List;

public interface KalahPlayer {

    /**
     * Metoda używana do odpytania gracza o jego ruch.
     *
     * @param pitsState lista zawierająca ilość kamieni w poszczególnych dołkach
     *                  (łącznie z bazowymi). Numeracja dołków od 0. Widok danych z
     *                  punktu widzenia gracza wykonującego ruch.
     *
     * @return numer dołka, z którego gracz chce wyjąć kamienie.
     */
    public int yourMove(List<Integer> pitsState);
}