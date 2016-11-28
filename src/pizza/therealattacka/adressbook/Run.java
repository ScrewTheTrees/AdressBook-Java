package pizza.therealattacka.adressbook;


import pizza.therealattacka.adressbook.Contacts.ContactList;
import pizza.therealattacka.adressbook.Contacts.ContactSaveHandler;

/**
* @author fredrik grimmenhag.
*/
public class Run {

    public static void main(String[] args)
    {
        Run app = new Run();
        app.run();
    }

    private void run()
    {
        ContactList list = new ContactList();
        CommandInput input = new CommandInput();
        String saveDir = "Contacts";
        boolean doExit = false;
        LogHandler.LogCreate();

        LogHandler.LogInfo("Application has Successfully started");

        ContactSaveHandler.LoadUserList(saveDir,list);

        AutoSave save = new AutoSave(list,saveDir);
        save.start();

        ContactSaveHandler.SaveUserList(saveDir,list);



        System.out.println("Welcome, type 'help' to get started.");
        while (!doExit)
        {
            boolean success = input.ExecuteCommands(list);

            if (!success)
            {
                doExit = true;
            }
        }
        LogHandler.LogInfo("Application has started closing, waiting for savethread to finish.");

        save.quitThread();


    }

}


