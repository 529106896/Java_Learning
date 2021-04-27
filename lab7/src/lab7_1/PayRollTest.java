package lab7_1;

public class PayRollTest 
{
	public static void main(String[] args)
	{
		SalariedEmployee salariedEmployee = new SalariedEmployee("Jackie","Chan","123456",100);
		HourlyEmployee hourlyEmployee = new HourlyEmployee("Jonathan","Joestar","654321",15,10);
		CommissionEmployee commissionEmployee = new CommissionEmployee("Kujo","Jotaro","114514", 10000, 0.6);
		BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Joseph","Joestar","1919810", 5000, 0.4, 300);
		PieceWorker pieceWorker = new PieceWorker("Higashikata","Josuke","987654",20,14);
		
		Employee[] employees = new Employee[5];
		
		employees[0] = salariedEmployee;
		employees[1] = hourlyEmployee;
		employees[2] = commissionEmployee;
		employees[3] = basePlusCommissionEmployee;
		employees[4] = pieceWorker;
		
		for(Employee eachEmployee : employees)
		{
			System.out.printf("%s%n%s: $%,.2f%n%n", eachEmployee, "earned", eachEmployee.earnings());
		}
	}
}
