package part3;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class WrkrRunnable implements Runnable{
    protected Socket clntSocket = null;
    protected String txtFrmSrvr   = null;

    public WrkrRunnable(Socket clntSocket, String txtFrmSrvr) {
        this.clntSocket = clntSocket;
        this.txtFrmSrvr   = txtFrmSrvr;
    }
    public void run() {
        try {
        	
        	String  consoleoutput;
    		        	         
        	InputStream inputstrm  = clntSocket.getInputStream();
            OutputStream outputstrm = clntSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outputstrm);
            
            //Reads received command and converts it to String
            BufferedReader buff = new BufferedReader(new InputStreamReader(clntSocket.getInputStream()));
    		String receivedcmd;
           
    		while((receivedcmd = buff.readLine())!= null) {
    		System.out.println("received : " + receivedcmd);
    		
    		consoleoutput = exccmd(receivedcmd);
    		
    		//sending console output to client
    			out.print(consoleoutput);
    			out.flush(); 
            }
    		
    		//closes connection
    	/*	clntSocket.close();
    		inputstrm.close();
    		outputstrm.close();
    	*/	  		 		
            
        } catch (IOException e) {           
            e.printStackTrace();
        }
    }
  
 //executes lnx command and sends output string from console    
 public static String exccmd (String inputcmd) throws IOException {
	 
	 String cnsloputput = "";
	
	 Process p = null;
	 String  line = "";
	 
	 
	  String lnxcmd = inputcmd.replaceAll(" && ",";");
	  String[] cmds = lnxcmd.split(";");
	  String finalCmd = "";
	  for(int i = 0; i < cmds.length; i++) {
		  finalCmd = finalCmd + cmds[i].trim() + ";";
	  }
	  System.out.println("lnxcmd: " + finalCmd);
	  
	 try {
	 	//command execution
	 	p = Runtime.getRuntime().exec("/bin/sh -c " + finalCmd);
		
		 //reading console output
		BufferedReader stdInput = new BufferedReader(new 
			        InputStreamReader(p.getInputStream()));
		
		//reading console error output
		BufferedReader stdInputError = new BufferedReader(new 
					        InputStreamReader(p.getErrorStream()));
		
		//writing output from console
		while((line = stdInput.readLine()) != null) 
		{
			cnsloputput = cnsloputput + line + "\n";
		}
		
		while((line = stdInputError.readLine()) != null) 
		{
			cnsloputput = cnsloputput + line + "\n";
		}
	 }
	 catch(Exception e){}
	 
	 return cnsloputput;	 
 }
    
}


				
	   

