package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//class for all re-usable methods
public class Utils {
	
	public static RequestSpecification req; //make it static so req is available for further tests until whole run is executed.
	
	public RequestSpecification requestSpecification() throws IOException {
		
		
		if(req==null) //for first run, logging file is created.  
			{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))//logging redirected to PrintStream object
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
			
			return req;
			}
		return req; //for second run, req will just be returned and won't go through if block;  log file already created in the first run.  
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();//can now scan any file which has .properties extension.
		FileInputStream fis = new FileInputStream("C:\\Users\\C-T74A897\\eclipse-workspace\\restAssuredFramework\\src\\test\\java\\resources\\global.properties"); //read the file
		prop.load(fis);
		
		return prop.getProperty(key);
	}
	
	
	public String getJsonPath(Response response , String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
