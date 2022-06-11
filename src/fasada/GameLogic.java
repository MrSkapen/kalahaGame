package fasada;

import java.util.ArrayList;

public class GameLogic {

    int housesNumber;
    Board board = new Board();
    Counting counting = new Counting();

    public GameLogic(int housesNumber) {
        this.housesNumber = housesNumber;
    }

    public ArrayList<Integer> refreshBoard(ArrayList<Integer> currentBoard, int number_of_house, int number_of_seeds){
        return board.moves(currentBoard, number_of_house,number_of_seeds,housesNumber);
    }

    public boolean checkIsEndOfGame(ArrayList<Integer> sampleBoard){
        return board.isEmptyBoard(sampleBoard);
    }

    public ArrayList<Integer> getFinalBoard(ArrayList<Integer> sampleBoard){
        return board.endingBoard(sampleBoard, housesNumber);
    }

    public int countPoints(ArrayList<Integer> sampleBoard){
        return counting.counting(sampleBoard);
    }

    public int getCounter(int number_of_house, int number_of_seeds){
       return counting.getCounter(number_of_house,number_of_seeds);
    }

}
