import java.util.ArrayList;

import interfaces.KalahaState.GameStates;

public class Player1Strategy implements Strategy {

    @Override
    public void afterPlayerTurn(ArrayList<Integer> currentBoard, ArrayList<Integer> boardOne, ArrayList<Integer> boardTwo, GameStates gameState) {
        currentBoard.addAll(boardOne);
        currentBoard.addAll(boardTwo);
        gameState = interfaces.KalahaState.GameStates.AFTER_PLAYER1_TURN;
    }
}
