package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logic.Battlestar;
import logic.Fighter;
import logic.Game;
import logic.Planet;
import logic.Spaceship;

public interface Client extends Remote {
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException;

	public void joinGame(String gameName) throws RemoteException;

	public void addCash(int cash);

	public int getCash();
	public int getAmountOfPlanets();
	public Game getGamePlaying();

	public void setGamePlaying(Game gamePlaying);

	public int getOwnerId();

	public boolean isPlayerReady();

	public void setPlayerReady(boolean playerReady);

	public String getUsername();

	public void buyBattlestar();

	public void buyFighter();

	public void sendShip(Spaceship ship, Planet destination);

	public String toString();

	public List<Spaceship> getStock();

	public void setStock(List<Spaceship> stock);
}
