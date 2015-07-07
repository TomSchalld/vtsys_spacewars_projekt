package logik;

public abstract class Spaceship {
	protected final int ownerId;
	public Spaceship(int ownerId){
		this.ownerId=ownerId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	
}
