package pizza.therealattacka.adressbook;

import java.util.Scanner;


class CommandInput {

    private Scanner sc = new Scanner(System.in);

    boolean ExecuteCommands(ContactList list)
    {
        boolean keepRunning;


        String input = sc.nextLine();

        String[] inputParts = input.split(" ");

        switch (inputParts[0].toLowerCase())
        {
            case "help":
                CommandHelp();
                if (inputParts.length>=2) System.out.println("Arguments not required for 'help' command: "+(inputParts.length-1) );
                keepRunning = true;
                break;

            case "add":
                CommandAdd(inputParts,list);
                keepRunning = true;
                break;

            case "list":
                list.listAllUsers();
                keepRunning = true;
                System.out.println("All users have been listed.");
                if (inputParts.length>=2) System.out.println("Arguments not required for 'list' command: "+(inputParts.length-1) );
                break;

            case "search":
                CommandSearch(inputParts,list);
                keepRunning = true;
                break;

            case "delete":
                CommandDelete(inputParts,list);
                keepRunning = true;
                break;

            case "quit":
                keepRunning = false;
                System.out.println("Quitting application.");
                if (inputParts.length>=2) System.out.println("Arguments not required for 'quit' command: "+(inputParts.length-1) );
                sc.close();
                break;

            default:
                keepRunning = true; //Since we use quit to exit application
                System.out.println("Unrecognized command: "+inputParts[0]+"\n");
                break;
        }

        return keepRunning;
    }






    //Commands:
    private void CommandHelp()
    {
        System.out.println("help        Show this screen.");
        System.out.println("add         Adds new Contact with arguments (FirstName,LastName,EMail).");
        System.out.println("list        Lists all users.");
        System.out.println("search      Searches for Contact using either first or last name defined in argument.");
        System.out.println("delete      Deletes a Contact using the UUID defined in argument.");
        System.out.println("quit        Saves all Contacts, and exits this application.");

    }

    private void CommandSearch(String[] inputParts, ContactList list)
    {
        if (inputParts.length>=2)
            list.printSearch(inputParts[1]);
        if (inputParts.length!=2) System.out.println("Wrong amount of arguments for 'search': "+(inputParts.length-1) );
    }

    private void CommandDelete(String[] inputParts, ContactList list)
    {
        if (inputParts.length>=2)
            list.removeUserUUID(inputParts[1]);
        if (inputParts.length!=2) System.out.println("Wrong amount of arguments for 'delete': "+(inputParts.length-1) );
    }

    private void CommandAdd(String[] inputParts, ContactList list)
    {
        if (inputParts.length==4) {
            list.add(inputParts[1],inputParts[2],inputParts[3]);
            System.out.println("Contact has been successfully added!");
        }
        if (inputParts.length!=4) System.out.println("Wrong amount of arguments for 'add': "+(inputParts.length-1) );
    }


}
