package TC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper 
{ 
	private JSONObject currentJASON;
	private JSONArray robots; 
	
	public JsonHelper(String sPath)
	{
		try 
		{
			Path file = Paths.get(sPath); 

			String jsonFile = new String(Files.readAllBytes(file));
			currentJASON = new JSONObject(jsonFile);
			
			robots =  currentJASON.getJSONArray("robots");
		} 
		catch(JSONException exx)
		{
			System.out.println("JsonHelper " + exx);
			exx.printStackTrace();
		}
		catch(IOException ex)
		{
			System.out.println("JsonHelper " + ex);
			ex.printStackTrace();
		}
		catch (Exception e) 
		{
			System.out.println("JsonHelper " + e);
			e.printStackTrace();
		}	
	}

	public void AddRobot(MazeRobot robot) throws JSONException
	{
		JSONObject newRobot = new JSONObject();

		initModifyJsonRobot(newRobot,robot);
		
		robots.put(newRobot);
	}

	public void RemoveRobot(MazeRobot robot) throws JSONException
	{
		int index = getRobot(robot.ID);
	
		if(index > -1)
			robots.remove(index);		
	}	

	public void UpdateRobot(MazeRobot robot) throws JSONException
	{
		int index = getRobot(robot.ID); 
		JSONObject selectedRobot; 
			
		if(index > -1)
		{
			selectedRobot = robots.getJSONObject(index);
			initModifyJsonRobot(selectedRobot,robot);		
		}	
	}
	
	private int getRobot(String ID) throws JSONException
	{
		String tempRobotID;

		for(int i = 0; i < robots.length(); i++)
		{	
			tempRobotID =  robots.getJSONObject(i).getString("id");

			if(tempRobotID == ID)
				return i;
		}

		return 	-1;
	}

	private void initModifyJsonRobot(JSONObject objRobot,MazeRobot robot) throws JSONException
	{
		objRobot.put("type", "robot");
		objRobot.put("id", robot.Name);
		objRobot.put("kind", robot.Type);
		
		JSONObject location = new JSONObject();
		location.put("x", robot.Position.x);  //pozycja podawana w metrach
		location.put("y", robot.Position.y);
		location.put("z",  robot.Position.z);
		location.put("alpha", 0.0001);
		
		objRobot.put("location", location);
	}
	
	public String GetCurrentJSON()
	{
		return currentJASON.toString();
	}
}
