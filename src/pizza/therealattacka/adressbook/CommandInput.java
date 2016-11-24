package pizza.therealattacka.adressbook;

import java.util.Scanner;


public class CommandInput {

    private Scanner sc = new Scanner(System.in);

    public boolean ExecuteCommands(UserList list)
    {
        boolean success;


        String input = sc.nextLine();

        String[] inputParts = input.split(" ");

        switch (inputParts[0].toLowerCase())
        {
            case "help":
                CommandHelp(inputParts,list);
                success = true;
                break;

            case "add":
                CommandAdd(inputParts,list);
                success = true;
                break;

            case "list":
                list.listAllUsers();
                success = true;
                System.out.println("All users have been listed.");
                break;

            case "search":
                CommandSearch(inputParts,list);
                success = true;
                break;

            case "delete":
                CommandDelete(inputParts,list);
                success = true;
                break;

            case "quit":
                success = false;
                System.out.println("Quitting application.");
                break;

            default:
                success = true; //Since we use quit to exit application
                System.out.println("Unrecognized command: "+inputParts[0]+"\n");
                break;
        }

        return success;
    }






    //Commands:
    private void CommandHelp(String[] inputParts, UserList list)
    {
        System.out.println("help        Show this screen.");
        System.out.println("add         Adds new Contact with arguments (FirstName,LastName,EMail).");
        System.out.println("list        Lists all users.");
        System.out.println("search      Searches for Contact using either first or last name defined in argument.");
        System.out.println("delete      Deletes a Contact using the UUID defined in argument.");
        System.out.println("quit        Saves all Contacts, and exits this application.");

        if (inputParts.length>=2) System.out.println("Arguments not required for help command: "+(inputParts.length-1) );
    }

    private void CommandSearch(String[] inputParts, UserList list)
    {
        if (inputParts.length>=2)
            list.printSearch(inputParts[1]);
        else System.out.println("Wrong amount of arguments for Search: "+(inputParts.length-1) );
    }

    private void CommandDelete(String[] inputParts, UserList list)
    {
        if (inputParts.length>=2)
            list.removeUserUUID(inputParts[1]);
        else System.out.println("Wrong amount of arguments for Delete: "+(inputParts.length-1) );
    }

    private void CommandAdd(String[] inputParts, UserList list)
    {
        if (inputParts.length>=4) {
            list.add(inputParts[1],inputParts[2],inputParts[3]);
            System.out.println("Contact has been successfully added!");
        }
        else System.out.println("Wrong amount of arguments for add: "+(inputParts.length-1) );
    }


}
