package models.db.classes;

public class Parameter<T> {
    private int key_;
    private T value_;

    public Parameter(int key_,T value_){
        this.key_ = key_;
        this.value_ = value_;
    }

    public int getIndex() {
        return key_;
    }

    public T getValue() {
        return value_;
    }
}
