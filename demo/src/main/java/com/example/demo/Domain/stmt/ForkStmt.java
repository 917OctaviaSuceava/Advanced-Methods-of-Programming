package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.IDict;
import com.example.demo.Domain.adt.IStack;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.adt.MyStack;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.ProgramException;

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