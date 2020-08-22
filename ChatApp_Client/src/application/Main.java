package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application
{
	Stage primaryStage;
	public void start(Stage primaryStage)
	{
	this.primaryStage=primaryStage;
		try
		{
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setMaxHeight(400);
			primaryStage.setMaxWidth(400);
			
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(400);	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		database.connect();
		launch(args);
		
	}
}
