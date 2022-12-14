package Attributes;

public class Mark {
	private int grade;
    private char letterGrade;
    private double firstAttestation;
    private double secondAttestation;
    private double finalScore;
    private double total;
    private double gpa;
    
    public Mark() {
    	grade = 0;
    	firstAttestation = 0;
    	secondAttestation = 0;
    	finalScore = 0;
    	total = 0;
    	gpa = 0;
    	letterGrade = 'N';
    }
    
    public Mark(int grade, char letterGrade, double firstAttestation, double secondAttestation, double finalScore, double total, double gpa) {
    	this.grade = grade;
    	this.letterGrade = letterGrade;
    	this.firstAttestation = firstAttestation;
    	this.secondAttestation = secondAttestation;
    	this.finalScore = finalScore;
    	this.total = total;
    	this.gpa = gpa;
    }
    
    public int getGrade() {
    	return grade;
    }
    
    public void setGrade(int grade) {
    	this.grade = grade;
    }
    
    public char getLetterGrade() {
    	return letterGrade;
    }
    
    public void setLetterGrade(char letterGrade) {
    	this.letterGrade = letterGrade;
    }
    
    public double getFirstAttestation() {
    	return firstAttestation;
    }
    
    public void setFirstAttestation(double firstAttestation) {
    	this.firstAttestation = firstAttestation;
    }
    
    public double getSecondAttestation() {
    	return secondAttestation;
    }
    
    public void setSecondAttestation(double secondAttestation) {
    	this.secondAttestation = secondAttestation;
    }
    
    public double getFinalScore() {
    	return finalScore;
    }
    
    public void setFinalScore(double finalScore) {
    	this.finalScore = finalScore;
    }
    
    public double getTotal() {
    	return total;
    }
    
    public void setTotal(double total) {
    	this.total = total;
    }
    
    public double getGpa() {
    	return gpa;
    }
    
    public void setGpa(double gpa) {
    	this.gpa = gpa;
    }
    
    public int hashCode() {
    	final int prime = 31;
    	int result = 1;
    	long temp;
    	temp = Double.doubleToLongBits(finalScore);
    	result = prime * result + (int) (temp ^ (temp >>> 32));
    	temp = Double.doubleToLongBits(firstAttestation);
    	result = prime * result + (int) (temp ^ (temp >>> 32));
    	temp = Double.doubleToLongBits(gpa);
    	result = prime * result + (int) (temp ^ (temp >>> 32));
    	result = prime * result + grade;
    	result = prime * result + letterGrade;
    	temp = Double.doubleToLongBits(secondAttestation);
    	result = prime * result + (int) (temp ^ (temp >>> 32));
    	temp = Double.doubleToLongBits(total);
    	result = prime * result + (int) (temp ^ (temp >>> 32));
    	return result;
    }
 
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (getClass() != obj.getClass()) return false;
    	Mark other = (Mark) obj;
    	if (Double.doubleToLongBits(finalScore) != Double.doubleToLongBits(other.finalScore)) return false;
    	if (Double.doubleToLongBits(firstAttestation) != Double.doubleToLongBits(other.firstAttestation)) return false;
    	if (Double.doubleToLongBits(gpa) != Double.doubleToLongBits(other.gpa)) return false;
    	if (grade != other.grade) return false;
    	if (letterGrade != other.letterGrade) return false;
    	if (Double.doubleToLongBits(secondAttestation) != Double.doubleToLongBits(other.secondAttestation)) return false;
    	if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total)) return false;
    	return true;
    }
    
    public String toString() {
    	return "Mark [grade=" + grade + ", letterGrade=" + letterGrade + ", firstAttestation=" + firstAttestation
    			+ ", secondAttestation=" + secondAttestation + ", finalScore=" + finalScore + ", total=" + total
    			+ ", gpa=" + gpa + "]";
    }
}