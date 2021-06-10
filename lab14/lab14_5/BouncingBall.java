package lab14_5;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.event.EventHandler;

public class BouncingBall extends Application
{
	 @Override
	   public void start(Stage stage) throws Exception 
	   {
		 	FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("BouncingBall.fxml"));
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			
	      Parent root = fxmlLoader.load();
	      
	      Scene scene = new Scene(root);
	      stage.setTitle("Ball Bouncing");
	      stage.setScene(scene);
	      stage.show();
	      
	      BouncingBallController controller = fxmlLoader.getController();
	      
	      stage.setOnCloseRequest(new EventHandler<WindowEvent>()
	    		  {
	    	  			public void handle(WindowEvent event)
	    	  			{
	    	  				controller.executorService.shutdown();
	    	  				try 
	    	  				{
	    	  					if(!controller.executorService.awaitTermination(10, TimeUnit.MILLISECONDS))
	    	  					{
	    	  						controller.executorService.shutdownNow();
	    	  					}
	    	  						
							} catch (InterruptedException e) 
	    	  				{
								controller.executorService.shutdownNow();
							}
	    	  				//System.out.println(controller.executorService.isShutdown());
	    	  			}
	    		  });
	      
	   }

	   public static void main(String[] args) 
	   {
	      launch(args);
	   }
}
