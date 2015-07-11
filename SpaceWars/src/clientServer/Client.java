package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logic.Game;
import logic.Planet;
import logic.Spaceship;

public interface Client extends Remote {
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException;

	public void joinGame(String gameName) throws RemoteException;

	public void addCash(int cash) throws RemoteException;

	public int getCash() throws RemoteException;

	public int getAmountOfPlanets() throws RemoteException;

	public Game getGamePlaying() throws RemoteException;

	public void setGamePlaying(Game gamePlaying) throws RemoteException;

	public int getOwnerId() throws RemoteException;

	public boolean isPlayerReady() throws RemoteException;

	public void setPlayerReady(boolean playerReady) throws RemoteException;

	public boolean equals(Client other) throws RemoteException;

	public String getUsername() throws RemoteException;

	public void buyBattlestar() throws RemoteException;

	public void buyFighter() throws RemoteException;

	public void sendShip(Spaceship ship, Planet destination) throws RemoteException;

	public List<Spaceship> getStock() throws RemoteException;

	public void setStock(List<Spaceship> stock) throws RemoteException;
}
