package controllers.observer;

public interface ISubject {

    void subscribeObserver(IObserver observer);
    void unsubscribeObserver(IObserver observer);
    void notifyObservers();
    String subjectDetails();

}
