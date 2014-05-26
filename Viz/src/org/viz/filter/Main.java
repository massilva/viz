package org.viz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.visminer.main.VisMiner;
import org.viz.main.Viz;

@WebFilter("*.do")
public class Main implements Filter {

    /**
     * Default constructor. 
     */
    public Main() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)(request)).getSession();
		Viz viz = (Viz)session.getAttribute("viz");		
		if(viz==null){
			/*
			 * check if is passed the parameter createTable by URL
			 * IF is null THEN the user to want visualization information of new repository
			 * ELSE the user to want visualization information of old repository
			 */
			boolean createTable = (request.getParameter("createTable") != null);
			session.setAttribute("viz",new Viz(createTable));
		}
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}