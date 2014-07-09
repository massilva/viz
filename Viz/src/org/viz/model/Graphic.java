package org.viz.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
	@SuppressWarnings("unchecked")
	public String generateChart(List<MetricValue> chosen, String relatedto){
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
			//IF count elements is equal to ONE the metric is not metric file because of default the visminer store the total value of metric
			if(chosen.size() == 1){
				MetricValue mv = chosen.get(0);
				writer.beginObject(); // {
				writer.name("name").value(mv.getMetric().getDescription()); 
				writer.name("size").value(mv.getValue());
				writer.endObject(); // }
				json.add(mv.getValue());
			}else{
				for(MetricValue mv : chosen){
					int li = 0;
					String name = "";
					if(relatedto.equals("file") && mv.getFile() != null){
						li = mv.getFile().getPath().lastIndexOf("/") + 1;
						name = mv.getFile().getPath().substring(li);
						this.putObject(writer,name,mv.getValue()+"");
						json.add(mv.getValue());
					}else if(relatedto.equals("tag") && mv.getTag() != null){
						name = mv.getTag().getId().getName();
						this.putObject(writer,name,mv.getValue()+"");
						json.add(mv.getValue());
					}
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

	private void putObject(JsonWriter writer,String name, String value) throws IOException{
		writer.beginObject(); // {
		writer.name("name").value(name); 
		writer.name("size").value(value);
		writer.endObject(); // }
	}

	public String readJson() throws org.json.simple.parser.ParseException, FileNotFoundException, IOException{
		JSONParser parser = new JSONParser();	 
		Object obj = parser.parse(new FileReader(this.path));
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject.toJSONString();
	}
}
