package lab5_2;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle 
{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;
	private boolean filled;
	
	public Rectangle(int x1, int y1, int x2, int y2, Color color, boolean filled)
	{
		setX1(x1);
		setY1(y1);
		setX2(x2);
		setY2(y2);
		setColor(color);
		setFilled(filled);
	}
	
	public Rectangle()
	{
		this(0, 0, 0, 0, Color.black, false);
	}
	
	public boolean isFilled() 
	{
		return filled;
	}

	public void setFilled(boolean filled) 
	{
		this.filled = filled;
	}

	public int getX1() 
	{
		return x1;
	}

	public void setX1(int x1) 
	{
		if(x1 >= 0)
			this.x1 = x1;
		else
			this.x1 = 0;
	}

	public int getY1() 
	{
		return y1;
	}

	public void setY1(int y1) 
	{
		if(y1 >= 0)
			this.y1 = y1;
		else
			this.y1 = 0;
	}

	public int getX2() 
	{
		return x2;
	}

	public void setX2(int x2) 
	{
		if(x2 >= 0)
			this.x2 = x2;
		else
			this.x2 = 0;
	}

	public int getY2() 
	{
		return y2;
	}

	public void setY2(int y2) 
	{
		if(y2 >= 0)
			this.y2 = y2;
		else
			y2 = 0;
	}

	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}
	
	public int getUpperLeftX()
	{
		return Math.min(x1, x2);
	}
	
	public int getUppetLeftY()
	{
		return Math.min(y1, y2);
	}
	
	public int getWidth()
	{
		return Math.abs(x1 - x2);
	}
	
	public int getHeight()
	{
		return Math.abs(y1 - y2);
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		if(filled == true)
		{
			g.fillRect(getUpperLeftX(), getUppetLeftY(), getWidth(), getHeight());

		}
		else
		{
			g.drawRect(getUpperLeftX(), getUppetLeftY(), getWidth(), getHeight());

		}
	}
}
