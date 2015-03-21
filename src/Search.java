
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search {
	private  ArrayList<Concert> apisData = new ArrayList<Concert>();
	private ArrayList<Concert> tempList = new ArrayList<Concert>();
	private JSONObject obj;
	

	public ArrayList<Concert> getApisData() throws JSONException{
		//Lesa inn json hlut frá apis.is
		try {
			obj = readJsonFromUrl("http://apis.is/concerts");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Búið að lesa inn json hlutinn*/
		
		JSONArray results = obj.getJSONArray("results");
		for (int i=0; i<results.length(); i++) {
		    JSONObject item = results.getJSONObject(i);
		    String name = item.getString("eventDateName");
		    String timeDate = item.getString("dateOfShow");
		    String loc = item.getString("eventHallName");
		    
		    String[] splitTimeDate = timeDate.split("T");
		    Concert concert = new Concert();
		    
		    concert.setName(name);
		    concert.setDate(splitTimeDate[0]);
		    concert.setTime(splitTimeDate[1]);
		    concert.setLoc(loc);
		    
		    apisData.add(concert);
		}
		
		return apisData;
	}
	
	private String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      InputStreamReader in = new InputStreamReader(is, Charset.forName("UTF-8"));
	      BufferedReader rd = new BufferedReader(in);
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	
	
	private ArrayList<Concert> filter(ArrayList<Concert> data, String inputType, String inputData){
		ArrayList<Concert> list = new ArrayList<Concert>();
		for(int i = 0; i < data.size(); i++ ){
			Concert temp = data.get(i);
			if(inputType == "name" && inputData == temp.getName()){
				list.add(temp);
			}
			if(inputType == "time" && inputData == temp.getTime()){
				list.add(temp);
			}
			if(inputType == "loc" && inputData == temp.getLoc()){
				list.add(temp);
			}
			if(inputType == "date" && inputData == temp.getDate()){
				list.add(temp);
			}
		}
		return list;
	}
	
	public ArrayList<Concert> getFilteredData(String name, String time, String loc, String date) throws JSONException{
		apisData = getApisData();
			
		if(name != "") {
			for(int i = 0; i < apisData.size(); i++){
				Concert temp = apisData.get(i);
				if(name.equals(temp.getName())){
					tempList = filter(apisData, "name", temp.getName());
					break;
				}
			}
		}
		if(time != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(time.equals(temp.getTime())){
						tempList = filter(apisData, "time", temp.getTime());
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(time.equals(temp.getTime())){
						tempList = filter(tempList, "time", temp.getTime());
						break;
					}
				}
			}
		}
			
		if(loc != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(loc.equals(temp.getLoc())){
						tempList = filter(apisData, "loc", temp.getLoc());
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(loc.equals(temp.getLoc())){
						tempList = filter(tempList, "loc", temp.getLoc());
					}
				}
			}
		}
			
		if(date != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(date.equals(temp.getDate())){
						tempList = filter(apisData, "date", temp.getDate());
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(date.equals(temp.getDate())){
						tempList = filter(tempList, "date", temp.getDate());
						break;
					}
				}
			}
		}
		return tempList;
	}
	
	public static void main(String[]args) throws JSONException{
		Search search = new Search();
		//ArrayList<Concert> concerts = search.getApisData();
		ArrayList<Concert> filter = search.getFilteredData("","21:00:00","","");
		
		for(int i = 0; i < filter.size(); i++){
			System.out.println(filter.get(i).getName());
		}
	}
}
