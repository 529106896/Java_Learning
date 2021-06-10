package lab14_3;

import java.security.SecureRandom;

public class Consumer implements Runnable 
{
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation;
	
	public Consumer(Buffer sharedLocation)
	{
		this.sharedLocation = sharedLocation;
	}
	
	//从sharedLocation中读30次，并且把读到的value累加起来
	public void run()
	{
		int sum = 0;
		
		for(int i = 1; i <= 30; i++)
		{
			try
			{
				Thread.sleep(generator.nextInt(1000));
				sum += sharedLocation.blockingGet();
			}
			catch(InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
		
		System.out.printf("%n%s %d%n%s%n", "Consumer read values totaling", sum, "结束Consumer");
	}
}
