package pizza.therealattacka.adressbook.RemoteList;

import pizza.therealattacka.adressbook.Contacts.Contact;

import java.util.ArrayList;

/**
 * Created by Fredrik Grimmenhag on 2016-12-06.
 */
public class RemoteList {

    private static ArrayList<Contact> remoteList = new ArrayList<>();


    public void start()
    {
        //Add more servers here to load from more sources
        Thread remotesServer1 = new Thread(new RemoteListRetrieve("127.0.0.1",61616));
        remotesServer1.start();


    }


    public static synchronized ArrayList<Contact> getRemoteList()
    {
        return remoteList;
    }

    /**
     *  Use this one since we do not want multithread errors
     * @param cont
     */
    public static synchronized void add(Contact cont)
    {
        remoteList.add(cont);
    }
}
