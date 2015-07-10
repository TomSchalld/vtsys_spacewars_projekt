package logic;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import clientServer.Client;

public class Computer extends UnicastRemoteObject implements Client {

	public Computer(Game gamePlaying) throws MalformedURLException, RemoteException, NotBoundException {
		super();
	}

	@Override
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinGame(String gameName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCash(int cash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCash() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAmountOfPlanets() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Game getGamePlaying() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGamePlaying(Game gamePlaying) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOwnerId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPlayerReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPlayerReady(boolean playerReady) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buyBattlestar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buyFighter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendShip(Spaceship ship, Planet destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Spaceship> getStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStock(List<Spaceship> stock) {
		// TODO Auto-generated method stub
		
	}


}
