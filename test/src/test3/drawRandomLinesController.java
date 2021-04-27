package test3;

import java.security.SecureRandom;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class drawRandomLinesController 
{
	@FXML private Canvas canvas;
	private static final SecureRandom rand = new SecureRandom();
	
	@FXML
	public void drawLinesButtonPressed(ActionEvent event)
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//画一百条线
		myLine[] lines = new myLine[100];
		
		final int w = (int)canvas.getWidth();
		final int h = (int)canvas.getHeight();
		
		for(int i = 0; i < lines.length; i++)
		{
			int x1 = rand.nextInt(w);
			int y1 = rand.nextInt(h);
			int x2 = rand.nextInt(w);
			int y2 = rand.nextInt(h);
			
			//产生随机颜色
			Color color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			
			lines[i] = new myLine(x1, y1, x2, y2, color);
		}
		
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		for(myLine line : lines)
		{
			line.draw(gc);
		}
	}
	
}
