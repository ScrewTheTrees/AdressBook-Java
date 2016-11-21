package pizza.therealattacka.adressbook;



public class Run {

    public static void main(String[] args)
    {
        Run app = new Run();
        app.run();
    }

    private void run()
    {
        UserList list = new UserList();
        String saveDir = "Contacts.alulc";

        list.LoadUserList(saveDir);
        CommandInput input = new CommandInput();
        boolean doExit = false;

        while (!doExit)
        {
            boolean success = input.ExecuteCommands(list);

            if (!success)
            {
                doExit = true;
            }
        }


        list.SaveUserList(saveDir);

    }

}
