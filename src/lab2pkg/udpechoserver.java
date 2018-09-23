package lab2pkg;


import java.net.*;// need this for InetAddress, Socket, ServerSocket 
import java.io.*;// need this for I/O stuff

public class udpechoserver { 
	static final int BUFSIZE=1024;
	
	static public void main(String args[]) throws SocketException 
	{ 
		
		if (args.length != 1) {
			throw new IllegalArgumentException("Must specify a port!"); 
						
		}
		
		int port = Integer.parseInt(args[0]);
		DatagramSocket s = new DatagramSocket(port);
		byte[] buffData = new  byte[BUFSIZE];
		
		DatagramPacket dp = new DatagramPacket(buffData, BUFSIZE);
		
		try { 
			while (true) {
				s.receive(dp);
				// print out client's address 
				System.out.println("Message from " + dp.getAddress().getHostAddress());
				
				
				String name = "Sunnat\n";
				byte[] b = name.getBytes();
				
				
				
				int a = buffData.length + b.length;
				byte[] nameadded = new byte[a];

				System.arraycopy(buffData, 0, nameadded, 0, buffData.length);
				System.arraycopy(b, 0, nameadded, buffData.length, b.length);
				
				DatagramPacket	 ndp = new DatagramPacket (nameadded, nameadded.length, dp.getAddress(), dp.getPort()); // adding clients address and port bcs for new datagram it is not defined
				// Send it right back 
				s.send(ndp);
				ndp.setLength(ndp.getLength());// avoid shrinking the packet buffer
				
				
				nameadded= null;
				//buffData = null;
				
				//s.close();
				
			} 
		} catch (IOException e) {
			System.out.println("Fatal I/O Error !"); 
			System.exit(0);
			
		} 

	}
}
