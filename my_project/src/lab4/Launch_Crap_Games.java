package lab4;

import java.util.ArrayList;

//这是掷骰子游戏的总程序，模拟一百万场游戏下的各种情况

public class Launch_Crap_Games 
{
	public static void main(String[] args) 
	{
		Crap_Games game = new Crap_Games();
		
		//用数组存放每一局游戏的结果
		ArrayList<Play_Summary> summaries = new ArrayList<>();
		
		for(int i = 0; i< 1000000; i++)
		{
			summaries.add(game.play());		//玩1000000局游戏      注意：原本的测试数据是10000局，挺快的
		}									                    //后来才发现文档里要求的是一百万局，所以会跑的比较慢，大概需要7-8s
																//所以Run了之后请耐心等待，不是程序崩了，而是编译器累了
		//计算文档中的五个问题
		calculate(summaries);
	}
	
	private static void calculate(ArrayList<Play_Summary> array)
	{
		int Game_times = 0;
		for(int i = 0; i < array.size(); i++)
		{
			Game_times++;
		}
		
		System.out.printf("一共玩了%,d局游戏\n", Game_times);
		int wonGames = calculateWonGames(array);			//统计赢
		calculateLostGames(array);							//统计输
		calculateWinningChance(array.size(), wonGames);		//统计赢得概率
		calculateAverageLength(array);						//统计每局游戏平均要玩多少把
		calculateIfWinningChanceImproveWithLength(array);	//统计是否玩的把数越多胜率越高
	}
	
	//统计赢了多少把
	private static int calculateWonGames(ArrayList<Play_Summary> array)
	{
		int []frequency = new int[21];
		int gamesWon = 0;
		for(Play_Summary summary : array)
		{
			if(summary.isGameWon() == true)
			{
				if(summary.getRolls() < 20) //记录20 rolls之前的赢得次数
				{
					frequency[summary.getRolls()]++;	//记录在不同rolls下赢的次数
					gamesWon++;
				}
				else
				{
					frequency[20]++; //把>=20 rolls 才赢得归为一类
					gamesWon++;
				}
			}
		}
		System.out.println("\n一共赢了" + gamesWon + "局");
		for(int i = 1; i < frequency.length; i++)
		{
			if(i < 20)
				System.out.println("在第" + i + "把就取得胜利的局数有" + frequency[i] + "局");
			else
				System.out.println("在第20把或20把之后才取得胜利的局数有" + frequency[i] + "局");
		}
		
		return gamesWon;	//这里返回一下赢了多少把，因为下面要计算赢的概率
	}
	
	//统计输了多少把
	private static void calculateLostGames(ArrayList<Play_Summary> array)
	{
		int []frequency = new int[21];
		int gamesLost = 0;
		for(Play_Summary summary : array)
		{
			if(summary.isGameWon() == false)
			{
				if(summary.getRolls() < 20) //记录20 rolls之前的赢得次数
				{
					frequency[summary.getRolls()]++;	//记录在不同rolls下赢的次数
					gamesLost++;
				}
				else
				{
					frequency[20]++; //把>=20 rolls 才赢得归为一类
					gamesLost++;
				}
			}
		}
		System.out.println("\n一共输了" + gamesLost + "局");
		for(int i = 1; i < frequency.length; i++)
		{
			if(i < 20)
				System.out.println("在第" + i + "把就输的局数有" + frequency[i] + "局");
			else
				System.out.println("在第20把或20把之后才输的局数有" + frequency[i] + "局");
		}
	}
	
	//计算赢的概率
	private static void calculateWinningChance(int n, int wonGames)
	{
		double chance = wonGames / (double)n;
		System.out.println("\n赢得概率是" + chance);
	}
	
	//计算一下每次平均要玩多少局
	private static void calculateAverageLength(ArrayList<Play_Summary> array)
	{
		int totalRolls = 0;
		for(Play_Summary summary : array)
		{
			totalRolls += summary.getRolls();
		}
		int averageLength = totalRolls/array.size();
		System.out.println("\n平均每次掷骰子要玩" + averageLength + "把\n");
	}
	
	//计算是否玩的把数越多越容易赢
	private static void calculateIfWinningChanceImproveWithLength(ArrayList<Play_Summary> array)
	{
		for(int i = 1; i<=20; i++)
		{
			int gamesWon = 0;
			int gamesRolls = 0;				//单独统计在不同把数下赢的局数与总局数
			for(Play_Summary summary : array)
			{
				if(i < 20)
				{
					if(summary.getRolls() == i && summary.isGameWon() == true)
						gamesWon++;
					if(summary.getRolls() == i)
						gamesRolls++;
				}
				
				if(summary.getRolls() >= 20 && summary.isGameWon() == false)
					gamesWon++;
				if(summary.getRolls() >=20)
					gamesRolls++;
				
			}
			
			double chanceOfWinning = gamesWon / (double)gamesRolls;
			if(i < 20)
				System.out.println("玩" + i + "把游戏之后赢的概率是" + chanceOfWinning);
			else
				System.out.println("玩>=20把游戏之后赢的概率是" + chanceOfWinning);
			
		}
	}

}