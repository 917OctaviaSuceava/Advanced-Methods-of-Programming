package com.example.demo.View;

import com.example.demo.Controller.Controller;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Exceptions.ProgramException;

import java.io.FileNotFoundException;

public class RunExample extends Command {
    private Controller ctr;

    public RunExample(String key, String desc, Controller ctr)
    {
        super(key, desc);
        this.ctr = ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.getRepo().getPrgStateList().get(0).getExeStack().getFirst().typeCheck(new MyDict<>());
            ctr.allSteps();
        }
        /*catch() //InterruptedException | ProgramException e
        {
            System.out.println(e.getMessage());
        } */
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            //exception.printStackTrace();
        }
    }
}
