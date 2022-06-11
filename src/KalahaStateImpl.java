import interfaces.KalahaState;

import java.util.List;
import java.util.Observable;

class KalahaStateImpl extends Observable implements interfaces.KalahaState {
    public KalahaState.GameStates gameState;
    public KalahaState.GameResults gameResult;
    public List<Integer> pitsState;

    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    public void notifyChanged() {
        setChanged();
        notifyObservers();
    }

    public void setGameResult(GameResults gameResult) {
        this.gameResult = gameResult;
    }

    public void setPitsState(List<Integer> pitsState) {
        this.pitsState = pitsState;
    }

    KalahaStateImpl() {}

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