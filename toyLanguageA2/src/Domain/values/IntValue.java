package Domain.values;

import Domain.types.IType;
import Domain.types.IntType;

public class IntValue implements IValue {
    int val;

    public IntValue(int v)
    {
        val = v;
    }

    public int getVal()
    {
        return val;
    }

    public String toString()
    {
        return String.valueOf(val);
    }

    public IType getType()
    {
        return new IntType();
    }

    @Override
    public boolean equals(IValue v1)
    {
        return v1 instanceof IntValue && ((IntValue) v1).getVal() == val;
    }

}
