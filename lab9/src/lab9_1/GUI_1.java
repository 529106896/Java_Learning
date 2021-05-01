package lab9_1;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class GUI_1 extends Application 
{
	@Override
	public void start(Stage stage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("GUI_1.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("file:src/lab9_1/sample.css");
		stage.setTitle("GUI_1");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
