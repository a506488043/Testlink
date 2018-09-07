package PTQ.restclient;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RestClient {
	
	final static Logger Log = Logger.getLogger(RestClient.class);
	
	/**
	 * 涓嶅甫璇锋眰澶寸殑get鏂规硶灏佽
	 * @param url
	 * @return 杩斿洖鍝嶅簲瀵硅薄
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse get (String url) throws ClientProtocolException, IOException {
		
		//鍒涘缓涓�涓彲鍏抽棴鐨凥ttpClient瀵硅薄
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//鍒涘缓涓�涓狧ttpGet鐨勮姹傚璞�
		HttpGet httpget = new HttpGet(url);
		//鎵ц璇锋眰,鐩稿綋浜巔ostman涓婄偣鍑诲彂閫佹寜閽紝鐒跺悗璧嬪�肩粰HttpResponse瀵硅薄鎺ユ敹
		Log.info("寮�濮嬪彂閫乬et璇锋眰...");
		CloseableHttpResponse httpResponse = httpclient.execute(httpget);
		Log.info("鍙戦�佽姹傛垚鍔燂紒寮�濮嬪緱鍒板搷搴斿璞°��");
		return httpResponse;
	}
	
	/**
	 * 甯﹁姹傚ご淇℃伅鐨刧et鏂规硶
	 * @param url
	 * @param headermap锛岄敭鍊煎褰㈠紡
	 * @return 杩斿洖鍝嶅簲瀵硅薄
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse get (String url,HashMap<String,String> headermap) throws ClientProtocolException, IOException {
			
		//鍒涘缓涓�涓彲鍏抽棴鐨凥ttpClient瀵硅薄
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//鍒涘缓涓�涓狧ttpGet鐨勮姹傚璞�
		HttpGet httpget = new HttpGet(url);
		//鍔犺浇璇锋眰澶村埌httpget瀵硅薄
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		//鎵ц璇锋眰,鐩稿綋浜巔ostman涓婄偣鍑诲彂閫佹寜閽紝鐒跺悗璧嬪�肩粰HttpResponse瀵硅薄鎺ユ敹
		CloseableHttpResponse httpResponse = httpclient.execute(httpget);
		Log.info("寮�濮嬪彂閫佸甫璇锋眰澶寸殑get璇锋眰...");	
		return httpResponse;
	}
	
	/**
	 * 灏佽post鏂规硶
	 * @param url
	 * @param entityString锛屽叾瀹炲氨鏄缃姹俲son鍙傛暟
	 * @param headermap锛屽甫璇锋眰澶�
	 * @return 杩斿洖鍝嶅簲瀵硅薄
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse post (String url, String entityString, HashMap<String,String> headermap) throws ClientProtocolException, IOException {
		//鍒涘缓涓�涓彲鍏抽棴鐨凥ttpClient瀵硅薄
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//鍒涘缓涓�涓狧ttpPost鐨勮姹傚璞�
		HttpPost httppost = new HttpPost(url);
		//璁剧疆payload
		httppost.setEntity(new StringEntity(entityString));
		
		//鍔犺浇璇锋眰澶村埌httppost瀵硅薄
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		//鍙戦�乸ost璇锋眰
		CloseableHttpResponse httpResponse = httpclient.execute(httppost);
		Log.info("寮�濮嬪彂閫乸ost璇锋眰");
		return httpResponse;
	}
	
	/**
	 * 灏佽 put璇锋眰鏂规硶锛屽弬鏁板拰post鏂规硶涓�鏍�
	 * @param url
	 * @param entityString锛岃繖涓富瑕佹槸璁剧疆payload,涓�鑸潵璇村氨鏄痡son涓�
	 * @param headerMap锛屽甫璇锋眰鐨勫ご淇℃伅锛屾牸寮忔槸閿�煎锛屾墍浠ヨ繖閲屼娇鐢╤ashmap
	 * @return 杩斿洖鍝嶅簲瀵硅薄
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse put (String url, String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpput = new HttpPut(url);
		httpput.setEntity(new StringEntity(entityString));
	
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpput.addHeader(entry.getKey(), entry.getValue());
		}
		//鍙戦�乸ut璇锋眰
		CloseableHttpResponse httpResponse = httpclient.execute(httpput);
		return httpResponse;
	}
	
	/**
	 * 灏佽 delete璇锋眰鏂规硶锛屽弬鏁板拰get鏂规硶涓�鏍�
	 * @param url锛� 鎺ュ彛url瀹屾暣鍦板潃
	 * @return锛岃繑鍥炰竴涓猺esponse瀵硅薄锛屾柟渚胯繘琛屽緱鍒扮姸鎬佺爜鍜宩son瑙ｆ瀽鍔ㄤ綔
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse delete (String url) throws ClientProtocolException, IOException {
			
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpDelete httpdel = new HttpDelete(url);
		
		//鍙戦�乨elete璇锋眰
		CloseableHttpResponse httpResponse = httpclient.execute(httpdel);
		return httpResponse;
	}
	
	/**
	 * 鑾峰彇鍝嶅簲鐘舵�佺爜锛屽父鐢ㄦ潵鍜孴estBase涓畾涔夌殑鐘舵�佺爜甯搁噺鍘绘祴璇曟柇瑷�浣跨敤
	 * @param response
	 * @return 杩斿洖int绫诲瀷鐘舵�佺爜
	 */
	public int getStatusCode (CloseableHttpResponse response) {
		
		int statusCode = response.getStatusLine().getStatusCode();
		Log.info("瑙ｆ瀽锛屽緱鍒板搷搴旂姸鎬佺爜:"+ statusCode);
		return statusCode;
		
	}
	
	/**
	 * 
	 * @param response, 浠讳綍璇锋眰杩斿洖杩斿洖鐨勫搷搴斿璞�
	 * @return锛� 杩斿洖鍝嶅簲浣撶殑json鏍煎紡瀵硅薄锛屾柟渚挎帴涓嬫潵瀵笿SON瀵硅薄鍐呭瑙ｆ瀽
	 * 鎺ヤ笅鏉ワ紝涓�鑸細缁х画璋冪敤TestUtil绫讳笅鐨刯son瑙ｆ瀽鏂规硶寰楀埌鏌愪竴涓猨son瀵硅薄鐨勫��
	 * @throws ParseException
	 * @throws IOException
	 */
	public JSONObject getResponseJson (CloseableHttpResponse response) throws ParseException, IOException {
		Log.info("寰楀埌鍝嶅簲瀵硅薄鐨凷tring鏍煎紡");
		String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject responseJson = JSON.parseObject(responseString);
		Log.info("杩斿洖鍝嶅簲鍐呭鐨凧SON鏍煎紡");
		return responseJson;
	}
}
