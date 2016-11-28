package pizza.therealattacka.adressbook;


import pizza.therealattacka.adressbook.Contacts.ContactList;
import pizza.therealattacka.adressbook.Contacts.ContactSaveHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
* @author fredrik grimmenhag.
*/
public class Run {
    private static final Logger log = Logger.getLogger( Run.class.getName() );

    private static void setupLogging() {
        String loggingFilePath = "logging.properties";
        try (FileInputStream fileInputStream = new FileInputStream(loggingFilePath)) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load log properties", e);
        }
    }


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
        setupLogging();

        log.info("Application has Successfully started");

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
        log.info("Application has started closing, waiting for savethread to finish.");

        save.quitThread();


    }

}


