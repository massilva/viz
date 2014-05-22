package org.viz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.visminer.main.VisMiner;
import org.visminer.model.Committer;
import org.visminer.model.Repository;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> props = new HashMap<String, String>();
		props.put(PersistenceUnitProperties.JDBC_DRIVER, "com.mysql.jdbc.Driver");
		props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost/visminer");
		props.put(PersistenceUnitProperties.JDBC_USER, "root");
		props.put(PersistenceUnitProperties.JDBC_PASSWORD, "123"); 
		//props.put(PersistenceUnitProperties.DDL_GENERATION, "create-tables");
		
		Map<Integer, String> api_cfg = new HashMap<Integer, String>();
		api_cfg.put(VisMiner.LOCAL_REPOSITORY_PATH, "/home/massilva/workspace/Visminer/.git");
		api_cfg.put(VisMiner.LOCAL_REPOSITORY_NAME, "Visminer");
		api_cfg.put(VisMiner.LOCAL_REPOSITORY_OWNER, "visminer");
		
		try {
			VisMiner visminer = new VisMiner(props, api_cfg);
			PrintWriter writer = response.getWriter();
			for(Committer committer : visminer.getCommitters()){
				writer.println(committer.getEmail());
			}
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
