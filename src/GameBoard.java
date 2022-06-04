import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameBoard implements GameLogic {
    private ArrayList<Integer> boardOne = new ArrayList<>();
    private ArrayList<Integer> boardTwo = new ArrayList<>();

    public void setBoards(ArrayList<Integer> boardOne, ArrayList<Integer> boardTwo) {
        this.boardOne = boardOne;
        this.boardTwo = boardTwo;
    }

    @Override
    public ArrayList<Integer> calculate() {
        List<Integer> concatResult = Stream.concat(boardOne.stream(), boardTwo.stream()).collect(Collectors.toList());
        ArrayList<Integer> result = new ArrayList<>(concatResult);
        return result;
    }
}
