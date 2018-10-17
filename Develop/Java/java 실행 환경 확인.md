###  JAVA 실행 환경 확인

---

가끔 java 가 어떤 환경에서 실행 되는지 확인 해야 할 때가 있습니다.

신규로 .jar 파일을 가져 왔는데 어떤 directory 에 복사 해야 하는지, 또는 JNI 를 이용 하는데 .so, .dll 파일을 어디에 복사 해야 하는지 등등 의 사유로 실행 환경을 조사 할 필요가 있을때 사용 하는 source 입니다.



[jsp 소스]


	<%-- GetEnv.jsp --%>
	<%@ page contentType="text/html; charset=euc-kr" %>
	<%@ page import="java.util.*" %>
	<%@ page import="java.util.Properties" %>
	<%@ page import="java.util.Enumeration" %>
	
	<%
		try
	    {
	      Properties props = System.getProperties();
	      for(Enumeration en = props.propertyNames(); en.hasMoreElements();)
	      {
	        String key = (String)en.nextElement();
	        String value = props.getProperty(key);
	        if ( key.equals("java.runtime.version") ||
	             key.equals("os.name" ) ||
	             key.equals("java.class.path" ) ||
	             key.equals("sun.arch.data.model" ) ||
	             key.equals("java.home" ) ||
	             key.equals("java.ext.dirs" ) ||
	             key.equals("java.library.path" ))
	            out.println("<b> <font color=\"#3058d2\">" + key + " = " + value +"</font> </b> <br>");
	        else
	            out.println(key + " = " + value +"<br>");
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace(System.out);
	    }
	%>



[Java 용 소스]


	// GetEnv.java
	import java.util.*;
	import java.util.Properties;
	import java.util.Enumeration;
	
	class JavaGetEnv
	{
	  public static void main(String[] args) 
	  {
		try
		{
		  Properties props = System.getProperties();
		  for(Enumeration en = props.propertyNames(); en.hasMoreElements();) 
		  {
			String key = (String)en.nextElement();
			String value = props.getProperty(key);
			System.out.println(key + " = " + value );
		  }       
		}
		catch (Exception e)
		{
		  e.printStackTrace(System.out);
		}
	  }
	}
