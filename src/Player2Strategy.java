import java.util.ArrayList;

import interfaces.KalahaState;

public class Player2Strategy implements Strategy {

    @Override
    public void afterPlayerTurn(ArrayList<Integer> currentBoard, ArrayList<Integer> boardOne, ArrayList<Integer> boardTwo, KalahaStateImpl kalahaState) {
        currentBoard.addAll(boardTwo);
        currentBoard.addAll(boardOne);
        kalahaState.setGameState(KalahaState.GameStates.AFTER_PLAYER2_TURN);
    }
}
