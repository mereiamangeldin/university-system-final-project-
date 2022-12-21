package Interfaces;

import java.util.Date;
import java.util.Vector;

import Attributes.News;

public interface User {
	public boolean login(String password);
	public boolean logout();
	public Vector<News> viewNews();
	public String toString();
	public int hashCode();
	public boolean equals(Object obj);
	public String getUsername();
    public void setUsername(String username);   
    public String getPassword();  
    public void setPassword(String password);  
    public String getName();  
    public void setName(String name);  
    public String getSurname();
    public void setSurname(String surname);  
    public Date getDateOfBirth();  
    public void setDateOfBirth(Date dateOfBirth);  
    public boolean getLogged();  
    public String getFullName();
}
