package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.exp.Exp;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.ProgramException;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp e)
    {
        exp = e;
    }

    public String toString()
    {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws ProgramException
    {
        try {
            IValue e = exp.eval(state.getSymTable(), state.getHeap());
            state.getOut().add(e);
        }
        catch (ProgramException e)
        {
            throw new ProgramException(e.getMessage());
        }
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
     */
}
