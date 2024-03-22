package api.test;



import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String UserName, String firstName, String lastName, String email,
			String password, String phone) {
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setUsername(UserName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response = UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	//@Test(priority=2,dataprovider)

	
	
//	@Test(priority=3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
//	public void testUpdateUser(String username)
//	{
//		User userpayload = new User();
//		userpayload.setFirstName("Geeta");
//		userpayload.setLastName("Bhatia");
//		userpayload.setEmail("GeetaBhatia@gmail.com");
//		Response response = UserEndpoints.updateUser(username, userpayload);
//		Assert.assertEquals(response.getStatusCode(), 200);
//	}
	
	
	
	@Test(priority=4, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String username)
	{
		Response response = UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}


