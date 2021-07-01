// ajax로 외부서버의 RESTFUL API를 호출하게되면 보안때문에 오류가 발생하게 된다.
// 이를 피하기 위해서는 Java 로 서블릿을 만든 후 Javascript 에서 호출 하는 방법을 사용 하면 된다.
// 다른 언어를 사용 하게 되면(예: php) 해당언어로 Rest API 용 Method 를 만든 후 Javascript 에서
// 호출 하는 방법을 사용 하면 될듯.

import java.io.*;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JavaRestPost { 
	public static void main(String[] args) { 
		try { 
			String data = "{\"Alias\" : \"normal\", \"Plain\" : \"1234567890123\"}";
			String strUrl = "http://127.0.0.1:8080/encrypt";
			String encJsonString = http_post(strUrl, data );

			JSONParser jsonParser = new JSONParser();
			JSONObject json = (JSONObject) jsonParser.parse( encJsonString );
			System.out.println( "Cipher : " + json.get("Cipher") );

			data   = "{\"Alias\" : \"normal\", \"Cipher\" : \"" + json.get("Cipher") +  " \"}";
			strUrl = "http://127.0.0.1:8080/decrypt";
			String decJsonString = http_post(strUrl, data );

			json = (JSONObject) jsonParser.parse( decJsonString );
			System.out.println( "Plain : " + json.get("Plain") );

		} catch(Exception e) { 
			e.printStackTrace(); 
		}
	} 

	static String http_post( String strUrl, String strData)
	{
		String strRes = "";
		BufferedReader in = null; 

		try {
			URL obj = new URL(strUrl); // 호출할 url 
			HttpURLConnection con = (HttpURLConnection)obj.openConnection(); 
			
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);

			con.setRequestMethod("POST"); 
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("accept", "application/json");
			
			DataOutputStream osw = null;
			osw = new DataOutputStream(con.getOutputStream());
			osw.writeBytes(strData);
			osw.flush();
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
			
			StringBuilder sb = new StringBuilder();
			String line; 
			
			while((line = in.readLine()) != null) { // response를 차례대로 출력  
				sb.append(line);
			} 
			strRes = sb.toString();
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally { 
			if(in != null) 
			{
				try { 
					in.close(); 
				} catch(Exception e) { 
					e.printStackTrace(); 
				} 
			}
		} 

		return strRes;
	}
}

/**
 * compile : javac -encoding UTF-8 -cp .;json-simple-1.1.1.jar JavaRestPost.java
 * run     : java -cp .;json-simple-1.1.1.jar JavaRestPost
 *
 * remark  : 한글 전달 문제는 조금 더 확인이 필요함. 
 *         : json 파싱을 위하여 json-simple-1.1.1.jar 를 사용 하였다.
 */
