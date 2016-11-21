package pizza.therealattacka.adressbook;



public class Run {

    public static void main(String[] args)
    {
        Run app = new Run();
        app.run();
    }

    public void run()
    {
        UserList list = new UserList();
        String saveFile = "Contacts.atf";

        list.LoadUserList(saveFile);
        CommandInput input = new CommandInput();
        boolean doExit = false;

        while (doExit==false)
        {
            boolean success = input.ExecuteCommands(list);

            if (!success)
            {
                doExit = true;
            }
        }


        list.SaveUserList(saveFile);

    }

}
