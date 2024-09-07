package userCreation;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class ParsingResponseBody4 {
	
	
	//firstnormal approch
	@Test
	public void normalparsing() {
		
		
		given()
			.contentType("application/json")
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("book[2].title",equalTo("Book three title"));
	}
	
	
	//approach2
		@Test
		public void Responseparsing() {
		
		
		Response resp =given()
			.contentType("application/json")
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("book[2].title",equalTo("Book three title"))
			.extract().response();
		
		
		int code = resp.statusCode();
		Assert.assertEquals(code, 200);
		
		String bookname = resp.jsonPath().get("book[2].title").toString();
		Assert.assertEquals(bookname, "Book three title");
		
		//you cah checkk all headers content type
		
	}
		
		
   //approach3
		@Test
		public void JsonObjectparsing() {
			
			
			Response resp =given()
				.contentType("application/json")
			.when()
				.get("http://localhost:3000/store")
			.then()
				.statusCode(200)
				.header("Content-Type", "application/json")
				.body("book[2].title",equalTo("Book three title"))
				.extract().response();
			
			//now we can iterate through the books
			JSONObject jo = new JSONObject(resp.asString());
			boolean status =false;
			for(int i=0; i<jo.getJSONArray("book").length();i++) {
				
				String bookname = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
				if(bookname.equals("Book three title")) {
					
					status=true;
					break;
				}
			}
			
			Assert.assertEquals(status, true);
			
			int sum=0;
			for(int i=0; i<jo.getJSONArray("book").length();i++) {
				
				String bookname = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
				sum+=Integer.parseInt(bookname);
			}
			
			System.out.println("The sum ...............is: "+sum);
			
			
			
			
		}
	
	
	
	
	

}
