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

	/**
	 * @param owner
	 * @throws RemoteException
	 */
	public Spaceship(Client owner) throws RemoteException {
		this.ownerId = owner.getOwnerId();
		this.owner = owner;
		shipNumber = shipID++;
	}

	/* (non-Javadoc)
	 * @see logic.SpaceshipIf#getShipID()
	 */
	@Override
	public int getShipID() throws RemoteException {
		return this.shipNumber;
	}

	/* (non-Javadoc)
	 * @see logic.SpaceshipIf#getOwnerId()
	 */
	@Override
	public int getOwnerId() throws RemoteException {
		return ownerId;
	}

	/* (non-Javadoc)
	 * @see logic.SpaceshipIf#getOrbiting()
	 */
	@Override
	public PlanetIf getOrbiting() throws RemoteException {
		return orbiting;
	}

	/* (non-Javadoc)
	 * @see logic.SpaceshipIf#setOrbiting(logic.PlanetIf)
	 */
	@Override
	public void setOrbiting(PlanetIf orbiting) throws RemoteException {
		this.orbiting = orbiting;
	}

	/* (non-Javadoc)
	 * @see logic.SpaceshipIf#getOwner()
	 */
	@Override
	public Client getOwner() throws RemoteException {
		return owner;
	}

	/* (non-Javadoc)
	 * @see logic.SpaceshipIf#attack()
	 */
	@Override
	public abstract int attack() throws RemoteException;
	
	/* (non-Javadoc)
	 * @see logic.SpaceshipIf#shipInfo()
	 */
	@Override
	public abstract String shipInfo() throws RemoteException;
	
	
	/* (non-Javadoc)
	 * @see java.rmi.server.RemoteObject#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		try {
			if (this.isFighter()) {
				s += "Fighter von: ";
			} else {
				s += "Battlestar von: ";
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

	/* (non-Javadoc)
	 * @see java.rmi.server.RemoteObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {

		try {
			if (this.getOwner().equals(((SpaceshipIf) other).getOwner())) {
				if (this.getShipID() == ((SpaceshipIf) other).getShipID()) {
					if (this.isFighter()) {
						if (((SpaceshipIf)other).isFighter()) {
							return true;
						}
					} else if (!this.isFighter()) {
						if (!((SpaceshipIf)other).isFighter()) {
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

	/* (non-Javadoc)
	 * @see logic.SpaceshipIf#increaseRank()
	 */
	@Override
	public abstract void increaseRank() throws RemoteException;

}
