import java.io.*;
import java.net.*;
import java.util.*;

import common.*;

class Client
{
    public static void main( String args[] )
    {
        System.out.println( "############ CLIENT ############" );
        final String host = "localhost";           // Host name
        final int port = 50000;                 // Port used

        try
        {
            Socket socket = new Socket( host, port );// Socket

            //User Input
            Console console = System.console();
            String filePath = console.readLine("Please input a file path on the server: ");
            NetStringWriter out = new NetTCPWriter(socket);
            out.put(filePath);

            //Get File back from server
            InputStream in = socket.getInputStream();
            String newFileName = console.readLine("Please input name for new file: ");
            OutputStream fileOut = new FileOutputStream(newFileName);
            while(in.available() > 0) {
                fileOut.write(in.read());
            }

            fileOut.close();
            out.close();
        }
        catch ( Exception e )
        {
            DEBUG.error("Error:\n%s", e.getMessage() );
        }
    }
}
