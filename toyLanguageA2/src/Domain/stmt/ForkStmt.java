package Domain.stmt;

import Domain.adt.IDict;
import Domain.adt.IStack;
import Domain.adt.MyDict;
import Domain.adt.MyStack;
import Domain.state.PrgState;
import Domain.types.IType;
import Domain.values.IValue;
import Exceptions.ProgramException;

import java.io.FileNotFoundException;

public class ForkStmt implements IStmt {

    IStmt forkStmt;

    public ForkStmt(IStmt fork_var)
    {
        this.forkStmt = fork_var;
    }

    public IStmt create_copy()
    {
        return new ForkStmt(forkStmt);
    }

    @Override
    public PrgState execute(PrgState programState) throws ProgramException, FileNotFoundException {

        MyDict<String, IValue> symTableCopy = programState.getSymTable().create_copy();
        MyStack<IStmt> newExeStack = new MyStack<>();
        newExeStack.push(this.forkStmt);
        PrgState newPrgState = new PrgState(newExeStack, symTableCopy, programState.getOut(),
                programState.getFileTable(), programState.getHeap());
        System.out.println(newPrgState.toString());
        newPrgState.setId();
        return newPrgState;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        forkStmt.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){
        return "fork(" + forkStmt.toString() +")";
    }
}