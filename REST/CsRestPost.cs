using System;  
using System.IO;  
using System.Net;  
using System.Text;
using System.Web.Script.Serialization;

namespace JconPost
{
    class Program
    {
        struct getData {
            public string Value {get; set;}
        }

        static void Main(string[] args)
        {
            Post("http://127.0.0.1:8080/encrypt", "{\"Alias\" : \"normal\", \"Plain\" : \"1234567890123\"}");
            Post("http://127.0.0.1:8080/decrypt", "{\"Alias\" : \"normal\", \"Cipher\" : \"AAF46P2Fcv1zhJOe++Ytv/VY\"}");
        }

        private static string Post( string url, string data )
        {
            string strRes = "";

            byte[] byteArray = Encoding.UTF8.GetBytes (data); 

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.ContentType = "application/json";
            request.Method = "POST";
            request.KeepAlive = true;
            request.ContentLength = byteArray.Length; 

            Stream st = request.GetRequestStream();
            st.Write(byteArray, 0, byteArray.Length);
            st.Close();

            WebResponse response = request.GetResponse ();  
            Console.WriteLine (((HttpWebResponse)response).StatusDescription);  
            st = response.GetResponseStream ();  

            StreamReader reader = new StreamReader (st);  
            string responseFromServer = reader.ReadToEnd ();  
            Console.WriteLine (responseFromServer);
            
            // Clean up the streams.  
            reader.Close ();  
            st.Close ();  
            response.Close (); 

            return strRes;
        }
    }
}

/*
 compile: csc CsRestPost.cs
*/