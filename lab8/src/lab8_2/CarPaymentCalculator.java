package lab8_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarPaymentCalculator extends Application 
{
	@Override
	   public void start(Stage stage) throws Exception 
	   {
	      Parent root = 
	         FXMLLoader.load(getClass().getResource("CarPaymentCalculator.fxml"));

	      Scene scene = new Scene(root);
	      stage.setTitle("Car Payment Calculator");
	      stage.setScene(scene);
	      stage.show();
	   }

	   public static void main(String[] args) 
	   {
	      launch(args); 
	   }
}
