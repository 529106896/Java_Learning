package test1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawShapesController 
{
	@FXML
	private Canvas canvas;
	
	public void drawRectanglesButtonPressed(ActionEvent event)
	{
		draw("rectangles");
	}
	
	public void drawOvalsButtonPressed(ActionEvent event)
	{
		draw("ovals");
	}
	
	public void draw(String input)
	{
		// get the GraphicsContext, which is used to draw on the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		// 清除矩形区域里的内容
		// 清除上一组图形，以便下次绘制
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		int step = 10;
		
		//draw 10 overlapping shapes
		for(int i = 0; i < 10; i++)
		{
			switch(input)
			{
			case "rectangles":
				//x y 是左上角的坐标
				gc.strokeRect(10 + i * step, 10 + i * step, 90 + i * step,  90 + i * step);
				break;
				
			case "ovals":
				gc.strokeOval(10 + i * step, 10 + i * step, 90 + i * step,  90 + i * step);
				break;
			}
		}
	}
}
