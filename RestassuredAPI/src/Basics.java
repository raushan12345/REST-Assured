import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import File.ReUsableMethods;
import File.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//Validate if add place API is working as expected
		
		//given - all input details
		//when - Submit the API
		//Then - validate the responce
		//content of the file to String-> content of file convert into Byte->Byte data to String
		
		RestAssured.baseURI= "https://rahulshettyacademy.com/";
		String responce=given().log().all().queryParam("key", "qaclick123").header("contnet-type", "application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		System.out.println(responce);
		JsonPath js=new JsonPath(responce); //for parsing Json
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//Update Place
		
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get place
		
		String getPlaceResponse= given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress =js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
		//Cucumber Junit, Testng
		
		
		
	}

}
