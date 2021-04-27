package my_project;

public class Prime_Number 
{
	//private static Scanner scan = new Scanner(System.in);
	
	private static boolean is_Prime_1(int n)
	{
		if(n == 1)
			return false;
		for(int i = 2; i <= n / 2; i++)
		{
			if(n % i == 0)
				return false;
			else
				continue;
		}
		return true;
	}
	
	private static boolean is_Prime_2(int n)
	{
		if(n == 1)
			return false;
		for(int i = 2; i <= Math.sqrt(n); i++)
		{
			if(n % i == 0)
				return false;
			else
				continue;
		}
		return true;
	}
	
	public static void main(String[] args) 
	{
		System.out.println("从1到n/2的方法：");
		int prime_num_1 = 0;
		long startTime_1 = System.currentTimeMillis();
		for(int i = 1; i <= 10000; i++)
		{
			if(is_Prime_1(i))
			{
				System.out.printf("第%d个质数是%d\n", ++prime_num_1, i);
			}
		}
		long endTime_1 = System.currentTimeMillis();
		long totalTime_1 = endTime_1 - startTime_1;
		System.out.printf("10,000内共%d个质数\n", prime_num_1);
		
		System.out.println("从1到sqrt(n)的方法：");
		int prime_num_2 = 0;
		long startTime_2 = System.currentTimeMillis();
		for(int i = 1; i <= 10000; i++)
		{
			if(is_Prime_2(i))
			{
				System.out.printf("第%d个质数是%d\n", ++prime_num_2, i);
			}
		}
		long endTime_2 = System.currentTimeMillis();
		long totalTime_2 = endTime_2 - startTime_2;
		System.out.printf("10,000内共%d个质数\n", prime_num_2);
		System.out.println("方法一（1-n/2）共用时" + totalTime_1 + "毫秒");
		System.out.println("方法二（1-sqrt(n)）共用时" + totalTime_2 + "毫秒");
		//scan.close();
	}

}
