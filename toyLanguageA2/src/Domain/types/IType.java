package Domain.types;

import Domain.values.IValue;

public interface IType {
    boolean equals(IType v);
    IValue defaultValue();

}
