import java.util.ArrayList;

public class Fasada {

    private ArrayList<Integer> currentBoard = new ArrayList<>();
    private int number_of_house;
    private int housesNumber;
    private int number_of_seeds;
    private int counter;
    private ArrayList<Integer> boardOne = new ArrayList<>();
    private ArrayList<Integer> boardTwo = new ArrayList<>();

    GameBoard gameBoard = new GameBoard();
    GameMoves gameMoves = new GameMoves();

    public void SetBoardOne(int counter, int number_of_house, ArrayList<Integer> currentBoard, int number_of_seeds, int housesNumber) {
        this.counter = counter;
        this.number_of_house = number_of_house;
        this.currentBoard = currentBoard;
        this.number_of_seeds = number_of_seeds;
        this.housesNumber = housesNumber;
    }

    public void setBoards(ArrayList<Integer> boardOne, ArrayList<Integer> boardTwo) {
        this.boardOne = boardOne;
        this.boardTwo = boardTwo;
    }

    public ArrayList<Integer> getGameMoves(){
        gameMoves.setBoardOne(counter, number_of_house, currentBoard, number_of_seeds, housesNumber);
        return gameMoves.calculate();
    }

    public ArrayList<Integer> getGameBoard(){
        gameBoard.setBoards(boardOne, boardTwo);
        return gameBoard.calculate();
    }
}
