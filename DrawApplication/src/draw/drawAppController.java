package draw;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.function.DoubleToIntFunction;

import javax.swing.text.html.CSS;

import org.omg.CORBA.INITIALIZE;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class drawAppController {
	@FXML private Pane drawingAreaPane;
	@FXML private Label countLabel;
	@FXML private Button drawButton;
	@FXML private Button undoButton;
	@FXML private Button clearButton;
	private SecureRandom sc = new SecureRandom();
	private Scanner input = new Scanner(System.in);
	private Integer count = 0;
//	private Double maxWidth = 0.0;
//	private Double maxHeight = 0.0;
	
//	public void initialize()  {
//		System.out.println(drawingAreaPane.widthProperty());
//		System.out.println(drawingAreaPane.heightProperty());
//	}
	
	@FXML
	private void drawButtonPressed(ActionEvent event) {
		if (count >= 20) {
			return;
		}
		Double maxHeight = drawingAreaPane.getHeight();
		Double maxWidth = drawingAreaPane.getWidth();
		Integer choice = sc.nextInt(3);
		// 可以考虑用对话框Dialog进行输入和相关提示
		switch (choice) {
		case 0:
			System.out.println("Now we will draw a line");
			System.out.println("Please input the start point:");
			System.out.println("x not bigger than " + maxWidth);
			System.out.println("y not bigger than " + maxHeight);
			Double startX = 0.0;
			Double startY = 0.0;
			startX = input.nextDouble();
			startY = input.nextDouble();
			if (startX > maxWidth || startX < 0 || startY > maxHeight || startY < 0) {
				System.out.println("Bad Input!");
				break;
			}
			System.out.println("Please input the end point:");
			System.out.println("x not bigger than " + maxWidth);
			System.out.println("y not bigger than " + maxHeight);
			Double endX = 0.0;
			Double endY = 0.0;
			endX = input.nextDouble();
			endY = input.nextDouble();
			if (endX > maxWidth || endX < 0 || endY > maxHeight || endY < 0) {
				System.out.println("Bad Input!");
				break;
			}
			System.out.println("Valid input! Now you can see your line!");
			Line line = new Line();
			line.setStartX(startX);
			line.setStartY(startY);
			line.setEndX(endX);
			line.setEndY(endY);
			drawingAreaPane.getChildren().add(line);
			count++;
			break;
		case 1:
			System.out.println("Now we will draw a rectangle");
			System.out.println("Please input the top left point:");
			System.out.println("x not bigger than " + maxWidth);
			System.out.println("y not bigger than " + maxHeight);
			Double topLeftX = 0.0;
			Double topLeftY = 0.0;
			topLeftX = input.nextDouble();
			topLeftY = input.nextDouble();
			if (topLeftX > maxWidth || topLeftX < 0 || topLeftY > maxHeight || topLeftY < 0) {
				System.out.println("Bad Input!");
				break;
			}
			System.out.println("Please input the botton right point:");
			System.out.println("x not bigger than " + maxWidth);
			System.out.println("y not bigger than " + maxHeight);
			Double bottomRightX = 0.0;
			Double bottomRightY = 0.0;
			bottomRightX = input.nextDouble();
			bottomRightY = input.nextDouble();
			if (bottomRightX > maxWidth || bottomRightX < 0 || bottomRightY > maxHeight || bottomRightY < 0) {
				System.out.println("Bad Input!");
				break;
			}
			System.out.println("Valid input! Now you can see your line!");
			Rectangle rectangle = new Rectangle(topLeftX, topLeftY, 
					Math.abs(topLeftX - bottomRightX), 
					Math.abs(topLeftY - bottomRightY));
			rectangle.setStroke(Color.BLACK);
			rectangle.setFill(Color.TRANSPARENT);
			drawingAreaPane.getChildren().add(rectangle);
			count++;
			break;
		case 2:
			System.out.println("Now we will draw a oval");
			System.out.println("Please input the center point:");
			System.out.println("x not bigger than " + maxWidth);
			System.out.println("y not bigger than " + maxHeight);
			Double centerX = 0.0;
			Double centerY = 0.0;
			centerX = input.nextDouble();
			centerY = input.nextDouble();
			if (centerX > maxWidth || centerX < 0 || centerY > maxHeight || centerY < 0) {
				System.out.println("Bad Input!");
				break;
			}
			System.out.println("Please input the radius:");
			System.out.println("radius x plus center x not bigger than " + maxWidth);
			System.out.println("radius y plus center y not bigger than " + maxHeight);
			Double radiusX = 0.0;
			Double radiusY = 0.0;
			radiusX = input.nextDouble();
			radiusY = input.nextDouble();
			if (radiusX <= 0 || radiusY <= 0 || centerX + radiusX > maxWidth || centerY + radiusY > maxHeight) {
				System.out.println("Bad Input!");
				break;
			}
			System.out.println("Valid input! Now you can see your line!");
			Ellipse ellipse = new Ellipse(centerX, centerY, radiusX, radiusY);
			ellipse.setStroke(Color.BLACK);
			ellipse.setFill(Color.TRANSPARENT);
			drawingAreaPane.getChildren().add(ellipse);
			count++;
			break;
		default:
			System.out.println("Opps! Error Number!");
			break;
		}
		countLabel.setText(String.valueOf(count));
//		if (count >= 20) {
//			drawButton.setDisable(true);
//		}
	}
	
	@FXML
	private void undoButtonPressed(ActionEvent event) {
//		int drawingCount = drawingAreaPane.getChildren().size();
		if (count > 0) {
			drawingAreaPane.getChildren().remove(count - 1);
			count--;
	        countLabel.setText(String.valueOf(count));
	    }
	}
	
	@FXML
	private void clearButtonPressed(ActionEvent event) {
		 drawingAreaPane.getChildren().clear();
		 count = 0;
		 countLabel.setText(String.valueOf(count));
	}
	
}
