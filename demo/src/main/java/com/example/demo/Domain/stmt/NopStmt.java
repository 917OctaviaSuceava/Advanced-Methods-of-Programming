package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.RefType;

public class NopStmt implements IStmt {
    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) {
        return state;
    }

    public String toString()
    {
        return "nop()";
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
            return typeEnv;
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
    */
}
