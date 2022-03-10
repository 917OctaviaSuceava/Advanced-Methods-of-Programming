package Domain.types;

import Domain.values.BoolValue;
import Domain.values.IValue;
import Domain.values.StringValue;

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
