package org.viz.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.LabelIssueQuantityJSON;
import org.json.RepositoryJSON;
import org.visminer.main.VisMiner;
import org.visminer.model.Issue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class LabelIssueQuantityController
 */
@WebServlet("/labelIssueQuantity")
public class LabelIssueQuantityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public LabelIssueQuantityController() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
		IOException {
		HttpSession session = request.getSession();
		VisMiner vm = (VisMiner)session.getAttribute("visminer");
		
		List<Issue> issues = vm.getIssuesByRepository();
		
		//Map<label, quantity>
		Map<String, Integer> mapLabels = new HashMap<String, Integer>();
		final String UNKNOWN = "unknown";
		
		for(Issue i: issues){
			
			if(!i.getLabels().isEmpty()){
				
				for(String label: i.getLabels()){
					
					if(mapLabels.containsKey(label)){
						
						mapLabels.put(label, mapLabels.get(label) + 1);
					
					}else{
						
						mapLabels.put(label, 1);
						
					}
				}
					
			}else{
				
				if(mapLabels.containsKey(UNKNOWN)){
					
					mapLabels.put(UNKNOWN, mapLabels.get(UNKNOWN) + 1);
				
				}else{
					
					mapLabels.put(UNKNOWN, 1);
					
				}
			}
		}
		
		List<LabelIssueQuantityJSON> labelIssuesJson = new ArrayList<LabelIssueQuantityJSON>();
		LabelIssueQuantityJSON labelIssueQuantityJSON = null;
		
		for(Entry<String, Integer> s: mapLabels.entrySet()){
			
			labelIssueQuantityJSON = new LabelIssueQuantityJSON();
			labelIssueQuantityJSON.setLabel(s.getKey());
			labelIssueQuantityJSON.setQuantity(s.getValue());
			labelIssuesJson.add(labelIssueQuantityJSON);
			
		}
		
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls(); //show nulls in json
        Gson gson = builder.create();
        String labels_json = gson.toJson(labelIssuesJson);
        
        RepositoryJSON repositoryJSON = new RepositoryJSON();
        repositoryJSON.setIdGit(vm.getRepository().getIdGit());
        
        String repository = gson.toJson(repositoryJSON);
        
        request.setAttribute("repository_json", repository);
		request.setAttribute("labelIssues_json", labels_json);
		request.getRequestDispatcher("/labelIssues.jsp").forward(request, response);

	}
	
}
