package lab2pkg;

import java.io.IOException;

public class start {

	public static void main(String[] args) throws IOException {
	    		
		frame.createwin();  		//call the method from frame class to build windows
				
		}

	}

class task extends Thread {
		
		private int nmblp;         //number of iterations
		
		public task(int b) {		//constructor for number of iteration
			nmblp = b;
		}
		
	public void run() {				// thread run
	try {
	long a=0;
	long b=1;
	
	System.out.println(a +"\n");
	for (int i=0; i<nmblp; i++)
	{ 	System.out.println(b +"\n");
		long c = b;
		b=a+b;
		a=c;
		Thread.sleep(1000);
	}
			       
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block  
		e.printStackTrace();
	}				
	
	}
	
}