package Attributes;

import java.util.*;
public class Message {
	private Date date;
    private String text;
    
    public Message(Date date, String text) {
		this.date = date;
		this.text = text;
	}
    
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int hashCode() {
		return Objects.hash(date, text);
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Message other = (Message) obj;
		return Objects.equals(date, other.date) && Objects.equals(text, other.text);
	}
	
	public String toString() {
		return "Message [date=" + date + ", text=" + text + "]";
	}
}