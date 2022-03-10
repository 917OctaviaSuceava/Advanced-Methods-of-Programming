package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;

    public TextMenu()
    {
        commands=new HashMap<>();
    }

    public void addCommand(Command c)
    {
        commands.put(c.getKey(),c);
    }

    private void printMenu()
    {
        System.out.println("\nChoose an option:\n");
        for(Command com : commands.values())
        {
            String line = String.format("%13s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.println("\nInput the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if (com == null)
            {
                System.out.println("\nInvalid Option");
                continue;
            }
            com.execute();
        }
    }
}
