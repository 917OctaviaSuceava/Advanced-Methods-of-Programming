package com.example.demo.Domain.adt;

import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.ADTException;

import java.util.HashMap;
import java.util.Map;

public interface IHeap {
    IValue get(int k) throws ADTException;
    void add(IValue e) throws ADTException;
    boolean isDefined(int addr);
    HashMap<Integer, IValue> get();
    void setHeap(HashMap<Integer, IValue> heap);
    int getFreeAddr();
    void update(int k, IValue new_elem) throws ADTException;
    void setContent(Map<Integer, IValue> heap1);
}
