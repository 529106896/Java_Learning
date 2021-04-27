package lab9_5;

@SuppressWarnings("serial")
public class userNameException extends Exception 
{
	public userNameException()
	{
		super();
	}
	
	public userNameException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public userNameException(String message) 
	{
		super(message);
	}
	
	public userNameException(Throwable cause)
	{
		super(cause);
	}
	
}