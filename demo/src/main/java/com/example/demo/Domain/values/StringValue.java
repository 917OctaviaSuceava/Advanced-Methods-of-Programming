package com.example.demo.Domain.values;

import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.StringType;

import java.util.Objects;

public class StringValue implements IValue {
    String s;
    public StringValue(String s1)
    {
        s = s1;
    }

    public String getString()
    {
        return s;
    }

    public IType getType()
    {
        return new StringType();
    }

    @Override
    public String toString() {
        return s.toString();
    }

    @Override
    public boolean equals(IValue v1)
    {
        return v1 instanceof StringValue && Objects.equals(((StringValue) v1).getString(), s);
    }


}
