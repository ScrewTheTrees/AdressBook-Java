package pizza.therealattacka.adressbook;

import java.io.*;
import java.util.ArrayList;


public class UserList {
    private ArrayList<Contact> userList = new ArrayList<>();




    public Contact add(String firstName, String lastName, String eMail) {

        Contact cont = new Contact(firstName, lastName, eMail);

        userList.add(cont);
        return cont;
    }

    public void listAllUsers()
    {
        for (int i=0; i<userList.size(); i++){
            System.out.println(userList.get(i));
        }
    }


    public void printSearch(String input1)
    {
        for (int i=0; i<userList.size(); i++){
            Contact cont = userList.get(i);
            String firstLower = cont.GetFirstName().toLowerCase();
            String lastLower = cont.GetLastName().toLowerCase();

            boolean fail=true;

            if (firstLower.startsWith(input1) || lastLower.startsWith(input1))
            {
                System.out.println(cont);
            }

        }
    }

    public void SaveUserList(String dir)
    {
        File folder = new File("Contacts");
        if (!folder.exists()) {
            folder.mkdir();
        }

        try {
            for (int i = 0; i < userList.size(); i++) {
                FileOutputStream fileOut = new FileOutputStream(dir+"/"+i+".cont");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(userList.get(i));
                out.close();
                fileOut.close();
            }
        }catch(IOException i) {
            i.printStackTrace();
        }
    }


    public void LoadUserList(String dir) {

        File folder = new File(dir);

        if (folder.exists()) {

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

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
