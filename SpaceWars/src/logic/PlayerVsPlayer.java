package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import clientServer.Client;

public class PlayerVsPlayer extends UnicastRemoteObject implements Game {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final String gameName;
	protected String passwort;
	protected int gameId;
	protected boolean hasEnoughPlayer=false;
	protected boolean gameFinished = false;
	protected Client winner;
	protected Client[] players;
	protected Universe universe;
	protected int round;
	private static int gameCount=0;
	protected Report endReport;
	public PlayerVsPlayer(String gameName, int universeSize) throws RemoteException {
		this.gameName=gameName;
		this.gameId=gameCount;
		this.universe=new Universe(universeSize);
		this.round = 0;
		this.endReport = new EndReport();
		gameCount++;
		this.players = new Client[2];
		
	}

	@Override
	public void addPlayer(Client newPlayer) throws RemoteException{
		if(this.players[0]==null){
			this.players[0]=newPlayer;
		}else{
			this.players[1]=newPlayer;
			this.hasEnoughPlayer=true;
		}
		newPlayer.setGamePlaying(this);

	}
	public void addPlayers(Player one, Player two) throws RemoteException{
		one.setGamePlaying(this);
		two.setGamePlaying(this);
		this.addPlayer(one);
		this.addPlayer(two);
	}
	private boolean playersReady(){
		if(this.players[0].isPlayerReady()==true&&this.players[1].isPlayerReady()==true){
			return true;
		}
		return false;
	}
	private void setPlayersUnready(){
		this.players[0].setPlayerReady(false);
		this.players[1].setPlayerReady(false);				
	}
	@Override
	public RoundReport endRound() throws RemoteException {
		if(this.playersReady()){
			RoundReport report = new RoundReport();
			Map<String,Planet>planets = this.getUniverse().getPlanets();
			for(Planet p: planets.values()){
				if(p.isFightAfterRoundEnded()){
					report.addReport(p.fight());
				}
			}
			for(Planet p: planets.values()){
				if(!p.getShipsInOrbit().isEmpty()){
					p.getShipsInOrbit().get(0).getOwner().addCash(p.getGeneratedCredits());
				}
			}

			this.setPlayersUnready();
			this.round++;
			if(this.players[0].getAmountOfPlanets()==this.getUniverse().getPlanets().size()){
				this.gameFinished = true;
				this.winner = this.players[0];
			}else if(this.players[1].getAmountOfPlanets()==this.getUniverse().getPlanets().size()){
				this.gameFinished = true;
				this.winner = this.players[1];
			}
			this.endReport.addReport(report);
			return report;
		}
		return null;
	}

	@Override
	public Universe getUniverse() throws RemoteException {
		return this.universe;
	}

	@Override
	public boolean hasEnoughPlayer() throws RemoteException {
		return this.hasEnoughPlayer;
	}

	@Override
	public boolean isGameFinished() throws RemoteException {
		return this.gameFinished;
	}

	@Override
	public Report getEndreport() throws RemoteException {
		return this.endReport;
	}

	@Override
	public String getGameName() throws RemoteException {
		return this.gameName;
	}

	@Override
	public boolean killAllReferences() throws RemoteException {
		return false;
	}

}
