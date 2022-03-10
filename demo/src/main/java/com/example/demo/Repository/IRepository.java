package com.example.demo.Repository;

import com.example.demo.Domain.adt.IDict;
import com.example.demo.Domain.adt.MyList;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Exceptions.ADTException;
import com.example.demo.Exceptions.ProgramException;

import java.util.List;

public interface IRepository {
    PrgState getCurrentProgram();
    void add(PrgState state) throws ADTException;
    List<PrgState> getPrgStateList();
    void setPrgStateList(List<PrgState> new_list);
    void logPrgStateExec(PrgState prgState) throws ProgramException;
    String getLogFilePath();

    PrgState get_prg_id(int id);
}
