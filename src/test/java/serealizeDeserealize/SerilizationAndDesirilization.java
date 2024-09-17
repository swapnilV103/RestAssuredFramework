package serealizeDeserealize;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerilizationAndDesirilization {
	
	people data = new people();
	
	@Test
	void serealize() throws JsonProcessingException {
		
		data.setName("newpy");
		data.setLocation("spain");
		data.setPhone("0987346323");
		String arr[]= {"course1","course2"};
		data.setCourses(arr);
		
		ObjectMapper obj = new ObjectMapper();
		
		String one = obj.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		String two=obj.writeValueAsString(one);
		System.out.println(one);
		System.out.println(two);
		
		
		//Now this data can be passed in the body of the request
	}
	
	
	
	@Test(priority = 4)
	void deserialize() throws JsonMappingException, JsonProcessingException {
		
		String jsonData = "{\r\n"
				+ "  \"name\" : \"newpy\",\r\n"
				+ "  \"location\" : \"spain\",\r\n"
				+ "  \"phone\" : \"0987346323\",\r\n"
				+ "  \"courses\" : [ \"course1\", \"course2\" ]\r\n"
				+ "}";
		
		ObjectMapper obj = new ObjectMapper();
		
		people data = obj.readValue(jsonData, people.class);	
		
		System.out.println(data.getName());
		
		
	}
	
	

}
