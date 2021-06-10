package lab14_4;

import java.security.SecureRandom;

public class Ball 
{
	private double centerX;
	private double centerY;
	private double radius;
	private double X_move;
	private double Y_move;
	private SecureRandom rand = new SecureRandom();
	private int [] direction = {-1, 1};
	
	public Ball(double x, double y, double radius)
	{
		centerX = x;
		centerY = y;
		this.radius = radius;
		X_move = direction[rand.nextInt(2)];
		Y_move = direction[rand.nextInt(2)];
	}

	public double getCenterX() 
	{
		return centerX;
	}

	public void setCenterX(double centerX) 
	{
		this.centerX = centerX;
	}

	public double getCenterY() 
	{
		return centerY;
	}

	public void setCenterY(double centerY) 
	{
		this.centerY = centerY;
	}

	public double getRadius() 
	{
		return radius;
	}

	public void setRadius(double radius) 
	{
		this.radius = radius;
	}
	
	public void Xbound()
	{
		X_move = -X_move;
	}
	
	public void Ybound()
	{
		Y_move = -Y_move;
	}
	
	public void move()
	{
		centerX += X_move;
		centerY += Y_move;
	}

	public double getX_move() {
		return X_move;
	}

	public double getY_move() {
		return Y_move;
	}
	
	
}
