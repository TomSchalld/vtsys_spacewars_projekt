package logic;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Universe extends UnicastRemoteObject implements UniverseIf, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, PlanetIf> planets;
	private int universeSize;
	private int planetId;

	public Universe(int universeSize) throws RemoteException {
		this.planets = new HashMap<String, PlanetIf>();
		this.universeSize = universeSize;
		this.planetId = 0;
		this.createWorld();
	}

	@Override
	public Map<String, PlanetIf> getPlanets() throws RemoteException {
		return planets;
	}

	private void createWorld() throws RemoteException {
		switch (this.universeSize) {
		case 1: {
			this.createThreePlanets();
			break;
		}
		case 2: {
			this.createFivePlanets();
			break;
		}
		case 3: {
			this.createSevenPlanets();
			break;
		}
		default: {
			this.createSevenPlanets();
			break;
		}
		}

	}

	private void createThreePlanets() throws RemoteException {
		this.planets.put("tatooine", new Planet("tatooine", planetId++));
		this.planets.put("endor", new Planet("endor", planetId++));
		this.planets.put("coruscant", new Planet("coruscant", planetId++));
		System.out.println("Tatooine endor coruscant erstellt");

	}

	private void createFivePlanets() throws RemoteException {
		this.createThreePlanets();
		this.planets.put("erde", new Planet("erde", planetId++));
		this.planets.put("caprica", new Planet("caprica", planetId++));
		System.out.println("erde und caprica erstellt");

	}

	private void createSevenPlanets() throws RemoteException {
		this.createFivePlanets();
		this.planets.put("gemini", new Planet("gemini", planetId++));
		this.planets.put("atlantis", new Planet("atlantis", planetId++));
		System.out.println("gemini und atlantis erstellt");

	}

	@Override
	public PlanetIf getRandomPlanet() throws RemoteException {
		return getPlanetByName(getRandomPlanetName());
	}

	@Override
	public PlanetIf getPlanetByName(String name) throws RemoteException {
		return this.getPlanets().get(name);
	}

	@Override
	public String getRandomPlanetName() throws RemoteException {

		Map<String, PlanetIf> planets = this.getPlanets();

		Random random = new Random();
		List<String> keys = new ArrayList<String>(planets.keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		return randomKey;

	}
	@Override
	public void killUniverse() throws RemoteException{
		for(PlanetIf p:this.getPlanets().values()){
			p.delShips();
		}
		this.getPlanets().clear();
	}
	@Override
	public int getSize() throws RemoteException {

		return this.universeSize;
	}
}
