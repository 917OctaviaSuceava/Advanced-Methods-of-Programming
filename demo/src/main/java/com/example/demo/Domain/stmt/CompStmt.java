package com.example.demo.Domain.stmt;

import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.adt.*;
import com.example.demo.Domain.types.IType;
import com.example.demo.Exceptions.ProgramException;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt f, IStmt s)
    {
        first = f;
        second = s;
    }

    public String toString()
    {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException
    {
        IStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }
     */
}
