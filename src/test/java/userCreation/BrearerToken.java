package userCreation;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class BrearerToken {
	
	
	
	@Test
	public void withBearerToken() {
		
		JSONObject data = new JSONObject();
		data.put("name", "swapnilI");
		data.put("email", "kjashfahs@gmail.com");
		data.put("gender", "male");
		data.put("status", "active");
		
		HashMap map = new HashMap();
		map.put("content-type", "application/json");
		map.put("Authorization", "Bearer 206250362681fdc8c6e36db62cbd1328fe5ed3a399960867c23ea1016d596f78");
		
		
		given()
			.contentType("application/json")
			.body(data.toString())
			.headers(map)
			
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
		
		
		.then()
			.statusCode(201)
			.log().all();
		
		
	}
	
	

}
