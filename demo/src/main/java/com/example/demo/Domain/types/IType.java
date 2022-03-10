package com.example.demo.Domain.types;

import com.example.demo.Domain.values.IValue;

public interface IType {
    boolean equals(IType v);
    IValue defaultValue();

}
