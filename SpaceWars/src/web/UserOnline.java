package web;

import java.util.HashMap;
import java.util.Map;

import clientServer.Client;

public class UserOnline {
	static private Map <String, Client> user = new HashMap<String,Client>();
	
	public static boolean isUserExisting(String uID){
		if(user.containsKey(uID)){
			return true;
		}
		return false;
	}
	public static Client getUserById(String uID){
		return user.get(uID);
	}
	public static void addUser(String uID, Client newUser) {
		user.put(uID, newUser);
	}
	
}
