package pizza.therealattacka.adressbook.Contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;


public class ContactList {
    private ArrayList<Contact> contactList = new ArrayList<>();


    private static final Logger log = Logger.getLogger( ContactList.class.getName() );


    public Contact add(String firstName, String lastName, String eMail)
    {
        Contact cont = new Contact(firstName, lastName, eMail);

        log.fine("Added Contact to contact list.");
        contactList.add(cont);
        return cont;

    }
    public void add(Contact cont) {

        log.fine("Added Contact to contact list.");
        contactList.add(cont);

    }



    public ArrayList<Contact> getArrayList()
    {
        return contactList;
    }


}
