package logic;

public class Fighter extends Spaceship {
	static final int price = 200;
	public Fighter(Player owner) {
		super(owner);
		
	}
	public static int getPrice() {
		return price;
	}
	@Override
	public int attack() {
		return (int)Math.random()*20+1;
	}

}
