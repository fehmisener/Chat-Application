package serverPack;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class serverMain
{

	private JFrame frame;
	static JTextArea txtConn;
	
	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream din;
	static DataOutputStream dout;
	
	static List<serverConnection> connections = new ArrayList<serverConnection>();
	static ArrayList<String> clientList;
	
	static boolean serverRun = true;
		
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					serverMain window = new serverMain();
					window.frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
		clientList = new ArrayList<String>();
		try
		{
			serverSocket = new ServerSocket(6064);		
			while(serverRun)
			{
				socket=serverSocket.accept();
				txtConn.append("Accept\n");
				serverConnection sc = new serverConnection(socket);
				sc.start();
				connections.add(sc);
			}
			din = new DataInputStream(socket.getInputStream());
			dout= new DataOutputStream(socket.getOutputStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public serverMain()
	{
		initialize();
	}
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 594, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtConn = new JTextArea();
		txtConn.setBounds(12, 50, 552, 331);
		frame.getContentPane().add(txtConn);
		
		JLabel lblServer = new JLabel("SERVER");
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setBounds(63, 8, 425, 35);
		frame.getContentPane().add(lblServer);
	}
}
