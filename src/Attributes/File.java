package Attributes;

import java.io.Serializable;
import java.util.Objects;

import Enums.*;
public class File implements Serializable {
	private int id;
	private String name;
    private TypeOfFile typeOfFile;
    private String content;
    private static int numberOfFiles;
    
    public File(String name, TypeOfFile typeOfFile, String content) {
		this.name = name;
		this.typeOfFile = typeOfFile;
		this.content = content;
	}
     
    {
    	numberOfFiles++;
    	id = numberOfFiles;
    	
    }
  
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TypeOfFile getTypeOfFile() {
		return typeOfFile;
	}
	
	public void setTypeOfFile(TypeOfFile typeOfFile) {
		this.typeOfFile = typeOfFile;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int hashCode() {
		return Objects.hash(content, name, typeOfFile);
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		File other = (File) obj;
		return Objects.equals(content, other.content) && Objects.equals(name, other.name)
				&& typeOfFile == other.typeOfFile;
	}
	
	public String toString() {
		return "File [name=" + name + ", typeOfFile=" + typeOfFile + ", content=" + content + "]";
	}
}