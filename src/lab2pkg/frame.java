package lab2pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPwd;
	private JTextArea textArea;
	private JButton btnRun;
	private JScrollPane scrollPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void createwin() {
	EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					frame frame = new frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. 
	 */
	public frame() {
		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 545);
		contentPane = new JPanel();
		contentPane.setToolTipText("10000");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPwd = new JTextField();
		txtPwd.setText("pwd");
		txtPwd.setBounds(32, 12, 326, 25);
		contentPane.add(txtPwd);
		txtPwd.setColumns(10);
			
		
		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  textArea.setText("");
				  String cmd = txtPwd.getText();
				//  cmd = cmd.replaceAll(";", "&&");
				  String[] cmdarray = cmd.split("&&"); // splitting commands into array
				  int i=0;
				  for (String s : cmdarray) {
					  cmdarray[i] = s;
				   
				      i++;
				    }
				  			
							
					try {
						executecommand(cmdarray);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			  } 
			} );
		btnRun.setBounds(370, 12, 117, 25);
		contentPane.add(btnRun);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(31, 47, 458, 229);
		contentPane.add(scrollPane);
		
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(false);
		textArea.setText("Output");
		
		textField = new JTextField();
		textField.setText("100000");
		textField.setToolTipText("");
		textField.setBounds(29, 320, 114, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Launch");               // These commands for getting number of iteration value 
		btnNewButton.addMouseListener(new MouseAdapter() {			// and sending it to task class to launch itertion 
			@Override
			public void mouseClicked(MouseEvent e) {
				int nmblp = Integer.parseInt(textField.getText());
				task Loop =new task(nmblp);
				Loop.run();
				
				}
		});
		btnNewButton.setBounds(187, 320, 117, 25);
		contentPane.add(btnNewButton);
		
		
	}

	
	public void executecommand(String cmdarray[]) throws IOException 
	{
	try {
		String abc = "";
		for (int j=0;j<cmdarray.length;j++)
		{
			abc = abc + cmdarray[j]  + ";";
		}
			String[] temp = {"/bin/sh","-c",abc};  
		Process p = Runtime.getRuntime().exec(temp);   //creates process for running OS commands
     
		BufferedReader stdInput = new BufferedReader(new 
        InputStreamReader(p.getInputStream()));
		
		BufferedReader stdInputError = new BufferedReader(new 
		        InputStreamReader(p.getErrorStream()));

		
		System.out.println(Arrays.toString(cmdarray)+"\t command output");
		textArea.append("\n" + Arrays.toString(cmdarray)+"\t command output");
		
		String line=null;
		
		while((line = stdInput.readLine()) != null) 
			{
			System.out.print("\n"+line);
			textArea.append("\n"+line);
			}
		
		 while ((line = stdInputError.readLine()) != null) 
		 {
             System.out.println("ERROR!!!\n"+line);
             textArea.setText("ERROR!!!\n"+line);
		 }
	   
	}catch (IOException e){
		System.out.println("Exception"); e.printStackTrace();
		
	}
	}
}
