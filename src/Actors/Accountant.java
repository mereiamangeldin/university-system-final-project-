package Actors;
import java.util.Vector;
import Attributes.Database;
import Attributes.Payment;
import Enums.TypeOfPayment;


public class Accountant{
	private Vector <Payment> payments;
	private Vector<Student> notPayForStudentFee;

	public Accountant(Vector<Payment> payments) {
		this.payments = new Vector<Payment>();
		this.notPayForStudentFee = new Vector<Student>();
	}

	public Vector<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Vector<Payment> payments) {
		this.payments = payments;
	}
	
	public Vector<Student> getNotPayForStudentFee(){
		return notPayForStudentFee;
	}
	
	public void setNotPayForStudentFee(Vector<Student> notPayForStudentFee) {
		this.notPayForStudentFee = notPayForStudentFee;
	}

	public String toString() {
		return "Accountant payments = " + payments + " Students who did not pay for student fee : " + notPayForStudentFee;
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