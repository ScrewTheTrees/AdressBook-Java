package pizza.therealattacka.adressbook;


import pizza.therealattacka.adressbook.Contacts.ContactList;
import pizza.therealattacka.adressbook.Contacts.ContactSaveHandler;
import pizza.therealattacka.adressbook.RemoteList.RemoteList;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by Fredrik Grimmenhag
 */
public class Run {
    private static final Logger log = Logger.getLogger( Run.class.getName() );


    public static void main(String[] args)
    {
        Run app = new Run();
        app.run();
    }

    private void run()
    {

        setupLogging();

        ContactList list = new ContactList();
        CommandInput input = new CommandInput();
        String saveDir = "Contacts";
        boolean doExit = false;


        log.info("Application has Successfully started");

        ContactSaveHandler.loadUserList(saveDir,list);

        AutoSave save = new AutoSave(list,saveDir);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(save,0,5000);

        RemoteList remoteList = new RemoteList();
        remoteList.start();


        System.out.println("Welcome, type 'help' to get started.");
        while (!doExit)
        {
            boolean success = input.executeCommands(list);

            if (!success)
            {
                doExit = true;
            }
        }
        log.info("Application has started closing, waiting for Save Thread to finish.");

        timer.cancel();
        log.info("TimerTask ended.");

        ContactSaveHandler.saveUserList(saveDir, list);

    }



    private static void setupLogging() {
        String loggingFilePath = "logging.properties";


        try (FileInputStream fileInputStream = new FileInputStream(loggingFilePath)) {

            log.setUseParentHandlers(false);    //Remove possible console output
            LogManager.getLogManager().readConfiguration(fileInputStream);

        } catch (IOException e) {
            throw new RuntimeException("Could not load log properties", e);
        }
    }




}


