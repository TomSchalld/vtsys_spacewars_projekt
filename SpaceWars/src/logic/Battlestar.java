package logic;

public class Battlestar extends Spaceship {
	static final int price = 2000;
	public Battlestar(Player owner) {
		super(owner);
	}
	public static int getPrice() {
		return price;
	}
	@Override
	public int attack() {
		return (int)Math.random()*200+10;
	}

}
