package lab14_4;

import javafx.application.Platform;


public class BouncingTask implements Runnable 
{
	private Bouncing ball;

	public BouncingTask(Bouncing ball)
	{
		this.ball = ball;
	}
	
	public void run()
	{
		Platform.runLater(() -> {
			ball.moving();
		});
	}
}
