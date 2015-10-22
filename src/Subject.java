import java.util.ArrayList;
import java.util.List;

/**
 * Created by 09006819 on 22/10/2015.
 */
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private boolean state;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
