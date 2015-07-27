package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.json.JSONObject;

import logic.Game;
import logic.PlanetIf;
import logic.SpaceshipIf;

public interface Client extends Remote {
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException;

	public JSONObject getRoundReport() throws RemoteException;

	public void setRoundReport(JSONObject roundReport) throws RemoteException;

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

	public void sendShip(SpaceshipIf ship, PlanetIf destination) throws RemoteException;

	public void sendAllShipsToStock(PlanetIf origin) throws RemoteException;

	public List<SpaceshipIf> getStock() throws RemoteException;

	public void setStock(List<SpaceshipIf> stock) throws RemoteException;

	public void setCash(int cash) throws RemoteException;

	public void setAmountOfPlanets(int planets) throws RemoteException;

	public boolean isKI() throws RemoteException;

	public void closeGame() throws RemoteException;

	public String getTeam() throws RemoteException;

	public void setTeam(String team) throws RemoteException;
}
