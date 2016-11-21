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
        for (Contact anUserList : userList) {
            System.out.println(anUserList);
        }
    }


    public void printSearch(String input1)
    {
        for (Contact cont : userList) {
            String firstLower = cont.GetFirstName().toLowerCase();
            String lastLower = cont.GetLastName().toLowerCase();

            if (firstLower.startsWith(input1) || lastLower.startsWith(input1)) {
                System.out.println(cont);
            }

        }
    }

    public void SaveUserList(String dir)
    {

        try {
                FileOutputStream fileOut = new FileOutputStream(dir);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(userList);
                out.close();
                fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public void LoadUserList(String dir) {

        File file = new File(dir);

        if (file.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                ArrayList<Contact> cont = (ArrayList<Contact>) in.readObject();
                userList = cont;

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
