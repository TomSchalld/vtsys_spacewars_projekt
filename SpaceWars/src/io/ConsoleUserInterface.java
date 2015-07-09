package io;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logic.GameIf;
import logic.Human;
import logic.Player;

public class ConsoleUserInterface {

	public void run() throws MalformedURLException, RemoteException, NotBoundException, InterruptedException {
		int eingabe;
		Player player = new Human(IO.readString("Bitte Usernamen eingeben"), null);
		Human human = (Human) player;
		System.out.println("Hallo Commander " + player.getUsername() + "\twas willst du tun?");
		System.out.println("(0) Neues Spiel erstellen\n(1) Spiel beitreten");
		eingabe = IO.readInt();
		if (eingabe == 0) {
			human.openGame("testgame", 0, 3);
		} else {
			human.joinGame("testgame");
		}
		while (!player.getGamePlaying().hasEnoughPlayer()) {
			System.out.println("waiting for other player.....");
			Thread.sleep(1000);
		}
		while (!player.getGamePlaying().isGameFinished()) {
			while (!player.isPlayerReady()) {

				System.out.println("Commander: " + player.toString()
						+ "\n(0)Buy Battlestar\n(1)Buy Fighter\n(2)send Ships to Planet\n(3)I'am ready!");
				switch (IO.readInt()) {
				case 0: {
					player.buyBattlestar();
					break;
				}
				case 1: {
					player.buyFighter();
					break;
				}
				case 2: {
					player.sendShip(player.getStock().get(0),
							player.getGamePlaying().getUniverse().getPlanets().get("Tatooine"));
					break;
				}
				default: {
					player.setPlayerReady(true);
					break;
				}
				}

			}
			System.out.println(player.getGamePlaying().endRound());
		}
		System.out.println(player.getGamePlaying().getEndreport());

	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, InterruptedException {
			ConsoleUserInterface ui = new ConsoleUserInterface();
			ui.run();
	}

}
