package lab13_4;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

public class PainterController 
{
	@FXML private RadioButton circleShapeButton;
	@FXML private RadioButton rectangleShapeButton;
	@FXML private RadioButton dashLineButton;
	@FXML private RadioButton solidLineButton;
	@FXML private RadioButton colorFillButton;
	@FXML private RadioButton textureFillButton;
	@FXML private ToggleGroup shapeToggleGroup;
	@FXML private ToggleGroup lineToggleGroup;
	@FXML private ToggleGroup fillModeToggleGroup;
	@FXML private AnchorPane drawingArea;
	
	private Shape shapeChoice = Shape.CIRCLE;
	private LineMode lineModeChoice = LineMode.SOLID;
	private fillMode fillModeChoice = fillMode.COLOR;
	private Color colorChoice = null;
	private ImagePattern textureChoice = null;
	
	private double mouseEnterX;
	private double mouseEnterY;
	
	private enum fillMode
	{
		COLOR(0), TEXTURE(1);
		
		private final int fillModeChoice;
		
		fillMode(int choice)
		{
			this.fillModeChoice = choice;
		}
		
		@SuppressWarnings("unused")
		public int getFillMode()
		{
			return fillModeChoice;
		}
	}
	
	private enum LineMode
	{
		DASH(0), SOLID(1);
		
		private final int lineModeChoice;
		
		LineMode(int choice)
		{
			this.lineModeChoice = choice;
		}
		
		@SuppressWarnings("unused")
		public int getLineMode()
		{
			return lineModeChoice;
		}
	}
	
    private enum Shape
    {
	    RECTANGLE(0), CIRCLE(1);
	   
	    private final int shapeChoice;
	    Shape(int shape)
	    {
	 	   this.shapeChoice = shape;
	    }
	   
	    @SuppressWarnings("unused")
		public int getShapeChoice()
	    {
	 	   return shapeChoice;
	    }
    }
    
    public void initialize()
    {
    	circleShapeButton.setUserData(Shape.CIRCLE);
    	rectangleShapeButton.setUserData(Shape.RECTANGLE);
    	dashLineButton.setUserData(LineMode.DASH);
    	solidLineButton.setUserData(LineMode.SOLID);
    	colorFillButton.setUserData(fillMode.COLOR);
    	textureFillButton.setUserData(fillMode.TEXTURE);
    }
    
    @FXML
    private void shapeRadioButtonSelected(ActionEvent e) 
    {
       shapeChoice = (Shape) shapeToggleGroup.getSelectedToggle().getUserData();
    }
    
    @FXML
    private void lineModeRadioButtonSelected(ActionEvent e)
    {
    	lineModeChoice = (LineMode) lineToggleGroup.getSelectedToggle().getUserData();
    }
    
    @FXML
    public void drawingAreaMousePressed(MouseEvent e)
    {
    	mouseEnterX = e.getX();
    	mouseEnterY = e.getY();
    }
    
    @FXML
    public void drawingAreaMouseReleased(MouseEvent e)
    {
    	if(shapeChoice == Shape.CIRCLE)
    	{
    		double circleCenterX = (mouseEnterX + e.getX()) / 2;
    		double circleCenterY = (mouseEnterY + e.getY()) / 2;
    		double radius1 = Math.abs(e.getX() - mouseEnterX) / 2;
    		double radius2 = Math.abs(e.getY() - mouseEnterY) / 2;
    		double radius = Math.min(radius1, radius2);
    		Circle newCircle = new Circle(circleCenterX, circleCenterY, radius);
    		newCircle.setStroke(Color.BLACK);
    		newCircle.setFill(null);
    		newCircle.setStrokeWidth(3);
    		
    		if(fillModeChoice == fillMode.COLOR)
    		{
    			newCircle.setFill(colorChoice);
    		}
    		else if(fillModeChoice == fillMode.TEXTURE)
    		{
    			//System.out.println(1);
    			newCircle.setFill(textureChoice);
    		}
    		
    		if(lineModeChoice == LineMode.DASH)
    		{
    			newCircle.setStrokeLineCap(StrokeLineCap.ROUND);
    			newCircle.getStrokeDashArray().addAll(6.0);
    		}
    		
    		drawingArea.getChildren().add(newCircle);
    	}
    	
    	if(shapeChoice == Shape.RECTANGLE)
    	{
    		double rectangleWidth = Math.abs(e.getX() - mouseEnterX);
    		double rectangleHeight = Math.abs(e.getY() - mouseEnterY);
			if(mouseEnterX > e.getX())
			{
				mouseEnterX = e.getX();
			}
			if(mouseEnterY > e.getY())
			{
				mouseEnterY = e.getY();
			}
			Rectangle newRectangle = new Rectangle(mouseEnterX, mouseEnterY, rectangleWidth, rectangleHeight);
			newRectangle.setStroke(Color.BLACK);
			newRectangle.setFill(null);
			newRectangle.setStrokeWidth(3);
			
			if(fillModeChoice == fillMode.COLOR)
    		{
    			newRectangle.setFill(colorChoice);
    		}
    		else if(fillModeChoice == fillMode.TEXTURE)
    		{
    			//System.out.println(1);
    			newRectangle.setFill(textureChoice);
    		}
			
			if(lineModeChoice == LineMode.DASH)
    		{
				newRectangle.setStrokeLineCap(StrokeLineCap.ROUND);
				newRectangle.getStrokeDashArray().addAll(6.0);
    		}
			drawingArea.getChildren().add(newRectangle);
    	}
    }
    
    @FXML
    private void colorFillButtonPressed(ActionEvent event)
    {
    	fillModeChoice = (fillMode) fillModeToggleGroup.getSelectedToggle().getUserData();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
 	   alert.setTitle("纯色填充选择");
 	   alert.setHeaderText("请从以下颜色中选择：");
 	   alert.setContentText(null);
 	    
 	   ButtonType buttonTypeOne = new ButtonType("不填充");
 	   ButtonType buttonTypeTwo = new ButtonType("红色");
 	   ButtonType buttonTypeThree = new ButtonType("绿色");
 	   ButtonType buttonTypeFour = new ButtonType("蓝色");
 	   ButtonType buttonTypeCancel = new ButtonType("取消", ButtonData.CANCEL_CLOSE);
 	   
 	   alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeCancel);
 	   Optional<ButtonType> result = alert.showAndWait();

 		if (result.get() == buttonTypeOne)
 		{
 			colorChoice = null;
 		} 
 		else if (result.get() == buttonTypeTwo) 
 		{
 			colorChoice = Color.RED;
 		} 
 		else if (result.get() == buttonTypeThree) 
 		{
 			colorChoice = Color.GREEN;
 		} 
 		else if (result.get() == buttonTypeFour)
 		{
 			colorChoice = Color.BLUE;
 		}
    }
    
    @FXML
    private void textureFillButtonPressed(ActionEvent e)
    {
    	fillModeChoice = (fillMode) fillModeToggleGroup.getSelectedToggle().getUserData();
    	ImagePattern m1 = new ImagePattern(new Image("file:src/lab13_4/1.jpg"));
    	ImagePattern m2 = new ImagePattern(new Image("file:src/lab13_4/2.jpg"));
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
  	   alert.setTitle("纯色填充选择");
  	   alert.setHeaderText("请从以下颜色中选择：");
  	   alert.setContentText(null);
  	    
  	   ButtonType buttonTypeOne = new ButtonType("图片1");
  	   ButtonType buttonTypeTwo = new ButtonType("图片2");
  	   ButtonType buttonTypeCancel = new ButtonType("取消", ButtonData.CANCEL_CLOSE);
  	   
  	   alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
  	   Optional<ButtonType> result = alert.showAndWait();
  	   
  	   if(result.get() == buttonTypeOne)
  		   textureChoice = m1;
  	   else if(result.get() == buttonTypeTwo)
  		   textureChoice = m2;
    }
    
    @FXML
    private void undoButtonPressed(ActionEvent event) 
    {
       int count = drawingArea.getChildren().size();

       // if there are any shapes remove the last one added
       if (count > 0) {
          drawingArea.getChildren().remove(count - 1);
       }
    }
    
    @FXML
    private void clearButtonPressed(ActionEvent event) 
    {
       drawingArea.getChildren().clear(); // clear the canvas
    }
}
