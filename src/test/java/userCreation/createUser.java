package userCreation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class createUser {
	
	int ID=0;

	
	//@Test
	void getUsers() 
	{
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("data[0].id",equalTo(7))
			//.log().body()
			.log().all();		
		
	}
	
	
	@Test (priority = 1)
	void createUsers() {
		
		HashMap<String, String> hm = new HashMap<String, String>();
		
		hm.put("name", "swapnil");
		hm.put("job", "SDET");
		
		ID=given()
			.contentType("application/json")
			.body(hm)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			
		
	//	.then()
	//		.statusCode(201)
	//		.log().all();	
	}
	

}
