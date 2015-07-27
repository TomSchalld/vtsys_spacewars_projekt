package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import clientServer.Client;

public interface PlanetIf extends Remote {

	/**Adds a ship to orbit
	 * @param ship
	 * @throws RemoteException
	 */
	void addShipToOrbit(SpaceshipIf ship) throws RemoteException;

	/**deletes all ships
	 * @throws RemoteException
	 */
	void delShips() throws RemoteException;

	/**does the fight
	 * @return
	 * @throws RemoteException
	 */
	BattleReport fight() throws RemoteException;

	/**
	 * @return the count of battlestars in orbit
	 * @throws RemoteException
	 */
	int getBattlestarsInOrbit() throws RemoteException;

	/**
	 * @param owner
	 * @return battlestar count specified by its owner
	 * @throws RemoteException
	 */
	int getBattlestarsInOrbit(Client owner) throws RemoteException;

	/**
	 * @return fighter count
	 * @throws RemoteException
	 */
	int getFighterInOrbit() throws RemoteException;

	/**
	 * @param owner
	 * @return fighter count specified by its owner
	 * @throws RemoteException
	 */
	int getFighterInOrbit(Client owner) throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	int getGeneratedCredits() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	int getGeneratedCreditsPerShip() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	String getName() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	int getPlanetId() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	Client getPlanetOwner() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	Client getSecondOwner() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	List<SpaceshipIf> getSecondOwnerOrbit() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	List<SpaceshipIf> getShipsInOrbit() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	List<SpaceshipIf> getShipsTryToOrbit() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	boolean isFightAfterRoundEnded() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	boolean isMultiPlanet() throws RemoteException;

	/**
	 * @throws RemoteException
	 */
	void payCash() throws RemoteException;

	/**
	 * @param shipToRemove
	 * @return
	 * @throws RemoteException
	 */
	boolean removeShipFromOrbit(SpaceshipIf shipToRemove) throws RemoteException;

	/**
	 * @throws RemoteException
	 */
	void roundEnd() throws RemoteException;
	/**
	 * @param fightAfterRoundEnded
	 * @throws RemoteException
	 */
	void setFightAfterRoundEnded(boolean fightAfterRoundEnded) throws RemoteException;

	/**
	 * @param planetOwner
	 * @throws RemoteException
	 */
	void setPlanetOwner(Client planetOwner) throws RemoteException;

}
