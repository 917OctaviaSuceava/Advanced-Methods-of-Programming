package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Exceptions.ADTException;
import com.example.demo.Exceptions.ProgramException;
import com.example.demo.Exceptions.UndeclaredVariable;

public class VarDeclStmt implements IStmt {
    String name;
    IType type;

    public VarDeclStmt(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public IType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException {
        if(state.getSymTable().isDefined(name))
            throw new UndeclaredVariable("The variable " + name + " is already defined!");
        state.getSymTable().add(name, type.defaultValue());
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        typeEnv.add(name, type);
        return typeEnv;
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, type.deepCopy());
    }
     */
}
