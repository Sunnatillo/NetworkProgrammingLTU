/** SocClient java file */ 
package part3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocClient {

	public static void main (String args[]) throws UnknownHostException, IOException
	{

		String ip = "localhost";
		int port = 1234;
		Socket s = new Socket(ip, 1234);

		String data = "ls";

		OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
		PrintWriter out = new PrintWriter(os);

		out.write(data);
		out.flush();


		BufferedReader buff = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String received = buff.readLine();

		System.out.println("Data received in client" + received);

		s.close();
	}
}
