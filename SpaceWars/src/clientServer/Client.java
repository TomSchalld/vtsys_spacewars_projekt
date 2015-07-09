package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
	public void openGame(String gameName,int variation, int universeSize)throws RemoteException;
	public void joinGame(String gameName)throws RemoteException;
	
}
