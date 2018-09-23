package lab2pkg;

import java.net.*;	// need this for InetAddress, Socket, ServerSocket 
import java.nio.charset.StandardCharsets;
import java.io.*;	// need this for I/O stuff


public class echoserver {
	// define a constant used as size of buffer 
	static final int BUFSIZE=1024;
	// main starts things rolling
	static public void main(String args[]) { 
		
		if (args.length != 1){
			throw new IllegalArgumentException("Must specify a port!");
		}
		
		int port = Integer.parseInt(args[0]);
		try { 
			
			// Create Server Socket (passive socket) 
			ServerSocket ss = new ServerSocket(port);
			
			while (true) { 
				Socket s = ss.accept();
				handleClient(s);
				
			} 
			
		} catch (IOException e) {
			System.out.println("Fatal I/O Error !"); 
			System.exit(0);
			
		}
		
	}
	
	//this method handles one client
	// declared as throwing IOException - this means it throws 
	// up to the calling method (who must handle it!)
	//try taking out the "throws IOException" and compiling, 
	// the compiler will tell us we need to deal with this!
	
	static void handleClient(Socket s) throws IOException 
	{ 
		byte[] buff = new byte[BUFSIZE];
		int bytesread=0;
		
		//print out client's address
		System.out.println("Connection from " + s.getInetAddress().getHostAddress());
		
		//Set up streams 
		InputStream in = s.getInputStream(); 
		OutputStream out = s.getOutputStream();
		
		//read/write loop 

		String nome = "Sunnat\n";
		byte[] b = nome.getBytes();
		
		int a = buff.length + b.length;
		byte[] nameadded = new byte[a];

		System.arraycopy(buff, 0, nameadded, 0, buff.length);
		System.arraycopy(b, 0, nameadded, buff.length, b.length);
			
//Modify your code here so that it sends back your name in addition to the echoed symbols
		
		while ((bytesread = in.read(nameadded)) != -1) 
		{		
			System.out.println("String to send is - " + new String(nameadded, StandardCharsets.UTF_8));
			out.write(nameadded,0,nameadded.length);
			out.flush();
		} 
		
		System.out.println("Client has left\n"); 
		
		
		nameadded= null;
		b=null;
		buff=null;
		
		s.close();
		in.close();
		out.close();
	}
	
}
