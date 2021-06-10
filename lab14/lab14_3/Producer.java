package lab14_3;

import java.security.SecureRandom;

public class Producer implements Runnable
{
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation;	//要共享的对象的引用
	
	public Producer(Buffer sharedLocation)
	{
		this.sharedLocation = sharedLocation;
	}
	
	//在sharedLocation中存储1-10的随机数
	public void run()
	{
		//int sum = 0;
		
		for(int i = 1; i <= 10; i++)
		{
			try	//先睡0-3秒，然后把value放进Buffer中
			{
				Thread.sleep(generator.nextInt(3000));	//随机睡觉
				sharedLocation.blockingPut(i);			//把value放进Buffer中
				//sum += i;	//把value累加
				//System.out.printf("\t%2d\n", sum);
			}
			catch(InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
		
		System.out.println("\nProducer done producing\nTerminating Producer");
	}
}
