package com.example.demo.Domain.exp;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.values.IValue;

public class ValueExp implements Exp {
    IValue e;

    public ValueExp(IValue e1)
    {
        e = e1;
    }

    @Override
    public IValue eval(MyDict<String, IValue> table, IHeap heap) {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public IType typeCheck(MyDict<String, IType> typeEnv) throws Exception
    {
        return e.getType();
    }

    /*
    @Override
    public Exp deepCopy() {
        return new ValueExp(e.deepCopy());
    }
     */
}
