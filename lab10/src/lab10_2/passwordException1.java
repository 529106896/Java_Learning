package lab10_2;

@SuppressWarnings("serial")
public class passwordException1 extends Exception
{
	public passwordException1()
	{
		super();
	}
	
	public passwordException1(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public passwordException1(String message) 
	{
		super(message);
	}
	
	public passwordException1(Throwable cause)
	{
		super(cause);
	}
}