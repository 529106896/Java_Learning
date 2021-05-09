package lab10_2;

@SuppressWarnings("serial")
public class emailException2 extends Exception
{
	public emailException2()
	{
		super();
	}
	
	public emailException2(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public emailException2(String message) 
	{
		super(message);
	}
	
	public emailException2(Throwable cause)
	{
		super(cause);
	}
}