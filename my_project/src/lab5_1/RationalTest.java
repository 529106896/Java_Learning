package lab5_1;

import java.util.ArrayList;
import java.util.Collections;

public class RationalTest 
{
	public static void main(String[] args)
	{
		Rational test1 = new Rational(5, 6);
		Rational test2 = new Rational(8, 16);
		Rational test3 = new Rational(6, 9);
		Rational test4 = new Rational(6, 20);
		
		System.out.println("test1 is " + test1.print1());
		System.out.println("test2 is " + test2.print1());
		System.out.println("test3 is " + test3.print1());
		System.out.println("test4 is " + test4.print1());
		System.out.println("test1 is " + test1.print2(4));
		System.out.println("test2 is " + test2.print2(4));
		System.out.println("test3 is " + test3.print2(4));
		System.out.println("test4 is " + test4.print2(4));
		
		Rational addtest = Rational.add(test1, test2);
		System.out.println("test1 + test2 is " + addtest.print1());
		
		Rational subtest = Rational.sub(test1, test2);
		System.out.println("test1 - test2 is " + subtest.print1());
		
		Rational multest = Rational.multiply(test1, test2);
		System.out.println("test1 * test2 is " + multest.print1());
		
		Rational divtest = Rational.divide(test1, test2);
		System.out.println("test1 / test2 is " + divtest.print1());
		
		ArrayList<Rational> list = new ArrayList<Rational>();
		list.add(test1);
		list.add(test2);
		list.add(test3);
		list.add(test4);
		Collections.sort(list);
		
		System.out.println("The sorted result of test1-4 is:");
		for(Rational temp : list)
			System.out.println(temp.print1());
	}
}
