package lab9_3;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class PainterController 
{
   // enum representing pen sizes
   private enum PenSize 
   {
      SMALL(2), 
      MEDIUM(4), 
      LARGE(6);
      
      private final int radius;
      
      PenSize(int radius) {this.radius = radius;}
      
      public int getRadius() {return radius;}
   };
   
   // 形状枚举：笔、椭圆、矩形、直线、橡皮擦
   private enum Shape
   {
	   PEN(0), OVAL(1), RECTANGLE(2), LINE(3), ERASER(4);
	   
	   private final int shapeChoice;
	   Shape(int shape)
	   {
		   this.shapeChoice = shape;
	   }
	   
	   public int getShapeChoice()
	   {
		   return shapeChoice;
	   }
   }

   // instance variables that refer to GUI components
   @FXML private RadioButton penRadioButton;
   @FXML private RadioButton ovalRadioButton;
   @FXML private RadioButton rectangleRadioButton;
   @FXML private RadioButton lineRadioButton;
   @FXML private RadioButton eraserRadioButton;
   @FXML private RadioButton smallRadioButton;
   @FXML private RadioButton mediumRadioButton;
   @FXML private RadioButton largeRadioButton;
   @FXML private Button chooseColorButton;
   @FXML private Pane drawingAreaPane;
   @FXML private ToggleGroup shapeToggleGroup;
   @FXML private ToggleGroup sizeToggleGroup;
   @FXML private Label colorLabel;

   // instance variables for managing Painter state
   private PenSize radius = PenSize.MEDIUM; // radius of circle
   private Paint brushColor = Color.BLACK; // drawing color
   private Shape shapeChoice = Shape.PEN;
   private double mouseEnterX;
   private double mouseEnterY;
   private double rectangleWidth;
   private double rectangleHeight;
   private double ellipseCenterX;
   private double ellipseCenterY;
   private double ellipseRadiusX;
   private double ellipseRadiusY;
   
   // set user data for the RadioButtons
   public void initialize() 
   {
      // user data on a control can be any Object
	   penRadioButton.setUserData(Shape.PEN);
	   ovalRadioButton.setUserData(Shape.OVAL);
	   rectangleRadioButton.setUserData(Shape.RECTANGLE);
	   lineRadioButton.setUserData(Shape.LINE);
	   eraserRadioButton.setUserData(Shape.ERASER);
	   
		smallRadioButton.setUserData(PenSize.SMALL);
		mediumRadioButton.setUserData(PenSize.MEDIUM);
		largeRadioButton.setUserData(PenSize.LARGE);
   }
   
   //按下鼠标的事件
   @FXML
   private void drawingAreaMousePressed(MouseEvent e)
   {
	   if(e.getX() <= 480 && e.getX() >= 0 && e.getY() >= 0 && e.getY() <= 460)
	   {
		   mouseEnterX = e.getX();
		   mouseEnterY = e.getY();
	   }
   }
   
   //松开鼠标拖动时事件
   @FXML
   private void drawingAreaMouseReleased(MouseEvent e)
   {
	   if(e.getX() <= 480 && e.getX() >= 0 && e.getY() >= 0 && e.getY() <= 460)
	   {
		   if(shapeChoice.getShapeChoice() == 1)
		   {
			   ellipseCenterX = (mouseEnterX + e.getX()) / 2;
			   ellipseCenterY = (mouseEnterY + e.getY()) / 2;
			   ellipseRadiusX = Math.abs(e.getX() - mouseEnterX) / 2;
			   ellipseRadiusY = Math.abs(e.getY() - mouseEnterY) / 2;
			   Ellipse newOval = new Ellipse(ellipseCenterX, ellipseCenterY, ellipseRadiusX, ellipseRadiusY);
			   newOval.setStroke(Color.BLACK);
			   newOval.setFill(brushColor);
			   newOval.setStrokeWidth(radius.getRadius());
			   drawingAreaPane.getChildren().add(newOval);
		   }
		   else if(shapeChoice.getShapeChoice() == 2)
		   {
			   rectangleWidth = Math.abs(e.getX() - mouseEnterX);
			   rectangleHeight = Math.abs(e.getY() - mouseEnterY);
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
			   newRectangle.setFill(brushColor);
			   newRectangle.setStrokeWidth(radius.getRadius());
			   drawingAreaPane.getChildren().add(newRectangle);
		   }
		   else if(shapeChoice.getShapeChoice() == 3)
		   {
			   Line newline = new Line(mouseEnterX, mouseEnterY, e.getX(), e.getY());
			   newline.setStroke(brushColor);
			   newline.setStrokeWidth(radius.getRadius());
			   drawingAreaPane.getChildren().add(newline);
		   }
	   }
   }
   
   // handles drawingArea's onMouseDragged MouseEvent
   @FXML
   private void drawingAreaMouseDragged(MouseEvent e) 
   {
	   if(e.getX() <= 480 && e.getX() >= 0 && e.getY() >= 0 && e.getY() <= 460)
	   {
		   if(shapeChoice.getShapeChoice() == 0)
		   {
			   Circle newCircle = new Circle(e.getX(), e.getY(), 
					   radius.getRadius(), brushColor);
			   drawingAreaPane.getChildren().add(newCircle); 
		   }
		   if(shapeChoice.getShapeChoice() == 4)
		   {
			   Circle newCircle = new Circle(e.getX(), e.getY(), 
					   radius.getRadius(), brushColor);
			   newCircle.setStrokeWidth(5 * radius.getRadius());
			   newCircle.setStroke(Color.WHITE);
			   newCircle.setFill(Color.WHITE);
			   drawingAreaPane.getChildren().add(newCircle); 
		   }
	   }
   }
   
   // handles color RadioButton's ActionEvents
   @FXML
   private void shapeRadioButtonSelected(ActionEvent e) 
   {
      // user data for each color RadioButton is the corresponding Color
      shapeChoice = 
         (Shape) shapeToggleGroup.getSelectedToggle().getUserData();
   } 
      
   // handles size RadioButton's ActionEvents
   @FXML
   private void sizeRadioButtonSelected(ActionEvent e) 
   {
      // user data for each size RadioButton is the corresponding PenSize
      radius = 
         (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
   } 
      
   // handles Undo Button's ActionEvents
   @FXML
   private void undoButtonPressed(ActionEvent event) 
   {
      int count = drawingAreaPane.getChildren().size();

      // if there are any shapes remove the last one added
      if (count > 0) {
         drawingAreaPane.getChildren().remove(count - 1);
      }
   }
   
   // handles Clear Button's ActionEvents
   @FXML
   private void clearButtonPressed(ActionEvent event) 
   {
      drawingAreaPane.getChildren().clear(); // clear the canvas
   }
   
   //选择颜色
   @FXML
   private void chooseColorButtonPressed(ActionEvent event)
   {
	   Alert alert = new Alert(AlertType.CONFIRMATION);
	   alert.setTitle("Color Choose Dialog");
	   alert.setHeaderText("You can choose your color from [Black Red Green Blue]");
	   alert.setContentText("Choose your option.");
	    
	   ButtonType buttonTypeOne = new ButtonType("Black");
	   ButtonType buttonTypeTwo = new ButtonType("Red");
	   ButtonType buttonTypeThree = new ButtonType("Green");
	   ButtonType buttonTypeFour = new ButtonType("Blue");
	   ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	   
	   alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeCancel);
	   Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == buttonTypeOne)
		{
			brushColor = Color.BLACK;
			colorLabel.setText("Black");
		} 
		else if (result.get() == buttonTypeTwo) 
		{
			brushColor = Color.RED;
			colorLabel.setText("Red");
		} 
		else if (result.get() == buttonTypeThree) 
		{
			brushColor = Color.GREEN;
			colorLabel.setText("Green");
		} 
		else if (result.get() == buttonTypeFour)
		{
			brushColor = Color.BLUE;
			colorLabel.setText("Blue");
		}
   }
}