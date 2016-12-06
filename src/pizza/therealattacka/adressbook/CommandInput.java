package pizza.therealattacka.adressbook;

import pizza.therealattacka.adressbook.Contacts.ContactList;

import java.util.Scanner;
import java.util.logging.Logger;


class CommandInput {

    private Scanner sc = new Scanner(System.in);
    private static final Logger log = Logger.getLogger( CommandInput.class.getName());
    boolean executeCommands(ContactList list)
    {
        boolean keepRunning = true;;


        String input = sc.nextLine();

        input = input.trim().replaceAll(" +", " ");


        log.fine("Command Executed: "+input);

        String[] inputParts = input.split(" ");


        switch (inputParts[0].toLowerCase())
        {
            case "help":
                Commands.commandHelp(inputParts);
                break;

            case "add":
                Commands.commandAdd(inputParts,list);
                break;

            case "list":
                Commands.commandList(inputParts,list);
                break;

            case "search":
                Commands.commandSearch(inputParts,list);
                break;

            case "delete":
                Commands.commandDelete(inputParts,list);
                break;

            case "quit":
                Commands.commandQuit(inputParts);
                sc.close();
                keepRunning = false;
                break;

            default:
                Commands.commandNone(inputParts);

                break;
        }

        return keepRunning;
    }


}
