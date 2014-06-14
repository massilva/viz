package org.viz.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.visminer.model.MetricValue;

import com.google.gson.stream.JsonWriter;

public class Graphic {
	private String path;
	
	public Graphic(){
		
	}
	
	public Graphic(String path) {
		super();
		this.path = path;
	}
	
	/**
	 * 
	 * @param chosen List of metric values
	 * @return create json file and return String containing value of metric value.
	 */
	public String generateChart(List<MetricValue> chosen){
		//Generating JSON file
		JsonWriter writer;
		JSONArray json = new JSONArray();
		try {
			//instance json file 
			File f = new File(this.path);
			f.delete();//deleting json file
			//creating new json file
			writer = new JsonWriter(new FileWriter(this.path));
			writer.beginObject(); // {
			writer.name("name").value("class"); // "name" : "class"
			writer.name("children"); // "children" : 
			writer.beginArray(); // [
			for(MetricValue mv : chosen){
		    	if(mv.getFile() != null){
			    	writer.beginObject(); // {
			    	int li = mv.getFile().getPath().lastIndexOf("/") + 1;
			    	String file = mv.getFile().getPath().substring(li);
			    	writer.name("name").value(file); 
					writer.name("size").value(mv.getValue());
					writer.endObject(); // }
					json.put(mv.getValue());
		    	}
			}
			writer.endArray(); // ]				
			writer.endObject(); // }
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
}
