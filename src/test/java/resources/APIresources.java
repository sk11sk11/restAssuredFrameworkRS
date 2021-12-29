package resources;

public enum APIresources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource; 
	
	//this constructor will load and pass to resource
	APIresources(String resource) {
		this.resource= resource;
	}
	
	//simply call the resource
	public String getResource() {
		return resource;
	}

}
