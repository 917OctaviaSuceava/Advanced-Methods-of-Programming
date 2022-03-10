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
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStmt {
    private Exp exp;

    public OpenRFile(){}

    public OpenRFile(Exp exp1)
    {
        exp = exp1;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "OpenRFile(" + exp.toString() + ')';
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException, FileNotFoundException
    {
        IValue res = exp.eval(state.getSymTable(), state.getHeap());
        if(res.getType().equals(new StringType()))
        {
            StringValue string_res = (StringValue) res;
            if(state.getFileTable().isDefined(string_res.getString()))
                throw new ProgramException("File already exists!");
            try
            {
                BufferedReader f = new BufferedReader(new FileReader(string_res.getString()));
                state.getFileTable().add(string_res.toString(), f);
            }
            catch(IOException e)
            {
                //throw new ProgramException(e.getMessage());
                throw new ProgramException("File cannot be opened!");
            }
        }
        else throw new ProgramException("Should be a string!");
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
        return new OpenRFile(exp.deepCopy());
    }
     */
}
