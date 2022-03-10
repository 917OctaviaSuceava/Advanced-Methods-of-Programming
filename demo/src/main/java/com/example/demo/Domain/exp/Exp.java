package com.example.demo.Domain.exp;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.DivisionByZero;
import com.example.demo.Exceptions.InvalidOperand;
import com.example.demo.Exceptions.ProgramException;

public interface Exp {
    IValue eval(MyDict<String, IValue> table, IHeap heap) throws DivisionByZero, ProgramException, InvalidOperand;
    String toString();
    IType typeCheck(MyDict<String, IType> typeEnv) throws Exception;
    //Exp deepCopy();
}
