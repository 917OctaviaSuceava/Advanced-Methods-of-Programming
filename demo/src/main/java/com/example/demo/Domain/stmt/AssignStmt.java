package com.example.demo.Domain.stmt;

import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.adt.MyStack;
import com.example.demo.Domain.exp.Exp;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.types.IType;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.InvalidOperand;
import com.example.demo.Exceptions.ProgramException;
import com.example.demo.Exceptions.TypeException;
import com.example.demo.Exceptions.UndeclaredVariable;

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
