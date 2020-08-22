package serverPack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class serverConnection extends Thread
{
	
	Socket socket; 
	DataInputStream din;
	DataOutputStream dout;
	boolean serverStatus=true;
	
	
	public serverConnection(Socket socket)
	{
		this.socket=socket;
	}
	public void sendToClient(String msg)
	{	
		try
		{
			dout.writeUTF(msg);
			dout.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sendToAllClient(String msg)
	{
		for(int i =0; i<serverMain.connections.size(); i++)
		{
			serverConnection sc = serverMain.connections.get(i);
			sc.sendToClient(msg);
		}
	}
	public void run()
	{	
		try
		{
			din=new DataInputStream(socket.getInputStream());
			dout=new DataOutputStream(socket.getOutputStream());
			
			while(serverStatus)
			{			
				while(din.available()==0)
				{
					try
					{
						Thread.sleep(1);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				
				String text = din.readUTF();
				serverMsgControl smc = new serverMsgControl();
				
				smc.splitText(text);
				smc.print();
				
				String users="l1st£"+Integer.toString(serverMain.clientList.size())+"£";
				for (String str : serverMain.clientList) {
					users+=str+"£";
				}			
				sendToAllClient(users);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
