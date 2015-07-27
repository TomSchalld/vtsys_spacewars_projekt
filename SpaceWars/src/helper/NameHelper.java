/**
 * 
 */
package helper;

/**
 * @author Thomas
 *
 */
public class NameHelper {
	static final String serverAddress = "rmi://192.168.178.23:1099/GameServer";

	/**<p>
	 * A method that returns the address of the bound GameServer
	 * </p>
	 * 
	 * @return a String the address
	 */
	public static String getServeraddress() {
		return serverAddress;
	}
}
