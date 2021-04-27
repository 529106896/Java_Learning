package lab4;

import java.security.SecureRandom;

//这个类用来模拟一次游戏
//总程序是Launch_Crap_Games.java

public class Crap_Games 
{
	//随机数生成器
	private static final SecureRandom randomNumbers = new SecureRandom();
	
	//三种游戏状态
	private enum Status {Continue, Won, Lost};
	
	//五种设定的点数
	private static final int Snake_Eyes = 2;
	private static final int Trey = 3;
	private static final int Seven = 7;
	private static final int Yo_Leven = 11;
	private static final int Box_Cars = 12;
	
	//掷骰子、计算点数
	public static int rollDice()
	{
		int dice1 = 1 + randomNumbers.nextInt(6);
		int dice2 = 1 + randomNumbers.nextInt(6);
		
		int sum = dice1 + dice2;
		
		return sum;
	}
	
	//玩一轮游戏
	public Play_Summary play() 
	{
		Play_Summary summary = new Play_Summary();
		int rolls = 1;
		
		int myPoint = 0;//用于判断胜负的点数；
		Status gameStatus;//游戏状态
		
		int sumOfDice = rollDice();
		
		switch(sumOfDice)
		{
			case Seven:					//赢的两种情况
			case Yo_Leven:				
				gameStatus = Status.Won;
				summary.setGameWon(true);
				summary.setRolls(rolls);
				break;
			case Snake_Eyes:			//输的三种情况
			case Trey:
			case Box_Cars:
				gameStatus = Status.Lost;
				summary.setGameWon(false);
				summary.setRolls(rolls);
				break;
			default:					//暂时没输
				gameStatus = Status.Continue;
				myPoint = sumOfDice;	//先记录当前点数
				break;
		}
		
		while(gameStatus == Status.Continue)
		{
			sumOfDice = rollDice();		//继续摇骰子
			rolls++;					//轮数增加
			if(sumOfDice == myPoint)	//掷出和之前一样的点数就算赢
			{
				gameStatus = Status.Won;
				summary.setGameWon(true);
				summary.setRolls(rolls);
			}
			else if(sumOfDice == Seven)	//掷出7算输
			{
				gameStatus = Status.Lost;
				summary.setGameWon(false);
				summary.setRolls(rolls);
			}
		}
		
		return summary;
		
		
	}
}
