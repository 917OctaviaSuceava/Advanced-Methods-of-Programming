package com.example.demo.Domain.types;

import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.RefValue;

public class RefType implements IType {
    IType inner;

    public RefType(IType i)
    {
        inner = i;
    }

    public IType getInner()
    {
        return inner;
    }

    @Override
    public boolean equals(IType another)
    {
        if (another instanceof RefType)
            return inner.equals(((RefType)another).getInner());
        else
            return false;
    }

    public String toString()
    {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public IValue defaultValue()
    {
        return new RefValue(0, inner);
    }

}
