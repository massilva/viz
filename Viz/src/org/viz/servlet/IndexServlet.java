
package org.viz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.visminer.main.VisMiner;
import org.viz.main.Viz;

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
		HttpSession session = request.getSession();
		Viz viz = (Viz)session.getAttribute("viz");
		
		try {
			
			VisMiner vm = viz.getVisminer();
			/*
			Metric metric = null;

			for(Metric m : viz.getVisminer().getMetrics()) {
				if(m.getName().equals("NOC")){
					metric = m;
					break;
				}
			}
			
			if(metric != null){
				System.out.println(metric.getName()+"-"+metric.getDescription());
				for (MetricValue metricValue : metric.getMetricValues()) {
					System.out.println("File: "+metricValue.getFile());
					System.out.println("Value: "+metricValue.getValue());
				}
			}*/
			request.setAttribute("metrics",vm.getMetrics());	
			request.setAttribute("LOCAL_REPOSITORY_PATH",viz.getLocalRepositoryPath());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (GitAPIException e) {
			PrintWriter writer = response.getWriter();
			writer.println("ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
