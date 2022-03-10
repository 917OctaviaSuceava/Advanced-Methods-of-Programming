package Domain.adt;

import Exceptions.ADTException;

public interface IStack<T> {
    T pop() throws ADTException;
    void push(T value);
    boolean isEmpty();
    T getFirst();
    String toString();
}
