package pizza.therealattacka.adressbook;


import pizza.therealattacka.adressbook.Contacts.Contact;
import pizza.therealattacka.adressbook.Contacts.ContactList;
import pizza.therealattacka.adressbook.RemoteList.RemoteList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * Created by Fredrik Grimmenhag on 2016-11-28.
 */
class Commands {

    private static final Logger log = Logger.getLogger( Commands.class.getName() );

    //Commands:
    static synchronized void commandHelp(String[] inputParts)
    {


        System.out.println("help        Show this screen.");
        System.out.println("add         Adds new Contacts with arguments (FirstName,LastName,EMail).");
        System.out.println("list        Lists all users.");
        System.out.println("search      Searches for Contacts using either first or last name defined in argument.");
        System.out.println("delete      Deletes a Contacts using the UUID defined in argument.");
        System.out.println("quit        Saves all Contacts, and exits this application.");

        if (inputParts.length>=2)
            System.out.println("Arguments not required for 'help' command: "+(inputParts.length-1) );

        log.fine("User Commando: list");
    }

    static synchronized void commandList(String[] inputParts, ContactList list) {
        ArrayList<Contact> newArr = new ArrayList<>();

        for (Contact cont : list.getArrayList()) {
            newArr.add(cont);
        }
        for (Contact cont : RemoteList.getRemoteList()) {
            newArr.add(cont);
        }

        newArr = sortFirstName(newArr);
        for (Contact cont : newArr)
            System.out.println("UUID: "+cont.getUUID()+"\nFirst name: "+cont.getFirstName()+"\nLast name: "+cont.getLastName()+"\nE-Mail: "+cont.getEMailName()+"\n");


        System.out.println("All users have been listed.");
        if (inputParts.length >= 2)
            System.out.println("Arguments not required for 'list' command: " + (inputParts.length - 1));

        log.fine("User Commando: list");
    }


    static synchronized void commandSearch(String[] inputParts, ContactList list) {
        ArrayList<Contact> newArr = new ArrayList<>();

        if (inputParts.length >= 2) {
            int num = 0;

            for (Contact cont : list.getArrayList()) {
                String firstLower = cont.getFirstName().toLowerCase();
                String lastLower = cont.getLastName().toLowerCase();

                if (firstLower.startsWith(inputParts[1]) || lastLower.startsWith(inputParts[1])) {
                    newArr.add(cont);
                    num += 1;
                }
            }
            for (Contact cont : RemoteList.getRemoteList()) {
                String firstLower = cont.getFirstName().toLowerCase();
                String lastLower = cont.getLastName().toLowerCase();

                if (firstLower.startsWith(inputParts[1]) || lastLower.startsWith(inputParts[1])) {
                    newArr.add(cont);
                    num += 1;
                }
            }

            newArr = sortFirstName(newArr);
            for (Contact cont : newArr) {
                System.out.println("UUID: "+cont.getUUID()+"\nFirst name: "+cont.getFirstName()+"\nLast name: "+cont.getLastName()+"\nE-Mail: "+cont.getEMailName()+"\n");
            }

            System.out.println(num + " Contacts has been found by the query: " + inputParts[1]);

            if (inputParts.length != 2)
                System.out.println("Wrong amount of arguments for 'search': " + (inputParts.length - 1));
        }
        log.fine("User Commando: search");
    }


    static synchronized void commandDelete(String[] inputParts, ContactList list)
    {
        if (inputParts.length>=2)
        {
            boolean deleted=false;
            for (int i = 0; i < list.getArrayList().size(); i++)
            {
                if (list.getArrayList().get(i).getUUID().equals(inputParts[1]))
                {
                    System.out.println(list.getArrayList().get(i).getFirstName()+" "+list.getArrayList().get(i).getLastName()+" has been deleted!");
                    list.getArrayList().remove(i);

                    deleted=true;
                }
            }
            for (int i = 0; i < RemoteList.getRemoteList().size(); i++)
            {
                if (RemoteList.getRemoteList().get(i).getUUID().equals(inputParts[1]))
                {
                    System.out.println(RemoteList.getRemoteList().get(i).getFirstName()+" "+RemoteList.getRemoteList().get(i).getLastName()+" Is not a local Contact and cannot be removed.");

                    deleted=true;
                }
            }


            if (!deleted)
                System.out.println("No user found with the UUID: "+inputParts[1]);
        }
        if (inputParts.length!=2) System.out.println("Wrong amount of arguments for 'delete': "+(inputParts.length-1) );

        log.fine("User Commando: delete");
    }

    static synchronized void  commandAdd(String[] inputParts, ContactList list)
    {
        if (inputParts.length==4) {
            list.add(inputParts[1],inputParts[2],inputParts[3]);
            System.out.println("Contacts has been successfully added!");
        }
        if (inputParts.length!=4) System.out.println("Wrong amount of arguments for 'add': "+(inputParts.length-1) );

        log.fine("User Commando: add");
    }

    static synchronized void commandQuit(String[] inputParts)
    {
        System.out.println("Quitting application.");
        if (inputParts.length>=2) System.out.println("Arguments not required for 'quit' command: "+(inputParts.length-1) );

        log.fine("User Commando: quit");
    }

    static synchronized void commandNone(String[] inputParts)
    {
        System.out.println("Unrecognized command: "+inputParts[0]+"\n");
        log.fine("Unknown User Commando: "+inputParts[0]);
    }



    public static synchronized ArrayList<Contact> sortFirstName(ArrayList<Contact> list)
    {
        ArrayList<Contact> newList = new ArrayList<>();
        newList.addAll(list);
        Collections.sort(newList, (s1, s2) -> s1.getFirstName().compareToIgnoreCase(s2.getFirstName()));
        return newList;
    }

}
