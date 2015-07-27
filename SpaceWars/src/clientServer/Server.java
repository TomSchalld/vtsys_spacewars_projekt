package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import logic.Game;
import logic.Human;



public interface Server extends Remote {
	/**<p>Puts the newGame into a Map of games, depends on how much players are already in the game</p>
	 * @param newGame
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void openGame(Game newGame) throws RemoteException, Exception;
	/**<p>Brings the game to run if a new client has joined</p>
	 * @param gameName
	 * @throws RemoteException
	 */
	public void joinGame(String gameName)throws RemoteException;
	/**<p>Returns a game by the gameName</p>
	 * @param gameName
	 * @return returns <p>Returns the game on success</p><strong>null</strong> if there is no game with that name
	 * @throws RemoteException
	 */
	public Game getGameByName(String gameName)throws RemoteException;
	public boolean closeGame(String username) throws RemoteException;
	public Map<String, Game> gamesInLobby() throws RemoteException;
	public Game openGameOnServer(String gameName, int variation, int universeSize, Client player) throws Exception, RemoteException;


}
