package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import clientServer.Client;

public interface Game extends Remote {
	/**
	 * 
	 */

	/**
	 * @return returns the universe of the game
	 * @throws RemoteException
	 */
	public UniverseIf getUniverse() throws RemoteException;

	/**
	 * @return true if there are enough Clients to play a round
	 * @throws RemoteException
	 */
	public boolean hasEnoughPlayer() throws RemoteException;

	/**
	 * @return true if game is finished
	 * @throws RemoteException
	 */
	public boolean isGameFinished() throws RemoteException;

	/**
	 * @return true if all playing Clients are marked as ready
	 * @throws RemoteException
	 */
	public boolean playersReady() throws RemoteException;

	/**
	 * @return endReport of the game
	 * @throws RemoteException
	 */
	public Report getEndreport() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	public String getGameName() throws RemoteException;

	/**Helps the GC to destroy game
	 * @return true if done.
	 * @throws RemoteException
	 */
	public boolean killAllReferences() throws RemoteException;

	/**Adds a new Player to the game
	 * @param newPlayer
	 * @throws RemoteException
	 */
	public void addPlayer(Client newPlayer) throws RemoteException;

	/**Does the regular round
	 * @return the new Report
	 * @throws RemoteException
	 */
	public RoundReport endRound() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	public int getRound() throws RemoteException;

	/**
	 * @return name of game creator
	 * @throws RemoteException
	 */
	public String getHostName() throws RemoteException;

	/**
	 * @return
	 * @throws RemoteException
	 */
	int getVariation() throws RemoteException;

	/**
	 * @param variation
	 * @throws RemoteException
	 */
	void setVariation(int variation) throws RemoteException;

	/**
	 * @return winning player or team
	 * @throws RemoteException
	 */
	public String getWinnerByName() throws RemoteException;

}
