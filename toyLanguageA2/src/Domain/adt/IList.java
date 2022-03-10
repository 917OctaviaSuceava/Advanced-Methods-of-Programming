package Domain.adt;

import Exceptions.ADTException;

import java.util.ArrayList;

public interface IList<T> {
    T get(int index);
    ArrayList<T> getList();
    void add(T elem) throws ADTException;
    void remove(int index) throws ADTException;
    void set(int index, T elem) throws ADTException;
    void setList(ArrayList<T> new_list);
    int size();
    boolean contains(T elem);
    String toString();
}
