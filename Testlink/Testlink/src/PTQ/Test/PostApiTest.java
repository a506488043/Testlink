package PTQ.Test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import PTQ.base.TestBase;
import PTQ.data.Users;
import PTQ.restclient.RestClient;
import PTQ.util.TestUtil;

public class PostApiTest extends TestBase {
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeClass
	public void setUp() {
		testBase = new TestBase();
		host = prop.getProperty("HOST");
		url = host + "/api/users";
		
	}
	
	@Test
	public void postApiTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		//鍑嗗璇锋眰澶翠俊鎭�
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //杩欎釜鍦╬ostman涓彲浠ユ煡璇㈠埌
		
		//瀵硅薄杞崲鎴怞son瀛楃涓�
		Users user = new Users("Anthony","tester");
		String userJsonString = JSON.toJSONString(user);
		//System.out.println(userJsonString);
		
		closeableHttpResponse = restClient.post(url, userJsonString, headermap);
		
		//楠岃瘉鐘舵�佺爜鏄笉鏄�201
		int statusCode = restClient.getStatusCode(closeableHttpResponse);
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_201,"status code is not 200");
		
		//鏂█鍝嶅簲json鍐呭涓璶ame鍜宩ob鏄笉鏄湡寰呯粨鏋�
		JSONObject responseJson = restClient.getResponseJson(closeableHttpResponse);
		//System.out.println(responseString);
		String name = TestUtil.getValueByJPath(responseJson, "name");
		String job = TestUtil.getValueByJPath(responseJson, "job");
		Assert.assertEquals(name, "Anthony","name is not same");
		Assert.assertEquals(job, "tester","job is not same");
		
	}

}
