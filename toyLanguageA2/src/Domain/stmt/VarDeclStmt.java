package Domain.stmt;

import Domain.adt.MyDict;
import Domain.state.PrgState;
import Domain.types.IType;
import Exceptions.ADTException;
import Exceptions.ProgramException;
import Exceptions.UndeclaredVariable;

public class VarDeclStmt implements IStmt {
    String name;
    IType type;

    public VarDeclStmt(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public IType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws ProgramException {
        if(state.getSymTable().isDefined(name))
            throw new UndeclaredVariable("The variable " + name + " is already defined!");
        state.getSymTable().add(name, type.defaultValue());
        return null;
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
        typeEnv.add(name, type);
        return typeEnv;
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, type.deepCopy());
    }
     */
}
