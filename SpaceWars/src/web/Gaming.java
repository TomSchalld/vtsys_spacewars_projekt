package web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import clientServer.Client;
import logic.BattleReport;
import logic.Battlestar;
import logic.EndReport;
import logic.Fighter;
import logic.Game;
import logic.PlanetIf;
import logic.Report;
import logic.RoundReport;
import logic.SpaceshipIf;
import logic.UniverseIf;

/**
 * Servlet implementation class Gaming
 */
@WebServlet(asyncSupported = true)
public class Gaming extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Gaming() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		if (!request.getParameter("closeGame").equals("true")) {
			JSONObject roundObject = requestParamsToJSON(request);
			System.out.println("input::::::");
			System.out.println(roundObject.toString());
			System.out.println("input::::::\n\n\n\n\n");
			try {
				doRound(roundObject, request.getRequestedSessionId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("output::::::");
			System.out.println(roundObject.toString());
			System.out.println("output::::::\n\n\n\n\n");

			response.getWriter().write(roundObject.toString());
			response.getWriter().close();
		} else {
			System.out.println("User is closing game...");
			Client user = UserOnline.getUserById(request.getRequestedSessionId());
			if (user.getGamePlaying() != null) {
				user.closeGame();
			}
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write("?username=" + user.getUsername());
			response.getWriter().close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO check if sessionID is valid
		doGet(request, response);
	}

	private JSONObject requestParamsToJSON(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		JSONObject roundObject = new JSONObject();
		JSONObject planetObject;
		String planets[] = { "atlantis", "caprica", "coruscant", "endor", "erde", "gemini", "tatooine" };

		for (String s : planets) {
			planetObject = new JSONObject();
			// System.out.println( params.get(s+"[newFighter]")[0]);
			planetObject.put("newFighter", params.get(s + "[newFighter]")[0]);
			planetObject.put("newBattlestar", params.get(s + "[newBattlestar]")[0]);
			planetObject.put("sum", params.get(s + "[sum]")[0]);
			roundObject.put(s, planetObject);
		}
		roundObject.put("fightersInStock", Integer.parseInt(params.get("fightersInStock")[0].trim()));
		roundObject.put("battlestarsInStock", Integer.parseInt(params.get("battlestarsInStock")[0].trim()));
		roundObject.put("fightersToBuy", Integer.parseInt(params.get("fightersToBuy")[0].trim()));
		roundObject.put("battlestarsToBuy", Integer.parseInt(params.get("battlestarsToBuy")[0].trim()));
		roundObject.put("playersCash", Integer.parseInt(params.get("playersCash")[0].trim()));

		return roundObject;

	}

	private void doRound(JSONObject roundObject, String sID)
			throws RemoteException, JSONException, InterruptedException {
		List<SpaceshipIf> tmpShips = new LinkedList<SpaceshipIf>();
		Client user = UserOnline.getUserById(sID);
		Game actual = user.getGamePlaying();
		int actualRound = actual.getRound();
		UniverseIf universe = user.getGamePlaying().getUniverse();
		int battlestarsInStockAfterRound = 0;
		int fighterInStockAfterRound = 0;
		String planets[] = { "atlantis", "caprica", "coruscant", "endor", "erde", "gemini", "tatooine" };
		JSONObject planet;
		user.setCash(roundObject.getInt("playersCash"));
		System.out.println("user: " + user.getUsername() + " kauft fighter: " + roundObject.getInt("fightersToBuy"));
		for (int i = 0; i < roundObject.getInt("fightersToBuy"); i++) {
			user.buyFighter();
		}
		System.out.println(
				"user: " + user.getUsername() + " kauft battlestar: " + roundObject.getInt("battlestarsToBuy"));

		for (int i = 0; i < roundObject.getInt("battlestarsToBuy"); i++) {
			user.buyBattlestar();
		}
		System.out.println("-------------------------------------------------------------------------doRound");
		for (String planetName : planets) {
			planet = roundObject.getJSONObject(planetName);
			System.out.println(planetName);
			System.out.println("anzahl battlestar soll " + planet.getInt("newBattlestar"));
			for (int i = 0; i < planet.getInt("newBattlestar"); i++) {
				for (SpaceshipIf ship : user.getStock()) {
					if (!ship.isFighter()) {
						if (!tmpShips.contains(ship)) {
							tmpShips.add(ship);
							user.sendShip(ship, universe.getPlanetByName(planetName));
							break;
						}
					}
				}
			}
			System.out.println("anzahl fighter soll " + planet.getInt("newFighter"));
			for (int i = 0; i < planet.getInt("newFighter"); i++) {
				for (SpaceshipIf ship : user.getStock()) {
					if (ship.isFighter()) {
						if (!tmpShips.contains(ship)) {
							tmpShips.add(ship);
							user.sendShip(ship, universe.getPlanetByName(planetName));
							break;
						}

					}
				}
			}
		}
		System.out.println("----------------------------------------------------------------------------doRoundEnd");
		user.getStock().removeAll(tmpShips);
		// ships sendet
		if (!user.getStock().isEmpty()) {
			for (SpaceshipIf ships : user.getStock()) {
				if (ships.isFighter()) {
					fighterInStockAfterRound++;
				} else {
					battlestarsInStockAfterRound++;
				}
			}
		}
		System.out.println("fighters in stock after round: -->" + fighterInStockAfterRound);
		System.out.println("battlestars in stock after round: -->" + battlestarsInStockAfterRound);
		roundObject.put("fightersInStock", fighterInStockAfterRound);
		roundObject.put("battlestarsInStock", battlestarsInStockAfterRound);
		roundObject.put("fightersToBuy", 0);
		roundObject.put("battlestarsToBuy", 0);
		roundObject.put("endReport", "null");
		roundObject.put("closeGame", "false");
		user.setPlayerReady(true);
		if (!user.getGamePlaying().isGameFinished()) {
			int count = 0;
			while (actualRound == actual.getRound()) {
				//racecondition
				System.out.println("Schleifendurchläufe: " + count++ + "username: " + user.getUsername()+" actualRound: "+actualRound+" actual.GetRound: "+actual.getRound());
				Thread.sleep(1000);
				if (user.getGamePlaying().isGameFinished()) {
					roundObject.put("endReport", ((EndReport) user.getGamePlaying().getEndreport()).endReportToJSON());
				}
				
			}

		} else {
			roundObject.put("endReport", ((EndReport) user.getGamePlaying().getEndreport()).endReportToJSON());
			System.out.println("do highscore...");
			this.generateHighscore(user.getGamePlaying().getEndreport());
		}
		roundObject.put("playersCash", user.getCash());
		roundObject.put("roundReport", user.getRoundReport());
		System.out.println(user.getUsername() + " has got: " + user.getCash() + " Credits");
		System.out.println("------------------------------------put JSON-----------------------");

		int plsum;
		for (String planetName : planets)

		{
			plsum = 0;
			PlanetIf pl = universe.getPlanetByName(planetName);
			if (pl != null) {
				if (pl.getPlanetOwner() != null) {
					if (pl.getPlanetOwner().equals(user)) {
						planet = roundObject.getJSONObject(planetName);
						planet.put("newFighter", pl.getFighterInOrbit(user));
						planet.put("newBattlestar", pl.getBattlestarsInOrbit(user));
						plsum = pl.getFighterInOrbit(user) + pl.getBattlestarsInOrbit(user);
						planet.put("sum", plsum);
						System.out.println(
								pl.getName() + " zu JSON nach Runde user:" + user.getUsername() + " anzahlFighter: "
										+ pl.getFighterInOrbit(user) + " anzahl batlestar: " + pl.getBattlestarsInOrbit(user));
					} else {
						planet = roundObject.getJSONObject(planetName);
						planet.put("newFighter", 0);
						planet.put("newBattlestar", 0);
						planet.put("sum", 0);
					}
				}

			}

		}
		System.out.println("------------------------------------put JSON Ende-----------------------");

	}

	private void generateHighscore(Report endreport) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		EndReport report = (EndReport) endreport;
		JSONObject score = new JSONObject();
		int sumOfDefeatedShipsInGame = 0;
		for (Report rr : report.roundReports) {
			for (BattleReport br : ((RoundReport) rr).getReports().values()) {
				sumOfDefeatedShipsInGame += br.getDefeatedShips().size();
			}
		}
		score.put("roundCount", report.roundReports.size());
		score.put("winner", report.getWinner());
		score.put("gameName", report.gameName);
		score.put("datum", dateFormat.format(cal.getTime()));
		score.put("defeatedShipCount", sumOfDefeatedShipsInGame);
		Highscore.addHighscore(score);
		
	}

}
