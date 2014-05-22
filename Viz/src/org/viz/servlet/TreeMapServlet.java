package org.viz.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class TreeMap
 */
@WebServlet("/TreeMap")
public class TreeMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public JSONObject treemap;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TreeMapServlet() {
		super();
		treemap = new JSONObject();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		JSONArray aux = new JSONArray();
		List<Comparable> dados = new ArrayList<Comparable>();
		dados.add("Location");
		dados.add("Parente");
		dados.add("Market trade volume (size)");
		dados.add("Market increase/decrease (color)");
		aux.put(dados);
		dados.clear();
		
		dados.add("Global");
		dados.add(null);
		dados.add(0);
		dados.add(0);
		aux.put(dados);
		dados.clear();
		
		dados.add("America");
		dados.add("Global");
		dados.add(0);
		dados.add(0);
		aux.put(dados);
		dados.clear();
		
		dados.add("Europe");
		dados.add("Global");
		dados.add(0);
		dados.add(0);
		aux.put(dados);
		dados.clear();
		
		dados.add("Asia");
		dados.add("Global");
		dados.add(0);
		dados.add(0);
		aux.put(dados);
		dados.clear();
		
		dados.add("Australia");
		dados.add("Global");
		dados.add(0);
		dados.add(0);
		aux.put(dados);
		dados.clear();
		
		dados.add("Africa");
		dados.add("Global");
		dados.add(0);
		dados.add(0);
		aux.put(dados);
		dados.clear();
		
		dados.add("Brazil");
		dados.add("America");
		dados.add(11);
		dados.add(10);
		aux.put(dados);
		dados.clear();
		
		dados.add("USA");
		dados.add("America");
		dados.add(52);
		dados.add(31);
		aux.put(dados);
		dados.clear();
		
		dados.add("Mexico");
		dados.add("America");
		dados.add(24);
		dados.add(12);
		aux.put(dados);
		dados.clear();
		
		dados.add("Canada");
		dados.add("America");
		dados.add(16);
		dados.add(-23);
		aux.put(dados);
		dados.clear();
		
		dados.add("France");
		dados.add("Europe");
		dados.add(42);
		dados.add(-11);
		aux.put(dados);
		dados.clear();
		
		dados.add("Germany");
		dados.add("Europe");
		dados.add(31);
		dados.add(-2);
		aux.put(dados);
		dados.clear();
		
		dados.add("Sweden");
		dados.add("Europe");
		dados.add(22);
		dados.add(-13);
		aux.put(dados);
		dados.clear();
		
		dados.add("Italy");
		dados.add("Europe");
		dados.add(17);
		dados.add(4);
		aux.put(dados);
		dados.clear();
		
		dados.add("UK");
		dados.add("Europe");
		dados.add(21);
		dados.add(-5);
		aux.put(dados);
		dados.clear();
		
		dados.add("China");
		dados.add("Asia");
		dados.add(36);
		dados.add(4);
		aux.put(dados);
		dados.clear();
		
		dados.add("Japan");
		dados.add("Asia");
		dados.add(20);
		dados.add(-12);
		aux.put(dados);
		dados.clear();
		
		dados.add("India");
		dados.add("Asia");
		dados.add(40);
		dados.add(63);
		aux.put(dados);
		dados.clear();
		
		dados.add("Laos");
		dados.add("Asia");
		dados.add(4);
		dados.add(34);
		aux.put(dados);
		dados.clear();
		
		dados.add("Mongolia");
		dados.add("Asia");
		dados.add(1);
		dados.add(-5);
		aux.put(dados);
		dados.clear();
		
		dados.add("Israel");
		dados.add("Asia");
		dados.add(12);
		dados.add(24);
		aux.put(dados);
		dados.clear();
		
		dados.add("Iran");
		dados.add("Asia");
		dados.add(18);
		dados.add(13);
		aux.put(dados);
		dados.clear();
		
		dados.add("Pakistan");
		dados.add("Asia");
		dados.add(11);
		dados.add(-52);
		aux.put(dados);
		dados.clear();
		
		dados.add("Egypt");
		dados.add("Africa");
		dados.add(21);
		dados.add(0);
		aux.put(dados);
		dados.clear();
		
		dados.add("S. Africa");
		dados.add("Africa");
		dados.add(30);
		dados.add(43);
		aux.put(dados);
		dados.clear();
		
		dados.add("Sudan");
		dados.add("Africa");
		dados.add(12);
		dados.add(2);
		aux.put(dados);
		dados.clear();
		
		dados.add("Congo");
		dados.add("Africa");
		dados.add(10);
		dados.add(12);
		aux.put(dados);
		dados.clear();
		
		dados.add("Zaire");
		dados.add("Africa");
		dados.add(8);
		dados.add(10);
		aux.put(dados);
		dados.clear();
		
		request.getSession().setAttribute("dados", aux.toString());
		request.getRequestDispatcher("/treemap.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String treemap = request.getParameter("treeMap");
		System.out.println(treemap);
	}

}
