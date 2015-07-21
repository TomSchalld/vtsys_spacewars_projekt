package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import logic.Game;



public interface Server extends Remote {
	public void openGame(Game newGame) throws RemoteException, Exception;
	public void joinGame(String gameName)throws RemoteException;
	public Game getGameByName(String gameName)throws RemoteException;
	public boolean closeGame(String username) throws RemoteException;
	public Map<String, Game> gamesInLobby() throws RemoteException;
	public Game openGameOnServer(String gameName, int variation, int universeSize) throws Exception, RemoteException;


}
