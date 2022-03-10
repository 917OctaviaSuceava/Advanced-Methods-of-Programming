import Controller.Controller;
import Exceptions.ADTException;
import Repository.IRepository;
import Repository.Repository;
import View.View;

public class Main {

    public static void main(String[] args) throws ADTException {
        View view = new View();
        view.run();
    }
}
