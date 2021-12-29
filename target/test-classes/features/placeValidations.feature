Feature:  Validating Place APIs

@AddPlace @Regression
#Scenario Outline whenever Examples keyword is used
Scenario Outline: Verify if Place is being successfully added by using AddPlace API
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then then API call is successful with Status Code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"


Examples: 
	|name			|language		|address       |
	|OrleansHouse	|English		|World X Center|
#	|DallasHouse	|Spanish		|Sea X Center|
#	|SomaHouse		|Latin			|Land X Center|


@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then then API call is successful with Status Code 200
	And "status" in response body is "OK"
	
	




