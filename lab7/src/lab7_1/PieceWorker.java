package lab7_1;

public class PieceWorker extends Employee
{
	private double wage;
	private int pieces;
	
	//constructor
	public PieceWorker(String firstName, String lastName, String socialSecurityNumber,
			double wage, int pieces) 
	{
		super(firstName, lastName, socialSecurityNumber);
		this.pieces = pieces;
		this.wage = wage;
	}
	
	public double getWage() 
	{
		return wage;
	}

	public void setWage(double wage) 
	{
		this.wage = wage;
	}

	public int getPieces() 
	{
		return pieces;
	}

	public void setPieces(int pieces) 
	{
		this.pieces = pieces;
	}
	
	//计算收益
	@Override
	public double earnings()
	{
		return pieces * wage;
	}
	
	
	@Override
	public String toString()
	{
		return String.format("pieces emplyee: %s%n%s: $%,.2f; %s: %d", super.toString(),
				"wage per piece", getWage(), "piece made", getPieces());
	}
	
}
