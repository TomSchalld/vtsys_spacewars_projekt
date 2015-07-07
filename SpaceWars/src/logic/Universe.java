package logic;

import java.util.HashMap;
import java.util.Map;

public class Universe {
	private Map<String, Planet> planets;
	private int universeSize;
	private int planetId;

	public Universe(int universeSize) {
		this.planets = new HashMap<String, Planet>();
		this.universeSize = universeSize;
		this.planetId = 0;
		this.createWorld();
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
		this.planets.put("Tatooine", new Planet("Tatooine", planetId++));
		this.planets.put("Endor", new Planet("Endor", planetId++));
		this.planets.put("Coruscant", new Planet("Coruscant", planetId++));
	}

	private void createFivePlanets() {
		this.createThreePlanets();
		this.planets.put("Erde", new Planet("Erde", planetId++));
		this.planets.put("Caprica", new Planet("Caprica", planetId++));

	}

	private void createSevenPlanets() {
		this.createFivePlanets();
		this.planets.put("Gemini", new Planet("Gemini", planetId++));
		this.planets.put("Atlantis", new Planet("Atlantis", planetId++));
	}
}
