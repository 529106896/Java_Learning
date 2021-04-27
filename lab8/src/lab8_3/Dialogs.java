package lab8_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dialogs extends Application 
{
	@Override
	   public void start(Stage stage) throws Exception 
	   {
	      Parent root = 
	         FXMLLoader.load(getClass().getResource("Dialogs.fxml"));

	      Scene scene = new Scene(root); // attach scene graph to scene
	      stage.setTitle("Dialogs Test"); // displayed in window's title bar
	      stage.setScene(scene); // attach scene to stage
	      stage.show(); // display the stage
	   }

	   public static void main(String[] args) {
	      // create a TipCalculator object and call its start method
	      launch(args); 
	   }
}
