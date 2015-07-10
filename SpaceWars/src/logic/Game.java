package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

import clientServer.Client;

public interface Game extends Remote{
	/**
	 * 
	 */
	
	
	public Universe getUniverse()throws RemoteException;
	
	public boolean hasEnoughPlayer()throws RemoteException;
	
	public boolean isGameFinished() throws RemoteException;
	
	public Report getEndreport()throws RemoteException;
	
	public String getGameName()throws RemoteException;
	
	public boolean killAllReferences()throws RemoteException;
	
	public abstract void addPlayer(Client newPlayer)throws RemoteException;
	
	public abstract RoundReport endRound()throws RemoteException;
}
