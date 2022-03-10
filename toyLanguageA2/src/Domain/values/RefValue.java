package Domain.values;

import Domain.types.IType;
import Domain.types.RefType;

public class RefValue implements IValue {
    int address;
    IType locationType;

    public RefValue(int address, IType locationType)
    {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress()
    {
        return address;
    }

    public IType getLocationType()
    {
        return locationType;
    }

    public String toString()
    {
        return "(" + String.valueOf(address) + ", " + locationType.toString() + ")";
    }

    @Override
    public boolean equals(IValue v1) {
        return v1 instanceof RefValue && ((RefValue) v1).getAddress() == address &&
                locationType.equals(((RefValue) v1).getType());
    }

    @Override
    public IType getType()
    {
        return new RefType(locationType);
    }
}
