package Attributes;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class News implements Serializable {
	private String title;
    private String text;
    private Vector<String> comments;
    
    public News(String title, String text) {
		this.title = title; 
		this.text = text;
		comments = new Vector<String>();
	}
    
    {
    	Database.getNews().add(this);
    }
    
    public void addComment(String comment) {
    }
    
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Vector<String> getComments() {
		return comments;
	}
	
	public void setComments(Vector<String> comments) {
		this.comments = comments;
	}
	
	public int hashCode() {
		return Objects.hash(comments, text, title);
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		News other = (News) obj;
		return Objects.equals(comments, other.comments) && Objects.equals(text, other.text)
				&& Objects.equals(title, other.title);
	}

	public String toString() {
		return "News [title=" + title + ", text=" + text + ", comments=" + comments + "]";
	}
}