package PTQ.Test;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PTQ.base.TestBase;
import PTQ.restclient.RestClient;


public class DeleteApiTest extends TestBase {
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeClass
	public void setUp() {
		testBase = new TestBase();
		host = prop.getProperty("HOST");
		url = host + "/api/users/2";  //直接在这个网站可以找到delete的api
		
	}
	
	@Test
	public void deleteApiTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.delete(url);
		
		int statusCode = restClient.getStatusCode(closeableHttpResponse);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_204,"status code is not 204");
	}
}
