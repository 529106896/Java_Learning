package lab14_5;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BouncingBallController
{
    @FXML
    private Pane pane;

    private int ballnum = 0;

    @SuppressWarnings("unused")
	private double angle;

    private List<Ball> balls = new ArrayList<>();

    public ExecutorService executorService = Executors.newScheduledThreadPool(100);
    @FXML
    void initialize(){
        pane.setOnMousePressed
        (
                e->{
                    balls.add(new Ball(e));
                    pane.getChildren().add(balls.get(ballnum).ball);
                    executorService.execute(balls.get(ballnum));
                    ballnum++;
                }
        );
    }

class Ball implements Runnable
{
    public double x;
    public double y;
    public double angle;
    public Circle ball;
    private SecureRandom rand = new SecureRandom();
    Ball(MouseEvent me)
    {
        try
        {
	        x = me.getX();
	        y = me.getY();
	        angle = 3.1415926*2*rand.nextDouble();
	        ball = new Circle(x,y,10);
	        ball.setFill(Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()));
	        }
        catch (Exception e)
        {
	            //System.out.println(e);
        }
    }

    @Override
    public void run() 
    {
        while (true)
        {
            Platform.runLater(()->
            {
                if(y >= 390)
                {
                    angle =3.1415926-angle;
                }
                if(y <= 10)
                {
                    angle =3.1415926-angle;
                }
                if(x >= 590)
                {
                    angle=-angle;
                }
                if(x <= 10)
                {
                    angle=-angle;
                }
                x += 3*Math.sin(angle);
                y += 3*Math.cos(angle);
                ball.setCenterX(x);
                ball.setCenterY(y);
            });

            try 
            {
                Thread.sleep(10);
            } catch (InterruptedException exception) 
            {
                Thread.currentThread().interrupt();
            }
           // System.out.println("x:"+x+"\ty:"+y);
        }
    }
}
}
