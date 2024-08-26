import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI= "https://kraushan682.atlassian.net/";
		
		String createissueresponce = given()
		.header("Content-Type", "application/json")
		.header("Authorization", "Basic a3JhdXNoYW42ODJAZ21haWwuY29tOkFUQVRUM3hGZkdGMDZGT09vUGJ3dUFpWC1rY2FZVENySnlseGVXZnpfeWdMWllBU1BkWS1NODFOcUI3clotYXJfTE52VzFRZDdXV1dhOGhLV3B0ZkZUMnlfS19kTlJwM1REdGNOYjlYNjlCMzRPb0xURUJOV0s5bXN0VW9CbFVuellyQVBCdHF1LVBWZV9ZN1hwOFpYNVVRNExBLWg5dm96Y3RLcFp0TmxVMzFYRjljVWNmUk0xcz02OTdBQUI1NQ==")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Check box are not working-- automation\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all()
		.post("rest/api/3/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(createissueresponce);
		String issueId = js.getString("id");
		System.out.println(issueId);
		
		given()
		.pathParam("key", issueId)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic a3JhdXNoYW42ODJAZ21haWwuY29tOkFUQVRUM3hGZkdGMDZGT09vUGJ3dUFpWC1rY2FZVENySnlseGVXZnpfeWdMWllBU1BkWS1NODFOcUI3clotYXJfTE52VzFRZDdXV1dhOGhLV3B0ZkZUMnlfS19kTlJwM1REdGNOYjlYNjlCMzRPb0xURUJOV0s5bXN0VW9CbFVuellyQVBCdHF1LVBWZV9ZN1hwOFpYNVVRNExBLWg5dm96Y3RLcFp0TmxVMzFYRjljVWNmUk0xcz02OTdBQUI1NQ")
		.multiPart("file",new File("\\Users\\raukumar3\\Desktop\\ANNOUNCEMENTS\\abc.jpeg")).log().all()
		.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		
		

	
	}

}
