package Attributes;

import Enums.*;
import java.util.Objects;
import java.util.Vector;
import Actors.*;

public class Lesson {
	private Format format;
    private TypeOfLesson type;
    private int durationMinutes;
    private Vector <Student> participants;
    private Teacher teacher;
    
    public Lesson(Format format, TypeOfLesson type, int durationMinutes, Vector<Student> participants, Teacher teacher) {
		this.format = format;
		this.type = type;
		this.durationMinutes = durationMinutes;
		this.participants = participants;
		this.teacher = teacher;
	}
    
	public Format getFormat() {
		return format;
	}
	
	public void setFormat(Format format) {
		this.format = format;
	}
	
	public TypeOfLesson getType() {
		return type;
	}
	
	public void setType(TypeOfLesson type) {
		this.type = type;
	}
	
	public int getDurationMinutes() {
		return durationMinutes;
	}
	
	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	
	public Vector<Student> getParticipants() {
		return participants;
	}
	
	public void setParticipants(Vector<Student> participants) {
		this.participants = participants;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public int hashCode() {
		return Objects.hash(durationMinutes, format, participants, teacher, type);
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Lesson other = (Lesson) obj;
		return durationMinutes == other.durationMinutes && format == other.format
				&& Objects.equals(participants, other.participants) && Objects.equals(teacher, other.teacher)
				&& type == other.type;
	}

	public String toString() {
		return "Lesson [format=" + format + ", type=" + type + ", durationMinutes=" + durationMinutes
				+ ", participants=" + participants + ", teacher=" + teacher + "]";
	}
}