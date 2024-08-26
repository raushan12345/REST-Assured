package File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider = "BooksData")
	public void addbook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().header("Conent-Type", "application/json").
		body(payload.Addbook(isbn,aisle)).
		when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js= ReUsableMethods.rawToJson(response);
		String id=js.get("ID");
		System.out.println(id);
		
		//deleteBook

	}

	@DataProvider(name="BooksData")

	public Object[][]  getData()
	{

		//array=collection of elements
		//multidimensional array= collection of arrays
		return new Object[][] {{"test1","1111"},{"test2","2222"}, {"test3","3333"} };

	}

}
	
	

