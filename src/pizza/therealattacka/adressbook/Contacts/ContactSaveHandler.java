package pizza.therealattacka.adressbook.Contacts;


import pizza.therealattacka.adressbook.LogHandler;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Fredrik Grimmenhag on 2016-11-28.
 */
public class ContactSaveHandler {

    public static void SaveUserList(String dir, ContactList contactList)
    {
        CleanUserList(dir);

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
                LogHandler.LogSevere(e.getStackTrace().toString());
            }

            try {
                out.close();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
                LogHandler.LogSevere(e.getStackTrace().toString());
            }
        }
        LogHandler.LogInfo("ContactList has been saved to: "+dir);

    }

    public static void LoadUserList(String dir, ContactList list)
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
                        Contact cont = (Contact) in.readObject();
                        list.add(cont);


                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                        LogHandler.LogSevere(e.getStackTrace().toString());
                    }


                    try {
                        fileIn.close();
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        LogHandler.LogSevere(e.getStackTrace().toString());
                    }


                }
            }
        }
        LogHandler.LogInfo("ContactList has been loaded from: "+dir);
    }

    static void CleanUserList(String dir)
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
        LogHandler.LogInfo("Cleaning up old saved Serialized files inside: "+dir);
    }


}
