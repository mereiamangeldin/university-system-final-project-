package Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import Actors.*;
import Attributes.*;
import Enums.*;
import Exceptions.*;
import Interfaces.*;

public class AdminMenu {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void menu(User user) {
    	Admin admin = (Admin)user;
    	String menuAdmin = "\nWelcome, Admin: " + admin.getFullName() + """
    			1. 
    			"""
    }
}