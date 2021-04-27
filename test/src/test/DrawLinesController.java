package test;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.ActionEvent;

public class DrawLinesController 
{

    @FXML
    private Canvas canvas;

    @FXML
    public void drawLinesButtonPressed(ActionEvent event) 
    {
    	//get the GraphicsContext, which is used to draw on the Canvas
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	
    	//坐标原点是0,0,
    	//向右是x轴正方向
    	//向下是y轴正方向
    	gc.strokeLine(0,0,canvas.getWidth(),canvas.getHeight());
    	gc.strokeLine(canvas.getWidth(),0,0,canvas.getHeight());
    }
    
    public void initialize()
    {
    	
    }

}

