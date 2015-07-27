package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

import clientServer.Client;

public interface SpaceshipIf extends Remote {

	/**
	 * @throws RemoteException
	 */
	public void increaseRank() throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	public int attack() throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	public Client getOwner() throws RemoteException;
	/**
	 * @param orbiting
	 * @throws RemoteException
	 */
	public void setOrbiting(PlanetIf orbiting) throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	public PlanetIf getOrbiting() throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	public int getOwnerId() throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	public int getShipID() throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	public String shipInfo() throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	public boolean isFighter() throws RemoteException;

	

}
