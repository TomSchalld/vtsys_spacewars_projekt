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

	boolean removeShipFromOrbit(Spaceship shipToRemove) throws RemoteException;

	void addShipToOrbit(Spaceship newShip) throws RemoteException;

	void setPlanetOwner(Client planetOwner) throws RemoteException;

	Client getPlanetOwner() throws RemoteException;

	List<Spaceship> getShipsTryToOrbit() throws RemoteException;

	List<Spaceship> getShipsInOrbit() throws RemoteException;

	void setFightAfterRoundEnded(boolean fightAfterRoundEnded) throws RemoteException;

	boolean isFightAfterRoundEnded() throws RemoteException;

	int getPlanetId() throws RemoteException;

	int getGeneratedCredits() throws RemoteException;

	int getGeneratedCreditsPerShip() throws RemoteException;

	String getName() throws RemoteException;

}
