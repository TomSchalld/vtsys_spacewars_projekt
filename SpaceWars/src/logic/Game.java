package logic;


public abstract class Game {
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
	public Game(String gameName, int universeSize){
		this.gameName=gameName;
		this.gameId=gameCount;
		this.universe=new Universe(universeSize);
		this.round = 0;
		this.endReport = new EndReport();
		gameCount++;
	}
	public Universe getUniverse() {
		return universe;
	}
	public boolean hasEnoughPlayer(){
		return this.hasEnoughPlayer;
	}
	
	public boolean isGameFinished() {
		return gameFinished;
	}
	public Report getEndreport(){
		return this.endReport;
	}
	public String getGameName() {
		return gameName;
	}
	public boolean killAllReferences(){
		//TODO write something that terminates all references so GC can clean up
		return true;
	}
	public abstract void addPlayer(Player newPlayer);
	public abstract RoundReport endRound();
}
