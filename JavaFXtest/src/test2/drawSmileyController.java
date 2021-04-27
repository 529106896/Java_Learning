package test2;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class drawSmileyController 
{
	@FXML
	private Canvas canvas;
	
	public void drawSmileyButtonPressed(ActionEvent event)
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.setFill(Color.YELLOW);
		gc.fillOval(10, 10, 280, 280);
		gc.strokeOval(10, 10, 280, 280);
		
		gc.setFill(Color.BLACK);
		gc.fillOval(75, 85, 40, 40);
		gc.fillOval(185, 85, 40, 40);
		
		gc.fillOval(50, 130, 200, 120);
		
		gc.setFill(Color.YELLOW);
		gc.fillRect(50, 130, 200, 60);
		gc.fillOval(50, 140, 200, 90);
	}
}
