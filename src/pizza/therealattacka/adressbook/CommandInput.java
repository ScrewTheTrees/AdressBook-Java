package pizza.therealattacka.adressbook;

import java.util.Scanner;


public class CommandInput {

    Scanner sc = new Scanner(System.in);

    public boolean ExecuteCommands(UserList list)
    {
        boolean success = false;


        System.out.println("Input your desired command: " +
                "\nadd FirstName LastName EMail" +
                "\nlist" +
                "\nsearch Query" +
                "\nquit");

        String input = sc.nextLine();

        String[] inputParts = input.split(" ");

        switch (inputParts[0])
        {
            case "add":
                if (inputParts.length>=4) {
                    list.add(inputParts[1],inputParts[2],inputParts[3]);
                    //System.out.println("Successfully added!\n");

                }
                else {
                    //System.out.println("It appears you did not add enough arguments, please try again.\n");
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
