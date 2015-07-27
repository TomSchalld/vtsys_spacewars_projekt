package clientServer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.Game;
import logic.PlayerPlayerVsPC;
import logic.PlayerVsPC;
import logic.PlayerVsPlayer;
import logic.Report;

public class GameServer extends UnicastRemoteObject implements Server, Serializable {
	private Map<String, Game> runningGames;
	private Map<String, Game> lobby;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameServer() throws RemoteException {
		// TODO Auto-generated constructor stub
		this.runningGames = new HashMap<String, Game>();
		this.lobby = new HashMap<String, Game>();
	}

	/* (non-Javadoc)
	 * @see clientServer.Server#openGame(logic.Game)
	 */
	@Override
	public void openGame(Game newGame) throws Exception, RemoteException {
		// System.out.println("try to open new game");
		String gameName = newGame.getGameName();
		if (this.runningGames.containsKey(gameName) || this.lobby.containsKey(gameName)) {
			System.out.println("Game already existing!");
			throw new Exception("Game Could not be Opened Exception");
		}
		if (newGame.hasEnoughPlayer()) {
			runningGames.put(gameName, newGame);
			System.out.println("added new game to running games");
		} else {
			System.out.println("put game into lobby....");
			lobby.put(gameName, newGame);
			System.out.println("..done.");
		}
	}

	/* (non-Javadoc)
	 * @see clientServer.Server#openGameOnServer(java.lang.String, int, int, clientServer.Client)
	 */
	@Override
	public Game openGameOnServer(String gameName, int variation, int universeSize, Client player)
			throws Exception, RemoteException {
		System.out.println("try to open new game");
		Game newGame = null;
		if (variation == 0) {
			newGame = new PlayerVsPlayer(gameName, universeSize, variation);
			newGame.addPlayer(player);
			System.out.println("erstelle neuese pvp game");
			try {
				this.openGame(newGame);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (variation == 1) {
			newGame = new PlayerVsPC(gameName, universeSize);
			newGame.addPlayer(player);
			System.out.println("erstelle neuese pvPC game");
			try {
				this.openGame(newGame);
				System.out.println("send game to server");
			} catch (Exception e) {
				System.out.println("exception e");
				e.printStackTrace();
			}

		} else if (variation == 2) {
			newGame = new PlayerPlayerVsPC(gameName, universeSize);
			newGame.addPlayer(player);
			System.out.println("erstelle neuese P,PvPC game");

			try {
				this.openGame(newGame);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return newGame;
	}

	/* (non-Javadoc)
	 * @see clientServer.Server#joinGame(java.lang.String)
	 */
	@Override
	public void joinGame(String gameName) throws RemoteException {
		if (this.lobby.containsKey(gameName)) {
			this.bringGameToRun(this.lobby.get(gameName));
		} else {
			System.out.println("There is no such game");
		}
	}

	/**<p>Takes the the game removes it from lobby and added it to the runnig games</p>
	 * @param gameInLobby
	 * @throws RemoteException
	 */
	public void bringGameToRun(Game gameInLobby) throws RemoteException {
		this.runningGames.put(gameInLobby.getGameName(), this.lobby.remove(gameInLobby.getGameName()));
		System.out.println("game is now running");
	}

	/* (non-Javadoc)
	 * @see clientServer.Server#closeGame(java.lang.String)
	 */
	@Override
	public boolean closeGame(String gameName) throws RemoteException {
		// TODO Auto-generated method stub

		if (runningGames.containsKey(gameName)) {
			Game gameToBeClosed = this.runningGames.get(gameName);
			if (gameToBeClosed.killAllReferences()) {
				runningGames.remove(gameName);
				System.out.println("Game is closed");
				return true;

			}

		}
		return false;
	}

	/* (non-Javadoc)
	 * @see clientServer.Server#getGameByName(java.lang.String)
	 */
	@Override
	public Game getGameByName(String gameName) throws RemoteException {
		if (this.runningGames.containsKey(gameName)) {
			return this.runningGames.get(gameName);
		} else if (this.lobby.containsKey(gameName)) {
			return this.lobby.get(gameName);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see clientServer.Server#gamesInLobby()
	 */
	@Override
	public Map<String, Game> gamesInLobby() throws RemoteException {
		return this.lobby;
	}

	/**<p>Starts an instance of GameServer and binds it to NameService</p>
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		} catch (RemoteException rex) {
			System.err.println("A wild RemoteException appeard: " + rex);
		}
		if (registry == null) {
			System.err.println("Cannot find registry");
			System.exit(0);
		}

		try {
			registry.rebind("GameServer", new GameServer());
			System.out.println("GameServer registered as 'GameServer' ...");
		} catch (java.rmi.ConnectException cex) {
			System.err.println("ConnectException while accessing registry (port = " + Registry.REGISTRY_PORT + ")");
			System.err.println("Run 'rmiregistry " + Registry.REGISTRY_PORT + "'");
			System.exit(0);
		} catch (Exception ex) {
			System.err.println("Exception during server registration (registry port = " + Registry.REGISTRY_PORT + ")");
			ex.printStackTrace();
			System.exit(0);
		}
	}

}
