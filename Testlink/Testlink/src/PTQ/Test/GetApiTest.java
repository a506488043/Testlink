package PTQ.Test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;

import PTQ.base.TestBase;
import PTQ.restclient.RestClient;
import PTQ.util.TestUtil;

public class GetApiTest extends TestBase {
	
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	final static Logger Log = Logger.getLogger(GetApiTest.class);
	
	@BeforeClass
	public void setUp() {
		
		testBase = new TestBase();
		//Log.info("娴嬭瘯鏈嶅姟鍣ㄥ湴鍧�涓猴細"+ host.toString());
		host = prop.getProperty("HOST");
		//Log.info("褰撳墠娴嬭瘯鎺ュ彛鐨勫畬鏁村湴鍧�涓猴細"+url.toString());
		url = host + "/api/users?page=2";
		
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		Log.info("寮�濮嬫墽琛岀敤渚�...");
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		//鏂█鐘舵�佺爜鏄笉鏄�200
		Log.info("娴嬭瘯鍝嶅簲鐘舵�佺爜鏄惁鏄�200");
		int statusCode = restClient.getStatusCode(closeableHttpResponse);
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "response status code is not 200");
		
        JSONObject responseJson = restClient.getResponseJson(closeableHttpResponse);
        //System.out.println("respon json from API-->" + responseJson); 
        
        //json鍐呭瑙ｆ瀽
        String s = TestUtil.getValueByJPath(responseJson,"data[0]/first_name");
        Log.info("鎵цJSON瑙ｆ瀽锛岃В鏋愮殑鍐呭鏄� " + s);
        //System.out.println(s);
        Log.info("鎺ュ彛鍐呭鍝嶅簲鏂█");
        Assert.assertEquals(s, "Eve","first name is not Eve");
        Log.info("鐢ㄤ緥鎵ц缁撴潫...");
	}
	
	
}
