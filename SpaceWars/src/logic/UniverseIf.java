package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface UniverseIf extends Remote {

	int getSize() throws RemoteException;

	String getRandomPlanetName() throws RemoteException;

	PlanetIf getPlanetByName(String name) throws RemoteException;

	PlanetIf getRandomPlanet() throws RemoteException;

	Map<String, PlanetIf> getPlanets() throws RemoteException;
	

}
