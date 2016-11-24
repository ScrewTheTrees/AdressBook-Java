package pizza.therealattacka.adressbook;


import java.io.Serializable;
import java.util.UUID;


class Contact implements Serializable {
    private String firstName;
    private String lastName;
    private String eMail;
    private UUID ID;


    Contact(String firstName, String lastName, String eMail)
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

    //Getter Setters
    String GetFirstName() { return this.firstName; }
    String GetLastName() { return this.lastName; }
    String GetUUID() { return this.ID.toString(); }


}
