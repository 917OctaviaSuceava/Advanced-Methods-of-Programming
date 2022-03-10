package com.example.demo.Domain.exp;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.ADTException;
import com.example.demo.Exceptions.UndeclaredVariable;

public class VarExp implements Exp {
    String id;

    public VarExp(String i) {
        id = i;
    }

    @Override
    public IValue eval(MyDict<String, IValue> table, IHeap heap) throws ADTException, UndeclaredVariable {
        if(!table.isDefined(id))
            throw new UndeclaredVariable("The variable " + id + " is not defined!");
        return table.get(id);
    }

    @Override
    public IType typeCheck(MyDict<String, IType> typeEnv) throws Exception
    {
        return typeEnv.get(id);
    }

    @Override
    public String toString() {
        return id;
    }

    /*
    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }*/
}
