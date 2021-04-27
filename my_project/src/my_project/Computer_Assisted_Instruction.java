package my_project;

import java.security.SecureRandom;
import java.util.Scanner;

public class Computer_Assisted_Instruction 
{
	private static SecureRandom rnd = new SecureRandom();
	private static Scanner scan = new Scanner(System.in);
	
	private static int give_question()
	{
		int a = 1 + rnd.nextInt(10);
		int b = 1 + rnd.nextInt(10);
		System.out.printf("How much is %d times %d?\n",a, b);
		return a * b;
	}
	
	public static void main(String[] args) 
	{
		System.out.println("You can input integer \"-1\" to quit.");
		while(true)
		{
			int answer = give_question();
			int input = scan.nextInt();
			if(input == answer)
			{
				System.out.println("Very Good!");
			}
			else if(input == -1)
				break;
			else if(input != answer)
			{
				do
				{
					System.out.println("No. Please try again!");
					input = scan.nextInt();
				}
				while(input != answer);
				System.out.println("Very Good!");
			}
		}
		System.out.println("Now quit application.");
	}

}
