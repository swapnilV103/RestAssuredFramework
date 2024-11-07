package userCreation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class createUser {
	
	
	/*In the given section : Content type,body, set cookies, add Auth, add parameters, set headers etc..
	 * In the when section: Get, Put, Post, Delete
	 * In the Then section: Verify status code, o/p data, response body, Extract response Extract Headers
	 */
	  
	
	int ID=0;

	
	@Test
	void getUsers() 
	{   
		
		given()
			.contentType("application/json")
			.param("page", "2")
			
		
		.when()
			.get("https://reqres.in/api/users")
		
		.then()
			.statusCode(200)
			.body("data[0].id",equalTo(7))
			.header("Content-Type",equalTo("application/json; charset=utf-8"))
			//.log().body()
			.log().all();		 //display entire response in the console
		
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
			.jsonPath().getInt("id"); // the request is ended with the semicolon
		//and now the response will be shared in the ID variable
			
		
	//	.then()
	//		.statusCode(201)
	//		.log().all();	
	}
	
	
	@Test (priority = 3, dependsOnMethods= {"createUsers"})
	void updateUser() {
		
		HashMap<String, String> hm = new HashMap<String, String>();
		
		hm.put("name", "Sushant");
		hm.put("job", "SWE");
		
		given()
			.contentType("application/json")
			.body(hm)
		
		.when()
			.put("https://reqres.in/api/users/"+ID)
		
		.then()
			.statusCode(200)
			.log().all();
			
		
	}
	
	
	
	@Test(priority = 4, dependsOnMethods= {"updateUser"})
	void deleteUser() {
		
		
	Response resp =	RestAssured.given()
			.contentType("application/json")
		
		.when()
			.delete("https://reqres.in/api/users/"+ID)
		
		.then()
			.statusCode(204)
			.log().body()
			.extract().response();//there is not body for this request
			
		
		
	}
	

}
