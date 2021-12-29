package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	// Hooks allow you to provide Pre/Post conditions for running a test.

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		// write code that gives you a place_id
		// execute this code only when place_id is null

		// create object of stepDefinitions class to access the add_place_payload_with
		// method, as this is not running via TestRunner class

		stepDefinitions m = new stepDefinitions();
		//since place_id is static, call static variable w/ class name.  static variable belongs to class memory not object memory.
		if (stepDefinitions.place_id == null) {

			m.add_place_payload_with("Hema", "French", "Asia");
			m.user_calls_with_post_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Hema", "getPlaceAPI");
		}

	}

}
