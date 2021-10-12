package RestAssured;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class NegativeContactList 
{
  @Test(enabled = false)
  public void recordNotFound() 
  {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/5")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(404);    //Because record Not found will give this status code
  }
  
  @Test(enabled = false, description = "Adding contact with missing details")
  public void addContactWithMissingParameter() 
  {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  details.put("firstName", "Radhika");
	  details.put("lastName", "");
	  details.put("email", "abc@gmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  
	  emp.put("jobTitle", "Trainee");
	  emp.put("company", "LTI");
	  
	  
	  String error = given()     //Extracting multiple values using this variable
	  	.header("Content-Type","application/json")
	  	.body(details.toJSONString())
	  .when()
	  	.post("http://3.13.86.142:3000/contacts")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(400)    //Because its a client side mistake
	  	.extract()
	  	.path("err");   
	  
	  Assert.assertTrue(error.contains(" is required"));   //For double verification
	  
  }
  
  @Test(enabled = false, description = "Adding contact with too many characters")
  public void TooManyCharacter() 
  {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  details.put("firstName", "RadhikaRadhikaRadhikaRadhikaRadhikaRadhika");
	  details.put("lastName", "Kedia");
	  details.put("email", "abc@gmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  
	  emp.put("jobTitle", "Trainee");
	  emp.put("company", "LTI");
	  
	  
	  String error = given()     //Extracting multiple values using this variable
	  	.header("Content-Type","application/json")
	  	.body(details.toJSONString())
	  .when()
	  	.post("http://3.13.86.142:3000/contacts")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(400)
	  	.extract()
	  	.path("err");   
	  
	  Assert.assertTrue(error.contains("is longer than the maximum allowed length"));
	  
  }
  
  @Test(enabled = true, description = "Adding contact with invalid characters")
  public void InvalidCharacter() 
  {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  details.put("firstName", "Radhika546");
	  details.put("lastName", "Kedia123");    //Checking for both
	  details.put("email", "abc@gmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  
	  emp.put("jobTitle", "Trainee");
	  emp.put("company", "LTI");
	  
	  
	  String error = given()     //Extracting multiple values using this variable
	  	.header("Content-Type","application/json")
	  	.body(details.toJSONString())
	  .when()
	  	.post("http://3.13.86.142:3000/contacts")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(400)
	  	.extract()
	  	.path("err");   
	  
	  Assert.assertTrue(error.contains("Validator failed"));
	  
  }
  
  @Test(enabled = true, description = "Adding contact with improper format")
  public void ImproperFormat() 
  {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  details.put("firstName", "Radhika");
	  details.put("lastName", "Kedia");
	  details.put("email", "abcgmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  
	  emp.put("jobTitle", "Trainee");
	  emp.put("company", "LTI");
	  
	  
	  String error = given()     //Extracting multiple values using this variable
	  	.header("Content-Type","application/json")
	  	.body(details.toJSONString())
	  .when()
	  	.post("http://3.13.86.142:3000/contacts")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(400)
	  	.extract()
	  	.path("err");   
	  
	  Assert.assertTrue(error.contains("Validator failed"));
	  
  }
}
