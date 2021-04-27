package lab5_2;

import java.awt.Color;
import java.awt.Graphics;
import java.security.SecureRandom;
import javax.swing.JPanel;
import java.util.Scanner;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel
{
	//private DrawPanel paint = new DrawPanel();
	private SecureRandom rand = new SecureRandom();
	private int count = 0;
	private Scanner scan = new Scanner(System.in);
	
	public DrawPanel()
	{
		setBackground(Color.white);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int choice;
		while(count < 3)
		{
			choice = rand.nextInt(3);
			count++;
			switch(choice)
			{
			case 0:
				System.out.printf("第%d个图形是直线\n", count);
				createLine().draw(g);
				break;
			case 1:
				System.out.printf("第%d个图形是椭圆\n", count);
				createOval().draw(g);
				break;
			case 2:
				System.out.printf("第%d个图形是矩形\n", count);
				createRectangle().draw(g);
				break;
			}
		
		}

	}
	
	
	public Line createLine()
	{
		boolean flag = true;
		System.out.println("现在开始画线");
		System.out.print("请输入第一个点的x坐标(0-800)：");
		int x1 = scan.nextInt();
		while(flag)
		{
			if(x1 <= 800 && x1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入x1坐标：");
				x1 = scan.nextInt();
			}
		}
		System.out.print("请输入第一个点的y坐标(0-600)：");
		int y1 = scan.nextInt();
		while(flag)
		{
			if(y1 <= 600 && y1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入y1坐标：");
				y1 = scan.nextInt();
			}
		}
		System.out.print("请输入第二个点的x坐标(0-800)：");
		int x2 = scan.nextInt();
		while(flag)
		{
			if(x2 <= 800 && x1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入x2坐标：");
				x2 = scan.nextInt();
			}
		}
		System.out.print("请输入第二个点的y坐标(0-600)：");
		int y2 = scan.nextInt();
		while(flag)
		{
			if(y2 <= 600 && y2 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入y2坐标：");
				y2 = scan.nextInt();
			}
		}
		System.out.print("请输入颜色R值(0-255)：");
		int R = scan.nextInt();
		while(flag)
		{
			if(R <= 255 && R >= 0)
				break;
			else
			{
				System.out.print("R值超出范围，请重新输入R值：");
				R = scan.nextInt();
			}
		}
		System.out.print("请输入颜色G值(0-255)：");
		int G = scan.nextInt();
		while(flag)
		{
			if(G <= 255 && G >= 0)
				break;
			else
			{
				System.out.print("G值超出范围，请重新输入G值：");
				G = scan.nextInt();
			}
		}
		System.out.print("请输入颜色B值(0-255)：");
		int B = scan.nextInt();
		while(flag)
		{
			if(B <= 255 && B >= 0)
				break;
			else
			{
				System.out.print("B值超出范围，请重新输入B值：");
				B = scan.nextInt();
			}
		}
		Color color = new Color(R, G, B);
		Line line = new Line(x1, y1, x2, y2, color);
		return line;
	}
	
	public Oval createOval()
	{
		boolean flag = true;
		System.out.println("现在开始画椭圆");
		System.out.print("请输入第一个点的x坐标(0-800)：");
		int x1 = scan.nextInt();
		while(flag)
		{
			if(x1 <= 800 && x1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入x1坐标：");
				x1 = scan.nextInt();
			}
		}
		System.out.print("请输入第一个点的y坐标(0-600)：");
		int y1 = scan.nextInt();
		while(flag)
		{
			if(y1 <= 600 && y1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入y1坐标：");
				y1 = scan.nextInt();
			}
		}
		System.out.print("请输入第二个点的x坐标(0-800)：");
		int x2 = scan.nextInt();
		while(flag)
		{
			if(x2 <= 800 && x1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入x2坐标：");
				x2 = scan.nextInt();
			}
		}
		System.out.print("请输入第二个点的y坐标(0-600)：");
		int y2 = scan.nextInt();
		while(flag)
		{
			if(y2 <= 600 && y2 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入y2坐标：");
				y2 = scan.nextInt();
			}
		}
		System.out.print("请输入颜色R值(0-255)：");
		int R = scan.nextInt();
		while(flag)
		{
			if(R <= 255 && R >= 0)
				break;
			else
			{
				System.out.print("R值超出范围，请重新输入R值：");
				R = scan.nextInt();
			}
		}
		System.out.print("请输入颜色G值(0-255)：");
		int G = scan.nextInt();
		while(flag)
		{
			if(G <= 255 && G >= 0)
				break;
			else
			{
				System.out.print("G值超出范围，请重新输入G值：");
				G = scan.nextInt();
			}
		}
		System.out.print("请输入颜色B值(0-255)：");
		int B = scan.nextInt();
		while(flag)
		{
			if(B <= 255 && B >= 0)
				break;
			else
			{
				System.out.print("B值超出范围，请重新输入B值：");
				B = scan.nextInt();
			}
		}
		Color color = new Color(R, G, B);
		System.out.print("是否填充(1/0)：");
		boolean isFilled = false;
		int select = scan.nextInt();
		while(flag)
		{
			if(select == 0 || select == 1)
				break;
			else
			{
				System.out.print("输入错误，请重新输入：");
				select = scan.nextInt();
			}
		}
		if(select == 1)
			isFilled = true;
		else if(select == 0)
			isFilled = false;
		Oval oval = new Oval(x1, y1, x2, y2, color, isFilled);
		
		return oval;
	}
	
	public Rectangle createRectangle()
	{
		boolean flag = true;
		System.out.println("现在开始画矩形");
		System.out.print("请输入第一个点的x坐标(0-800)：");
		int x1 = scan.nextInt();
		while(flag)
		{
			if(x1 <= 800 && x1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入x1坐标：");
				x1 = scan.nextInt();
			}
		}
		System.out.print("请输入第一个点的y坐标(0-600)：");
		int y1 = scan.nextInt();
		while(flag)
		{
			if(y1 <= 600 && y1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入y1坐标：");
				y1 = scan.nextInt();
			}
		}
		System.out.print("请输入第二个点的x坐标(0-800)：");
		int x2 = scan.nextInt();
		while(flag)
		{
			if(x2 <= 800 && x1 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入x2坐标：");
				x2 = scan.nextInt();
			}
		}
		System.out.print("请输入第二个点的y坐标(0-600)：");
		int y2 = scan.nextInt();
		while(flag)
		{
			if(y2 <= 600 && y2 >= 0)
				break;
			else
			{
				System.out.print("超出范围，请重新输入y2坐标：");
				y2 = scan.nextInt();
			}
		}
		System.out.print("请输入颜色R值(0-255)：");
		int R = scan.nextInt();
		while(flag)
		{
			if(R <= 255 && R >= 0)
				break;
			else
			{
				System.out.print("R值超出范围，请重新输入R值：");
				R = scan.nextInt();
			}
		}
		System.out.print("请输入颜色G值(0-255)：");
		int G = scan.nextInt();
		while(flag)
		{
			if(G <= 255 && G >= 0)
				break;
			else
			{
				System.out.print("G值超出范围，请重新输入G值：");
				G = scan.nextInt();
			}
		}
		System.out.print("请输入颜色B值(0-255)：");
		int B = scan.nextInt();
		while(flag)
		{
			if(B <= 255 && B >= 0)
				break;
			else
			{
				System.out.print("B值超出范围，请重新输入B值：");
				B = scan.nextInt();
			}
		}
		Color color = new Color(R, G, B);
		System.out.print("是否填充(1/0)：");
		boolean isFilled = false;
		int select = scan.nextInt();
		while(flag)
		{
			if(select == 0 || select == 1)
				break;
			else
			{
				System.out.print("输入错误，请重新输入：");
				select = scan.nextInt();
			}
		}
		if(select == 1)
			isFilled = true;
		else if(select == 0)
			isFilled = false;
		Rectangle rectangle = new Rectangle(x1, y1, x2, y2, color, isFilled);
		return rectangle;
	}
	
	
}
