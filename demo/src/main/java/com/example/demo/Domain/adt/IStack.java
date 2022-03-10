package com.example.demo.Domain.adt;

import com.example.demo.Exceptions.ADTException;

public interface IStack<T> {
    T pop() throws ADTException;
    void push(T value);
    boolean isEmpty();
    T getFirst();
    String toString();
}
