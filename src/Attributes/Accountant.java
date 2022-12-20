package Attributes;
import java.util.Vector;

import Attributes.Database;
import Attributes.Payment;
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
	
<<<<<<< HEAD
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
=======
	public void getStudentWhoNotPayForSF() {
		for(Student s : Database.getStudents()) {
			boolean check = false;
			for(Payment p : payments) {
				if((p.getTypeOfPayment().equals(TypeOfPayment.STUDENT_FEE))) {
					if(p.getStudent().equals(s)) {
						check = true;
						break;
					}
				}
			}
			if(!check) {
				this.notPayForStudentFee.add(s);
			}
		}
	}
>>>>>>> ebeb90525217b743afe3b70f8ce93ca41eecbe2e
}