package pizza.therealattacka.adressbook;

import java.util.Scanner;


public class CommandInput {

    Scanner sc = new Scanner(System.in);

    public boolean ExecuteCommands(UserList list)
    {
        boolean success;


        String input = sc.nextLine();

        String[] inputParts = input.split(" ");

        switch (inputParts[0])
        {
            case "add":
                if (inputParts.length>=4) {
                    list.add(inputParts[1],inputParts[2],inputParts[3]);
                }
                success = true;
                break;

            case "list":
                list.listAllUsers();
                success = true;
                break;

            case "search":
                list.printSearch(inputParts[1]);

                success = true;
                break;

            case "quit":
            success = false;

            break;

            default:
                success = true; //Since we use quit/exit to exit application
                break;
        }

        return success;
    }

}
