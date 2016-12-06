package pizza.therealattacka.adressbook;

import pizza.therealattacka.adressbook.Contacts.ContactList;
import pizza.therealattacka.adressbook.Contacts.ContactSaveHandler;

import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Created by Fredrik Grimmenhag on 2016-11-28.
 */
class AutoSave extends TimerTask {

    private static final Logger log = Logger.getLogger( AutoSave.class.getName() );
    private ContactList list;
    private String dir;


    public AutoSave(ContactList list, String dir)
    {
        this.list = list;
        this.dir = dir;
    }

    public void run() {

        ContactSaveHandler.saveUserList(dir,list);
        log.info("AutoSave has saved the userList.");

    }

}