package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.exp.Exp;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.RefType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.RefValue;
import com.example.demo.Exceptions.ProgramException;

import java.io.FileNotFoundException;

public class HeapAllocation implements IStmt {
    String var_name;
    Exp exp;

    public HeapAllocation(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public String toString()
    {
        return "new(" + var_name + ", " + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException, FileNotFoundException {
        MyDict<String, IValue> symTable = state.getSymTable();
        if(!symTable.isDefined(var_name))
            throw new ProgramException("Variable not found!");
        if(!(symTable.get(var_name) instanceof RefValue))
            throw new ProgramException("The variable should be RefType!");

        RefValue refValue = (RefValue)symTable.get(var_name);
        IValue res = exp.eval(symTable, state.getHeap());

        IType resType = res.getType();
        IType locType = refValue.getLocationType();

        if(!resType.equals(locType))
            throw new ProgramException("The types are not compatible!");
        IHeap heap = state.getHeap();
        heap.add(res);
        symTable.update(var_name, new RefValue(heap.getFreeAddr()-1, ((RefValue) symTable.get(var_name)).getLocationType()));
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        IType typevar = typeEnv.get(var_name);
        IType typexp = exp.typeCheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else throw new Exception("NEW stmt: right hand side and left hand side have different types ");
    }


}
