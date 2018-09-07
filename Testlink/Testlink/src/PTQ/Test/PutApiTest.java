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

public class PutApiTest extends TestBase {
	
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeClass
	public void setUp() {
		testBase = new TestBase();
		host = prop.getProperty("HOST");
		url = host + "/api/users/2";
		
	}
	
	@Test
	public void putTest() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //杩欎釜鍦╬ostman涓彲浠ユ煡璇㈠埌
		
		//瀵硅薄杞崲鎴怞son瀛楃涓�
		Users user = new Users("Anthony","automation tester");
		String userJsonString = JSON.toJSONString(user);
		//System.out.println(userJsonString);
		
		closeableHttpResponse = restClient.put(url, userJsonString, headermap);
		
		//楠岃瘉鐘舵�佺爜鏄笉鏄�200
		int statusCode = restClient.getStatusCode(closeableHttpResponse);
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200,"response status code is not 200");
		
		//楠岃瘉鍚嶇О涓篈nthony鐨刯on鏄笉鏄� automation tester
		JSONObject responseJson = restClient.getResponseJson(closeableHttpResponse);
		String jobString = TestUtil.getValueByJPath(responseJson, "job");
		System.out.println(jobString);
		Assert.assertEquals(jobString, "automation tester","job is not same");
	}

}
