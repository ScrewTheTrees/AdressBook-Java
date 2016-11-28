package pizza.therealattacka.adressbook;

import pizza.therealattacka.adressbook.Contacts.ContactList;

import java.util.Scanner;



class CommandInput {

    Scanner sc = new Scanner(System.in);

    boolean ExecuteCommands(ContactList list)
    {
        boolean keepRunning = true;;


        String input = sc.nextLine();

        String[] inputParts = input.split(" ");

        switch (inputParts[0].toLowerCase())
        {
            case "help":
                Commands.CommandHelp(inputParts);
                break;

            case "add":
                Commands.CommandAdd(inputParts,list);
                break;

            case "list":
                Commands.CommandList(inputParts,list);
                break;

            case "search":
                Commands.CommandSearch(inputParts,list);
                break;

            case "delete":
                Commands.CommandDelete(inputParts,list);
                break;

            case "quit":
                Commands.CommandQuit(inputParts);
                sc.close();
                keepRunning = false;
                break;

            default:
                Commands.CommandNone(inputParts);
                break;
        }

        return keepRunning;
    }





}
