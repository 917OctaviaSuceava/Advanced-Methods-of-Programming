package Domain.stmt;

import Domain.adt.MyDict;
import Domain.exp.Exp;
import Domain.state.PrgState;
import Domain.types.IType;
import Domain.values.IValue;
import Exceptions.ProgramException;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp e)
    {
        exp = e;
    }

    public String toString()
    {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws ProgramException
    {
        try {
            IValue e = exp.eval(state.getSymTable(), state.getHeap());
            state.getOut().add(e);
        }
        catch (ProgramException e)
        {
            throw new ProgramException(e.getMessage());
        }
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
     */
}
