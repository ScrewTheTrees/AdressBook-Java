package pizza.therealattacka.adressbook.Contacts;


import java.io.Serializable;
import java.util.UUID;

/**
 * Do not change at all anymore for backwards compability.
 */
public class Contact implements Serializable {
    private String firstName;
    private String lastName;
    private String eMail;
    private UUID ID;


    public Contact(String firstName, String lastName, String eMail)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.ID = UUID.randomUUID();    //Assign ID

    }

    public String getContactInfoFormatted()
    {
        return ("UUID: "+ID+"\nFirst name: "+firstName+"\nLast name: "+lastName+"\nE-Mail: "+eMail+"\n");
    }

    //Getter Setters
    public String GetFirstName() { return this.firstName; }
    public String GetLastName() { return this.lastName; }
    public String GetEMailName() { return this.eMail; }
    public String GetUUID() { return this.ID.toString(); }


}
