package lab10_2;

@SuppressWarnings("serial")
public class emailException1 extends Exception
{
	public emailException1()
	{
		super();
	}
	
	public emailException1(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public emailException1(String message) 
	{
		super(message);
	}
	
	public emailException1(Throwable cause)
	{
		super(cause);
	}
}
