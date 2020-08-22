package serverPack;

import java.io.IOException;

public class serverMsgControl
{

	String [] pieces;
	
	public void splitText(String text) throws IOException
	{
		pieces = text.split("£");
		control();
	}
	public void control() throws IOException
	{
		if(pieces[0].equals("-usr"))
		{
			serverMain.clientList.add(pieces[1]);
		}
		else if(pieces[0].equals("msg"))
		{
			int clientNo= serverMain.clientList.indexOf(pieces[2]);
			serverConnection sc = serverMain.connections.get(clientNo);
			sc.sendToClient("msg£"+pieces[1]+"£"+pieces[3]);
		}
		else if(pieces[0].equals("exit"))
		{
			int clientNo= serverMain.clientList.indexOf(pieces[1]);
		
			System.out.println("1");
			serverMain.connections.remove(clientNo);
			System.out.println("2");
			serverMain.clientList.remove(clientNo);
			System.out.println("3");
		}
	}
	public void print()
	{
		for (String str : serverMain.clientList)
		{
			System.out.println(str);
		}
	}
}
