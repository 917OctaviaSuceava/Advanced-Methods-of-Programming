package Domain.exp;

import Domain.adt.IHeap;
import Domain.adt.MyDict;
import Domain.types.IType;
import Domain.values.IValue;
import Exceptions.DivisionByZero;
import Exceptions.InvalidOperand;
import Exceptions.ProgramException;

public interface Exp {
    IValue eval(MyDict<String, IValue> table, IHeap heap) throws DivisionByZero, ProgramException, InvalidOperand;
    String toString();
    IType typeCheck(MyDict<String, IType> typeEnv) throws Exception;
    //Exp deepCopy();
}
