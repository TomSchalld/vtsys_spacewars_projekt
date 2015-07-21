package logic;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import clientServer.Client;

public abstract class Spaceship extends UnicastRemoteObject implements SpaceshipIf, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final int ownerId;
	protected final Client owner;
	protected PlanetIf orbiting;
	static int shipID = 0;
	private int shipNumber;

	public Spaceship(Client owner) throws RemoteException {
		this.ownerId = owner.getOwnerId();
		this.owner = owner;
		shipNumber = shipID++;
	}

	@Override
	public int getShipID() throws RemoteException {
		return this.shipNumber;
	}

	@Override
	public int getOwnerId() throws RemoteException {
		return ownerId;
	}

	@Override
	public PlanetIf getOrbiting() throws RemoteException {
		return orbiting;
	}

	@Override
	public void setOrbiting(PlanetIf orbiting) throws RemoteException {
		this.orbiting = orbiting;
	}

	@Override
	public Client getOwner() throws RemoteException {
		return owner;
	}

	@Override
	public abstract int attack() throws RemoteException;

	@Override
	public String toString() {
		String s = "";
		if (this instanceof Fighter) {
			s += "Fighter von: ";
		} else {
			s += "Battlestar von: ";
		}
		try {
			s += this.getOwner().getUsername() + " mit iD: " + this.getShipID() + " im Orbit von: ";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (this.getOrbiting() != null) {
				try {
					s += this.getOrbiting().getName();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				s += "Ship ist in Stock";
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public boolean equals(Object other) {

		try {
			if (this.getOwner().equals(((SpaceshipIf) other).getOwner())) {
				if (this.getShipID() == ((SpaceshipIf) other).getShipID()) {
					if (this instanceof Fighter) {
						if (other instanceof Fighter) {
							return true;
						}
					} else if (this instanceof Battlestar) {
						if (other instanceof Battlestar) {
							return true;
						}
					}

				}

			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public abstract void increaseRank() throws RemoteException;

}
