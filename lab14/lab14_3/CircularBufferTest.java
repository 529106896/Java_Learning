package lab14_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CircularBufferTest 
{
	public static void main(String[] args)
	{
		ExecutorService application = Executors.newCachedThreadPool();
		
		CircularBuffer sharedLocation = new CircularBuffer();
		
		sharedLocation.displayState("Initial State");
		
		application.execute(new Producer(sharedLocation));
		application.execute(new Producer(sharedLocation));
		application.execute(new Producer(sharedLocation));
		application.execute(new Consumer(sharedLocation));
		
		application.shutdown();
	}
}
