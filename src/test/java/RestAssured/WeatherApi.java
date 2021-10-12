package RestAssured;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherApi 
{
  @Test(enabled = false, description = "Getting weather information of Specific City")
  public void getWeather() 
  {
	  RestAssured.given()     //Some Pre-condition like Authentication
	  			 .when()                 //Performs some steps
	  				.get("https://api.openweathermap.org/data/2.5/weather?q=Mira-Bhayander&appid=b5240ae203b9f253ffcd6d1e3e63e94a")
	  			 .then()       //Some Post condition like Verification
	  			 	.log()    //Prints data in console
	  			 	.body()
	  			 	.statusCode(200);
	  
	  
  }
  
  //Different ways of doing the same thing
  @Test(enabled = false, description = "Getting weather information of Specific City")
  public void getWeather2() 
  {
	  Response rs = RestAssured.given()     //Some Pre-condition like Authentication
	  			 .when()                 //Performs some steps
	  				.get("https://api.openweathermap.org/data/2.5/weather?q=Mira-Bhayander&appid=b5240ae203b9f253ffcd6d1e3e63e94a");
	  
	  System.out.println(rs.prettyPrint());
	  System.out.println(rs.getTime());
	  System.out.println(rs.getStatusCode());
	  System.out.println(rs.getContentType());
	  			 
	  Assert.assertEquals(rs.getStatusCode(), 200);      //Checking with help of TestNG Assertion
	  
  }
  
  //Using query
  @Test(enabled = false, description = "Getting weather information of Specific City")
  public void getWeather3() 
  {
	  RestAssured.given()     //Some Pre-condition like Authentication
			  .queryParam("q", "Mumbai")
			  .queryParam("appid", "b5240ae203b9f253ffcd6d1e3e63e94a")
	  			 .when()                 //Performs some steps
	  				.get("https://api.openweathermap.org/data/2.5/weather")
	  			.then()       //Some Post condition like Verification
	  				.log()    //Prints data in console
	  				.body()
	  				.statusCode(200);
  }
  
  //Using Map
  @Test(enabled = true, description = "Getting weather information of Specific City")
  public void getWeather4() 
  {
	  Map<String, String> param = new HashMap<String, String>();
	  param.put("q", "Jaipur");
	  param.put("appid", "b5240ae203b9f253ffcd6d1e3e63e94a");
	  RestAssured.given()     //Some Pre-condition like Authentication
			  		.queryParams(param)
	  			 .when()                 //Performs some steps
	  				.get("https://api.openweathermap.org/data/2.5/weather")
				 .then()       //Some Post condition like Verification
				 	.log()    //Prints data in console
				 	.body()
				 	.statusCode(200);
	  
  }
}
