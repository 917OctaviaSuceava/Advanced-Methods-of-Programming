package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.exp.Exp;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.BoolType;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.values.BoolValue;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.ProgramException;

import java.io.FileNotFoundException;

public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "while(" + exp.toString() + ") { " + stmt.toString() + " } ";
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException, FileNotFoundException {
        MyDict<String, IValue> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        IValue res = exp.eval(symTable, heap);

        if(!res.getType().equals(new BoolType()))
            throw new ProgramException("It should be a boolean!");

        if(((BoolValue) res).getVal() == true) {
            state.getExeStack().push(this);
            state.getExeStack().push(stmt);
        }
        //else state.getExeStack().pop();
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        IType typexp = exp.typeCheck(typeEnv);
        if (typexp.equals(new BoolType()))
            return typeEnv;
        else throw new Exception("The condition of IF does not have the type bool");
    }
}
