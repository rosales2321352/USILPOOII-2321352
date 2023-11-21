package controllers.observer;


import java.util.ArrayList;
import java.util.List;

public class MutableObject implements ISubject, IMutableObject {

    private final List<IObserver> observers;
    private int value;
    private final String subjectDetails;

    public MutableObject(String subjectDetails) {
        this.observers = new ArrayList<>();
        this.subjectDetails = subjectDetails;
    }

    @Override
    public synchronized void subscribeObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public synchronized void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(value);
        }
    }

    @Override
    public String subjectDetails() {
        return subjectDetails;
    }

    @Override
    public void setValue(int id) {
        this.value = id;
        notifyObservers();
    }

    @Override
    public int getValue(){
        return this.value;
    }
}
