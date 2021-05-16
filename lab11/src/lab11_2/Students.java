package lab11_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="students")
public class Students 
{
   // stores Account objects
   @XmlElement(name="student")
   private List<Student> students = new ArrayList<>();

   // returns the List<Accounts>
   public List<Student> getStudents() 
   {
	   return students;
   }

   public void setAccounts(List<Student> students) {this.students = students;}
}