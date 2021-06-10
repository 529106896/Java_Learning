package lab14_3;

//Buffer接口定义了要被Producer和Consumer调用的方法
public interface Buffer 
{
	//把value放到Buffer里
	public void blockingPut(int value) throws InterruptedException;
	
	//从Buffer中返回值
	public int blockingGet() throws InterruptedException;
	
}
