package TC;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;











import java.io.*;

import java.io.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class start 
{
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	public static void main(String[] args) throws JSONException, IOException, InterruptedException 
	{
		TeamController tc = new TeamController("");
		tc.Run();
	}
}
		
		/* HttpClient httpClient = new DefaultHttpClient();
		   
		 //try {
		      // this twitter call returns json results.
		      // see this page for more info: <a href="https://dev.twitter.com/docs/using-search" title="https://dev.twitter.com/docs/using-search">https://dev.twitter.com/docs/using-search</a>
		      // <a href="http://search.twitter.com/search.json?q=%40apple" title="http://search.twitter.com/search.json?q=%40apple">http://search.twitter.com/search.json?q=%40apple</a>
		 
		      // Example URL 1: this yahoo weather call returns results as an rss (xml) feed
		      //HttpGet httpGetRequest = new HttpGet("http://weather.yahooapis.com/forecastrss?p=80020&u=f");
		       
		      // Example URL 2: this twitter api call returns results in a JSON format
		 
		      HttpGet httpGetRequest = new HttpGet("http://search.twitter.com/search.json?q=%40apple");
		 
		      // Execute HTTP request
		      HttpResponse httpResponse = httpClient.execute(httpGetRequest);
		 
		      System.out.println("----------------------------------------");
		      System.out.println(httpResponse.getStatusLine());
		      System.out.println("----------------------------------------");
		
		      
		      HttpEntity entity = httpResponse.getEntity();
		      
		      // If the response does not enclose an entity, there is no need
		      // to bother about connection release
		      byte[] buffer = new byte[1024];
		      if (entity != null) 
		      {
		        InputStream inputStream = entity.getContent();
		        //try 
		        {
		          int bytesRead = 0;
		          BufferedInputStream bis = new BufferedInputStream(inputStream);
		          while ((bytesRead = bis.read(buffer)) != -1) 
		          {
		            String chunk = new String(buffer, 0, bytesRead);
		            System.out.println(chunk);
		          }
		        }
		      }
		        
		      
		
//		String request = "http://api.search.yahoo.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=umbrella&results=10";
//		
//		HttpClient client = new HttpClient();
//
//        GetMethod method = new GetMethod(request);


		// Send GET request
//
//        int statusCode = client.executeMethod(method);
		
		
		
//		CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials(
//                new AuthScope("192.168.100.5", 8080),null);
//       
//        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
//                .setDefaultCredentialsProvider(credsProvider)
//                .build();
//        try {
//            httpclient.start();
//            HttpHost proxy = new HttpHost("192.168.100.5", 8080);
//            RequestConfig config = RequestConfig.custom()
//                    .setProxy(proxy)
//                    .build();
//            HttpGet httpget = new HttpGet("https://issues.apache.org/");
//            httpget.setConfig(config);
//            Future<HttpResponse> future = httpclient.execute(httpget, null);
//            HttpResponse response = future.get();
//            System.out.println("Response: " + response.getStatusLine());
//            System.out.println("Shutting down");
//        } 
//        catch(Exception ex)
//        {
//        	ex.printStackTrace();
//        	
//        }
//        finally
//        {
//            httpclient.close();
//        }
//		
		

//		  CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
//	        //try {
//	            // Start the client
//	            httpclient.start();
//
//	            // Execute request
//	            final HttpGet request1 = new HttpGet("http://www.apache.org/");
//	            Future<HttpResponse> future = httpclient.execute(request1, null);
//	            // and wait until response is received
//	            HttpResponse response1 = future.get();
//	            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());

		
//				HttpClient httpClient = new DefaultHttpClient();
//	    //try 
//	    {
//	      HttpGet httpGetRequest = new HttpGet("https://public.opencpu.org/ocpu/library/");
//	 
//	      // Execute HTTP request
//	      HttpResponse httpResponse = httpClient.execute(httpGetRequest);
//	 
//	      System.out.println("----------------------------------------");
//	      System.out.println(httpResponse.getStatusLine());
//	     
//	      System.out.println("----------------------------------------");
//	    }

//		Date startTask = new  Date();
//		
//		Thread.sleep(1000);
//		
//		Date endTask = new Date();
//		
//		
//		long diff = (endTask.getTime() - startTask.getTime()) / 1000;
//
//		
//		System.out.print(diff);
//		
//		endTask.
//		
//		Date result = endTask - startTask;
		
		
		
//		JsonHelper helpME = new JsonHelper("D:/drop/Dropbox/TeamControler/TC/test/map/2ndFloor-rooms.roson");
//
//		MazeRobot  robocik = new MazeRobot("1", new Point3D(99, 99, 0));
//		
//		helpME.AddRobot(robocik);
//		
//		
//		robocik.Position.x =
//		robocik.Position.y =
//		robocik.Position.z = 777;
//		
//		helpME.UpdateRobot(robocik);
//		
//		
//		String s = helpME.GetCurrentJSON();
//		
//		Path file2 = Paths.get("D:/drop/Dropbox/TeamControler/TC/test/map/testowy12.roson");
//		
//		byte[] ss = s.getBytes();
//		
//		Files.write(file2,ss,StandardOpenOption.CREATE_NEW);
		
		
		//Path file = Paths.get("C:/Users/szsz/Documents/Dropbox/TeamControler/TC/test/map/2ndFloor-rooms.roson");
		
//		Path file = Paths.get("D:/drop/Dropbox/TeamControler/TC/test/map/2ndFloor-rooms.roson"); 
//		
//		//Path file = Paths.get(fileName); 
//		String jsonFile = new String(Files.readAllBytes(file));
//		JSONObject obj = new JSONObject(jsonFile);	
//
//		JSONArray walls =  obj.getJSONArray("robots");
//	
//		
//		JSONObject obiekt = new JSONObject();
//		obiekt.put("type", "robot");
//		obiekt.put("id", 17);
//		
//		walls.put(obiekt);
//		
//	    String tekst = obj.toString();
//				
//		walls =  obj.getJSONArray("robots");
//		
//		JSONObject  ooo = walls.getJSONObject(0);
//		 
//		ooo.put("type", "robot1");
//		ooo.put("id", 18);
//		
//     	tekst = obj.toString();
//		
//		byte t[] =  tekst.getBytes();
//			
//		Path file2 = Paths.get("D:/drop/Dropbox/TeamControler/TC/test/map/testowy11.roson");
//				
//		Files.write(file2,t,StandardOpenOption.CREATE_NEW);
		
		
			
//			 String jsonText = "{\"first\": 123, \"second\": [{\"k1\":{\"id\":\"id1\"}}, 4, 5, 6, {\"id\": 123}], \"third\": 789, \"id\": null}";
//			  JSONParser parser = new JSONParser();
//			  KeyFinder finder = new  KeyFinder();
//			  finder.setMatchKey("second");
//			  try{
//			    while(!finder.isEnd()){
//			      parser.parse(jsonText, finder, true);
//			      if(finder.isFound()){
//			        finder.setFound(false);
//			        System.out.println("found id:");
//			        System.out.println(finder.getValue());
//			      }
//			    }           
//			  }
//			  catch(ParseException pe){
//			    pe.printStackTrace();
//			  }
			
			
			
			/*String jsonText = "{\"first\": 123, \"second\": [4, 5, 6], \"third\": 789}";
			  JSONParser parser = new JSONParser();
			  ContainerFactory containerFactory = new ContainerFactory(){
			    public java.util.List creatArrayContainer() 
			    {
			      return new LinkedList();
			    }

			    public Map createObjectContainer() {
			      return new LinkedHashMap();
			    }
			                        
			  };
			                
			  try
			  {
			    
				Map json = (Map)parser.parse(jsonText, containerFactory);
			    
				parser.parse()
		
				Iterator iter = json.entrySet().iterator();
			  
			    
			    System.out.println("==iterate result==");
			    
			    
			    while(iter.hasNext())
			    {
			      Map.Entry entry = (Map.Entry)iter.next();
			      System.out.println(entry.getKey() + "=>" + entry.getValue());
			    }
			                        
			    System.out.println("==toJSONString()==");
			    System.out.println(JSONValue.toJSONString(json));
			  }
			  catch(ParseException pe){
			    System.out.println(pe);
			  }
			                */
			
			
			
			
			/*JSONParser parser = new JSONParser();
			obj = parser.parse(ss);
			
			array=(JSONArray)obj;
*/
						
			
			
			
			
			//obj=JSONValue.parse(ss);			
			//array = JSONArray
			//array=(JSONArray)obj;

//		} catch (IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


		//String s = new String(

		//	 		

		/*	try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) 
				{
			String line = null;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
				} catch (IOException x) {
					System.err.println(x);
				}*/

		//System.out.println("=======decode=======");
		/*
		String s="[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
		Object obj=JSONValue.parse(s);
		JSONArray array=(JSONArray)obj;
		System.out.println("======the 2nd element of array======");
		System.out.println(array.get(1));
		System.out.println();

		JSONObject obj2=(JSONObject)array.get(1);
		System.out.println("======field \"1\"==========");
		System.out.println(obj2.get("1"));    


		s="{}";
		obj=JSONValue.parse(s);
		System.out.println(obj);

		s="[5,]";
		obj=JSONValue.parse(s);
		System.out.println(obj);

		s="[5,,2]";
		obj=JSONValue.parse(s);
		System.out.println(obj);*/

//	}


	/*TeamController tc = new TeamController("");

	tc.Run();*/	


	//		/*String t1 = "[['goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3'],['goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3']]";
	//		
	//		String t2 = "'goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3'";
	//		
	//		//String[] temp1 = TeamController.ParseTasksFromPlanner(t1);
	//		
	//		
	//		
	//		String cut2 = ", ";
	//		String replace2 = "'";
	//		String[] t3;
	//		
	//		/*for (int i = 0; i < ee1.length; i++) 
	//		{
	//			t3 = ee1[i].split(cut2);			
	//		}*/
	//		
	//		
	//		/*for(int i = 0; i < t3.length; i++)
	//		{	
	//			
	//		}*/
	//		
	////		
	////		
	////		
	////		String tasks = "['goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3'],["
	////					  + "'goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3']";
	////
	////		String ttt = "'goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3'";
	////		String del3 = "(, )";
	//		
	//		String[] ee = ttt.split(del3);
	//		
	//		
	//		
	//		
	//		
	//		String delimiters2 =  "\\[*\\]";
	//		
	//		String[] tt = tasks.split(delimiters2);
	//		
	//		for (int i = 0; i < tt.length; i++) 
	//		{		
	//			String[] tt2;
	//			tt2 = tt[i].split(del3);
	//			
	//			
	//			tt2[0] = "444";
	//		}
	//		
	//	
	//		
	//		String str = "a d, m, i.n";
	//	    String delimiters = "\\s+|,\\s*|\\.\\s*";
	//
	//	    // analyzing the string 
	//	    String[] tokensVal = str.split(delimiters);
	//
	//	    // prints the number of tokens
	//	    System.out.println("Count of tokens = " + tokensVal.length);
	//	    
	//	    for(String token : tokensVal) {
	//	       System.out.print(token);
	//	    } 
	//		
	//		
	//		
	//		
	//		TeamController tc = new TeamController("");
	//
	//		//String tasks = "[['goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3'],['goto 13.9280556742 5.8314117592 Node1', 'goto 15.2255972991 7.11963471749 Node4', 'search Space2', 'goto 16.0514406825 8.9449781059 Node6', 'goto 16.9514257266 11.5398921354 Node7', 'search Space3']]";
	//		
	//		//tc.ParseTasks(tasks);
	//		
	//		//tc.Run();	
	//	}*/
//}
