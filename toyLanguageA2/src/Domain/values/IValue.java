package Domain.values;

import Domain.types.IType;

public interface IValue {
    boolean equals(IValue v1);
    IType getType();
}
