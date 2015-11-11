import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ObserverSubject {
    private List<Observer> observers = new ArrayList<>();
    private Date state;

    public Date getState() {
        return state;
    }

    public void setState(Date state) {
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
