package lab11_4;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class channelTest 
{
	public static void main(String[] args) throws IOException
	{
		RandomAccessFile File1 = new RandomAccessFile("src/lab11_4/file1.txt", "rw");
		RandomAccessFile File2 = new RandomAccessFile("src/lab11_4/file2.txt", "rw");
		FileChannel channelFirst = File1.getChannel();
		FileChannel channelSecond = File2.getChannel();
		
		
		String newData = "Java is the best Language in the World!";
		String newData2 = "C++ is the best Language in the world!";
		
		//覆盖写
//		ByteBuffer buf = ByteBuffer.allocate(50);
//		buf.clear();
//		buf.put(newData.getBytes());
//		buf.flip();
//		while(buf.hasRemaining())
//			channelFirst.write(buf);
		
		//非覆盖写（直接续写）
		ByteBuffer buf = ByteBuffer.allocate(50);
		if(channelFirst.size() == 0)
		{
			channelFirst.position(channelFirst.size());
			channelFirst.write(ByteBuffer.wrap(newData.getBytes()));
		}
		else
		{
			channelFirst.position(channelFirst.size());
			channelFirst.write(ByteBuffer.wrap("\n".getBytes()));
			channelFirst.position(channelFirst.size());
			channelFirst.write(ByteBuffer.wrap(newData.getBytes()));
		}
		
		if(channelSecond.size() == 0)
		{
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap(newData2.getBytes()));
		}
		else
		{
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap("\n".getBytes()));
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap(newData2.getBytes()));
		}
		
		
		File1.close();
		File2.close();
		
		
		//读取
		File1 = new RandomAccessFile("src/lab11_4/file1.txt", "rw");
		File2 = new RandomAccessFile("src/lab11_4/file2.txt", "rw");
		channelFirst = File1.getChannel();
		channelSecond = File2.getChannel();
		buf = ByteBuffer.allocate(50);
		
		System.out.println("File1内容");
		while(channelFirst.read(buf) != -1)
		{
			buf.flip();
			while(buf.hasRemaining())
				System.out.print((char)buf.get());
			buf.clear();
		}
		System.out.println();
		
		System.out.println("File2内容");
		while(channelSecond.read(buf) != -1)
		{
			buf.flip();
			while(buf.hasRemaining())
				System.out.print((char)buf.get());
			buf.clear();
		}
		
		File1.close();
		File2.close();
	}
}
