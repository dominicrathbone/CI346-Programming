import java.io.*;
import java.lang.*;
import java.net.*;

import common.*;

class Server
{
    public static void main( String args[] )
    {
        System.out.println( "############ SERVER ############" );
        final int port = 50000;
        ( new Server() ).process( port );
    }

    public void process( final int port )
    {
        try
        {
            ServerSocket ss = new ServerSocket(port);// Server Socket

            while( true )                            // Loop
            {
                Socket socket  = ss.accept();   //  Wait for connection

                NetStringReader in = new NetTCPReader(socket);     // Input
                OutputStream out = socket.getOutputStream();

                String filePath = in.get();
                File file = new File(filePath);

                if(!filePath.isEmpty() && file.exists()) {

                    InputStream fileIn = new FileInputStream(file);
                    byte[] bytes = new byte[(int) file.length()];
                    fileIn.read(bytes,0, bytes.length);
                    out.write(bytes);
                    out.flush();

                    System.out.println("Sent File: " + filePath);
                    fileIn.close();
                }
                out.close();
            }
        }
        catch ( Exception err )
        {
            DEBUG.error("Error: " + err.getMessage() );
        }
    }
}
