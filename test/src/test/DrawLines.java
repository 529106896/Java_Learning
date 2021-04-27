package test;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

//主类的名字要和fxml文件名一致
public class DrawLines extends Application 
{
	@Override
	public void start(Stage stage) throws Exception
	{
		//loads fxml and configures the DrawLinesController
		Parent root = FXMLLoader.load(getClass().getResource("DrawLines.fxml"));
		
		Scene scene = new Scene(root);	//attach scene graph to scene
		stage.setTitle("Draw Lines");	//displayed in window's title bar
		stage.setScene(scene);			//attach scene to stage
		stage.show();					//display the stage
	}
	
	public static void main(String[] args)
	{
		launch(args);	//create a DrawLines object and call its start method
	}
}
