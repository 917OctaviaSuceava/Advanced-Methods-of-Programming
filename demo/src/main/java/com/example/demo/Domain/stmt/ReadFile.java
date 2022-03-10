package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.exp.Exp;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.types.IntType;
import com.example.demo.Domain.types.StringType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Domain.values.IntValue;
import com.example.demo.Domain.values.StringValue;
import com.example.demo.Exceptions.ProgramException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile implements IStmt {
    private Exp exp;
    private String var_name;

    public ReadFile(){}

    public ReadFile(Exp exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public String getVar_name() {
        return var_name;
    }

    public void setVar_name(String var_name) {
        this.var_name = var_name;
    }

    @Override
    public String toString() {
        return "ReadFile(" + exp.toString() +", "+ var_name +")";
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException, FileNotFoundException {
        MyDict<String, IValue> symTable = state.getSymTable();
        MyDict<String, BufferedReader> fileTable = state.getFileTable();
        if(!symTable.isDefined(var_name))
            throw new ProgramException("That variable doesn't exist!");
        if(!symTable.get(var_name).getType().equals(new IntType()))
            throw new ProgramException("The variable should be int!");
        IValue value = exp.eval(symTable, state.getHeap());
        if(!value.getType().equals(new StringType()))
            throw new ProgramException("Should be a string!");
        StringValue fileName = (StringValue) value;
        if(!fileTable.isDefined(fileName.toString()))
            throw new ProgramException("That file is not defined!");
        BufferedReader fileDesc = fileTable.get(fileName.toString());
        int val;
        try {
            String line = fileDesc.readLine();
            if(line == null)
                val = 0;
            else val = Integer.parseInt(line);
        }
        catch(IOException e)
        {
            throw new ProgramException("File couldn't be read!");
        }
        symTable.update(var_name, new IntValue(val));
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        IType typevar = typeEnv.get(var_name);
        IType typexp = exp.typeCheck(typeEnv);
        if (typevar instanceof IntType)
        {
            if(typexp instanceof StringType)
                return typeEnv;
            else throw new Exception("Expression should be a string!");
        }
        else throw new Exception("Var name should be an integer!");
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new ReadFile(exp.deepCopy(), var_name);
    }
     */
}
