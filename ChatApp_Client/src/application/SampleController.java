package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SampleController  {

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXPasswordField txtUserPass;

    @FXML
    private JFXButton btnLogin;
    private static boolean logStat=false;
    public static String userName="";
    
    
    @FXML
    void btnLogin_click(ActionEvent event)
    {
    	userName=txtUserId.getText();
    	String sqlQuery = "SELECT count(user_name) as no FROM table_user WHERE user_name='"+txtUserId.getText()+"' and user_pass='"+txtUserPass.getText()+"'";
    	ResultSet myRs=database.show(sqlQuery);
    	try
    	{
    			while(myRs.next())
    			{
    				if(myRs.getInt("no")==1)
    				{
    					logStat=true;
    				}
    			}
    			if(logStat)
    			{
    				//Open main frame of chat app in successful login process				
    				FXMLLoader fxloader = new FXMLLoader(getClass().getResource("Second.fxml"));
    				Parent parent2 = (Parent)fxloader.load();	
    				Stage stage  = new Stage();
    				stage.setTitle("Fix CHAT APP");
    				stage.setScene(new Scene(parent2));
    				
    				stage.setHeight(640);
    				stage.setWidth(823);
    				
    				stage.setMaxHeight(640);
    				stage.setMaxWidth(823);
    				
    				stage.setMinHeight(640);
    				stage.setMinWidth(823);
    				
    				stage.show();
    				
    				stage.setOnHiding((EventHandler<WindowEvent>) new EventHandler<WindowEvent>()
    				{
    			         @Override
    			         public void handle(WindowEvent event)
    			         {
    			             Platform.runLater(new Runnable()
    			             {	           
    			                 public void run()
    			                 {    			                	
    			                	 SecondController sc = new SecondController();
    			                	 String exit="exit£"+txtUserId.getText()+"£";
    			                	 try
    			                	 {
										sc.dout.writeUTF(exit);
										sc.dout.flush();
    			                	 }
    			                	 catch (IOException e)
    			                	 {
										// TODO Auto-generated catch block
										e.printStackTrace();
    			                	 }   			                	
    			                	 System.exit(0);
    			                 }
    			             });
    			         }
    			     });
    			}
    			else
    			{  			
    				Alert alert = new Alert(Alert.AlertType.ERROR);
    				alert.setHeaderText("Username or password incorrect!");
    				alert.showAndWait();
    			}
		}
    	catch (SQLException | IOException e)
    	{
			e.printStackTrace();
		}
    }
}
