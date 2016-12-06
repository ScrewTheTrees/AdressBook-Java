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
    private String ID;


    public Contact(String firstName, String lastName, String eMail)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.ID = UUID.randomUUID().toString();    //Assign ID

    }
    public Contact(String firstName, String lastName, String eMail, String uuid)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.ID = uuid;    //Assign ID

    }

    //Getter Setters
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getEMailName() { return this.eMail; }
    public String getUUID() { return this.ID; }


}
