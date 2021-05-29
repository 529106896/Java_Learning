package lab13_1;

public class student 
{
	private String sNo;
	private String sName;
	private String sClass;
	private double sGrade;
	
	
	
	public student(String sNo, String sName, String sClass, double sGrade) 
	{
		this.sNo = sNo;
		this.sName = sName;
		this.sClass = sClass;
		this.sGrade = sGrade;
	}
	public String getsNo() 
	{
		return sNo;
	}
	public void setsNo(String sNo) 
	{
		this.sNo = sNo;
	}
	public String getsName() 
	{
		return sName;
	}
	public void setsName(String sName) 
	{
		this.sName = sName;
	}
	public String getsClass() 
	{
		return sClass;
	}
	public void setsClass(String sClass) 
	{
		this.sClass = sClass;
	}
	public double getsGrade() 
	{
		return sGrade;
	}
	public void setsGrade(double sGrade) 
	{
		this.sGrade = sGrade;
	}
	
	@Override
	public String toString()
	{
		return String.format("%-8s %-8s %-8s %8.2f", 
				getsNo(), getsName(), getsClass(), getsGrade());
	}
	
	
}
