package pizza.therealattacka.adressbook;

import java.util.UUID;


public class Contact {
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

    @Override
    public String toString()
    {
        return ("UUID: "+ID+"\nFirst name: "+firstName+"\nLast name: "+lastName+"\nE-Mail: "+eMail+"\n");
    }

    public String GetStringSimple()
    {
        return (ID+" "+GetFirstName()+" "+GetLastName()+" "+GetEMail());
    }

    //Getter Setters
    public String GetFirstName() { return this.firstName; }
    public String GetLastName() { return this.lastName; }
    public String GetEMail() { return this.eMail; }
    public void SetUUID(String uuid) {ID = UUID.fromString(uuid);}
}
