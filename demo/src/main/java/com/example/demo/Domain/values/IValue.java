package com.example.demo.Domain.values;

import com.example.demo.Domain.types.IType;

public interface IValue {
    boolean equals(IValue v1);
    IType getType();
}
