package pizza.therealattacka.adressbook;

import java.io.*;
import java.util.ArrayList;


class ContactList {
    private ArrayList<Contact> userList = new ArrayList<>();



    Contact add(String firstName, String lastName, String eMail) {


        Contact cont = new Contact(firstName, lastName, eMail);

        userList.add(cont);
        return cont;

    }

    void listAllUsers()
    {
        userList.forEach(System.out::println);
    }


    void printSearch(String input1)
    {
        int num = 0;

        for (Contact cont : userList) {
            String firstLower = cont.GetFirstName().toLowerCase();
            String lastLower = cont.GetLastName().toLowerCase();

            if (firstLower.startsWith(input1) || lastLower.startsWith(input1)) {
                System.out.println(cont);
                num += 1;
            }
        }
        System.out.println(num+" Contacts has been found by the query: "+input1);
    }

    void SaveUserList(String dir)
    {
        CleanUserList(dir);

        File folder = new File(dir);
        if (!folder.exists())
        {
            boolean temp = folder.mkdir();
        }

        try {
            for (int i = 0; i < userList.size(); i++)
            {
                FileOutputStream fileOut = new FileOutputStream(dir+"/"+i+".cont");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(userList.get(i));
                out.close();
                fileOut.close();
            }
        } catch(IOException i) {
            i.printStackTrace();
        }
    }

    void removeUserUUID(String checkID)
    {
        boolean deleted=false;
        for (int i = 0; i < userList.size(); i++)
        {
            if (userList.get(i).GetUUID().equals(checkID))
            {
                System.out.println(userList.get(i).GetFirstName()+" "+userList.get(i).GetLastName()+" has been deleted!");
                userList.remove(i);

                deleted=true;
            }
        }
        if (!deleted)
            System.out.println("No user found with the UUID: "+checkID);
    }

    void LoadUserList(String dir)
    {

        File folder = new File(dir);

        if (folder.exists())
        {
            File[] listOfFiles = folder.listFiles();

            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    try {
                        FileInputStream fileIn = new FileInputStream(listOfFile);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        Contact cont = (Contact) in.readObject();
                        userList.add(cont);
                        in.close();
                        fileIn.close();

                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    void CleanUserList(String dir)
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
    }
}
