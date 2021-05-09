package lab10_2;

@SuppressWarnings("serial")
public class userNameException1 extends Exception
{
	public userNameException1()
	{
		super();
	}
	
	public userNameException1(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public userNameException1(String message) 
	{
		super(message);
	}
	
	public userNameException1(Throwable cause)
	{
		super(cause);
	}
}
