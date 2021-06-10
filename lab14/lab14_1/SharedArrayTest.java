package lab14_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedArrayTest 
{
	public static void main(String[] args)
	{
		SimpleArray sharedSimpleArray = new SimpleArray(50);
		
		ArrayWriter writer1 = new ArrayWriter(1, sharedSimpleArray);
		ArrayWriter writer2 = new ArrayWriter(1, sharedSimpleArray);
		ArrayWriter writer3 = new ArrayWriter(1, sharedSimpleArray);
		ArrayWriter writer4 = new ArrayWriter(1, sharedSimpleArray);
		ArrayWriter writer5 = new ArrayWriter(1, sharedSimpleArray);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(writer1);
		executorService.execute(writer2);
		executorService.execute(writer3);
		executorService.execute(writer4);
		executorService.execute(writer5);
		
		executorService.shutdown();
		
		try
		{
			boolean tasksEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);
			
			if(tasksEnded)
			{
				System.out.println("\n数组内容：");
				System.out.println(sharedSimpleArray);
			}
			else
				System.out.println("线程超时！");
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
}
