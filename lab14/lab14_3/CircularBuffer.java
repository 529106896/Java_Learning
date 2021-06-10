package lab14_3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//用一个有三个元素的buffer数组来实现同步
public class CircularBuffer implements Buffer
{
	private final int[] buffer = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	
	//用来控制同步Buffer的锁
	private final Lock accessLock = new ReentrantLock();
	
	//用来控制写、读的condition
	private final Condition canWrite = accessLock.newCondition();
	private final Condition canRead = accessLock.newCondition();
	
	private int occupiedCells = 0;	//用来数有几个buffers被占用
	private int writeIndex = 0;		//下一个要写的元素的下标
	private int readIndex = 0;		//下一个要读的元素的下标
	
	public void displayState(String operation)
	{
		System.out.printf("%s%s%d)\n%s", operation,
				" (buffer cells occupied: ", occupiedCells, "buffer Cells:  ");
		
		for(int value : buffer)
		{
			System.out.printf(" %2d  ", value);		//展示buffer里的value
		}
		
		System.out.print("\n               ");
		
		for(int i = 0; i < buffer.length; i++)
		{
			System.out.print("---- ");
		}
		
		System.out.print("\n               ");
		
		for(int i = 0; i < buffer.length; i++)
		{
			if(i == writeIndex && i == readIndex)
				System.out.print(" WR");	//同时读写
			else if(i == writeIndex)
				System.out.print(" W   ");  //只写
			else if(i == readIndex)
				System.out.print("  R  ");  //只读
			else
				System.out.print("     ");	//什么都没有
		}
		
		System.out.println("\n");
	}
	
	public void blockingPut(int value) throws InterruptedException
	{
		accessLock.lock();
		
		try
		{
			//一直等到buffer有空位，然后再写入数据
			//当没有空位的时候，就把线程放入锁定状态
			while(occupiedCells == buffer.length)
			{
				System.out.printf("Buffer is full. Producer waits\n");
				canWrite.await();	//等到buffer cell有空位
			}
			
			buffer[writeIndex] = value;	//设置新值
			
			//下一个要写的位置，循环处理
			writeIndex = (writeIndex + 1) % buffer.length;
			
			occupiedCells++;
			displayState("Producer writes " + value);
		    canRead.signalAll();
		}
		finally
		{
			accessLock.unlock();
		}
		
		
	}
	
	public int blockingGet() throws InterruptedException
	{
		accessLock.lock();
		int readValue = 0;
		
		try
		{
			//等到有数据的时候再读，没数据就等
			while(occupiedCells == 0)
			{
				System.out.printf("Buffer is empty. Consumer watis.\n");
				canRead.await();
			}
			
			readValue = buffer[readIndex];
			
			readIndex = (readIndex + 1) % buffer.length;
			
			occupiedCells--;
			displayState("Consumer reads " + readValue);
			canWrite.signalAll();
		}
		finally
		{
			accessLock.unlock();
		}
		return readValue;
	}
}
