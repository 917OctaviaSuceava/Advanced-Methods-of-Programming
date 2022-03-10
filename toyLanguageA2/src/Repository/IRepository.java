package Repository;

import Domain.adt.IDict;
import Domain.adt.MyList;
import Domain.state.PrgState;
import Exceptions.ADTException;
import Exceptions.ProgramException;

import java.util.List;

public interface IRepository {
    PrgState getCurrentProgram();
    void add(PrgState state) throws ADTException;
    List<PrgState> getPrgStateList();
    void setPrgStateList(List<PrgState> new_list);
    void logPrgStateExec(PrgState prgState) throws ProgramException;

}
