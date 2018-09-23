package lab2pkg;

import java.net.*;	// need this for InetAddress 
import java.util.Scanner;

public class Lookup {
	static public void main(String args[]) 
	{ 	
		Scanner scan = new Scanner(System.in);
		String test = scan.nextLine();
		printAddress(test);
		// lookup the address of each hostname found on the command line
		
	}
	
	static void printAddress( String hostname ) 
	{ 
		// use a try/catch to handle failed lookups 
		try {
			InetAddress a = InetAddress.getByName(hostname);
			String name = "  Sunnat";
			hostname += name;
			System.out.println(hostname + ":" + a.getHostAddress());
		} catch (UnknownHostException e) { 
			System.out.println("No address found for " + hostname);
			
		}
		
		
		
	}
	
}
