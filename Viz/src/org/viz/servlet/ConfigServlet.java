package org.viz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.viz.model.Configuration;

/**
 * Servlet implementation class ConfigServlet
 */
@WebServlet("/ConfigServlet")
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String workingDir = System.getProperty("user.dir");
//		String localworkingDir);
		request.getRequestDispatcher("/config.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//values passed in the form
		String rrgit = (String)request.getParameter("remote_repository_git"), rrlogin = (String)request.getParameter("remote_repository_login"), rrpass = (String)request.getParameter("remote_repository_password");
		String lrp = (String)request.getParameter("local_repository_path"), lrn = (String)request.getParameter("local_repository_name"), lro = (String)request.getParameter("local_repository_owner");
		String user = (String)request.getParameter("jdbc_user"), pass = (String)request.getParameter("jdbc_password");
		//instancing the Configuration class
		Configuration cfg = new Configuration(rrgit, rrlogin,rrpass,lrp,lrn,lro,user,pass);
		try {
			//Creating XML File
			cfg.writeXmlFile();
			//after to create xml file redirect to index
			HttpServletResponse res = (HttpServletResponse) response;   
			String redirect = ((HttpServletRequest)(request)).getContextPath()+"/index.do";
			res.sendRedirect(redirect);
		} catch (ParserConfigurationException | TransformerException e) {
			request.getRequestDispatcher("/config.jsp").forward(request, response);
		}
	}

}
