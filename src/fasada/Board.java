package fasada;

import java.util.ArrayList;

public class Board {

    public ArrayList<Integer> moves(ArrayList<Integer> currentBoard, int number_of_house, int number_of_seeds, int housesNumber) {
        CalculatorSingleton calculatorSingleton = CalculatorSingleton.getInstance();
        currentBoard.set(number_of_house, 0);
        int counter = number_of_house + 1;
        for (int i = 0; i < number_of_seeds; i++) {
            if (counter > currentBoard.size() - 2) {
                counter = 0;
            }
            currentBoard.set(counter, currentBoard.get(counter) + 1);
            counter++;
        }
        counter--;
        if (counter != housesNumber) {
            if (currentBoard.get(counter) == 1) {
                if (counter <= housesNumber) {
                    int oppositeHouse = calculatorSingleton.calculateOppositeHouse(housesNumber, counter);
                    if (currentBoard.get(oppositeHouse) != 0) {
                        int bonusSeeds = currentBoard.get(counter) + currentBoard.get(oppositeHouse);
                        currentBoard.set(counter, 0);
                        currentBoard.set(oppositeHouse, 0);
                        currentBoard.set(housesNumber, currentBoard.get(housesNumber) + bonusSeeds);
                    }
                }
            }
        }
        return currentBoard;
    }


    public ArrayList<Integer> endingBoard(ArrayList<Integer> board, int housesNumber) {
        int sumOne = 0;
        for (int i = 0; i < board.size(); i++) {
            sumOne += board.get(i);
            if (i != housesNumber) {
                board.set(i, 0);
            } else {
                board.set(i, sumOne);
            }
        }
        return board;
    }

}
