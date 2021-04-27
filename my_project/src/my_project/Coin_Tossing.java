package my_project;

import java.util.*;

public class Coin_Tossing 
{
	public static int Head_times = 0;
	public static int Tail_times = 0;
	private static Scanner scan = new Scanner(System.in);
	static Random r = new Random();
	
	public enum Coin
	{
		TAIL, HEAD;
	}
	
	public static Coin Tossing()
	{

		int Head_Tail = r.nextInt(2);
		if(Head_Tail == 1)
		{
			Head_times++;
			return Coin.HEAD;
		}
			
		else
		{
			Tail_times++;
			return Coin.TAIL;
		}

	}
	
	public static void main(String[] args)
	{
		boolean flag = true;
		int choose = 0;
		System.out.println("This is a Coin Tossing application");
		System.out.println("Please make your choice:");
		
		while(flag)
		{
			System.out.println("\n1.投币1次		2.查看结果");
			System.out.println("3.自定义次数	4.结束程序");
			choose = scan.nextInt();
			if(choose == 1)
			{
				if(Tossing() == Coin.HEAD)
				{
					System.out.println("您的结果是：正面");
				}
				else
				{
					System.out.println("您的结果是：反面");
				}
			}
			else if(choose == 2)
			{
				System.out.println("当前统计结果为：");
				System.out.println("正面：" + Head_times + "次");
				System.out.println("正面概率：" + (double)Head_times / (double)(Head_times + Tail_times));
				System.out.println("反面：" + Tail_times + "次");
				System.out.println("反面概率：" + (double)Tail_times / (double)(Head_times + Tail_times));
			}
			else if(choose == 4)
			{
				flag = false;
			}
			else if(choose == 3)
			{
				System.out.print("请输入次数：");
				int n = scan.nextInt();
				while(n-- > 0)
				{
					Tossing();
				}
			}
			else
			{
				System.out.println("Wrong Input! Please input again!");
			}
		}
		scan.close();
	}

}
