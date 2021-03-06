
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApisManager {
	private static ArrayList<Concert> apisData = new ArrayList<Concert>();
	private static JSONObject obj;
	
	//Gets data from apis and returns them in a ArrayList of concerts.
	public static ArrayList<Concert> getApisData() throws DataNotFoundException{
		obj = readJsonFromUrl("http://apis.is/concerts");
		apisData = new ArrayList<Concert>();
		
		JSONArray results;
		if(obj == null){
			return apisData;
		}
		try {
			results = obj.getJSONArray("results");
			for (int i=0; i<results.length(); i++) {
			    JSONObject item = results.getJSONObject(i);
			    String name = item.getString("eventDateName");
			    String timeDate = item.getString("dateOfShow");
			    String loc2 = item.getString("eventHallName");
			    String loc1 = item.getString("userGroupName");
			    
			    String[] splitTimeDate = timeDate.split("T");
			    Concert concert = new Concert();
			    
			    concert.setName(name);
			    concert.setDate(splitTimeDate[0]);
			    concert.setTime(splitTimeDate[1]);
			    concert.setLoc(loc1+", "+loc2);
			    
			    apisData.add(concert);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new DataNotFoundException("Could not genarate ArrayList of Concerts from JSON file");
		}
		
		return apisData;
	}
	
	//Returns a string. 
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}

	//Reads JSON string from url and returns as a JSONObject.
	private static JSONObject readJsonFromUrl(String url) throws DataNotFoundException {
		InputStream is;
		JSONObject json = null;
		try {
			is = new URL(url).openStream();
			try {
				InputStreamReader in = new InputStreamReader(is, Charset.forName("UTF-8"));
		        BufferedReader rd = new BufferedReader(in);
		        String jsonText = readAll(rd);
		        json = new JSONObject(jsonText);
		        return json;
			} finally {
				is.close();
		    }
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new DataNotFoundException("I/O Error");
			
		} catch (JSONException e) {
			e.printStackTrace();
			throw new DataNotFoundException("JSON Error");
			
		}
		return json;
	  }
	
	
	// Filters out of ArrayList data by the value of inputData.
	private static ArrayList<Concert> filter(ArrayList<Concert> data, String inputType, String inputData){
		ArrayList<Concert> list = new ArrayList<Concert>();
		for(int i = 0; i < data.size(); i++ ){
			Concert temp = data.get(i);
			String getPriceString = String.valueOf(temp.getPrice());
			if(inputType == "name" && temp.getName().contains(inputData)){
				list.add(temp);
			}
			if(inputType == "time" && temp.getTime().contains(inputData)){
				list.add(temp);
			}
			if(inputType == "loc" && temp.getLoc().contains(inputData)){
				list.add(temp);
			}
			if(inputType == "date" && temp.getDate().contains(inputData)){
				list.add(temp);
			}
			if(inputType == "price" && getPriceString.contains(inputData)){
				list.add(temp);
			}
		}
		return list;
	}
	
	
	// Filters out by given inputs, name, time, location, date or price.
	public static ArrayList<Concert> getFilteredData(String name, String time, String loc, String date, int price) throws DataNotFoundException{
		apisData = getApisData();
		ArrayList<Concert> tempList = new ArrayList<Concert>();
		if(name != "") {
			for(int i = 0; i < apisData.size(); i++){
				Concert temp = apisData.get(i);
				if(temp.getName().contains(name)){
					tempList = filter(apisData, "name", name);
					break;
				}
			}
		}
		if(time != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(temp.getTime().contains(time)){
						tempList = filter(apisData, "time", time);
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(temp.getTime().contains(time)){
						tempList = filter(tempList, "time", time);
						break;
					}
				}
			}
		}
			
		if(loc != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(temp.getLoc().contains(loc)){
						tempList = filter(apisData, "loc", loc);
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(temp.getLoc().contains(loc)){
						tempList = filter(tempList, "loc", loc);
						break;
					}
				}
			}
		}
			
		if(date != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(temp.getDate().contains(date)){
						tempList = filter(apisData, "date", date);
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(temp.getDate().contains(date)){
						tempList = filter(tempList, "date", date);
						break;
					}
				}
			}
		}
		
		if(price != 0) {
			String priceString = String.valueOf(price);
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					String getPriceString = String.valueOf(temp.getPrice());
					if(getPriceString.contains(priceString)){
						tempList = filter(apisData, "price", priceString);
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					String getPriceString = String.valueOf(temp.getPrice());
					if(getPriceString.contains(priceString)){
						tempList = filter(tempList, "price", priceString);
						break;
					}
				}
			}
		}
		return tempList;
	}
}
