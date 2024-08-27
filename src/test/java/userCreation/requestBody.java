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

public class requestBody {
	int maxId=0;
	
	
	
	
	@Test
	public void currentIDfromDatabase() {
		Response response = get("http://localhost:3000/people");
		List<Integer> ids = response.jsonPath().getList("id", Integer.class);
        maxId = ids.stream().max(Integer::compare).orElse(0);
        System.out.println(maxId);
      
        
		
			
	}

	@Test (dependsOnMethods= {"currentIDfromDatabase"})
	void createUserUsingHashMap() {
		
		HashMap map = new HashMap();
		
		map.put("id", maxId+1);
		map.put("name", "swapnil");
		map.put("location", "baramati");
		map.put("phone", "9047463323");
		
		String arr[] = {"Python", "Java"};
		//System.out.print(Arrays.toString(arr));
		map.put("courses", arr);
		
		
		given()
			.contentType("application/json")
			.body(map)
			
		.when()
			.post("http://localhost:3000/people")
			
		.then()
			.log().all();
		maxId++;
		
	}
	
	
	
	@Test (dependsOnMethods= {"currentIDfromDatabase"})
	void createUserUsingJsonLibrary() {
		
		JSONObject data= new JSONObject();
		
		
		data.put("id", maxId+1);
		data.put("name", "swapyyy");
		data.put("location", "baramati");
		data.put("phone", "9047463323");
		
		String arr[] = {"Python", "Java"};
		//System.out.print(Arrays.toString(arr));
		data.put("courses", arr);
		
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("http://localhost:3000/people")
			
		.then()
			.log().all();
		maxId++;
		
		
	}
	
	
	

}
