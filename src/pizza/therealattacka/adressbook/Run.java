package pizza.therealattacka.adressbook;


/**
* @author fredrik grimmenhag.
*/
public class Run {

    public static void main(String[] args)
    {
        Run app = new Run();
        app.run();
    }

    private void run()
    {
        ContactList list = new ContactList();
        CommandInput input = new CommandInput();
        String saveDir = "Contacts";
        boolean doExit = false;

        list.LoadUserList(saveDir);





        System.out.println("Welcome, type 'help' to get started.");
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


