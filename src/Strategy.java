import java.util.ArrayList;

public interface Strategy {
    void afterPlayerTurn(ArrayList<Integer> currentBoard, ArrayList<Integer> boardOne, ArrayList<Integer> boardTwo, KalahaStateImpl kalahaState);
}
