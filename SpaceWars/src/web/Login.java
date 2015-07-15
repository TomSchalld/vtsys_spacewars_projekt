package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.*;
import logic.Human;

/**
 * Servlet implementation class hello
 */
@WebServlet(asyncSupported = true)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		HttpSession session = request.getSession();
		String uID = session.getId();
		String uname = request.getParameter("username");
		PrintWriter out = response.getWriter();
		if (request.getParameter("createGame").equals("true")) {
			// TODO ....
			String gameName = request.getParameter("gameName");
			int variation = Integer.parseInt(request.getParameter("gameMode").trim()); // 0=pvp
																						// 1=pvpc
																						// 2=ppvpc
			int universeSize = Integer.parseInt(request.getParameter("universeSize").trim()); // 0=3planets
			// 1=5planets
			// 2=7planets
			if (variation == 0) {
				
				if(universeSize==1){
					out.write("gameThree.html?gameName=" + gameName + "&universeSize=" + universeSize + "&");

				}else if(universeSize ==2){
					out.write("gameFive.html?gameName=" + gameName + "&universeSize=" + universeSize + "&");
				}else{
					out.write("gameSeven.html?gameName=" + gameName + "&universeSize=" + universeSize + "&");
				}
				UserOnline.getUserById(uID).openGame(gameName, variation, universeSize);
				System.out
				.println("createGame: " + gameName + " mode: " + variation + " universeSize: " + universeSize);
			} else if (variation == 1) {
				UserOnline.getUserById(uID).openGame(gameName, variation, universeSize);
				System.out
						.println("createGame: " + gameName + " mode: " + variation + " universeSize: " + universeSize);
			} else if (variation == 2) {
				out.write("?variation=2");
			} else {
				out.write("?gameName=" + gameName + "&universeSize=" + universeSize + "&");
			}

		}
		if (request.getParameter("logout").equals("true")) {
			UserOnline.logout(session.getId());
			System.out.println(uname + " successfully logged out");
		}
		if(request.getParameter("joinGame").equals("true")){
			out.write("?");
		}
		System.out.println(session.getId());

		out.write("username=" + uname);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
		try {
			if (!UserOnline.isUserExisting(sessionId)) {
				UserOnline.addUser(sessionId, new Human(uname, "192.168.178.23"));
			}
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(uname + " with iD: " + sessionId + " has logged in!");
		// TODO log in user here!
		out.write("?username=" + uname);
		response.getWriter().close();

	}
}
