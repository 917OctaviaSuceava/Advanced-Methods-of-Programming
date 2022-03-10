package com.example.demo.Repository;

import com.example.demo.Domain.adt.MyList;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Exceptions.ADTException;
import com.example.demo.Exceptions.ProgramException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    List<PrgState> prgStateList;
    private String logFilePath;

    public Repository()
    {
        prgStateList = new ArrayList<>();
    }

    public Repository(String logFilePath){
        prgStateList = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    public Repository(List<PrgState> newList) {
        prgStateList = newList;
    }

    public Repository(String logFilePath, List<PrgState> prgStateList)
    {
        this.logFilePath = logFilePath;
        this.prgStateList = prgStateList;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public PrgState get_prg_id(int id)
    {
        for(PrgState prgState: prgStateList)
            if(prgState.get_new_id() == id)
                return prgState;
        return null;
    }

    @Override
    public PrgState getCurrentProgram() {
        return prgStateList.get(prgStateList.size() - 1);
    }

    public List<PrgState> getPrgStateList()
    {
        return prgStateList;
    }

    public void setPrgStateList(List<PrgState> new_list)
    {
        prgStateList = new_list;
    }

    public void add(PrgState state) throws ADTException
    {
        prgStateList.add(state);
    }

    public void logPrgStateExec(PrgState prgState) throws ProgramException
    {
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(prgState.toString());
            logFile.write("\n");
            logFile.close();
        }
        catch (IOException e)
        {
            throw new ProgramException("File could not be opened!");
        }
    }
}
