package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace AddPlacePayload(String name, String language, String address) {
		
		//create a java object with supported pojo classes
				AddPlace ap = new AddPlace();
				ap.setAccuracy(50);
				ap.setAddress(address);
				ap.setLanguage(language);
				ap.setPhone_number("345-456-2342");
				ap.setWebsite("https://www.rahulshetty.com");
				ap.setName(name);
				List<String> myList = new ArrayList<String>();
				myList.add("Yellowstone National Park");
				myList.add("Yosemite National Park");
				ap.setTypes(myList);
				Location sierra = new Location();
				sierra.setLat(-40.4534);
				sierra.setLng(54.3453);
				ap.setLocation(sierra);
				
				return ap;
	}
	
	public String deletePlacePayload(String place_id) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}\r\n"
				+ "";
			
	}

}
