package pizza.therealattacka.adressbook;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
* @Author fredrik grimmenhag.
*/
public class Run {

    public static void main(String[] args)
    {
        Run app = new Run();
        app.run();
    }

    private void run()
    {
        UserList list = new UserList();
        CommandInput input = new CommandInput();
        String saveDir = "Contacts";
        boolean doExit = false;

        list.LoadUserList(saveDir);






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


