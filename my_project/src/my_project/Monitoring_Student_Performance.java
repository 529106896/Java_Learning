package my_project;

import java.security.SecureRandom;
import java.util.Scanner;

public class Monitoring_Student_Performance 
{
	private static SecureRandom rnd = new SecureRandom();
	private static Scanner scan = new Scanner(System.in);
	private static int sum = 0;
	private static double right = 0;
	
	private static int give_question()
	{
		int a = 1 + rnd.nextInt(9);
		int b = 1 + rnd.nextInt(9);
		System.out.printf("How much is %d times %d?\n",a, b);
		return a * b;
	}
	
	private static void correct(int n)
	{
		switch(n)
		{
		case 1:
			System.out.println("Very Good!");
			break;
		case 2:
			System.out.println("Excellent!");
			break;
		case 3:
			System.out.println("Nice work!");
			break;
		case 4:
			System.out.println("Keep up the good work!");
			break;
		}
	}
	
	private static void wrong(int n)
	{
		switch(n)
		{
		case 1:
			System.out.println("No. Please try again.");
			break;
		case 2:
			System.out.println("Wrong. Try once more.");
			break;
		case 3:
			System.out.println("Don't give up!");
			break;
		case 4:
			System.out.println("No. Keep trying.");
			break;
		}
	}
	
	public static void main(String[] args) 
	{
		while(true)
		{
			System.out.println("You can input integer \"-1\" to quit immediately.");
			while(sum<10)
			{
				int answer = give_question();
				int input = scan.nextInt();
				if(input == answer)
				{
					correct(1 + rnd.nextInt(4));
					right++;
					sum++;
				}
				else if(input == -1)
					break;
				else if(input != answer)
				{
					do
					{
						sum++;
						if(sum >= 10)
							break;
						wrong(1 + rnd.nextInt(4));
						input = scan.nextInt();
					}
					while(input != answer);
					if(sum >=10)
						break;
					correct(1 + rnd.nextInt(4));
				}
			}
			double rate = right/(double)sum;
			System.out.printf("正确率为：%.2f\n", rate);
			
			if(rate>=0.75)
				System.out.println("Congratulations, you are ready to go to the next level!");
			else
				System.out.println("Please ask your teacher for extra help.");
			
			sum = 0;
			right = 0;
			
		}

	}
		
}
