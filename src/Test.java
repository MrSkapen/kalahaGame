
import tests.KahalaPlayer;
import tests.Observer;

public class Test {
    public static void main(String[] args) {
        KalahaGame game = new KalahaGame();
        game.setVariant(6, 4);
        game.registerPlayer(new KahalaPlayer("1"));
        game.registerPlayer(new KahalaPlayer("2"));
        game.addObserver(new Observer());
        game.startGame();
    }
}
