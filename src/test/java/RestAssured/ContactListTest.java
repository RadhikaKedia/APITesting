package RestAssured;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ContactListTest 
{
	String id;
  @Test(enabled = false, description = "Getting all Contact List")
  public void getContactList() 
  {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200);
	  
  }
  
  @Test(enabled = false, description = "Getting Specific Contact details")
  public void getSpecificContact() 
  {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/615dd1a5f2967f0ec893abd8")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200);
	  
  }
  
  //Getting the response time just after the get function, created a variable for that because it will return a value which needs to be stored somewhere
  @Test(enabled = false, description = "Getting Response Time")
  public void getResponseTime() 
  {
	  Response rs = given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/615dd1a5f2967f0ec893abd8");
	  
	  System.out.println(rs.getTime());
	  
	  rs.then()
	  	.log()
	  	.body()
	  	.statusCode(200);
	  
  }
  
  //Sending details and that is done through JSON so in program we wrote JSON codes
  //POST Method
  @Test(enabled = true, description = "Adding new Contact details")
  public void addContact() 
  {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  details.put("firstName", "Radhika");
	  details.put("lastName", "Kedia");
	  details.put("email", "abc@gmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  
	  emp.put("jobTitle", "Trainee");
	  emp.put("company", "LTI");
	  
	  
	  ExtractableResponse<Response> rs = given()     //Extracting multiple values using this variable
	  	.header("Content-Type","application/json")
	  	.body(details.toJSONString())
	  .when()
	  	.post("http://3.13.86.142:3000/contacts")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200)
	  	.extract();
	  	//.path("_id")   //Extracting from this, value is stored in this variable
	  	//.path("firstName")    //Can only extract one value at a time this way
	  
	  id = rs.path("_id");  //So that it can be used for update and delete
	  
	  System.out.println(rs.path("_id"));
	  System.out.println(rs.path("firstName"));
	  System.out.println(rs.path("lastName"));
	  System.out.println(rs.path("employer.jobTitle"));
	  
  }
  
  @Test(enabled = false, dependsOnMethods = "addContact", description = "Updating specific Contact")
  public void updateContact() 
  {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  details.put("firstName", "Radhika");
	  details.put("lastName", "Kedia");
	  details.put("email", "abc@lti.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  
	  emp.put("jobTitle", "GET");
	  emp.put("company", "LTI");
	  
	  
	  given()     //Extracting multiple values using this variable
	  	.header("Content-Type","application/json")
	  	.body(details.toJSONString())
	  .when()
	  	.put("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(204);    //Code is different for update
	  	
  }
  
  //To verify if its updated successfully
  @Test(enabled = false, dependsOnMethods = "updateContact",  description = "Getting Specific Contact details")
  public void getSpecificContact2() 
  {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200);
	  
  }
  
  @Test(enabled = true, dependsOnMethods = "addContact", description = "Deleting specific Contact")
  public void delContact() 
  {
	  
	  given()     
	  .when()
	  	.delete("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(204);    //Code is same for update & delete
	  	
  }
}
