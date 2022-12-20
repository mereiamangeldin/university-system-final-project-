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
}