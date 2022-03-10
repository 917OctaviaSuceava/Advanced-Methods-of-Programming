package Domain.types;

import Domain.values.IValue;
import Domain.values.IntValue;

public class IntType implements IType {
    public IntType() {}

    @Override
    public boolean equals(IType v) {
        return v instanceof IntType;
    }

    public String toString()
    {
        return "int";
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
