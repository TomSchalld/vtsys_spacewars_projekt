package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import logic.Player;
import logic.Report;
import logic.RoundReport;
import logic.Universe;

public interface GameIf extends Remote {
	public Universe getUniverse()throws RemoteException;
	public boolean hasEnoughPlayer()throws RemoteException;
	public boolean isGameFinished()throws RemoteException;
	public Report getEndreport()throws RemoteException;
	public String getGameName()throws RemoteException;
	public boolean killAllReferences()throws RemoteException;
	public abstract void addPlayer(Player newPlayer)throws RemoteException;
	public abstract RoundReport endRound()throws RemoteException;
}
