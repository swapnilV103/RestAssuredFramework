package userCreation;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class BrearerToken {
	
	private static String ID;
	
	
	
	@Test(priority=0)
	void getUsersUsingPath() {
		HashMap<String,String> map = new HashMap<>();
		map.put("page", "1");
		map.put("per_page", "5");
		
	Response resp =given()
			.contentType("application/json")
			.params(map)
			
		.when()
			.get("https://gorest.co.in/public/v2/users")
		.then()
		.statusCode(200)
		.log().body()
		.extract().response();
	System.out.println("inside get User.................");
	
	
	}
	
	
	
	
	
	
	
	
	@Test (priority=1)
	public void withBearerToken() {
		
		
		
		JSONObject data = new JSONObject();
		data.put("name", "swapnilI");
		data.put("email", "jadshfaasoiuhgsdsderahkjjhh@gmail.com");
		data.put("gender", "male");
		data.put("status", "active");
		
		HashMap map = new HashMap();
		map.put("content-type", "application/json");
		//map.put("Authorization", "Bearer 206250362681fdc8c6e36db62cbd1328fe5ed3a399960867c23ea1016d596f78");
		
		
		Response response = given()
			.contentType("application/json")
			.body(data.toString())
			.headers(map)
			.auth().oauth2("206250362681fdc8c6e36db62cbd1328fe5ed3a399960867c23ea1016d596f78")
			
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
		
		
		.then()
			.statusCode(201)			
			.log().status() // Optionally logs the response status
            .time(lessThan(5000L))
            .extract().response(); 
		
		ID= response.jsonPath().getString("id");
		Assert.assertEquals(response.jsonPath().getString("id"), ID);
		
		
		System.out.println(ID);
	}
	
	
	
	
	
	
	@Test (priority=1,dependsOnMethods="withBearerToken")
	void deleteSameUser() {
		
		given()
			.contentType("application/json")
			.auth().oauth2("206250362681fdc8c6e36db62cbd1328fe5ed3a399960867c23ea1016d596f78")
			.pathParam("userID", ID)
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{userID}")
			
		.then()
			.statusCode(204);
		
	
		
	}
	

	
	

}
