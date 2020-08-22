 package application;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class SecondController implements Initializable
{
	
	Socket socket;
	public static DataInputStream din;
	public static DataOutputStream dout;
	static Integer userNo;
	static String[] pieces;
	static String who;
	static FileOutputStream fos;
	static FileInputStream fis;
	
    @FXML
    private Label lblUser;
    
	@FXML
	private JFXListView<Label> userList;

    @FXML
    private TextArea txtLog;

    @FXML
    private JFXTextField txtMsg;

    @FXML
    private JFXButton btnSend;
    
 
	
	
	public void split(String text)
	{
		pieces=text.split("£");
		if(pieces[0].equals("l1st"))
		{
			userNo=Integer.valueOf(pieces[1].trim());
		}
		control();
	}
	public void control()
	{
		if(pieces[0].equals("l1st"))
		{	
			// If the incoming code is l1st, we know that this is a user list and list it on the left bar.
			userList.getItems().clear();
			int clientNo =Integer.valueOf(pieces[1]);
			
			for(int i=2; i<=clientNo+1; i++)
			{
				Image icon = new Image(getClass().getResourceAsStream("/images/user_male_100px.png"));
				Label lbl = new Label(pieces[i]);
				lbl.setGraphic(new ImageView(icon));
				userList.getItems().add(lbl);
			}
		}
		else if(pieces[0].equals("msg"))
		{
			// If the incoming code is msg from server, we know that this is a message and print it on the screen.
			txtLog.appendText(pieces[1]+" : "+pieces[2]+"\n");
		}
	}
	public void connect()
	{
		SampleController sc = new SampleController();
			try {
				// Connect 
				socket = new Socket("Your server ip / enter localhost for testing your computer",6064);
				din = new DataInputStream(socket.getInputStream());
				dout = new DataOutputStream(socket.getOutputStream());
				
				String msgin="";
				dout.writeUTF("-usr£"+sc.userName);
				dout.flush();
				
				while(!msgin.equals("exit"))
				{
					// get msg from server
					msgin=din.readUTF();
					split(msgin);
				}		
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	@SuppressWarnings("unchecked")
	public void findSelectedPerson()
	{
		userList.setOnMouseClicked(new EventHandler()
		{
			@Override
			public void handle(Event arg0)
			{
				who=(userList.getItems().get(userList.getSelectionModel().getSelectedIndex()).getText());
				String fileName=lblUser.getText()+"-"+who+".txt";
				try
				{
					txtLog.setText("");
					fis = new FileInputStream(fileName);
					BufferedReader br = new BufferedReader(new InputStreamReader(fis,StandardCharsets.UTF_8));
					
					String line;
					while((line=br.readLine())!=null)
					{
						txtLog.appendText(line+"\n");
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	public void sendMessages(String str) throws IOException
	{	
		dout.writeUTF("msg£"+lblUser.getText()+"£"+who+"£"+str);
		dout.flush();
	}
	public void saveMsg()
	{
		try
		{
			fos = new FileOutputStream(lblUser.getText()+"-"+who+".txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		OutputStreamWriter osw = new OutputStreamWriter(fos,StandardCharsets.UTF_8);
		try
		{
			osw.write(txtLog.getText());
			osw.flush();
			osw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	void btnSend_click(ActionEvent event) throws IOException
	 {
		// Clicking the send button
		 sendMessages(txtMsg.getText()); // sendMessages method
		 txtLog.appendText(lblUser.getText()+": "+txtMsg.getText()+"\n"); // Show the sent message on the screen.
		 txtMsg.setText(""); 
		 saveMsg(); // Save the sent message on the screen.
	 }
	public void initialize(URL location, ResourceBundle resources)
	{
			EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SampleController sc = new SampleController();
				lblUser.setText(sc.userName);
				connect();		
			}
		});
			findSelectedPerson();
	}
	
}
