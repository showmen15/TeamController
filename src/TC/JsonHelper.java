package TC;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper 
{ 
	private JSONObject currentJASON;
	private JSONArray robots; 
	private JSONArray spaceRobots;

	private ArrayList<BB> box;

	public JsonHelper(String sPath)
	{
		try 
		{
			Path file = Paths.get(sPath); 

			String jsonFile = new String(Files.readAllBytes(file));
			currentJASON = new JSONObject(jsonFile);

			robots =  currentJASON.getJSONArray("robots");
			spaceRobots = currentJASON.getJSONArray("space-robots");

			box = new ArrayList<>();

			initBB();
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

	public void AddRobot(MazeRobot robot) throws Exception
	{
		String sSpaceName = getSpaceName(robot);

		JSONObject newRobot = new JSONObject();
		JSONObject newRobotSpaceRobots = new JSONObject();

		initModifyJsonRobot(newRobot,robot);
		initModifyJsonRobotSpaceRobots(newRobotSpaceRobots,robot,sSpaceName);

		robots.put(newRobot);
		spaceRobots.put(newRobotSpaceRobots);	
	}

	private String getSpaceName(MazeRobot robot) throws Exception 
	{
		/*
		for(int i = 0; i < box.size(); i++)
		{
			System.out.print(box.get(i).BBName);
			
			System.out.print("\t");
			
			System.out.print(box.get(i).x_start);
			
			System.out.print("\t");
			
			System.out.print(box.get(i).x_end);
			
			System.out.print("\t");
			
			System.out.print(box.get(i).y_start);
					
			System.out.print("\t");
			
		    System.out.print(box.get(i).y_end);
		    
		    System.out.println();
		}
			*/
		
		
		for(int i = 0; i < box.size(); i++)
		{			
			if((box.get(i).x_start <= robot.Position.x) && (box.get(i).x_end  >= robot.Position.x) &&
			   (box.get(i).y_start <= robot.Position.y) && (box.get(i).y_end >= robot.Position.y))
				return box.get(i).BBName;
		}
		
		throw new Exception("getSpaceName -> Not define SpaceName");
	}

	public void RemoveRobot(MazeRobot robot) throws JSONException
	{
		int index = getRobot(robot.ID);
		int indexRobotSpaceRobots = getRobotSpaceRobots(robot.ID);

		if(index > -1)
			robots.remove(index);	

		if(indexRobotSpaceRobots > -1)
			spaceRobots.remove(indexRobotSpaceRobots);
	}	

	public void UpdateRobot(MazeRobot robot) throws Exception
	{
		String sSpaceName = getSpaceName(robot);
		int index = getRobot(robot.ID); 
		int indexRobotSpaceRobots = getRobotSpaceRobots(robot.ID);

		JSONObject selectedRobot;
		JSONObject  selectedRobotSpaceRobots;

		if(index > -1)
		{
			selectedRobot = robots.getJSONObject(index);
			initModifyJsonRobot(selectedRobot,robot);		
		}

		if(indexRobotSpaceRobots > -1)
		{
			selectedRobotSpaceRobots = spaceRobots.getJSONObject(indexRobotSpaceRobots);
			initModifyJsonRobotSpaceRobots(selectedRobotSpaceRobots,robot,sSpaceName);	
		}
	}

	private int getRobot(String ID) throws JSONException
	{
		String tempRobotID;

		for(int i = 0; i < robots.length(); i++)
		{	
			tempRobotID =  robots.getJSONObject(i).getString("id");

			if(tempRobotID.equals(ID))
				return i;
		}

		return 	-1;
	}	

	private int getRobotSpaceRobots(String ID) throws JSONException
	{
		String tempRobotSpaceRobotsID;

		for(int i = 0; i < spaceRobots.length(); i++)
		{	
			tempRobotSpaceRobotsID =  spaceRobots.getJSONObject(i).getString("robotId");

			if(tempRobotSpaceRobotsID.equals(ID))
				return i;
		}

		return 	-1;
	}

	private int getWalls(JSONArray walls,String ID) throws JSONException
	{
		String tempWallsID;

		for(int i = 0; i < walls.length(); i++)
		{	
			tempWallsID =  walls.getJSONObject(i).getString("id");

			if(tempWallsID.equals(ID))
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

	private void initModifyJsonRobotSpaceRobots(JSONObject newRobotSpaceRobots,	MazeRobot robot, String sSpaceName) throws JSONException 
	{
		newRobotSpaceRobots.put("type", "space-robot");
		newRobotSpaceRobots.put("spaceId", sSpaceName);
		newRobotSpaceRobots.put("robotId", robot.Name);
	}

	public String GetCurrentJSON()
	{
		return currentJASON.toString();
	}

	private void initBB() throws JSONException
	{
		JSONArray walls = currentJASON.getJSONArray("walls");
		JSONArray space_walls = currentJASON.getJSONArray("space-walls"); 

		String wallID;		
		int index;
		double x_start,x_end,y_start, y_end;
		JSONObject from,to;
		JSONObject fromObj, toObj;

		JSONObject temp, temp1;
		JSONArray tempArray;
		
		for(int i = 0; i < space_walls.length(); i++)
		{	
			wallID = space_walls.getJSONObject(i).getString("wallId");
			index = getWalls(walls,wallID);
			
			from = walls.getJSONObject(index).getJSONObject("from");
			x_start = from.getDouble("x");
			y_start = from.getDouble("y");

			to = walls.getJSONObject(index).getJSONObject("to");
			x_end = to.getDouble("x");
			y_end = to.getDouble("y");

			insertUpateBox(x_start,x_end,y_start, y_end,space_walls.getJSONObject(i).getString("spaceId"));
		}
	}

	private void insertUpateBox(double x_start,double x_end, double y_start,double y_end,String spaceId)
	{
		BB tempBB = getIndex(spaceId);

		if(tempBB != null)
			tempBB.initBB(x_start, x_end,  y_start, y_end);
		else
			box.add(new BB(x_start, x_end,  y_start, y_end,spaceId));			
	}

	private BB getIndex(String spaceId)
	{	
		for(int i = 0; i < box.size(); i++)
		{	
			if(box.get(i).BBName.equals(spaceId))
				return box.get(i);
		}

		return null;
	}
}
