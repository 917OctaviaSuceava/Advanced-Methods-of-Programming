package Domain.stmt;

import Domain.adt.MyDict;
import Domain.state.PrgState;
import Domain.types.IType;
import Exceptions.ProgramException;

import java.io.FileNotFoundException;

public interface IStmt {
    PrgState execute(PrgState state) throws ProgramException, FileNotFoundException;
    String toString();
    MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception;
    //IStmt deepCopy();
}
