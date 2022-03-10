package Controller;

import Domain.adt.IList;
import Domain.adt.IStack;
import Domain.adt.MyList;
import Domain.state.PrgState;
import Domain.stmt.*;
import Exceptions.ADTException;
import Exceptions.DivisionByZero;
import Exceptions.ProgramException;
import Repository.IRepository;
import Repository.Repository;
import Controller.GarbageCollector;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private ExecutorService executor;

    public Controller()
    {
        repository = new Repository();
    }

    public Controller(IRepository repo) {
        repository = repo;
    }

    public IRepository getRepo()
    {
        return repository;
    }

    /*public Object oneStep(PrgState state) throws ProgramException, FileNotFoundException {
        IStack<IStmt> stk = state.getExeStack();
        IStmt currentStmt = stk.pop();
        return currentStmt.execute(state);
    }

    public void allSteps() throws ProgramException, FileNotFoundException {
        PrgState prg = repository.getCurrentProgram();
        repository.logPrgStateExec();
        System.out.println(prg.toString());

        while(!prg.getExeStack().isEmpty())
        {
            oneStep(prg);
            repository.logPrgStateExec();
            prg.getHeap().setContent(GarbageCollector.unsafeGarbageCollector(
                    GarbageCollector.getAddrFromSymTable(prg.getSymTable().getDict().values()),
                    prg.getHeap().get()));
            System.out.println(prg.toString());
        }
    }*/

    public void oneStepForAllPrg(List<PrgState> prgStateList) throws InterruptedException, ProgramException {
        /*prgStateList.forEach(prg-> {
            try {
                repository.logPrgStateExec(prg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
        List<Callable<PrgState>> callList=prgStateList.stream(
        ).map((PrgState p)->(Callable<PrgState>)(p::oneStep)).collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future-> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        prgStateList.addAll(newPrgList);
        prgStateList.forEach(
                p->{
                    try {
                        repository.logPrgStateExec(p);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        );
        repository.setPrgStateList(prgStateList);
    }

    public void allSteps() throws InterruptedException, ProgramException {
        executor= Executors.newFixedThreadPool(2);

        List<PrgState> programStates = removeCompletedPrg(repository.getPrgStateList());

        while (programStates.size() > 0)
        {
            GarbageCollector.conservativeGarbageCollector(programStates);
            try {
                oneStepForAllPrg(programStates);
            }
            catch (ProgramException exception)
            {
                exception.printStackTrace();
            }
            for(PrgState prg: programStates) {
                System.out.println(prg.toString());
            }
            programStates = removeCompletedPrg(repository.getPrgStateList());
        }
        executor.shutdownNow();
        repository.setPrgStateList(programStates);
    }



    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }


    public void addProgram(PrgState newPrg) throws ADTException {
        repository.add(newPrg);
    }

}
