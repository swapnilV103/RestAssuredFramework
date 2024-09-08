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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetUser {
	
	
	@Test
	void get_the_User(ITestContext context) {
		
		Integer id = (Integer) context.getSuite().getAttribute("user_id");
		
		given()
			.contentType("application/json")
			.header("Authorization","Bearer 206250362681fdc8c6e36db62cbd1328fe5ed3a399960867c23ea1016d596f78")
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}

}
