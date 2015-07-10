package logic;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import clientServer.GameIf;

public abstract class Game extends UnicastRemoteObject implements Serializable, Remote{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final String gameName;
	protected String passwort;
	protected int gameId;
	protected boolean hasEnoughPlayer=false;
	protected boolean gameFinished = false;
	protected Player winner;
	protected Player[] players;
	protected Universe universe;
	protected int round;
	private static int gameCount=0;
	protected Report endReport;
	public Game(String gameName, int universeSize) throws RemoteException{
		this.gameName=gameName;
		this.gameId=gameCount;
		this.universe=new Universe(universeSize);
		this.round = 0;
		this.endReport = new EndReport();
		gameCount++;
	}
	
	public Universe getUniverse()throws RemoteException {
		return universe;
	}
	
	public boolean hasEnoughPlayer()throws RemoteException{
		return this.hasEnoughPlayer;
	}
	
	public boolean isGameFinished() throws RemoteException{
		return gameFinished;
	}
	
	public Report getEndreport()throws RemoteException{
		return this.endReport;
	}
	
	public String getGameName()throws RemoteException {
		return gameName;
	}
	
	public boolean killAllReferences()throws RemoteException{
		//TODO write something that terminates all references so GC can clean up
		return true;
	}
	
	public abstract void addPlayer(Player newPlayer)throws RemoteException;
	
	public abstract RoundReport endRound()throws RemoteException;
}
