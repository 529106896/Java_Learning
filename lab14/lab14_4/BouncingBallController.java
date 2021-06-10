package lab14_4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class BouncingBallController 
{
	@FXML private Pane movingPane;
	@FXML private Canvas drawCanvas;
	@FXML private GraphicsContext gc;
	private int count = 0;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	@FXML
	private void click(MouseEvent e)
	{
		if(count >= 1)
			return;
		move(e.getX(), e.getY());
		count++;
		System.out.printf("%.2f %.2f\n", e.getX(), e.getY());
	}
	
	@FXML
	private void initialize() throws InterruptedException
	{
		gc = drawCanvas.getGraphicsContext2D();
		drawCanvas.setOnMouseClicked(e -> click(e));
	}
	
	private void move(double x, double y)
	{
		Bouncing ball = new Bouncing(new Ball(x, y, 30), gc);
		BouncingTask task = new BouncingTask(ball);
		scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.MILLISECONDS);
	}
	
}
