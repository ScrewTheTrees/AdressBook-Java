package pizza.therealattacka.adressbook.Contacts;

import pizza.therealattacka.adressbook.LogHandler;

import java.util.ArrayList;


public class ContactList {
    private ArrayList<Contact> contactList = new ArrayList<>();



    public Contact add(String firstName, String lastName, String eMail)
    {

        Contact cont = new Contact(firstName, lastName, eMail);

        LogHandler.LogFine("Added Contact to contact list.");
        contactList.add(cont);
        return cont;

    }
    public void add(Contact cont) {

        LogHandler.LogFine("Added Contact to contact list.");
        contactList.add(cont);

    }


    public ArrayList<Contact> getArrayList()
    {
        return contactList;
    }

}
