package Attributes;
import java.util.*;
public class ResearchProject {
	private String title;
	private String description;
	private Vector<ResearchProject> citations;
	private Date dateOfPublish;
	public ResearchProject(String title, String description, Date dateOfPublish) {
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
	public Vector<ResearchProject> getCitations() {
		return citations;
	}
	public void setCitations(Vector<ResearchProject> citations) {
		this.citations = citations;
	}

	public Date getDateOfPublish() {
		return dateOfPublish;
	}
	public void setDateOfPublish(Date dateOfPublish) {
		this.dateOfPublish = dateOfPublish;
	}
	@Override
	public int hashCode() {
		return Objects.hash(citations, dateOfPublish, description, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResearchProject other = (ResearchProject) obj;
		return Objects.equals(citations, other.citations) && Objects.equals(dateOfPublish, other.dateOfPublish)
				&& Objects.equals(description, other.description) && Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "ResearchProject [title=" + title + ", description=" + description + ", citations=" + citations
				+ ", dateOfPublish=" + dateOfPublish + "]";
	}
}
