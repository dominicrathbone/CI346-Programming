import java.lang.*;
import java.net.*;

import common.*;

class Server
{
  public static void main( String args[] )
  {
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
        NetStringReader in =
                 new NetTCPReader(socket);     // Input
        NetStringWriter out=
                 new NetTCPWriter(socket);     // Output
        
        long start = System.nanoTime();

        while ( true )                         // Loop
        {
           String message = in.get();          // From Client
           if ( message == null ) break;       // No more data
           System.out.println( message );      // Print message
           out.put( "" + message.length() );   // Return length
        }
        
        in.close();                            // Close Read
        out.close();                           // Close Write

        socket.close();                        // Close Socket
        
        long stop = System.nanoTime();
        
        double tt = (double) ((stop-start)/1_000_000_000);
        
        System.out.printf("Server processing time: %12.9f seconds\n", tt);
      }
    }
    catch ( Exception err )
    {
      DEBUG.error("Error: " + err.getMessage() );
    }
  }
}
