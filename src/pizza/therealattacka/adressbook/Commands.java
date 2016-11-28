package pizza.therealattacka.adressbook;


import pizza.therealattacka.adressbook.Contacts.Contact;
import pizza.therealattacka.adressbook.Contacts.ContactList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Fredrik Grimmenhag on 2016-11-28.
 */
public class Commands {

    //Commands:
    static synchronized void CommandHelp(String[] inputParts)
    {
        if (inputParts.length>=2) System.out.println("Arguments not required for 'help' command: "+(inputParts.length-1) );

        System.out.println("help        Show this screen.");
        System.out.println("add         Adds new Contacts with arguments (FirstName,LastName,EMail).");
        System.out.println("list        Lists all users.");
        System.out.println("search      Searches for Contacts using either first or last name defined in argument.");
        System.out.println("delete      Deletes a Contacts using the UUID defined in argument.");
        System.out.println("quit        Saves all Contacts, and exits this application.");

        LogHandler.LogFine("User Commando: list");
    }

    static synchronized void CommandList(String[] inputParts, ContactList list) {
        ArrayList<Contact> newArr = new ArrayList<>();

        for (Contact cont : list.getArrayList()) {
            newArr.add(cont);
        }

        sort(newArr);
        for (Contact cont : newArr) {
            System.out.println(cont.getContactInfoFormatted());


            System.out.println("All users have been listed.");
            if (inputParts.length >= 2)
                System.out.println("Arguments not required for 'list' command: " + (inputParts.length - 1));
            LogHandler.LogFine("User Commando: list");
        }
    }


    static synchronized void CommandSearch(String[] inputParts, ContactList list) {
        ArrayList<Contact> newArr = new ArrayList<>();

        if (inputParts.length >= 2) {
            int num = 0;

            for (Contact cont : list.getArrayList()) {
                String firstLower = cont.GetFirstName().toLowerCase();
                String lastLower = cont.GetLastName().toLowerCase();

                if (firstLower.startsWith(inputParts[1]) || lastLower.startsWith(inputParts[1])) {
                    newArr.add(cont);
                    num += 1;
                }
            }
            sort(newArr);
            for (Contact cont : newArr) {
                System.out.println(cont.getContactInfoFormatted());

                System.out.println(num + " Contacts has been found by the query: " + inputParts[1]);
            }
            if (inputParts.length != 2)
                System.out.println("Wrong amount of arguments for 'search': " + (inputParts.length - 1));

            LogHandler.LogFine("User Commando: search");
        }
    }


    static synchronized void CommandDelete(String[] inputParts, ContactList list)
    {
        if (inputParts.length>=2)
        {
            boolean deleted=false;
            for (int i = 0; i < list.getArrayList().size(); i++)
            {
                if (list.getArrayList().get(i).GetUUID().equals(inputParts[1]))
                {
                    System.out.println(list.getArrayList().get(i).GetFirstName()+" "+list.getArrayList().get(i).GetLastName()+" has been deleted!");
                    list.getArrayList().remove(i);

                    deleted=true;
                }
            }
            if (!deleted)
                System.out.println("No user found with the UUID: "+inputParts[1]);
        }
        if (inputParts.length!=2) System.out.println("Wrong amount of arguments for 'delete': "+(inputParts.length-1) );

        LogHandler.LogFine("User Commando: delete");
    }

    static synchronized void  CommandAdd(String[] inputParts, ContactList list)
    {
        if (inputParts.length==4) {
            list.add(inputParts[1],inputParts[2],inputParts[3]);
            System.out.println("Contacts has been successfully added!");
        }
        if (inputParts.length!=4) System.out.println("Wrong amount of arguments for 'add': "+(inputParts.length-1) );

        LogHandler.LogFine("User Commando: add");
    }

    static synchronized void CommandQuit(String[] inputParts)
    {
        System.out.println("Quitting application.");
        if (inputParts.length>=2) System.out.println("Arguments not required for 'quit' command: "+(inputParts.length-1) );

        LogHandler.LogFine("User Commando: quit");
    }

    static synchronized void CommandNone(String[] inputParts)
    {
        System.out.println("Unrecognized command: "+inputParts[0]+"\n");
        LogHandler.LogFine("Unknown User Commando: "+inputParts[0]);
    }



    public static ArrayList<Contact> sort(ArrayList<Contact> list)
    {
        Collections.sort(list, (s1, s2) -> s1.GetFirstName().compareToIgnoreCase(s2.GetFirstName()));
        return list;
    }



}
