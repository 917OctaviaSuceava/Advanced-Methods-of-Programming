package com.example.demo.Domain.types;

import com.example.demo.Domain.values.BoolValue;
import com.example.demo.Domain.values.IValue;

public class BoolType implements IType {
    public BoolType()
    {

    }

    public boolean equals(IType v)
    {
        return v instanceof BoolType;
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }

    public String toString()
    {
        return "bool";
    }
}
