package pizza.therealattacka.adressbook;

import pizza.therealattacka.adressbook.Contacts.ContactList;
import pizza.therealattacka.adressbook.Contacts.ContactSaveHandler;

import java.util.logging.Logger;

/**
 * Created by Fredrik Grimmenhag on 2016-11-28.
 */
public class AutoSave extends Thread  {

    private static final Logger log = Logger.getLogger( AutoSave.class.getName() );
    private ContactList list;
    private String dir;
    private boolean running = true;


    public AutoSave(ContactList list, String dir)
    {
        this.list = list;
        this.dir = dir;
    }

    public void run() {

        while (running)
        {
            try {
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
                log.severe(e.getStackTrace().toString());
            }

            ContactSaveHandler.SaveUserList(dir,list);
            log.info("AutoSave has saved the userList.");

        }
        log.info("AutoSave thread has stopped.");
        return; //Simple way of ending a thread in the run() method
    }


    public void quitThread()
    {
        running = false;
    }

}