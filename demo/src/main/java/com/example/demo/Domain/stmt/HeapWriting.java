package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.exp.Exp;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.RefType;
import com.example.demo.Domain.types.StringType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.RefValue;
import com.example.demo.Exceptions.ProgramException;

import java.io.FileNotFoundException;

public class HeapWriting implements IStmt {
    String var_name;
    Exp exp;
    public HeapWriting(String v, Exp e)
    {
        var_name = v;
        exp = e;
    }

    @Override
    public String toString() {
        return "wH(" + var_name + ", " + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException, FileNotFoundException {
        MyDict<String, IValue> symTable = state.getSymTable();
        IHeap heap = state.getHeap();

        if(!symTable.isDefined(var_name))
            throw new ProgramException("Variable not found!");
        if(!(symTable.get(var_name) instanceof RefValue))
            throw new ProgramException("The variable should be RefType!");
        if(!heap.isDefined(((RefValue)symTable.get(var_name)).getAddress()))
            throw new ProgramException("The address is not in the heap!");

        IValue res = exp.eval(symTable, heap);
        RefValue refCastVar= (RefValue) symTable.get(var_name);
        if(!res.getType().equals(refCastVar.getLocationType()))
            throw new ProgramException("Incompatible types!");

        heap.update(refCastVar.getAddress(), res);
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        IType typeExp = exp.typeCheck(typeEnv);
        IType typeVar = typeEnv.get(var_name);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else throw new Exception("Expression should be a reference!");
    }
}
