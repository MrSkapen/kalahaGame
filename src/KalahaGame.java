import interfaces.GameStateObserver;
import interfaces.Kalah;
import interfaces.KalahPlayer;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class KalahaStateImpl implements interfaces.KalahaState {

    public KalahaState.GameStates gameState;
    public KalahaState.GameResults gameResult;
    public List<Integer> pitsState;

    @Override
    public List<Integer> getPitsState() {
        return pitsState;
    }

    @Override
    public GameStates getGameState() {
        return gameState;
    }

    @Override
    public GameResults getGameResult() {
        return gameResult;
    }
}

public class KalahaGame implements Kalah {

    public KalahaGame() {
    }

    public ArrayList<GameStateObserver> listOfObservers = new ArrayList<GameStateObserver>();
    private KalahPlayer PLAYER_FIRST = null;
    private KalahPlayer PLAYER_SECOND = null;
    private KalahPlayer currentPlayer = null;
    private ArrayList<Integer> boardOne = new ArrayList<>();
    private ArrayList<Integer> boardTwo = new ArrayList<>();
    private KalahaStateImpl kalahaState = new KalahaStateImpl();
    private Integer housesNumber;

    @Override
    public void setVariant(int houses, int seeds) {
        for (int i = 0; i < houses; i++) {
            boardOne.add(seeds);
            boardTwo.add(seeds);
        }
        boardOne.add(0);
        boardTwo.add(0);
        kalahaState.gameState = KalahaState.GameStates.INITAL_STATE;
        kalahaState.gameResult = KalahaState.GameResults.UNKNOWN;
        kalahaState.pitsState = Stream.concat(boardOne.stream(), boardTwo.stream()).collect(Collectors.toList());
        housesNumber = houses;
    }

    @Override
    public void registerPlayer(KalahPlayer player) {
        if (PLAYER_FIRST == null) {
            PLAYER_FIRST = player;
        } else {
            PLAYER_SECOND = player;
        }
    }

    @Override
    public void addObserver(GameStateObserver observer) {
        listOfObservers.add(observer);
    }

    @Override
    public void startGame() {
        currentPlayer = PLAYER_FIRST;
        listOfObservers.forEach((observer) -> observer.currentState(kalahaState));

        while (kalahaState.getGameState() != KalahaState.GameStates.END_OF_GAME) {
            int number_of_house;
            ArrayList<Integer> currentBoard = new ArrayList<>();
            if (currentPlayer == PLAYER_FIRST) {
                currentBoard.addAll(boardOne);
                currentBoard.addAll(boardTwo);
                kalahaState.gameState = KalahaState.GameStates.AFTER_PLAYER1_TURN;
            } else {
                currentBoard.addAll(boardTwo);
                currentBoard.addAll(boardOne);
                kalahaState.gameState = KalahaState.GameStates.AFTER_PLAYER2_TURN;
            }
            do {
                number_of_house = currentPlayer.yourMove(currentBoard);
            } while ((number_of_house < 0) || (number_of_house >= housesNumber) || (currentBoard.get(number_of_house) == 0));

            int counter = number_of_house + 1;
            int number_of_seeds = currentBoard.get(number_of_house);
            currentBoard.set(number_of_house, 0);
            for (int i = 0; i < number_of_seeds; i++) {
                if (counter > currentBoard.size() - 1) {
                    counter = 0;
                }
                currentBoard.set(counter, currentBoard.get(counter) + 1);
                counter++;
            }
            counter--;
            if (counter != housesNumber) {
                if (currentBoard.get(counter) == 1) {
                    if (counter <= housesNumber) {
                        int oppositeHouse = ((currentBoard.size() / 2) + ((currentBoard.size() / 2) - counter - 2));
                        if (currentBoard.get(oppositeHouse) != 0) {
                            int bonusSeeds = currentBoard.get(counter) + currentBoard.get(oppositeHouse);
                            currentBoard.set(counter, 0);
                            currentBoard.set(oppositeHouse, 0);
                            currentBoard.set(housesNumber, currentBoard.get(housesNumber) + bonusSeeds);
                        }
                    }
                }
            }
            if (currentPlayer == PLAYER_FIRST) {
                boardOne = new ArrayList<>(currentBoard.subList(0, housesNumber + 1));
                boardTwo = new ArrayList<>(currentBoard.subList(housesNumber + 1, currentBoard.size()));
                if (counter + 1 != housesNumber + 1) {
                    currentPlayer = PLAYER_SECOND;
                }
            } else {
                boardTwo = new ArrayList<>(currentBoard.subList(0, housesNumber + 1));
                boardOne = new ArrayList<>(currentBoard.subList(housesNumber + 1, currentBoard.size()));
                if (counter + 1 != housesNumber + 1) {
                    currentPlayer = PLAYER_FIRST;
                }
            }
            kalahaState.pitsState = Stream.concat(boardOne.stream(), boardTwo.stream()).collect(Collectors.toList());
            boolean boardOneEmpty = true;
            boolean boardTwoEmpty = true;
            for (int i = 0; i < boardOne.size() - 1; i++) {
                if (boardOne.get(i) != 0) {
                    boardOneEmpty = false;
                }
                if (boardTwo.get(i) != 0) {
                    boardTwoEmpty = false;
                }
                if (!boardOneEmpty && !boardTwoEmpty) {
                    break;
                }
            }
            if (boardOneEmpty || boardTwoEmpty) {
                kalahaState.gameState = KalahaState.GameStates.END_OF_GAME;
                int sumOne = 0;
                int sumTwo = 0;
                for (int i = 0; i < boardOne.size(); i++) {
                    sumOne += boardOne.get(i);
                    sumTwo += boardTwo.get(i);
                }
                if (sumOne > sumTwo) {
                    kalahaState.gameResult = KalahaState.GameResults.PLAYER1_WON;
                } else if (sumTwo > sumOne) {
                    kalahaState.gameResult = KalahaState.GameResults.PLAYER2_WON;
                } else {
                    kalahaState.gameResult = KalahaState.GameResults.DRAW;
                }
            }
            listOfObservers.forEach((observer) -> observer.currentState(kalahaState));
        }
    }
}
