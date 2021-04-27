package test1;

import java.util.Scanner;

public class mytest 
{
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        int a = 0;
        int b = 0;
        a = scan.nextInt();
        b = scan.nextInt();
        System.out.println("a + b is " + (a + b));
    }
}
