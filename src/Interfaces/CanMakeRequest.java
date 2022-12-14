package Interfaces;

import Actors.Employee;
import Attributes.Request;

public interface CanMakeRequest{
	void makeRequest(Request request, Employee employee);
}