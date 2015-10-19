import java.net.*;
import java.util.*;

import common.*;

class Client
{
  public static void main( String args[] )
  {
    System.out.println( "Client" );
    final String host = "localhost";           // Host name 
    final int    port = 50000;                 // Port used 

    try
    {
      int iterations = 100;
        
      long totalStart = 0;
      long totalStop = 0;
      
      for(int j = 0; j < iterations; j++) {
    	  
    	  totalStart += System.nanoTime();
    	  
	      NetStringWriter out;                     // String output
	      NetStringReader in;                      // String input
	
	      Socket socket = new Socket( host, port );// Socket 
	
	      out = new NetTCPWriter( socket );        // Output 
	      in  = new NetTCPReader( socket );        // Input 
    	  
	      for (int i= 0; i<args.length; i++)       // Send messages 
	      {
	        out.put( args[i] );                    //   to Server 
	        String response = in.get();            //   Response 
	        if ( response == null ) break;         // Failure 
	        System.out.printf("Length of [%s] is %s\n",
	                           args[i], response );
	      }
	      
	      out.close();
		  totalStop += System.nanoTime();
      }

      totalStart = totalStart/(long) iterations;
      totalStop = totalStop/ (long) iterations;
      
      double tt = (double) (totalStop-totalStart)/1_000_000_000;
      
      System.out.printf("Avg processing time: %12.9f seconds\n", tt);
    }
    catch ( Exception e )
    {
      DEBUG.error("Error:\n%s", e.getMessage() );
    }
  }
}
