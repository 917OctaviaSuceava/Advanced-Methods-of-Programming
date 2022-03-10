package com.example.demo.Domain.types;

import com.example.demo.Domain.values.BoolValue;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.StringValue;

public class StringType implements IType {
    public StringType(){}

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public boolean equals(IType v) {
        return v instanceof StringType;
    }

    public String toString()
    {
        return "string";
    }

}
