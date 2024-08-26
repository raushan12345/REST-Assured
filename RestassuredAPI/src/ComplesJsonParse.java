import File.payload;
import io.restassured.path.json.JsonPath;

public class ComplesJsonParse {
	
	public static void main(String[] args) {
		
		JsonPath js= new JsonPath(payload.CoursePrice()); 
		
		//1.Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//2.print purchase amount
		int totalamount= js.getInt("dashboard.purchaseAmount");
		System.out.println(totalamount);
		
		//3.print title of the first course
		String titleFirstCourse=js.get("courses[0].title");
		System.out.println(titleFirstCourse);
		  
		//Print All course titles and their respective Prices
		for(int i=0;i<count;i++)
		{
			String courseTitles=js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			  
			System.out.println(courseTitles);
			  
		}
		
		  //Print no of copies sold by RPA Course
		  
		 System.out.println("Print no of copies sold by RPA Course");
		 
		 for(int i=0;i<count;i++)
		 {
			  String courseTitles=js.get("courses["+i+"].title");
			  if(courseTitles.equalsIgnoreCase("RPA"))
			  {
				  int copies=js.get("courses["+i+"].copies");
				  System.out.println(copies);
				  break;
			  }
			
			  
		 }
		
		
	}

}
