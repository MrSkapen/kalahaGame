import java.util.ArrayList;

import interfaces.KalahaState.GameStates;

public class Player2Strategy implements Strategy {

    @Override
    public void afterPlayerTurn(ArrayList<Integer> currentBoard, ArrayList<Integer> boardOne, ArrayList<Integer> boardTwo, GameStates gameState) {
        currentBoard.addAll(boardTwo);
        currentBoard.addAll(boardOne);
        gameState = interfaces.KalahaState.GameStates.AFTER_PLAYER2_TURN;
    }
}
