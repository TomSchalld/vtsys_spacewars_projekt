package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import clientServer.Client;

public interface PlanetIf extends Remote {

	int getBattlestarsInOrbit() throws RemoteException;

	int getFighterInOrbit() throws RemoteException;

	BattleReport fight() throws RemoteException;

	void roundEnd() throws RemoteException;

	boolean removeShipFromOrbit(SpaceshipIf shipToRemove) throws RemoteException;

	void addShipToOrbit(SpaceshipIf ship) throws RemoteException;

	void setPlanetOwner(Client planetOwner) throws RemoteException;

	Client getPlanetOwner() throws RemoteException;

	List<SpaceshipIf> getShipsTryToOrbit() throws RemoteException;

	List<SpaceshipIf> getShipsInOrbit() throws RemoteException;

	void setFightAfterRoundEnded(boolean fightAfterRoundEnded) throws RemoteException;

	boolean isFightAfterRoundEnded() throws RemoteException;

	int getPlanetId() throws RemoteException;

	int getGeneratedCredits() throws RemoteException;

	int getGeneratedCreditsPerShip() throws RemoteException;

	String getName() throws RemoteException;

	void delShips() throws RemoteException;

	Client getSecondOwner() throws RemoteException;

	int getFighterInOrbit(Client owner) throws RemoteException;

	int getBattlestarsInOrbit(Client owner) throws RemoteException;

	boolean isMultiPlanet() throws RemoteException;
	void payCash() throws RemoteException;

	List<SpaceshipIf> getSecondOwnerOrbit() throws RemoteException;

}
