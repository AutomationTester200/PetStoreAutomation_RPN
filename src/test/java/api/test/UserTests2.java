package api.test;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	User userPayload;
	Faker faker;
	public Logger logger;
	@BeforeClass
	public void setup() {
		 userPayload = new User();
		 faker= new Faker();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test (priority=1)
	public void testPostUser() {
		logger.info("------- creating user --------");
		System.out.println("testPostUser");
		Response response = UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("------- user created --------");
		
	}
	
	
	@Test(priority = 2)
	public void testGetUser() {
		logger.info("------- reading user details --------");
		System.out.println("testGetUser");
		Response response = UserEndpoints2.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("------- read user details --------");
	}
	
	@Test (priority =3)
	public void testUpdateUser()
	{
		logger.info("------- updating user details --------");
		System.out.println("testUpdateUser");
		this.userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);

		logger.info("------- updated user details --------");
	}
	
	@Test(priority =4)
	public void testDeleteUser()
	{
		logger.info("------- deleting user details --------");
		System.out.println("testDeleteUser");
		Response response = UserEndpoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("------- deleted user details --------");
		logger.debug("debugging...");
	}
}
