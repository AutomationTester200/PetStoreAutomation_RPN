package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;


import api.payload.*;

public class UserEndpoints2 {
	
	public static ResourceBundle getURLfromPropertiesFile()
	{
		ResourceBundle routes= ResourceBundle.getBundle("routes");//loads properties file
		return routes;
	}
	public static Response createUser(User userPayload)
	{
		String post_url=getURLfromPropertiesFile().getString("post_url");
		Response response =	
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(userPayload)
		.when()
			.post(post_url);
		return response;
	}

	public static Response getUser(String userName)
	{
		String get_url=getURLfromPropertiesFile().getString("get_url");
		Response response =given()
		.pathParam("username", userName)
		
		.when()
		.get(get_url);
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String update_url = getURLfromPropertiesFile().getString("update_url");
		Response response=
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when()
				.put(update_url);
		
		
		return response;		
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url=getURLfromPropertiesFile().getString("delete_url");
		Response response =given()
		.pathParam("username", userName)
		
		.when()
		.delete(delete_url);
		return response;
	}


}
