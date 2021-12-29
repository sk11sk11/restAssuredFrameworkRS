package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

//extend to inherit methods present in Utils
public class stepDefinitions extends Utils {

	ResponseSpecification resspec;
	RequestSpecification postResponse;
	Response response;
	static String place_id;
	
	//create object of TestDataBuild class to access methods within that class
	TestDataBuild data = new TestDataBuild();
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {	  		
								
		postResponse = given()
			.spec(requestSpecification())//extends from Utils class
			.body(data.AddPlacePayload(name, language, address));//serialized to JSON and passed in body.
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_request(String resource, String method) {
		
		APIresources resourceAPI = APIresources.valueOf(resource);//invoke constructor w/ value of resource.
		System.out.println(resourceAPI.getResource());		
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST")) {
			
			response = postResponse.when().post(resourceAPI.getResource());
			
		}else if(method.equalsIgnoreCase("GET")) {
				
			response = postResponse.when().get(resourceAPI.getResource());
		
		}
			
			
	}

	@Then("then API call is successful with Status Code {int}")
	public void then_api_call_is_successful_with_status_code(Integer int1) {
	
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	
		assertEquals(getJsonPath(response, keyValue), Expectedvalue);
		
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		place_id = getJsonPath(response, "place_id");
		//prepare request spec
		postResponse = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_post_request(resource, "GET");
		String actualname = getJsonPath(response, "name");
		assertEquals(actualname, expectedName );
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	   
		postResponse = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
