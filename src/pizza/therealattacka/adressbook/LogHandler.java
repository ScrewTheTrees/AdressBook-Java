package pizza.therealattacka.adressbook;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Fredrik Grimmenhag on 2016-11-28.
 */
public class LogHandler {
    private static FileHandler fh;
    private static final Logger LOGGER = Logger.getLogger( LogHandler.class.getName() );


    public static void LogCreate()
    {
        try {
            LOGGER.setUseParentHandlers(false);

            fh = new FileHandler("ContactList.log");
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);


        } catch (SecurityException e) {
            e.printStackTrace();
            LogHandler.LogSevere(e.getStackTrace().toString());
        } catch (IOException e) {
            e.printStackTrace();
            LogHandler.LogSevere(e.getStackTrace().toString());
        }
    }

    public static synchronized void LogSevere(String str)
    {
        LOGGER.severe(str);
    }
    public static synchronized void LogWarning(String str)
    {
        LOGGER.warning(str);
    }
    public static synchronized void LogInfo(String str)
    {
        LOGGER.info(str);
    }
    public static synchronized void LogFine(String str)
    {
        LOGGER.fine(str);
    }
    public static synchronized void LogFiner(String str)
    {
        LOGGER.finer(str);
    }
    public static synchronized void LogFinest(String str) { LOGGER.finest(str); }

}
