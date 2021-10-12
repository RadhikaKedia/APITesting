package RestAssured;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GithubRepository 
{
	//Rather than repeating it everytime, just mentioned in this
	@BeforeTest
	public void beforeTest()
	{
		baseURI = "https://api.github.com/user/repos";
		authentication = oauth2("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");
				
	}
	
  //Getting all the available repository from our account
  @Test(enabled = false)
  public void getAllRepo() 
  {
	  //Since nothing is mentioned in given(), you can remove it
	  /*given()
	  		.auth()
	  		.oauth2("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")   //Open authentication using token
	  		*/
	  when()
	  		.get()      //Either you can mention baseURI variable or keep it empty, works in both ways
	  		
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
  

//It will work only once because on github you can't create repository with same name
  @Test(enabled = true)
  public void createRepository() 
  {
	  JSONObject data = new JSONObject();
	  data.put("name", "RestAssured");
	  data.put("description", "Made using RestAssured Tool");
	  data.put("homepage", "https://github.com/RadhikaKedia");
	  
	  given()
	  		.header("Content-Type","application/json")    //This header is fixed whenever you send JSON file
		  	.body(data.toJSONString())
	  .when()
	  		.post()
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(201)    //This code is for creating new resource
	  		.time(Matchers.lessThan(1000L),TimeUnit.SECONDS);     //To check time. L is for long
	  //It will fail because creation takes time and it doesn't complete in 1000Miliseconds
  }
}
