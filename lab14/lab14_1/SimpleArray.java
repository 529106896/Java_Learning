package lab14_1;

//synchronized的用法：
//1.方法声明时使用，一次只能有一个线程进入该方法,其他线程要想在此时调用该方法,只能排队等候
//  当前线程(就是在synchronized方法内部的线程)执行完该方法后,别的线程才能进入.
//2.对某一代码块使用,synchronized后跟括号,括号里是变量,这样,一次只有一个线程进入该代码块.此时,线程获得的是成员锁
//3.synchronized后面括号里是一对象,此时,线程获得的是对象锁
//4.synchronized后面括号里是类,此时,线程获得的是对象锁
//  如果线程进入,则线程在该类中所有操作不能进行,包括静态变量和静态方法
//  实际上,对于含有静态方法和静态变量的代码块的同步,我们通常用4来加锁

import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray 
{
	private static final SecureRandom generator = new SecureRandom();
	private final int[] array;		//共享的int数组
	private int writeIndex = 0;		//共享的要写入的位置的下标
	
	public SimpleArray(int size)
	{
		array = new int[size];
	}
	
	public synchronized void add(int value)
	{
		int pos = writeIndex;	//存储写入位置
		
		try
		{
			Thread.sleep(generator.nextInt(500));
		}
		catch(InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
		
		array[pos] = value;
		System.out.printf("%s 把 %d 写入数组的%d下标位置\n", 
				Thread.currentThread().getName(), value, pos);
		
		writeIndex++;
		System.out.printf("下一个要写入的下标是：%d\n", writeIndex);
	}
	
	public synchronized String toString()
	{
		return Arrays.toString(array);
	}
}
