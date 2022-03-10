package com.example.demo.Domain.values;

import com.example.demo.Domain.types.BoolType;
import com.example.demo.Domain.types.IType;

public class BoolValue implements IValue {
    private boolean val;

    public BoolValue(boolean v)
    {
        val = v;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    public boolean getVal()
    {
        return val;
    }

    public String toString()
    {
        return String.valueOf(val);
    }

    @Override
    public boolean equals(IValue v1)
    {
        return v1 instanceof BoolValue && ((BoolValue) v1).getVal() == val;
    }

}
