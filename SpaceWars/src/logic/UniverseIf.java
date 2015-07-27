package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface UniverseIf extends Remote {

	/**
	 * @return
	 * @throws RemoteException
	 */
	int getSize() throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	String getRandomPlanetName() throws RemoteException;
	/**
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	PlanetIf getPlanetByName(String name) throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	PlanetIf getRandomPlanet() throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	Map<String, PlanetIf> getPlanets() throws RemoteException;
	/**
	 * @throws RemoteException
	 */
	public void killUniverse() throws RemoteException;
	

}
