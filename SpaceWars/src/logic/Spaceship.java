package logic;

public abstract class Spaceship {
	protected final int ownerId;
	protected int price;
	public Spaceship(int ownerId){
		this.ownerId=ownerId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public int getPrice() {
		return price;
	}
	
	
}
