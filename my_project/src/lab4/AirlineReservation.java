package lab4;

import java.util.Scanner;

public class AirlineReservation 
{
	private static final Scanner scan = new Scanner(System.in);
	
	//检查是否有空闲座位
	private static boolean checkSeats(boolean[] seats, int choice)
	{
		switch(choice)
		{
			case 1:
				for(int i = 0; i < 5; i++)//前五头等舱
				{
					if(seats[i] == false)
						return true;
				}
				break;
			case 2:
				for(int i = 5; i < 10; i++)//后五经济舱
				{
					if(seats[i] == false)
						return true;
				}
				break;
		}
		return false;
	}
	
	//分配座位
	private static void assignSeat(boolean[] seats, int choice)
	{
		switch(choice)
		{
			case 1:
				for(int i = 0; i < 5; i++)
				{
					if(seats[i] == false)
					{
						seats[i] = true;
						System.out.println("你的座位在头等舱" + (i+1) + "号位");
						return;		//这里刚开始用了break，但是会发生bug
									//后来发现，这里的break只是跳出了for循环，还会去执行下面的case 2
									//所以索性直接return
					}
				}
			case 2:
				for(int i = 5; i < 10; i++)
				{
					if(seats[i] == false)
					{
						seats[i] = true;
						System.out.println("你的座位在经济舱" + (i-4) + "号位");
						return;		//这里的return同上
					}
				}
		}
	}
	
	//如果没座了，如果本来选头等舱，先在经济舱看看有没空座，要是愿意调座就调座，不调座就等下一班
	private static void otherChoice(boolean seats[], int choice)
	{
		if(choice == 1)
			choice = 2;
		else
			choice = 1;
		
		boolean seatAvailable = checkSeats(seats, choice);
		
		if(seatAvailable == true)
		{
			System.out.println("头等舱满了，您愿不愿意调整到经济舱？\n1:愿意    2.不愿意");
			int choiceAgain = scan.nextInt();
			if(choiceAgain == 1)
				assignSeat(seats, choice);
			else
				System.out.println("所有座位已满，请您耐心等待，下一班航班将于3小时候到达");
		}
		else
			System.out.println("所有座位已满，请您耐心等待，下一班航班将于3小时候到达");
	}
	
	public static void main(String[] args)
	{
		boolean[] seats = new boolean[10];
		int choice;
		
		while(true)
		{
			System.out.printf("输入1选择头等舱，输入2选择经济舱：");
			choice = scan.nextInt();
			boolean seatAvailable = checkSeats(seats, choice);
			if(seatAvailable == true)
				assignSeat(seats, choice);
			else
				otherChoice(seats, choice);
		}
	}
}
