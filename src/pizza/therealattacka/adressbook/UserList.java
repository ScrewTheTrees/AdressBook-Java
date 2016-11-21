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
        try {
            PrintStream print = new PrintStream(new File(dir));

            print.println(userList.size());

            for (int i=0; i<userList.size(); i++) {
                Contact cont = userList.get(i);
                String prstr = cont.GetStringSimple();
                print.println(prstr);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void LoadUserList(String dir) {
        File f = new File(dir);

        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(dir));

                int entries = Integer.parseInt(br.readLine());


                for (int i = 0; i < entries; i++) {
                    String curr = br.readLine();

                    String[] splitted = curr.split(" ");
                    Contact cont = add(splitted[1], splitted[2], splitted[3]);
                    cont.SetUUID(splitted[0]);
                }

                br.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
