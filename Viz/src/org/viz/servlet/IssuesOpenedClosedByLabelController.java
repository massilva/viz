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

import org.json.IssueOpenedClosedByLabelJSON;
import org.json.RepositoryJSON;
import org.visminer.main.VisMiner;
import org.visminer.model.Issue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class IssuesOpenedClosedByLabelController
 */
@WebServlet("/labelIssues_OpenedClosed_byLabel")
public class IssuesOpenedClosedByLabelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssuesOpenedClosedByLabelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		VisMiner vm = (VisMiner)session.getAttribute("visminer");
			
		List<Issue> issues = vm.getIssuesByRepository();

		//Map<Label, Map<Opened or Closed, quantity>>
		Map<String, Map<String, Integer>> mapLabels = new HashMap<String, Map<String, Integer>>();
		final String CLOSED = "CLOSED";
		final String OPEN = "OPEN";
		final String UNKNOWN = "unknown";

		for(Issue i: issues){
			
			if(!i.getLabels().isEmpty()){
				
				if(i.getStatus().equals(OPEN)){
					
					for(String label: i.getLabels()){
						
						if( mapLabels.containsKey(label) ){ 
							
							if(mapLabels.get(label).containsKey(OPEN)){
								
								mapLabels.get(label).put(OPEN, mapLabels.get(label).get(OPEN)+1);
							
							}else{
								
								mapLabels.get(label).put(OPEN, 1);
								
							}
							
						}else{ //add a new label in "mapLabels"
							
							Map<String, Integer> value = new HashMap<String, Integer>(2);
							value.put(OPEN, 1);
							mapLabels.put(label, value);
							
						}
					}
					
				}else{ //issue closed
					
					for(String label: i.getLabels()){
						
						if( mapLabels.containsKey(label) ){ 
							
							if(mapLabels.get(label).containsKey(CLOSED)){
								
								mapLabels.get(label).put(CLOSED, mapLabels.get(label).get(CLOSED)+1);
							
							}else{
								
								mapLabels.get(label).put(CLOSED, 1);
								
							}
							
						}else{ //add a new label in "mapLabels"
							
							Map<String, Integer> value = new HashMap<String, Integer>(2);
							value.put(CLOSED, 1);
							mapLabels.put(label, value);
							
						}
					}	
				}
				
			}else{ //unknown label
				
				if(i.getStatus().equals(OPEN)){
						
						if( mapLabels.containsKey(UNKNOWN) ){ 
							
							if(mapLabels.get(UNKNOWN).containsKey(OPEN)){
								
								mapLabels.get(UNKNOWN).put(OPEN, mapLabels.get(UNKNOWN).get(OPEN)+1);
							
							}else{
								
								mapLabels.get(UNKNOWN).put(OPEN, 1);
								
							}
							
						}else{ //add a new label in "mapLabels"
							
							Map<String, Integer> value = new HashMap<String, Integer>(2);
							value.put(OPEN, 1);
							mapLabels.put(UNKNOWN, value);
							
						}
					
				}else{ //issue closed
							
						if( mapLabels.containsKey(UNKNOWN) ){ 
							
							if(mapLabels.get(UNKNOWN).containsKey(CLOSED)){
								
								mapLabels.get(UNKNOWN).put(CLOSED, mapLabels.get(UNKNOWN).get(CLOSED)+1);
							
							}else{
								
								mapLabels.get(UNKNOWN).put(CLOSED, 1);
								
							}
							
						}else{ //add a new label in "mapLabels"
							
							Map<String, Integer> value = new HashMap<String, Integer>(2);
							value.put(CLOSED, 1);
							mapLabels.put(UNKNOWN, value);
							
						}
					}	
				}
			}
		
		List<IssueOpenedClosedByLabelJSON> issuesOpenedClosedByLabelJson = new ArrayList<IssueOpenedClosedByLabelJSON>();
		IssueOpenedClosedByLabelJSON issueOpenedClosedByLabelJson = null;
		
		for(Entry<String, Map<String, Integer>> m: mapLabels.entrySet()){
			
			issueOpenedClosedByLabelJson = new IssueOpenedClosedByLabelJSON();
			
			if(m.getValue().containsKey(CLOSED))
				issueOpenedClosedByLabelJson.setClosed(m.getValue().get(CLOSED));
			else
				issueOpenedClosedByLabelJson.setClosed(0);
				
			issueOpenedClosedByLabelJson.setLabel(m.getKey());
			
			if(m.getValue().containsKey(OPEN))
				issueOpenedClosedByLabelJson.setOpened(m.getValue().get(OPEN));
			else
				issueOpenedClosedByLabelJson.setOpened(0);
				
			issuesOpenedClosedByLabelJson.add(issueOpenedClosedByLabelJson);
			
		}
		
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls(); //show nulls in json
        Gson gson = builder.create();
        String labels_json = gson.toJson(issuesOpenedClosedByLabelJson);
        
        RepositoryJSON repositoryJSON = new RepositoryJSON();
        repositoryJSON.setIdGit(vm.getRepository().getIdGit());
        
        String repository = gson.toJson(repositoryJSON);
        
        request.setAttribute("repository_json", repository);
		request.setAttribute("labelIssues_json", labels_json);
		request.getRequestDispatcher("/labelIssues_OpenedClosed_byLabel.jsp").forward(request, response);

	}


}
