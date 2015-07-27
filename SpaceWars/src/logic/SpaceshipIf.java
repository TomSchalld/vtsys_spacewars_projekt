package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

import clientServer.Client;

public interface SpaceshipIf extends Remote {

	public void increaseRank() throws RemoteException;
	public int attack() throws RemoteException;
	public Client getOwner() throws RemoteException;
	public void setOrbiting(PlanetIf orbiting) throws RemoteException;
	public PlanetIf getOrbiting() throws RemoteException;
	public int getOwnerId() throws RemoteException;
	public int getShipID() throws RemoteException;
	public String shipInfo() throws RemoteException;
	public boolean isFighter() throws RemoteException;

	

}
