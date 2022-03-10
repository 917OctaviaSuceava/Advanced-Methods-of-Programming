package Domain.stmt;

import Domain.adt.MyDict;
import Domain.state.PrgState;
import Domain.types.IType;
import Domain.types.RefType;

public class NopStmt implements IStmt {
    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) {
        return state;
    }

    public String toString()
    {
        return "nop()";
    }

    @Override
    public MyDict<String, IType> typeCheck(MyDict<String, IType> typeEnv) throws Exception {
            return typeEnv;
    }

    /*
    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
    */
}
