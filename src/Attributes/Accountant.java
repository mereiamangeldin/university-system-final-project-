package Attributes;
import java.util.Vector;
import Actors.*;
import Enums.SocialStatus;
import Enums.TypeOfPayment;
import javafx.util.*;

public class Accountant{
	private Vector <Payment> payments;
	public Vector<Student> notPayForStudentFee;

	public Accountant(Vector<Payment> payments) {
		this.payments = new Vector<Payment>();
	}

	public Vector<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Vector<Payment> payments) {
		this.payments = payments;
	}
	

	public String toString() {
		return "Accountant payments = " + payments;
	}	
	
	public String viewAllPayments() {
		for(Payment p : payments) {
			return p.toString();
		}
		return "\n";
	}
	
//	public void defaultAmount() {
//		for(Student s : Database.getStudents()) {
//			for(Payment p : payments) {
//				if(!(s.contains(p.getStudent()))){
//					
//				}
//			}
//		}
//	}
	

	
//	public void checkStudentFee() {
//		for(Payment p : payments) {
//			if((p.getTypeOfPayment().equals(TypeOfPayment.STUDENT_FEE))){
//				
//			}
//		}
//	}
	
//	public void getStudentWhoNotPayForSF() {
//		for(Student p : payForStudentFee) {
//			for(Student s : Database.getStudents()) {
//				if(!p.contains(s)) {
//					
//				}
//			}
//		}
//	}
}