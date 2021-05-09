package lab10_2;

@SuppressWarnings("serial")
public class passwordException extends Exception
{
	public passwordException()
	{
		super();
	}
	
	public passwordException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public passwordException(String message) 
	{
		super(message);
	}
	
	public passwordException(Throwable cause)
	{
		super(cause);
	}
}
