package Attributes;
import java.util.*;
public class ResearchPaper {
	private String title;
	private String description;
	private Date dateOfPublish;
	public ResearchPaper(String title, String description, Date dateOfPublish) {
		this.setTitle(title);
		this.setDescription(description);
		this.setDateOfPublish(dateOfPublish);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateOfPublish() {
		return dateOfPublish;
	}
	public void setDateOfPublish(Date dateOfPublish) {
		this.dateOfPublish = dateOfPublish;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dateOfPublish, description, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResearchPaper other = (ResearchPaper) obj;
		return Objects.equals(dateOfPublish, other.dateOfPublish) && Objects.equals(description, other.description)
				&& Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "ResearchPaper [title=" + title + ", description=" + description + ", dateOfPublish=" + dateOfPublish
				+ "]";
	}
}
