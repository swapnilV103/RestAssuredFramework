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

public class QueryParam3 {
	
	@Test(priority = -1)
	void testQueryParameter() {
		
		Response resp =given()
			.queryParam("id",5 )
			.queryParam("page", "2" )
			.pathParam("mypath1","api")
			.pathParam("mypath","users")
		.when()
			.get("https://reqres.in/{mypath1}/{mypath}")
		.then()
		.statusCode(200)
		.time(lessThan(600L))
		.log().body()
		.extract().response();
		  long time =resp.getTime();
		  System.out.println("timeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: "+time);
		
		

	}
	
	
	@Test
	void getCookieInfo() {
		
		Response resp = given()
				               
				        .when()
				           .get("https://www.google.com")
				        .then()
				        	.extract().response();
		
		String cook = resp.getCookie("AEC");
		
		
		
		  long time =resp.getTime();
		  assert time < 1000L;
		 
		  System.out.println("timeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: "+time);
		Headers a =resp.getHeaders();
		
		for(Header h:a) {
			System.out.println("Header: "+h);
		}
		
		/*HashMap<String,String> Cookies = new HashMap<>();
		Cookies= (HashMap<String, String>) resp.getCookies();
		
		
		HashMap<String,String> map = resp.getCookies();
		System.out.print("the value of cookies is: "+cook);*/
		
		Map<String,String> map = resp.getCookies();
		
	   for( Entry<String, String> entry: map.entrySet()) {
		   
		  // System.out.println("The cooki name : "+entry.getKey()+": "+ entry.getValue());
		   
	   }
	   
	   
	   HashMap<String,String> map1 = new HashMap<String, String> (resp.getCookies());
	   
	   for (String key:map1.keySet()) {
		   //System.out.println(resp.getCookie(key));

	   }
	   
	   for(Entry<String,String> entry:map1.entrySet()) {
		   System.out.println("Name: "+entry.getKey()+": "+entry.getValue());

	   }
				       
				      
	}

}
