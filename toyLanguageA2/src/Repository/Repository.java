package Repository;

import Domain.adt.MyList;
import Domain.state.PrgState;
import Exceptions.ADTException;
import Exceptions.ProgramException;

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
