package org.viz.filter;

import java.io.File;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.viz.main.Viz;
import org.viz.model.Configuration;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
		HttpServletResponse res = (HttpServletResponse) response;   

		File XmlFile = new File("config.xml");
		if(!XmlFile.exists()){
			String redirect = ((HttpServletRequest)(request)).getContextPath()+"/index.cfg";
			res.sendRedirect(redirect);
		}else{
			try{
				Configuration cfg = new Configuration();
				Document doc = cfg.readXmlFile(XmlFile);
				System.out.println(cfg.docToString(doc));
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

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}