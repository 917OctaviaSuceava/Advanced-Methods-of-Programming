package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyDict;
import Domain.types.IType;
import Domain.values.IValue;

public class ValueExp implements Exp {
    IValue e;

    public ValueExp(IValue e1)
    {
        e = e1;
    }

    @Override
    public IValue eval(MyDict<String, IValue> table, IHeap heap) {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public IType typeCheck(MyDict<String, IType> typeEnv) throws Exception
    {
        return e.getType();
    }

    /*
    @Override
    public Exp deepCopy() {
        return new ValueExp(e.deepCopy());
    }
     */
}
