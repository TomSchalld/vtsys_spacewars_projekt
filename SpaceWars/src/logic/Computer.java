package logic;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Computer extends Player {

	public Computer(Game gamePlaying) throws MalformedURLException, RemoteException, NotBoundException {
		super("Computer",gamePlaying);
	}


}
