package helper;

import java.util.HashMap;
import java.util.Map;

import clientServer.Client;

public class UserOnline {
	static private Map <String, Client> user = new HashMap<String,Client>();
	
	/**Checks if a user is existign
	 * @param uID
	 * @return <strong>false</strong> if there is no user with this ID
	 */
	public static boolean isUserExisting(String uID){
		if(user.containsKey(uID)){
			return true;
		}
		return false;
	}
	/**<p>Returns the user by his idea</p>
	 * @param uID
	 * @return Client if there is one
	 * @throws Exception
	 */
	public static Client getUserById(String uID) throws Exception{
		if(isUserExisting(uID)){
			return user.get(uID);
		}
		throw new Exception("User not Found Exception");
	}
	/**Add's a new user to the list
	 * @param uID
	 * @param newUser
	 */
	public static void addUser(String uID, Client newUser) {
		user.put(uID, newUser);
	}
	/**Removes a user from the list
	 * @param uID
	 */
	public static void logout(String uID) {
		user.remove(uID);
	}
	
}
