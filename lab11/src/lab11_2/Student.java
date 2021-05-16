package lab11_2;

//Fig. 15.9: Account.java
//Account class for storing records as objects.
public class Student 
{
	private String studentNumber;
	private String studentName;
	private String studentPhoneNumber;
	private String studentEmail;
	
	// initializes an Account with default values
	public Student() 
	{
		this("", "", "", "");
	} 
	
	// initializes an Account with provided values
	public Student(String studentNumber, String studentName, String studentPhoneNumber, String studentEmail) 
	{
	   this.studentNumber = studentNumber;
	   this.studentName = studentName;
	   this.studentPhoneNumber = studentPhoneNumber;
	   this.studentEmail = studentEmail;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentPhoneNumber() {
		return studentPhoneNumber;
	}

	public void setStudentPhoneNumber(String studentPhoneNumber) {
		this.studentPhoneNumber = studentPhoneNumber;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	
} 

/*************************************************************************
* (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
* Pearson Education, Inc. All Rights Reserved.                           *
*                                                                        *
* DISCLAIMER: The authors and publisher of this book have used their     *
* best efforts in preparing the book. These efforts include the          *
* development, research, and testing of the theories and programs        *
* to determine their effectiveness. The authors and publisher make       *
* no warranty of any kind, expressed or implied, with regard to these    *
* programs or to the documentation contained in these books. The authors *
* and publisher shall not be liable in any event for incidental or       *
* consequential damages in connection with, or arising out of, the       *
* furnishing, performance, or use of these programs.                     *
*************************************************************************/