
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
import org.visminer.model.Metric;
import org.visminer.model.MetricValue;
import org.viz.main.Viz;
import org.viz.model.Graphic;

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
			
			String metricChosen = request.getParameter("m");
			if(metricChosen == null){
				metricChosen = "LOC";
			}
			
			//getting the chosen metric
			Metric chosen = new Metric();
			for (Metric m : vm.getMetrics()) {
				if(m.getName().equals(metricChosen)){
					chosen = m;
					break;
				}
			}
			
			//getting the most value from the value of the metric
			double greater = 1;
			for(MetricValue mv : chosen.getMetricValues()){
				if(mv.getFile() != null){
				    //verifies that the value of metricValue is greater than the last value is set higher and the file exists because of the LOC TAG
					if(mv.getValue() > greater && mv.getFile() != null){
						greater = mv.getValue();
					}
		    	}
			}
			
			Graphic g = new Graphic("chart.json");//new Graphic("/home/massilva/workspace/Viz/Viz/WebContent/bubbleChart.json");
			String values = g.generateChart(chosen.getMetricValues());
			
			String selectedChart = "bubbleChart";
			
			request.setAttribute("selectedChart",selectedChart);	
		    request.setAttribute("values",values);	
		    request.setAttribute("greater",greater+"");
		    request.setAttribute("metrics",vm.getMetrics());
		    request.setAttribute("metricName",chosen.getName());
		    request.setAttribute("metricDescription",chosen.getDescription());	
			request.setAttribute("LOCAL_REPOSITORY_PATH",Viz.getLocalRepositoryPath());
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
