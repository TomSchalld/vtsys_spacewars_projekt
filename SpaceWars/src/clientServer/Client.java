package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.json.JSONObject;

import logic.Game;
import logic.PlanetIf;
import logic.SpaceshipIf;

public interface Client extends Remote {
	/**Delegates to the Server where a new game is opened
	 * @param gameName
	 * @param variation
	 * @param universeSize
	 * @throws RemoteException
	 */
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException;

	/**
	 * @return the roundReport of the actual game
	 * @throws RemoteException
	 */
	public JSONObject getRoundReport() throws RemoteException;

	public void setRoundReport(JSONObject roundReport) throws RemoteException;

	/**Lets a user an existing game by gameName
	 * @param gameName
	 * @throws RemoteException
	 */
	public void joinGame(String gameName) throws RemoteException;

	/**Adds some cash to the Clients account
	 * @param cash
	 * @throws RemoteException
	 */
	public void addCash(int cash) throws RemoteException;

	public int getCash() throws RemoteException;

	/**Gets the actual amount of Planets owned by the Client
	 * @return
	 * @throws RemoteException
	 */
	public int getAmountOfPlanets() throws RemoteException;

	/**
	 * @return the Game the Client is playing
	 * @throws RemoteException
	 */
	public Game getGamePlaying() throws RemoteException;

	public void setGamePlaying(Game gamePlaying) throws RemoteException;

	/**
	 * @return an unique owner id 
	 * @throws RemoteException
	 */
	public int getOwnerId() throws RemoteException;

	/**
	 * @return true if the player is ready
	 * @throws RemoteException
	 */
	public boolean isPlayerReady() throws RemoteException;

	public void setPlayerReady(boolean playerReady) throws RemoteException;

	/**Checks if this is equal to other
	 * @param other
	 * @return true if equal
	 * @throws RemoteException
	 */
	public boolean equals(Client other) throws RemoteException;

	/**get players Username
	 * @return
	 * @throws RemoteException
	 */
	public String getUsername() throws RemoteException;

	/**Buys new ship
	 * @throws RemoteException
	 */
	public void buyBattlestar() throws RemoteException;

	/**Buys new ship
	 * @throws RemoteException
	 */
	public void buyFighter() throws RemoteException;

	/**<p>Sends a ship to its destination</p><p>If destination is <strong>null</strong> ship will return to stock</p>
	 * @param ship
	 * @param destination
	 * @throws RemoteException
	 */
	public void sendShip(SpaceshipIf ship, PlanetIf destination) throws RemoteException;

	/**<p>Sends all ships back to stock</p>
	 * @param origin
	 * @throws RemoteException
	 */
	public void sendAllShipsToStock(PlanetIf origin) throws RemoteException;

	/**Gets all ships in stock
	 * @return
	 * @throws RemoteException
	 */
	public List<SpaceshipIf> getStock() throws RemoteException;

	public void setStock(List<SpaceshipIf> stock) throws RemoteException;

	public void setCash(int cash) throws RemoteException;

	public void setAmountOfPlanets(int planets) throws RemoteException;

	/**
	 * @return true if instance is of type KI
	 * @throws RemoteException
	 */
	public boolean isKI() throws RemoteException;

	/**Closes the actual game, delegates close game in server
	 * @throws RemoteException
	 */
	public void closeGame() throws RemoteException;

	public String getTeam() throws RemoteException;

	public void setTeam(String team) throws RemoteException;
}
