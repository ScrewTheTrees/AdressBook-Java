package pizza.therealattacka.adressbook;

import pizza.therealattacka.adressbook.Contacts.ContactList;
import pizza.therealattacka.adressbook.Contacts.ContactSaveHandler;

/**
 * Created by Fredrik Grimmenhag on 2016-11-28.
 */
public class AutoSave extends Thread  {

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
                LogHandler.LogSevere(e.getStackTrace().toString());
            }

            ContactSaveHandler.SaveUserList(dir,list);
            LogHandler.LogInfo("AutoSave has saved the userList.");

        }
        LogHandler.LogInfo("AutoSave thread has stopped.");
        return; //Simple way of ending a thread in the run() method
    }


    public void quitThread()
    {
        running = false;
    }

}