package logic;

import io.IO;

public class Demo {

	public static void main(String[] args) {
		Game firstTry = new PlayerVsPlayer("TestGame", 2);
		Player one = new Human("Thomas", firstTry);
		Player two = new Human("Fabian", firstTry);
		firstTry.addPlayer(one);
		firstTry.addPlayer(two);
		while (true) {

			while (!one.isPlayerReady()) {

				System.out.println("Player one: "+one.toString()+"\n (0)Buy Battlestar\n(1)Buy Fighter\n(2)send fighter to Planet");
				switch (IO.readInt()) {
				case 0: {
					one.buyBattlestar();
					break;
				}
				case 1: {
					one.buyFighter();
					break;
				}
				case 2: {
					one.sendShip(one.stock.get(0), firstTry.getUniverse().getPlanets().get("Tatooine"));
					break;
				}
				default: {
					one.setPlayerReady(true);
					break;
				}

				}
			}
			while (!two.isPlayerReady()) {
				
				System.out.println("Player two: "+two.toString()+"\n (0)Buy Battlestar\n(1)Buy Fighter\n(2)send fighter to Planet");
				switch (IO.readInt()) {
				case 0: {
					two.buyBattlestar();
					break;
				}
				case 1: {
					two.buyFighter();
					break;
				}
				case 2: {
					two.sendShip(two.stock.get(0), firstTry.getUniverse().getPlanets().get("Tatooine"));
					break;
				}
				default: {
					two.setPlayerReady(true);
					break;
				}
				
				}
				
			}
			BattleReport rep = firstTry.endRound().getBattleReport("Tatooine");
			System.out.println(rep.getListOfDefeats());
			System.out.println("Winner is: "+rep.getWinnersUsername());
			System.out.println("Looser is: "+rep.getLoosersUsername());
		}
	}

}
