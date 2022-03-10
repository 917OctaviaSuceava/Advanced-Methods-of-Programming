package Domain.stmt;

import Domain.adt.MyDict;
import Domain.exp.Exp;
import Domain.state.PrgState;
import Domain.types.IType;
import Domain.types.StringType;
import Domain.values.IValue;
import Domain.values.StringValue;
import Exceptions.ProgramException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CloseRFile implements IStmt {
    private Exp exp;

    public CloseRFile(){}

    public CloseRFile(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "CloseRFile(" + exp.toString() + ')';
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException, FileNotFoundException {
        MyDict<String, IValue> symTable = state.getSymTable();
        MyDict<String, BufferedReader> fileTable = state.getFileTable();

        IValue value = exp.eval(symTable, state.getHeap());
        if(!value.getType().equals(new StringType()))
            throw new ProgramException("Should be a string!");
        StringValue fileName = (StringValue) value;
        if(!fileTable.isDefined(fileName.toString()))
            throw new ProgramException("That file is not defined!");
        BufferedReader fileDesc = fileTable.get(fileName.toString());
        try {
            fileDesc.close();
        }
        catch(IOException e)
        {
            throw new ProgramException("File couldn't be closed!");
        }
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        IType typeExp = exp.typeCheck(typeEnv);
        if (typeExp.equals(new StringType()))
            return typeEnv;
        else throw new Exception("Expression should be a string");
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new CloseRFile(exp.deepCopy());
    }
     */
}
