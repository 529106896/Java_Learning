package lab7_4;

@SuppressWarnings("serial")
public class IllegalNameException extends Exception 
{
	public IllegalNameException()
	{
		super();
	}
	
	public IllegalNameException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public IllegalNameException(String message) 
	{
		super(message);
	}
	
	public IllegalNameException(Throwable cause)
	{
		super(cause);
	}
	
	
}
