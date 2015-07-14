package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Universe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Planet> planets;
	private int universeSize;
	private int planetId;

	public Universe(int universeSize) {
		this.planets = new HashMap<String, Planet>();
		this.universeSize = universeSize;
		this.planetId = 0;
		this.createWorld();
	}

	public Map<String, Planet> getPlanets() {
		return planets;
	}

	private void createWorld() {
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

	private void createThreePlanets() {
		this.planets.put("tatooine", new Planet("Tatooine", planetId++));
		this.planets.put("endor", new Planet("Endor", planetId++));
		this.planets.put("coruscant", new Planet("Coruscant", planetId++));
	}

	private void createFivePlanets() {
		this.createThreePlanets();
		this.planets.put("erde", new Planet("Erde", planetId++));
		this.planets.put("caprica", new Planet("Caprica", planetId++));

	}

	private void createSevenPlanets() {
		this.createFivePlanets();
		this.planets.put("gemini", new Planet("Gemini", planetId++));
		this.planets.put("atlantis", new Planet("Atlantis", planetId++));
	}
	public Planet getRandomPlanet(){
		return getPlanetByName(getRandomPlanetName());
	}
	public Planet getPlanetByName(String name){
		return this.getPlanets().get(name);
	}
	public String getRandomPlanetName() {

		Map<String, Planet> planets = this.getPlanets();

		Random random = new Random();
		List<String> keys = new ArrayList<String>(planets.keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		return randomKey;

	}
}
