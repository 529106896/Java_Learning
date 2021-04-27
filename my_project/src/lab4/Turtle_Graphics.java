package lab4;

import java.util.Scanner;

public class Turtle_Graphics 
{
	private static final Scanner scan = new Scanner(System.in);
	
	private enum direction {north, south, west, east};
	
	private static int [][] board = new int[20][20];	//画布大小
	private static int[] turtlePosition = {0, 0};		//初始位置
	private static direction turtleFacing = direction.south;	//初始朝向
	private static boolean penDown = false;						//初始笔状态
	
	public static void main(String[] args)
	{
		System.out.println("请选择操作：");
		System.out.println("1.Pen up");
		System.out.println("2.Pen down");
		System.out.println("3.Turn right");
		System.out.println("4.Turn left");
		System.out.println("5.Move forward(输入示例：5 10，向前移动十步)");
		System.out.println("6.Display the 20-by20 array");
		System.out.println("7.End of data(sentinel)");
		
		int choice = scan.nextInt();
		
		while(choice != 7)
		{
			
			switch(choice)
			{
				case 1:				//起笔
					penDown = false;
					break;
					
				case 2:				//落笔
					penDown = true;
					break;
					
				case 3:				//右转向
					if(turtleFacing == direction.south)
						turtleFacing = direction.west;
					else if(turtleFacing == direction.west)
						turtleFacing = direction.north;
					else if(turtleFacing == direction.north)
						turtleFacing = direction.east;
					else if(turtleFacing == direction.east)
						turtleFacing = direction.south;
					break;
					
				case 4:				//左转向
					if(turtleFacing == direction.south)
						turtleFacing = direction.east;
					else if(turtleFacing == direction.east)
						turtleFacing = direction.north;
					else if(turtleFacing == direction.north)
						turtleFacing = direction.west;
					else if(turtleFacing == direction.west)
						turtleFacing = direction.south;
					break;
					
				case 5:				//移动
					move(board);
					break;
					
				case 6:				//显示画布
					display(board);
					break;
			}
			
			showStatus();
					
			System.out.println("请选择操作：");
			System.out.println("1.Pen up");
			System.out.println("2.Pen down");
			System.out.println("3.Turn right");
			System.out.println("4.Turn left");
			System.out.println("5.Move forward(输入示例：5 10，向前移动十步)");
			System.out.println("6.Display the 20-by20 array");
			System.out.println("7.End of data(sentinel)");
			choice = scan.nextInt();					
		}
		
		System.out.println("退出程序，欢迎下次使用！");
	}
	
	//显示画布的函数
	private static void display(int [][] board)
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j] == 0)
					System.out.printf("○ ");
				else if(board[i][j] == 1)
					System.out.printf("■ ");
			}
			System.out.printf("\n");
		}
		System.out.printf("\n");
	}
	
	//移动函数
	private static int[][] move(int[][] board)
	{
		int dis = scan.nextInt();
		
		if(turtleFacing == direction.south)		//向南移动，行数变化，列数不变
		{
			if(penDown == true)
			{
				for(int i = 0; i < dis; i++)
				{
					board[turtlePosition[0]][turtlePosition[1]] = 1;
					turtlePosition[0]++;
				}
			}
			else
			{
				turtlePosition[0] += dis;		//如果笔没落下，只改变坐标，但是不画
			}
			return board;
		}
		
		if(turtleFacing == direction.north)		//向北移动，行数变化，列数不变
		{
			if(penDown == true)
			{
				for(int i = 0; i < dis; i++)
				{
					board[turtlePosition[0]][turtlePosition[1]] = 1;
					turtlePosition[0]--;
				}
			}
			else
			{
				turtlePosition[0] -= dis;		//如果笔没落下，只改变坐标，但是不画
			}
			return board;
		}
		
		if(turtleFacing == direction.west)		//向西移动，行数不变，列数变化
		{
			if(penDown == true)
			{
				for(int i = 0; i < dis; i++)
				{
					board[turtlePosition[0]][turtlePosition[1]] = 1;
					turtlePosition[1]--;
				}
			}
			else
			{
				turtlePosition[1] -= dis;		//如果笔没落下，只改变坐标，但是不画
			}
			return board;
		}
		
		if(turtleFacing == direction.east)		//向东移动，行数不变，列数变化
		{
			if(penDown == true)
			{
				for(int i = 0; i < dis; i++)
				{
					board[turtlePosition[0]][turtlePosition[1]] = 1;
					turtlePosition[1]++;
				}
			}
			else
			{
				turtlePosition[1] += dis;		//如果笔没落下，只改变坐标，但是不画
			}
			return board;
		}
		return board;
	}
	
	//展示各种东西的状态
	private static void showStatus()
	{
		System.out.printf("当前笔的状态是：");
		if(penDown == true)
			System.out.println("Pen Down");
		else
			System.out.println("Pen Up");
		
		System.out.printf("当前乌龟的位置是：[%d,%d]\n",turtlePosition[0],turtlePosition[1]);
		System.out.printf("当前乌龟的朝向是：");
		if(turtleFacing == direction.south)
		{
			System.out.printf("South\n");
		}
		else if(turtleFacing == direction.north)
		{
			System.out.printf("North\n");
		}
		else if(turtleFacing == direction.west)
		{
			System.out.printf("West\n");
		}
		else if(turtleFacing == direction.east)
		{
			System.out.printf("East\n");
		}
	}
}
