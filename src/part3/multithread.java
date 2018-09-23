package part3;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class multithread implements Runnable{
		protected int          serverPortVal   = 1234;
	    protected ServerSocket serverSocketVal = null;
	    protected boolean      hasStopped    = false;
	    protected Thread       movingThread= null;

	    public multithread(int port){      //constructor
	        this.serverPortVal = port;
	    }

	    public void run(){
	        synchronized(this){
	            this.movingThread = Thread.currentThread();
	        }
	        opnSvrSocket();
	        while(! hasStopped()){
	            Socket clntSocket = null;
	            try {
	                clntSocket = this.serverSocketVal.accept();
	            } catch (IOException e) {
	                if(hasStopped()) {
	                    System.out.println("Server has Stopped...Please check") ;
	                    return;
	                }
	                throw new RuntimeException(
	                    "Client cannot be connected - Error", e);
	            }
	            new Thread(
	                new WrkrRunnable(
	                    clntSocket, "This is a multithreaded Server")
	            ).start();
	        }
	        System.out.println("Server has Stopped...Please check") ;
	    }
	    private synchronized boolean hasStopped() {
	        return this.hasStopped;
	    }
	    public synchronized void stop(){
	        this.hasStopped = true;
	        try {
	            serverSocketVal.close();
	        } catch (IOException e) {
	            throw new RuntimeException("Server can not be closed - Please check error", e);
	        }
	    }
	    private void opnSvrSocket() {
	        try {
	            serverSocketVal = new ServerSocket(serverPortVal);
	        } catch (IOException e) {
	            throw new RuntimeException("Not able to open the port ", e);
	        }
	    }

    public static void main(String[] args) throws Exception{
    	
    	multithread server = new multithread(1234);
    	Thread t1 = new Thread (server);
    	t1.start();
}
}

