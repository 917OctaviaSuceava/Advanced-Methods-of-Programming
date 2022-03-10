package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Exceptions.ProgramException;

import java.io.FileNotFoundException;

public interface IStmt {
    PrgState execute(PrgState state) throws ProgramException, FileNotFoundException;
    String toString();
    MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception;
    //IStmt deepCopy();
}
