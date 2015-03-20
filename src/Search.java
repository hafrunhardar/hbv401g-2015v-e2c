import java.util.*;

public class Search {
	private ArrayList<Concert> apisData = new ArrayList<Concert>();
	private ArrayList<Concert> tempList = new ArrayList<Concert>();
	
	public ArrayList<Concert> getApisData(){
		apisData = ApisMock.getData();
		return apisData;
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
			if(inputType == "price" && inputData == temp.getPrice()){
				list.add(temp);
			}
			if(inputType == "date" && inputData == temp.getDate()){
				list.add(temp);
			}
		}
		return list;
	}
	
	public ArrayList<Concert> getFilteredData(String name, String time, String price, String date){
		apisData = ApisMock.getData();
			
		if(name != "") {
			for(int i = 0; i < apisData.size(); i++){
				Concert temp = apisData.get(i);
				if(name == temp.getName()){
					tempList = filter(apisData, "name", temp.getName());
					break;
				}
			}
		}
		if(time != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(time == temp.getTime()){
						tempList = filter(apisData, "time", temp.getTime());
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(time == temp.getTime()){
						tempList = filter(tempList, "time", temp.getTime());
						break;
					}
				}
			}
		}
			
		if(price != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(price == temp.getPrice()){
						tempList = filter(apisData, "price", temp.getPrice());
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(price == temp.getPrice()){
						tempList = filter(tempList, "price", temp.getPrice());
					}
				}
			}
		}
			
		if(date != "") {
			if(tempList.size() == 0){
				for(int i = 0; i < apisData.size(); i++){
					Concert temp = apisData.get(i);
					if(date == temp.getDate()){
						tempList = filter(apisData, "date", temp.getDate());
						break;
					}
				}
			}else{
				for(int i = 0; i < tempList.size(); i++){
					Concert temp = tempList.get(i);
					if(date == temp.getDate()){
						tempList = filter(tempList, "date", temp.getDate());
						break;
					}
				}
			}
		}
		if(tempList.size()==0){ 
			return apisData;
		}else{
			return tempList;
		}
	}
}
