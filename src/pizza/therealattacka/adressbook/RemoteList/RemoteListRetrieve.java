package pizza.therealattacka.adressbook.RemoteList;


import pizza.therealattacka.adressbook.Contacts.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;


/**
 * Created by Fredrik Grimmenhag on 2016-12-06.
 * Used in conjunction with RemoteList at all times.
 */
public class RemoteListRetrieve implements Runnable {

    private static final Logger log = Logger.getLogger( RemoteListRetrieve.class.getName() );
    private String host;
    private int port;
    private Socket socket = new Socket();
    private boolean running = true;

    public RemoteListRetrieve( String host, int port)
    {
        this.host=host;
        this.port=port;
    }

    /**
     * Spaghetti Code (God try/catch statements take a lot of space).
     */
    @Override
    public void run() {

        try {
            socket = new Socket(host,port);

        } catch (IOException e) {
            log.warning("Socket connection to central catalog server is unavailable");
            System.out.println("Warning, Connection to central catalog is unavailable.");
        }

        PrintWriter out = null;
        BufferedReader in = null;

        if (socket.isConnected()) {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                log.warning(e.getMessage());
            }

            out.println("getall");

            while (running) {

                if (!socket.isConnected())

                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                if (socket.isClosed()) {
                    try {
                        out.close();
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    running = false;
                    return;
                }


                try {
                    if (in.ready()) {

                        String input = in.readLine();

                        String[] inputParts = input.split(" ");
                        if (inputParts[0].compareTo("endln") == 0) {
                            out.println("exit");
                            socket.close();
                            //Do not know how keepAlive works with Java, as in does it send the last message effectively before disconnect like many other languages?
                            //Cannot test unless i simulate unstable connection or bring in another PC, which i do not currently have on hand.

                        } else {
                            log.fine(inputParts[1]+" "+inputParts[2]+" has successfully been added to local list.");
                            RemoteList.add(new Contact(inputParts[1], inputParts[2], inputParts[3], inputParts[0]));
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        else
        {
            return;
        }
    }
}
