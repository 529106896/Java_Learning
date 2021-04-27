package lab4;


//这个类用于模拟骰子的各种状态
//总程序是Launch_Crap_Games.java

public class Play_Summary 
{
	int rolls;			//记录进行几轮
	boolean gameWon;	//记录是否获胜
	
	public int getRolls()
	{
		return rolls;
	}
	
	public void setRolls(int rolls)
	{
		this.rolls = rolls;
	}
	
	public boolean isGameWon()
	{
		return gameWon;
	}
	
	public void setGameWon(boolean gameWon)
	{
		this.gameWon = gameWon;
	}
}
