package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.exp.Exp;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.BoolType;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.values.BoolValue;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.ProgramException;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenStatement;
    IStmt elseStatement;

    public IfStmt(Exp e, IStmt t, IStmt el)
    {
        exp = e;
        thenStatement = t;
        elseStatement = el;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN (" + thenStatement.toString() + ") ELSE (" + elseStatement.toString() + "))";
    }

    public PrgState execute(PrgState state) throws ProgramException
    {
        try {
            IValue res = exp.eval(state.getSymTable(), state.getHeap());
            if (res.getType().equals(new BoolType())) {
                BoolValue result = (BoolValue) res;
                if (result.getVal())
                    state.getExeStack().push(thenStatement);
                else state.getExeStack().push(elseStatement);
            }
        }
        catch(ProgramException e)
        {
            throw new ProgramException(e.getMessage());
        }
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        IType typexp = exp.typeCheck(typeEnv);
        if (typexp.equals(new BoolType()))
        {
            thenStatement.typeCheck(typeEnv.create_copy());
            elseStatement.typeCheck(typeEnv.create_copy());
            return typeEnv;
        }
        else
            throw new Exception("The condition of IF does not have the type bool");
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }
     */
}
