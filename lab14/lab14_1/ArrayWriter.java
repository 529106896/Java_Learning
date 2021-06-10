package lab14_1;

import java.security.SecureRandom;

public class ArrayWriter implements Runnable
{
	private static final SecureRandom generator = new SecureRandom();
	private final SimpleArray sharedSimpleArray;
	private final int startValue;
	
	public ArrayWriter(int value, SimpleArray array)
	{
		startValue = value;
		sharedSimpleArray = array;
	}
	
	public void run()
	{
		for(int i = startValue; i < startValue + 10; i++)
		{
			sharedSimpleArray.add(1 + generator.nextInt(6));	//把一个元素加到数组里去
		}
	}
}
