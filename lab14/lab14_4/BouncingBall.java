package lab14_4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class BouncingBall extends Application
{
	 @Override
	   public void start(Stage stage) throws Exception 
	   {
	      Parent root = 
	         FXMLLoader.load(getClass().getResource("BouncingBall.fxml"));
	      
	      Scene scene = new Scene(root);
	      stage.setTitle("Ball Bouncing");
	      stage.setScene(scene);
	      stage.show();
	   }

	   public static void main(String[] args) 
	   {
	      launch(args);
	   }
}
