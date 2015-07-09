package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logic.Game;



public interface Server extends Remote {
	public void openGame(Game newGame, PlayerProxyIf handle) throws RemoteException, Exception;

	public boolean closeGame(String username) throws RemoteException;

	public List<GameProxy> getGames() throws RemoteException;

}
