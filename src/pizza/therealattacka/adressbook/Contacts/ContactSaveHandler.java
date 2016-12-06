package pizza.therealattacka.adressbook.Contacts;


import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Fredrik Grimmenhag on 2016-11-28.
 */
public class ContactSaveHandler {

    private static final Logger log = Logger.getLogger( ContactSaveHandler.class.getName() );

    public static synchronized void saveUserList(String dir, ContactList contactList)
    {
        cleanUserList(dir);

        ArrayList<Contact> list = contactList.getArrayList();

        File folder = new File(dir);

        if (!folder.exists())
        {
            folder.mkdir();
        }
        for (int i = 0; i < list.size(); i++) {
            FileOutputStream fileOut = null;
            ObjectOutputStream out = null;

            try {
                fileOut = new FileOutputStream(dir + "/" + i + ".cont");
                out = new ObjectOutputStream(fileOut);
                out.writeObject(list.get(i));

            } catch (IOException e) {
                e.printStackTrace();
                log.severe(e.getStackTrace().toString());
            }

            try {
                out.close();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.severe(e.getStackTrace().toString());
            }
        }
        log.info("ContactList has been saved to: "+dir);

    }

    public static synchronized void loadUserList(String dir, ContactList list)
    {

        File folder = new File(dir);

        if (folder.exists())
        {
            File[] listOfFiles = folder.listFiles();

            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {

                    FileInputStream fileIn = null;
                    ObjectInputStream in = null;
                    try {
                        fileIn = new FileInputStream(listOfFile);
                        in = new ObjectInputStream(fileIn);
                        list.add( (Contact) in.readObject() );
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                        log.severe(e.getStackTrace().toString());
                    }


                    try {
                        fileIn.close();
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.severe(e.getStackTrace().toString());
                    }


                }
            }
        }
        log.info("ContactList has been loaded from: "+dir);
    }

    private static void cleanUserList(String dir)
    {
        File folder = new File(dir);

        if (folder.exists()) {
            File[] listOfFiles = folder.listFiles();

            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    listOfFile.delete();
                }

            }

        }
        log.info("Cleaning up old saved Serialized files inside: "+dir);
    }


}
