package Interfaces;

import Actors.Employee;
import Attributes.Request;

public interface CanMakeRequest{
	String makeRequest(Request request, Employee employee);
}