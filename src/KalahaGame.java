import interfaces.GameStateObserver;
import interfaces.Kalah;
import interfaces.KalahPlayer;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class KalahaGame implements Kalah  {

    public KalahaGame() {
    }

    private KalahPlayer PLAYER_FIRST = null;
    private KalahPlayer PLAYER_SECOND = null;
    private ArrayList<Integer> boardOne = new ArrayList<>();
    private ArrayList<Integer> boardTwo = new ArrayList<>();
    private final KalahaStateImpl kalahaState = new KalahaStateImpl();
    private Integer housesNumber;
    Strategy strategy;

    @Override
    public void setVariant(int houses, int seeds) {
        for (int i = 0; i < houses; i++) {
            boardOne.add(seeds);
            boardTwo.add(seeds);
        }
        boardOne.add(0);
        boardTwo.add(0);
        kalahaState.setGameState(KalahaState.GameStates.INITAL_STATE);
        kalahaState.setGameResult(KalahaState.GameResults.UNKNOWN);
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
        new Observer(kalahaState, observer);
    }

    @Override
    public void startGame() {
        KalahPlayer currentPlayer = PLAYER_FIRST;
        kalahaState.notifyChanged();
        while (kalahaState.getGameState() != KalahaState.GameStates.END_OF_GAME) {
            int number_of_house;
            ArrayList<Integer> currentBoard = new ArrayList<>();
            if (currentPlayer == PLAYER_FIRST) {
                strategy = new Player1Strategy();
            } else {
                strategy = new Player2Strategy();
            }
            strategy.afterPlayerTurn(currentBoard, boardOne, boardTwo, kalahaState);
            do {
                number_of_house = currentPlayer.yourMove(currentBoard);
            } while ((number_of_house < 0) || (number_of_house >= housesNumber) || (currentBoard.get(number_of_house) == 0));

            int counter = number_of_house + 1;
            int number_of_seeds = currentBoard.get(number_of_house);
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
                        int oppositeHouse = housesNumber + (housesNumber - counter);
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
            kalahaState.notifyChanged();
            if (boardOneEmpty || boardTwoEmpty) {
                kalahaState.gameState = KalahaState.GameStates.END_OF_GAME;
                int sumOne = 0;
                int sumTwo = 0;
                for (int i = 0; i < boardOne.size(); i++) {
                    sumOne += boardOne.get(i);
                    sumTwo += boardTwo.get(i);

                    if (i != housesNumber) {
                        boardOne.set(i, 0);
                        boardTwo.set(i, 0);
                    } else {
                        boardOne.set(i, sumOne);
                        boardTwo.set(i, sumTwo);
                    }
                }
                kalahaState.setPitsState(Stream.concat(boardOne.stream(), boardTwo.stream()).collect(Collectors.toList()));
                if (sumOne > sumTwo) {
                    kalahaState.setGameResult(KalahaState.GameResults.PLAYER1_WON);
                } else if (sumTwo > sumOne) {
                    kalahaState.setGameResult(KalahaState.GameResults.PLAYER2_WON);
                } else {
                    kalahaState.setGameResult(KalahaState.GameResults.DRAW);
                }
            }
        }
        kalahaState.notifyChanged();
    }
}
