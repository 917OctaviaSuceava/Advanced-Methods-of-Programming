package Domain.types;

import Domain.values.BoolValue;
import Domain.values.IValue;

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
