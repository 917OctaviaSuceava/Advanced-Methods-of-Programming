package com.example.demo;

import com.example.demo.Controller.Controller;
import com.example.demo.Domain.adt.IHeap;
import com.example.demo.Domain.adt.MyDict;
import com.example.demo.Domain.adt.MyHeap;
import com.example.demo.Domain.adt.MyStack;
import com.example.demo.Domain.state.PrgState;
import com.example.demo.Domain.stmt.IStmt;
import com.example.demo.Domain.values.IValue;
import com.example.demo.Exceptions.ADTException;
import com.example.demo.Exceptions.ProgramException;
import com.example.demo.Repository.IRepository;
import com.example.demo.Repository.Repository;
import com.example.demo.View.View;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
    private List<IStmt> programs;
    private View view;
    private Controller ctrl;
    List<Integer> ids = new ArrayList<>();

    @FXML
    private ListView<String> exeStackListView;

    @FXML
    private ListView<String> fileTableListView = new ListView<>();

    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, Integer> heapAddress = new TableColumn<>();

    @FXML
    private TableView<Map.Entry<Integer, String>> heapTableView = new TableView<>();

    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, String> heapValue = new TableColumn<>();

    @FXML
    private ListView<String> outTableListView = new ListView<>();

    @FXML
    private ListView<Integer> prgidListView;

    @FXML
    private TableColumn<Map.Entry<String, String>, String> symTableName = new TableColumn<>();

    @FXML
    private TableColumn<Map.Entry<String, String>, String> symTableValue = new TableColumn<>();

    @FXML
    private TableView<Map.Entry<String, String>> symTableView;

    @FXML
    private TextField nrPrgStates;

    @FXML
    private Button oneStepBtn;

    @FXML
    private Button executeBtn;

    @FXML
    void handleExecuteBtn() throws ADTException {
        int index = prgListView.getSelectionModel().getSelectedIndex();
        if(index < 0)
            return;

        PrgState prgState = new PrgState(programs.get(index));
        IRepository repository = new Repository("C:\\Users\\Octavia\\Desktop\\MAP\\demo\\src\\main\\java\\com\\example\\demo\\log" + (index+1) + ".txt");

        repository.add(prgState);
        ctrl = new Controller(repository);

    }

    @FXML
    void handleRunOneStep() throws FileNotFoundException, ProgramException {
        // ctrl.getRepo().getCurrentProgram().oneStep();
        if(getCurrentPrgState().getExeStack().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("THIS PROGRAM IS DONE EXECUTING!");
            alert.setContentText("The program you chose was already executed. Choose another program from the list!");
            alert.showAndWait();
            return;
        }
        try {
            ctrl.oneStep();
            update_program(getCurrentPrgState());
        }
        catch(Exception e)
        {
            String msg = e.getMessage();

            System.out.println(msg);
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("ERROR");
            alert1.setHeaderText("PROGRAM ERROR!");
            alert1.setContentText(msg);
            alert1.showAndWait();
        }

    }

    @FXML
    private ListView<String> prgListView;

    private void get_all_options()
    {
        view = new View();
        programs = new ArrayList<>();
        IStmt e1 = view.option1();
        IStmt e2 = view.option2();
        IStmt e3 = view.option3();
        IStmt e4 = view.option4();
        IStmt e5 = view.option5();
        IStmt e6 = view.option6();
        IStmt e7 = view.option7();
        IStmt e8 = view.option8();
        IStmt e9 = view.option9();
        IStmt e10 = view.option10();
        IStmt e11 = view.option11();
        IStmt e12 = view.option12();
        IStmt e13 = view.option13();
        IStmt e14 = view.option14();
        IStmt e15 = view.option15();
        IStmt e16 = view.option16();

        programs.add(e1);
        programs.add(e2);
        programs.add(e3);
        programs.add(e4);
        programs.add(e5);
        programs.add(e6);
        programs.add(e7);
        programs.add(e8);
        programs.add(e9);
        programs.add(e10);
        programs.add(e11);
        programs.add(e12);
        programs.add(e13);
        programs.add(e14);
        programs.add(e15);
        programs.add(e16);
    }

    public List<String> programsToString()
    {
        return programs.stream().map(IStmt::toString).collect(Collectors.toList());
    }

    public void populatePrgList()
    {
        get_all_options();
        prgListView.setItems(FXCollections.observableArrayList(programsToString()));
    }

    private void populateFileTable(PrgState prg)
    {
        fileTableListView.getItems().setAll(String.valueOf(prg.getFileTable().getDict().keySet().toString()));
        fileTableListView.refresh();
    }

    private void populateOutList(PrgState prg)
    {
        outTableListView.getItems().
                setAll(prg.getOut().getList().stream().map(Object::toString).collect(Collectors.toList()));
        outTableListView.refresh();
    }

    private void populatePrgIds(PrgState prg) {
        int id = prg.get_new_id();
        for(int i: prgidListView.getItems())
            if(id == i)
                return;
        ids.add(id);
        prgidListView.setItems(FXCollections.observableList(ids));
        prgidListView.refresh();
    }

    private PrgState getCurrentPrgState(){
        return ctrl.getRepo().getCurrentProgram();
    }

    private void populateExeStack(PrgState prgState)
    {
        MyStack<IStmt> exeStack = prgState.getExeStack();
        List<String> exeStackList = new ArrayList<>();

        for(IStmt s : exeStack.getStack()){
            exeStackList.add(s.toString());
        }

        exeStackListView.setItems(FXCollections.observableList(exeStackList));
        exeStackListView.refresh();
    }

    private void populateSymTable(PrgState prgState)
    {
        MyDict<String, IValue> symTable = prgState.getSymTable();
        List<Map.Entry<String, String>> symTableList = new ArrayList<>();
        for(Map.Entry<String, IValue> element: symTable.getDict().entrySet()){
            Map.Entry<String, String> entry =
                    new AbstractMap.SimpleEntry<String, String>(element.getKey(), element.getValue().toString());
            symTableList.add(entry);
        }

        symTableView.setItems(FXCollections.observableList(symTableList));
        symTableView.refresh();
    }

    public void populateHeapTable(PrgState prg)
    {
        IHeap heapTable = prg.getHeap();
        List<Map.Entry<Integer, String>> heapTableList=new ArrayList<>();
        for(Map.Entry<Integer, IValue> element: heapTable.get().entrySet()){
            Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<Integer, String>
                    (element.getKey(), element.getValue().toString());
            heapTableList.add(entry);
        }
        heapTableView.setItems(FXCollections.observableList(heapTableList));
        heapTableView.refresh();
    }

    private void update_program(PrgState prg)
    {
        populateExeStack(prg);
        populateSymTable(prg);
        populatePrgIds(prg);
        populateOutList(prg);
        populateFileTable(prg);
        populateHeapTable(prg);
        nrPrgStates.setText(Integer.toString(ctrl.getRepo().getPrgStateList().size()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populatePrgList();
        executeBtn.setOnAction(actionEvent -> {
            try {
            handleExecuteBtn();
        } catch (ADTException e) {
            e.printStackTrace();
            }
        });

        oneStepBtn.setOnAction(actionEvent -> {
            try {
                handleRunOneStep();
            } catch (FileNotFoundException | ProgramException e) {
                e.printStackTrace();
            }
        });

        symTableName.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey()));
        symTableValue.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue()));

        heapAddress.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapValue.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue()));



    }

}
