import java.util.ArrayList;

public class GameMoves implements GameLogic {


    private ArrayList<Integer> currentBoard = new ArrayList<>();
    private int number_of_house;
    private int housesNumber;
    private int number_of_seeds;
    CalculatorSingleton calculatorSingleton = CalculatorSingleton.getInstance();
    private int counter;

    GameMoves() {
    }

    @Override
    public ArrayList<Integer> calculate() {
        currentBoard.set(number_of_house, 0);
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

    public void setBoardOne(int counter, int number_of_house, ArrayList<Integer> currentBoard, int number_of_seeds, int housesNumber) {
        this.counter = counter;
        this.number_of_house = number_of_house;
        this.currentBoard = currentBoard;
        this.number_of_seeds = number_of_seeds;
        this.housesNumber = housesNumber;
    }
    
}
