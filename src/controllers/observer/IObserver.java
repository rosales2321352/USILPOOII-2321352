package controllers.observer;

public interface IObserver {
    void update(int value);
    void subscribe();
    void unSubscribe();
}
