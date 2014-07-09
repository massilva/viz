
package org.viz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.visminer.main.VisMiner;
import org.visminer.model.Metric;
import org.visminer.model.MetricValue;
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
		VisMiner vm = (VisMiner)session.getAttribute("visminer");

		String metricChosen = request.getParameter("m");
		if(metricChosen == null){
			metricChosen = "LOC";
		}
		String chartChosen = request.getParameter("c");
		if(chartChosen == null){
			chartChosen = "bubbleChart";
		}
		String relatedtoChosen = request.getParameter("r");
		if(relatedtoChosen == null){
			relatedtoChosen = "file";
		}

		//getting the chosen metric
		Metric chosen = vm.getMetric(metricChosen);

		//getting the most value from the value of the metric
		double greater = 1;
		for(MetricValue mv : chosen.getMetricValues()){
			//verifies that the value of metricValue is greater than the last value is set higher and the file exists because of the LOC TAG
			if(mv.getValue() > greater){
				greater = mv.getValue();
			}
		}

		//Get json with datas
		Graphic g = new Graphic("chart.json");
		String values = g.generateChart(chosen.getMetricValues(),relatedtoChosen);
		//Setting the selected chart 
		request.setAttribute("selectedChart",chartChosen);	
		request.setAttribute("relatedto",relatedtoChosen);	
		request.setAttribute("values",values);	
		request.setAttribute("greater",greater+"");
		request.setAttribute("metrics",vm.getMetrics());
		request.setAttribute("metricName",chosen.getName());
		request.setAttribute("metricDescription",chosen.getDescription());	
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
