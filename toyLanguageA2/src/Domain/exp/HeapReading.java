package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyDict;
import Domain.types.IType;
import Domain.types.IntType;
import Domain.types.RefType;
import Domain.values.IValue;
import Domain.values.RefValue;
import Exceptions.DivisionByZero;
import Exceptions.InvalidOperand;
import Exceptions.ProgramException;

public class HeapReading implements Exp {
    Exp exp;

    public HeapReading(Exp e)
    {
        exp = e;
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }

    @Override
    public IType typeCheck(MyDict<String, IType> typeEnv) throws Exception
    {
        IType typ = exp.typeCheck(typeEnv);
        if (typ instanceof RefType)
        {
            RefType reft =(RefType) typ;
            return reft.getInner();
        }
        else throw new Exception("the rH argument is not a Ref Type");

    }

    @Override
    public IValue eval(MyDict<String, IValue> table, IHeap heap) throws DivisionByZero, ProgramException, InvalidOperand {
        IValue res = exp.eval(table, heap);
        if(!(res instanceof RefValue))
            throw new ProgramException("It should be a RefType!");
        int res_addr = ((RefValue) res).getAddress();
        if(!heap.isDefined(res_addr))
            throw new ProgramException("That address does not exist!");
        return heap.get(res_addr);
    }
}
