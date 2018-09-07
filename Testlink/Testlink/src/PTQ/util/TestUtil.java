package PTQ.util;

import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import PTQ.base.TestBase;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;

public class TestUtil {
	public static TestLinkAPI connect() {
		TestBase base = new TestBase();
		String url = base.prop.getProperty("TestLinkUrl");
	    String devKey = base.prop.getProperty("DEVKEY");
	    TestLinkAPI api = null;
	     
	    URL testlinkURL = null;
	     
	    try {
	            testlinkURL = new URL(url);
	    } catch ( MalformedURLException mue )   {
	            mue.printStackTrace( System.err );
	            System.exit(-1);
	    }
	     
	    try {
	    	
	           api = new TestLinkAPI(testlinkURL, devKey);
	           
	    } catch( Exception te) {
	            te.printStackTrace( System.err );
	            System.exit(-1);
	    }
	     
	    System.out.println(api.ping());
		return api;
	}
	
	/**
	 * 
	 * @param responseJson ,这个变量是拿到响应字符串通过json转换成json对象
	 * @param jpath,这个jpath指的是用户想要查询json对象的值的路径写法
	 * jpath写法举例：1) per_page  2) data[1]/first_name ，data是一个json数组，[1]表示索引
	 * /first_name 表示data数组下某一个元素下的json对象的名称为first_name
	 * @return， 返回first_name这个json对象名称对应的值
	 */
	
	//1 json解析方法
	public static String getValueByJPath(JSONObject responseJson, String jpath) {
		
		Object obj = responseJson;
		for(String s : jpath.split("/")) {
			if(!s.isEmpty()) {
				if(!(s.contains("[") || s.contains("]"))) {
					obj = ((JSONObject) obj).get(s);
				}else if(s.contains("[") || s.contains("]")) {
					obj = ((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replaceAll("]", "")));
				}
			}
		}
		return obj.toString();
	}

}
