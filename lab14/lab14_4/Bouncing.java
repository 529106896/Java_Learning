package lab14_4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bouncing
{
	private Ball ball;

	private GraphicsContext context;
	
	public Bouncing(Ball ball, GraphicsContext context)
	{
		this.ball = ball;
		this.context = context;
	}
	
	private void clear()
	{
		//context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
		context.setFill(Color.WHITE);
		context.fillOval(ball.getCenterX() - 4.2*ball.getX_move(), ball.getCenterY() - 4.2*ball.getY_move(), ball.getRadius(), ball.getRadius());
	}
	
	private void draw()
	{
		context.setFill(Color.BLUE);
		context.fillOval(ball.getCenterX(), ball.getCenterY(), ball.getRadius(), ball.getRadius());
	}
	
	public void moving()
	{
		clear();
		draw();
		double w = context.getCanvas().getWidth();
		double h = context.getCanvas().getHeight();
		double r = ball.getRadius();
		if(ball.getCenterX() + r >= w || ball.getCenterX() <= 0)
			ball.Xbound();
		if(ball.getCenterY() + r >= h || ball.getCenterY() <= 0)
			ball.Ybound();
		ball.move();
	}
}
