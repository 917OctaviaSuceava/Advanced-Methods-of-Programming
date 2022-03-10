package Domain.state;

import Domain.adt.*;
import Domain.stmt.IStmt;
import Domain.values.IValue;
import Domain.values.StringValue;
import Exceptions.ADTException;
import Exceptions.ProgramException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class PrgState {
    MyStack<IStmt> exeStack;
    MyDict<String, IValue> symTable;
    MyList<IValue> out;
    MyDict<String, BufferedReader> fileTable;
    IHeap heap;
    IStmt originalProgram;
    private int id;
    private static int previous_id = 1;

    public synchronized void setId(){
        previous_id++;
        id = previous_id;
        //id++;
    }

    public PrgState(MyStack<IStmt> stk, MyDict<String,IValue> symtbl, MyList<IValue> ot, MyDict<String, BufferedReader> ft, IStmt prg,
                    IHeap h)
    {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        originalProgram = prg; //recreate the entire original prg
        heap = h;
        id = previous_id;

        exeStack.push(prg);
    }

    public PrgState(MyStack<IStmt> stk, MyDict<String,IValue> symtbl, MyList<IValue> ot, MyDict<String, BufferedReader> ft,
                    IHeap h)
    {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        //id = get_new_id();
        id = previous_id;
        // originalProgram = prg; //recreate the entire original prg
        heap = h;

        //exeStack.push(prg);
    }

    public PrgState(IStmt prg)
    {
        exeStack = new MyStack<>();
        symTable = new MyDict<>();
        out = new MyList<>();
        fileTable = new MyDict<>();
        heap = new MyHeap();
        originalProgram = prg; //recreate the entire original prg
        id = previous_id;

        exeStack.push(prg);
    }

    public synchronized int get_id(){
        return id;
    }


    public IHeap getHeap()
    {
        return heap;
    }

    public MyStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public MyDict<String, IValue> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyDict<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public MyList<IValue> getOut() {
        return out;
    }

    public void setOut(MyList<IValue> out) {
        this.out = out;
    }

    public MyDict<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyDict<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }


    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws ProgramException, FileNotFoundException {
        if(exeStack.isEmpty())
            throw new ProgramException("Program state stack is empty!");
        IStmt crt = exeStack.pop();
        return crt.execute(this);
    }

    @Override
    public String toString()
    {
        return " PROGRAM STATE: \n" +
                " > execution stack = " + exeStack.toString() +
                "\n > id = " + id +
                "\n > symbol table = " + symTable.toString() +
                "\n > file table = " + fileTable.toString() +
                "\n > heap = " + heap.toString() +
                "\n > output = " + out.toString() + "\n";
    }


}
