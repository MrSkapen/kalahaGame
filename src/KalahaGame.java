import fasada.GameLogic;
import interfaces.GameStateObserver;
import interfaces.Kalah;
import interfaces.KalahPlayer;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class KalahaGame implements Kalah {

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

        GameLogic gameLogic = new GameLogic(housesNumber);
        KalahPlayer currentPlayer = PLAYER_FIRST;
        kalahaState.notifyChanged();

        while (kalahaState.getGameState() != KalahaState.GameStates.END_OF_GAME) {

            if (currentPlayer == PLAYER_FIRST) {
                strategy = new Player1Strategy();
            } else {
                strategy = new Player2Strategy();
            }

            ArrayList<Integer> currentBoard = new ArrayList<>();
            strategy.afterPlayerTurn(currentBoard, boardOne, boardTwo, kalahaState);

            int number_of_house;
            do {
                number_of_house = currentPlayer.yourMove(currentBoard);
            } while ((number_of_house < 0) || (number_of_house >= housesNumber) || (currentBoard.get(number_of_house) == 0));

            int number_of_seeds = currentBoard.get(number_of_house);

            currentBoard = gameLogic.refreshBoard(currentBoard, number_of_house, number_of_seeds);
            int counter = gameLogic.getCounter(number_of_house,number_of_seeds);

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
            kalahaState.notifyChanged();


            boolean boardOneEmpty = gameLogic.checkIsEndOfGame(boardOne);
            boolean boardTwoEmpty = gameLogic.checkIsEndOfGame(boardTwo);

            if (boardOneEmpty || boardTwoEmpty) {
                kalahaState.gameState = KalahaState.GameStates.END_OF_GAME;

                boardOne = gameLogic.getFinalBoard(boardOne);
                boardTwo = gameLogic.getFinalBoard(boardTwo);

                int sumOne = gameLogic.countPoints(boardOne);
                int sumTwo = gameLogic.countPoints(boardTwo);

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
