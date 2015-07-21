package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import clientServer.Client;

public interface Game extends Remote {
	/**
	 * 
	 */

	public UniverseIf getUniverse() throws RemoteException;

	public boolean hasEnoughPlayer() throws RemoteException;

	public boolean isGameFinished() throws RemoteException;

	public boolean playersReady() throws RemoteException;

	public Report getEndreport() throws RemoteException;

	public String getGameName() throws RemoteException;

	public boolean killAllReferences() throws RemoteException;

	public void addPlayer(Client newPlayer) throws RemoteException;

	public RoundReport endRound() throws RemoteException;

	public int getRound() throws RemoteException;

	public String getHostName() throws RemoteException;

	int getVariation() throws RemoteException;

	void setVariation(int variation) throws RemoteException;

}
