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
	private JSONArray gateNodes;
	private JSONArray spaces;
	private JSONArray nodeNodes;
	private JSONArray gates;
	private JSONArray nodes;

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

			gateNodes =  currentJASON.getJSONArray("gate-nodes");
			spaces = currentJASON.getJSONArray("spaces");
			nodeNodes = currentJASON.getJSONArray("node-nodes");
			gates = currentJASON.getJSONArray("gates");
			nodes = currentJASON.getJSONArray("nodes");
					
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

	public void GateBlocked(String gateName) throws Exception
	{		
		ArrayList<String> gateNodesList = getGateNodes(gateName);

		if(gateNodesList.size() != 2)
			throw new Exception("Wrong number of gateNodes (GateNodesList is != 2)");

		setGateBlocked(gateName);
		setNodeNodesBlocked(gateNodesList.get(0),gateNodesList.get(1));
	}
	
	public void SetSpaceSearched(String spaceId) throws JSONException
	{
		JSONObject tempObj; 
		
		for (int i = 0; i < spaces.length(); i++) 
		{
			tempObj = spaces.getJSONObject(i);
			
			if(tempObj.getString("id").compareTo(spaceId) == 0)
				tempObj.put("searched", 1);
			else
				continue;
		}	
	}	
	
	private void setGateBlocked(String gateName) throws JSONException 
	{
		JSONObject tempObj;
		
		for (int i = 0; i < gates.length(); i++) 
	 	{
			tempObj = gates.getJSONObject(i);
			
			if(tempObj.getString("id").compareTo(gateName) == 0)
				tempObj.put("blocked", 1.0);
			else
				continue;
		}	
	}

	private void setNodeNodesBlocked(String sNodeFromId,String sNodeToId) throws JSONException 
	{			
		JSONObject tempObj;
		String tempNodeFromId;
		String tempNodeToId;

		for (int i = 0; i < nodeNodes.length(); i++) 
		{
			tempObj = nodeNodes.getJSONObject(i);
			tempNodeFromId = tempObj.getString("nodeFromId");
			tempNodeToId = tempObj.getString("nodeToId");

			if(((sNodeFromId.compareTo(tempNodeFromId) == 0 )  && (sNodeToId.compareTo(tempNodeToId) == 0 )) || ((sNodeFromId.compareTo(tempNodeToId) == 0) && (sNodeToId.compareTo(tempNodeFromId) == 0)))
				nodeNodes.getJSONObject(i).put("blocked", 1.0);			
		}	
	}

	private ArrayList<String> getGateNodes(String wallName) throws JSONException
	{
		ArrayList<String> tmpGateNodes = new ArrayList<String>();	
		
		for (int i = 0; i < gateNodes.length(); i++) 
		{			
			if(gateNodes.getJSONObject(i).getString("gateId").compareTo(wallName) == 0)
				tmpGateNodes.add(gateNodes.getJSONObject(i).getString("nodeId"));
			else
				continue;
		}		

		return tmpGateNodes;
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

	public ArrayList<Node> GetNodesList() throws JSONException 
	{
		ArrayList<Node> temp = new ArrayList<Node>();
		JSONObject tempObj;
		
		for(int i = 0; i < nodes.length(); i++)
		{
			tempObj = nodes.getJSONObject(i);
			
			temp.add(new Node(tempObj.getString("id"),tempObj.getString("kind")));			
		}

		return temp;
	}
}
