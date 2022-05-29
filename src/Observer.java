import interfaces.GameStateObserver;

import java.util.ArrayList;
import java.util.Observable;

public class Observer implements java.util.Observer {
    private Observable observable;
    public ArrayList<GameStateObserver> listOfObservers = new ArrayList<>();

    public Observer(Observable observable, GameStateObserver observer) {
        this.observable = observable;
        observable.addObserver(this);
        listOfObservers.add(observer);
    }

    @Override
    public void update(Observable o, Object arg) {
        listOfObservers.forEach((observer) -> observer.currentState((KalahaStateImpl) o));
    }
}
