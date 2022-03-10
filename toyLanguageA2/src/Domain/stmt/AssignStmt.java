package Domain.stmt;

import Domain.adt.MyDict;
import Domain.adt.MyStack;
import Domain.exp.Exp;
import Domain.state.PrgState;
import Domain.types.IType;
import Domain.values.IValue;
import Exceptions.InvalidOperand;
import Exceptions.ProgramException;
import Exceptions.TypeException;
import Exceptions.UndeclaredVariable;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt(String i, Exp e)
    {
        id = i;
        exp = e;
    }

    public String toString()
    {
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws ProgramException {
        try {
            MyStack<IStmt> stk = state.getExeStack();
            MyDict<String, IValue> symTbl = state.getSymTable();
            if (symTbl.isDefined(id)) {
                IValue val = exp.eval(symTbl, state.getHeap());
                IType typId = (symTbl.get(id)).getType();
                if ((val.getType()).equals(typId))
                    symTbl.update(id, val);
                else
                    throw new TypeException("Declared type of variable" + id + " and type of the assigned expression do not match!");
            } else throw new UndeclaredVariable("The used variable" + id + " was not declared before");
        }
        catch(ProgramException e)
        {
            throw new ProgramException(e.getMessage());
        }
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        IType typevar = typeEnv.get(id);
        IType typexp = exp.typeCheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new Exception("Assignment: right hand side and left hand side have different types!");
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }
     */

}
