package lab5_1;

public class Rational implements Comparable<Object>
{
	private int numerator;
	
	private int denominator;
	
	public Rational(int numerator, int denominator)
	{
		int commonDivisor = greatestCommonDivisor(numerator, denominator);
		this.numerator = numerator / commonDivisor;
		this.denominator = denominator / commonDivisor;
	}
	
	public Rational()
	{
		this(1,1);
	}
	
	public static Rational add(Rational num1, Rational num2)
	{
		int lcd = (Math.abs(num1.getDenominator() * num2.getDenominator())) / greatestCommonDivisor(num1.getDenominator(), num2.getDenominator());
		
		int numerator1 = (lcd / num1.getDenominator()) * num1.getNumerator();
		int numerator2 = (lcd / num2.getDenominator()) * num2.getNumerator();
		
		return new Rational(numerator1 + numerator2, lcd);
	}
	
	public static Rational sub(Rational num1, Rational num2)
	{
		int lcd = (Math.abs(num1.getDenominator() * num2.getDenominator())) / greatestCommonDivisor(num1.getDenominator(), num2.getDenominator());
		
		int numerator1 = (lcd / num1.getDenominator()) * num1.getNumerator();
		int numerator2 = (lcd / num2.getDenominator()) * num2.getNumerator();
		
		return new Rational(numerator1 - numerator2, lcd);
	}
	
	public static Rational multiply(Rational num1, Rational num2)
	{
		return new Rational(num1.getNumerator() * num2.getNumerator(), num1.getDenominator() * num2.getDenominator());
	}
	
	public static Rational divide(Rational num1, Rational num2)
	{
		return new Rational(num1.getNumerator() * num2.getDenominator(), num1.getDenominator() * num2.getNumerator());
	}
	
	public String print1()
	{
		return String.format("%d/%d", numerator, denominator);
	}
	
	public String print2(int precision)
	{
		double num = (double)numerator / denominator;
		return String.format("%." + precision + "f", num);
	}
	
	public static int greatestCommonDivisor(int num1, int num2)
	{
		int result = 0;
		while(num2 != 0)
		{
			result = num1 % num2;
			num1 = num2;
			num2 = result;
		}
		return num1;
	}
	
	public int compareTo(Object o)
	{
		Rational num = (Rational)o;
		if((double)this.getNumerator()/this.getDenominator() > (double)num.getNumerator() / num.getDenominator())
			return 1;
		else if((double)this.getNumerator()/this.getDenominator() == (double)num.getNumerator() / num.getDenominator())
			return 0;
		else
			return -1;
	}
	
	public int getNumerator() 
	{
		return numerator;
	}
	
	public void setNumerator(int numerator) 
	{
		this.numerator = numerator;
	}
	
	public int getDenominator() 
	{
		return denominator;
	}
	
	public void setDenominator(int denominator) 
	{
		this.denominator = denominator;
	}
	
	
}
