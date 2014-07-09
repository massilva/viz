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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.visminer.main.VisMiner;
import org.viz.main.Viz;
import org.viz.model.Configuration;
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
				Configuration cfg = new Configuration(XmlFile);
				VisMiner vm = (VisMiner)session.getAttribute("visminer");		
				if(vm==null){
					Viz viz = new Viz(cfg);
					vm = viz.getVisminer();
					//If created the tables, set flag to false, 
					//case the server is restarted, 
					//it do not try to create the tables again.
					if(cfg.isCreateTableFlag()){
						cfg.setCreateTableFlag(false);
						cfg.writeXmlFile();
					}
					session.setAttribute("visminer",vm);
				}			
				chain.doFilter(request, response);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
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