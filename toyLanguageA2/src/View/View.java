package View;

import Controller.Controller;
import Domain.adt.MyList;
import Domain.exp.*;
import Domain.state.PrgState;
import Domain.stmt.*;
import Domain.types.BoolType;
import Domain.types.IntType;
import Domain.types.RefType;
import Domain.types.StringType;
import Domain.values.BoolValue;
import Domain.values.IntValue;
import Domain.values.StringValue;
import Exceptions.ADTException;
import Exceptions.ProgramException;
import Repository.IRepository;
import Repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Controller controller;

    public View()
    {
        //run();
        //IRepository myRepository = new Repository();
        controller = new Controller();
        //controller = new Controller();
    }

    //public View(Controller controller) {
     //   this.controller = controller;
    //}

    public void menu()
    {
        System.out.println("\n");
        System.out.println("""
                Choose an option:
                 0 - exit
                 
                 1 - execute the following example:\s
                 >>>>> int v; v = 2; print(v)
                 
                 2 - execute the following example:
                 >>>>> int a; int b; a=2+3*5; b=a+1; print(b)
                 
                 3 - execute the following example:
                 >>>>> bool a; int v; a=true; (if a then v=2 else v=3); print(v)
                 
                 4 - execute example that throws an exception (Division by zero):
                 >>>>> int a; int b; int c; a=2; b=0; c=a/b; print(c)
                 
                 5 - execute example that throws an exception (Invalid Operand):
                 >>>>> int a; int b; int c; a=4; b=3; c=a%b; print(c)
                 
                 6 -  execute example that throws an exception (UndeclaredVariable):
                 >>>>> int a; int b; int a; print(a)
                 
                 7 - execute example that throws an exception (UndeclaredVariable):
                 >>>>> int a; print(b)
                 
                 8 - execute the following file example:
                 >>>>> string varf; varf = test.in; OpenRFile(varf); int varc; readFile(varf,varc); print(varc);
                 readFile(varf,varc); print(varc); CloseRFile(varf)
                 
                 9 - execute:
                 >>>>> int a; int b; a=4; b=7; print(a>b)
                 
                """);
    }

    public IStmt option9()
    {
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(4))),
                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(7))),
                                        new PrintStmt(new RelationalExp(new VarExp("a"), new VarExp("b"), ">"))))));
    }

    public IStmt option1()
    {
        return new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
    }

    public IStmt option2()
    {
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)),
                                    new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt("b", new ArithExp('+',
                                            new VarExp("a"), new ValueExp(new IntValue(1)))),
                                            new PrintStmt(new VarExp("b"))))));
    }

    public IStmt option3()
    {
        return new CompStmt(new VarDeclStmt("a", new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                    new CompStmt(new IfStmt(new VarExp("a"),
                                            new AssignStmt("v", new ValueExp(new IntValue(2))),
                                            new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                            new PrintStmt(new VarExp("v"))))));
    }

    public IStmt option4()
    { //int a; int b; int c; a=2; b=0; c=a/b; print(c)
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new VarDeclStmt("c", new IntType()),
                                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                            new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(0))),
                                                    new CompStmt(new AssignStmt("c", new ArithExp('/', new VarExp("a"),
                                                            new VarExp("b"))), new PrintStmt(new VarExp("c"))))))));

    }

    public IStmt option5()
    { //int a; int b; int c; a=4; b=3; c=a%b; print(c)
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new VarDeclStmt("c", new IntType()),
                                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                            new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(0))),
                                                    new CompStmt(new AssignStmt("c", new ArithExp('%', new VarExp("a"),
                                                            new VarExp("b"))), new PrintStmt(new VarExp("c"))))))));

    }

    public IStmt option6()
    { //int a; int b; int a; print(a)
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new VarDeclStmt("a", new IntType()),
                                    new PrintStmt(new VarExp("a")))));
    }

    public IStmt option7()
    { //int a; print(b)
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                    new PrintStmt(new VarExp("b")));
    }

    public IStmt option8()
    {
        return new CompStmt(new VarDeclStmt("varf", new StringType()),
                        new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\test.in"))),
                            new CompStmt(new VarDeclStmt("varc", new IntType()),
                                    new CompStmt(new OpenRFile(new VarExp("varf")),
                                            new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                    new CompStmt(new PrintStmt(new VarExp("varc")),
                                                            new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                    new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                            new CloseRFile(new VarExp("varf"))))))))));
    }

    public IStmt option10()
    {
        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        return new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocation("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
    }

    public IStmt option11()
    {
        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        return new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocation("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new HeapReading(new HeapReading(new VarExp("a"))),
                                                        new ValueExp(new IntValue(5)))))))));
    }

    public IStmt option12()
    {
        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        return new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))),
                                new CompStmt(new HeapWriting("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new HeapReading(new VarExp("v")),
                                                new ValueExp(new IntValue(5))))))));
    }

    public IStmt option13()
    {
        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        return new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocation("a", new VarExp("v")),
                                        new CompStmt(new HeapAllocation("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new HeapReading(new HeapReading(new VarExp("a")))))))));
    }

    public IStmt option14()
    {
        //int v; v=4; (while (v>0) print(v); v=v-1); print(v)
        return new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(0)),">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
    }

    public IStmt option15()
    {
        // int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))

        return new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocation("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new HeapWriting("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new HeapReading(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new HeapReading(new VarExp("a")))))))));
    }

    public IStmt option16()
    { //string a; int b; int c; a=2; b=0; c=a+b; print(c)
        return new CompStmt(new VarDeclStmt("a", new StringType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new VarDeclStmt("c", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(0))),
                                                new CompStmt(new AssignStmt("c", new ArithExp('+', new VarExp("a"),
                                                        new VarExp("b"))), new PrintStmt(new VarExp("c"))))))));

    }

    public void run() throws ADTException {
        /*
        MyList<PrgState> prg = new MyList<>();

            prg.add(new PrgState(ex));
            IRepository repo = new Repository(prg);
            Controller ctrl = new Controller(repo);
            ctrl.allSteps();
         */
        IStmt ex1 = option1();
        PrgState prg1 = new PrgState(ex1);
        IRepository repo1 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log1.txt");
        Controller ctr1 = new Controller(repo1);
        ctr1.addProgram(prg1);

        IStmt ex2 = option2();
        PrgState prg2 = new PrgState(ex2);
        IRepository repo2 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log2.txt");
        Controller ctr2 = new Controller(repo2);
        ctr2.addProgram(prg2);


        IStmt ex3 = option3();
        PrgState prg3 = new PrgState(ex3);
        IRepository repo3 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log3.txt");
        Controller ctr3 = new Controller(repo3);
        ctr3.addProgram(prg3);

        IStmt ex4 = option4();
        PrgState prg4 = new PrgState(ex4);
        IRepository repo4 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log4.txt");
        Controller ctr4 = new Controller(repo4);
        ctr4.addProgram(prg4);

        IStmt ex5 = option5();
        PrgState prg5=new PrgState(ex5);
        IRepository repo5=new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log5.txt");
        Controller ctr5=new Controller(repo5);
        ctr5.addProgram(prg5);

        IStmt ex6 = option6();
        PrgState prg6 = new PrgState(ex6);
        IRepository repo6 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log6.txt");
        Controller ctr6 = new Controller(repo6);
        ctr6.addProgram(prg6);

        IStmt ex7 = option7();
        PrgState prg7 = new PrgState(ex7);
        IRepository repo7 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log7.txt");
        Controller ctr7 = new Controller(repo7);
        ctr7.addProgram(prg7);

        IStmt ex8 = option8();
        PrgState prg8 = new PrgState(ex8);
        IRepository repo8 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log8.txt");
        Controller ctr8 = new Controller(repo8);
        ctr8.addProgram(prg8);

        IStmt ex9 = option9();
        PrgState prg9 = new PrgState(ex9);
        IRepository repo9 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log9.txt");
        Controller ctr9 = new Controller(repo9);
        ctr9.addProgram(prg9);

        IStmt ex10 = option10();
        PrgState prg10 = new PrgState(ex10);
        IRepository repo10 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log10.txt");
        Controller ctr10 = new Controller(repo10);
        ctr10.addProgram(prg10);

        IStmt ex11 = option11();
        PrgState prg11 = new PrgState(ex11);
        IRepository repo11 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log11.txt");
        Controller ctr11 = new Controller(repo11);
        ctr11.addProgram(prg11);

        IStmt ex12 = option12();
        PrgState prg12 = new PrgState(ex12);
        IRepository repo12 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log12.txt");
        Controller ctr12 = new Controller(repo12);
        ctr12.addProgram(prg12);

        IStmt ex13 = option13();
        PrgState prg13 = new PrgState(ex13);
        IRepository repo13 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log13.txt");
        Controller ctr13 = new Controller(repo13);
        ctr13.addProgram(prg13);

        IStmt ex14 = option14();
        PrgState prg14 = new PrgState(ex14);
        IRepository repo14 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log14.txt");
        Controller ctr14 = new Controller(repo14);
        ctr14.addProgram(prg14);

        IStmt ex15 = option15();
        PrgState prg15 = new PrgState(ex15);
        IRepository repo15 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log15.txt");
        Controller ctr15 = new Controller(repo15);
        ctr15.addProgram(prg15);

        IStmt ex16 = option16();
        PrgState prg16 = new PrgState(ex16);
        IRepository repo16 = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\toyLanguageA2\\src\\log15.txt");
        Controller ctr16 = new Controller(repo16);
        ctr16.addProgram(prg16);

        TextMenu menu = new TextMenu();

        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctr9));
        menu.addCommand(new RunExample("10",ex10.toString(),ctr10));
        menu.addCommand(new RunExample("11",ex11.toString(),ctr11));
        menu.addCommand(new RunExample("12",ex12.toString(),ctr12));
        menu.addCommand(new RunExample("13",ex13.toString(),ctr13));
        menu.addCommand(new RunExample("14",ex14.toString(),ctr14));
        menu.addCommand(new RunExample("15",ex15.toString(),ctr15));
        menu.addCommand(new RunExample("16",ex16.toString(),ctr16));

        menu.show();
    }

    /*public void run()
    {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            menu();
            int option = scanner.nextInt();
            if(option == 0)
                return;
            else if(option == 1)
                option1();
            else if(option == 2)
                option2();
            else if(option == 3)
                option3();
            else if(option == 4)
                option4();
            else if(option == 5)
                option5();
            else if(option == 6)
                option6();
            else if(option == 7)
                option7();
            else if(option == 8)
                option8();
        }
    }*/

}
