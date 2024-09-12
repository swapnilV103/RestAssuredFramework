package apiChaining;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UpdateUser {
	
	
	@Test
	void Update_The_user(ITestContext context) {
		
		
		
		Integer id = (Integer) context.getSuite().getAttribute("user_id");
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "Active");
		
		
		
		Response resp =given()
			.contentType("application/json")
			.auth().oauth2("206250362681fdc8c6e36db62cbd1328fe5ed3a399960867c23ea1016d596f78")
			.body(data.toString())
			.pathParam("id", id)
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.log().body()
			.extract().response();
		
	
		
		int scode = resp.getStatusCode();
	
		
		System.out.println("**codeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee:     "+scode);


	
		
	}

}
