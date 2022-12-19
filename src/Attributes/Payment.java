package Attributes;

import java.util.Date;
import java.util.Objects;
import Actors.*;
import Enums.*;

public class Payment {
	private Student student;
	private TypeOfPayment typeOfPayment;
	private Date dateOfPayment;
	private double amount;
	
	public Payment(Student student, TypeOfPayment typeOfPayment, Date dateOfPayment, double amount) {
		this.student = student;
		this.typeOfPayment = typeOfPayment;
		this.dateOfPayment = dateOfPayment;
		this.amount = amount;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TypeOfPayment getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(TypeOfPayment typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String toString() {
		return "Payment student = " + student + ", typeOfPayment = " + typeOfPayment + ", dateOfPayment = " + dateOfPayment
				+ ", amount = " + amount;
	}

	public int hashCode() {
		return Objects.hash(amount, dateOfPayment, student, typeOfPayment);
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Payment other = (Payment) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(dateOfPayment, other.dateOfPayment) && Objects.equals(student, other.student)
				&& typeOfPayment == other.typeOfPayment;
	}
	
	
	
	
	
	
	
}
