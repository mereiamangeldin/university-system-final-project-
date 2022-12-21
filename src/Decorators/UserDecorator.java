package Decorators;
import Interfaces.*;
import java.util.Date;
import java.util.Vector;
import Attributes.News;

public class UserDecorator implements User {
	protected final User DecoratedUser;
	public UserDecorator(User u) {
		this.DecoratedUser = u;
	}
	public boolean login(String password) {
		return DecoratedUser.login(password);
	}
	public boolean logout() {
		return DecoratedUser.logout();
	}
	public Vector<News> viewNews() {
		return DecoratedUser.viewNews();
	}
	public String getUsername() {
		return DecoratedUser.getUsername();
	}
	public void setUsername(String username) {
		DecoratedUser.setUsername(username);
	}
	public String getPassword() {
		return DecoratedUser.getPassword();
	}
	public void setPassword(String password) {
		DecoratedUser.setPassword(password);
	}
	public String getName() {
		return DecoratedUser.getName();
	}
	public void setName(String name) {
		DecoratedUser.setName(name);
	}
	public String getSurname() {
		return DecoratedUser.getSurname();
	}
	public void setSurname(String surname) {
		DecoratedUser.setSurname(surname);
	}
	public Date getDateOfBirth() {
		return DecoratedUser.getDateOfBirth();
	}
	public void setDateOfBirth(Date dateOfBirth) {
		DecoratedUser.setDateOfBirth(dateOfBirth);
	}
	public boolean getLogged() {
		return DecoratedUser.getLogged();
	}
	public String getFullName() {
		return DecoratedUser.getFullName();
	}
}