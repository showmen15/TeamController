package TC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import TC.Task.KindTaskType;
import TC.Task.TaskType;
import pl.edu.agh.amber.location.LocationCurrent;
import pl.edu.agh.amber.drivetopoint.Point;
import pl.edu.agh.amber.drivetopoint.Location;


public class RobotController 
{
	public double X;
	public double Y;
	public double P;
	public double Angle;
	public double TimeStamp;

	private static int AmberPort = 26233; 

	private static double R = 0.3;
	private static double deltaTime = 30; //30 sekund
	private static double RobotV = 0.015;

	private LocationController location;
	private DriveToPointController driveToPoint;

	private ArrayList<Task> Tasks;

	private ArrayList<Task> GoToTasks;
	//private ArrayList<Task> EndTasks;
	//private int iTasksCount;

	public String ID; 
	public int RobotID;

	private long time; //in seconds
	private Date startTask; 

	RobotController()
	{
		Tasks = new ArrayList<Task>();
		GoToTasks = new ArrayList<Task>();
	}

	RobotController(String hostname,String id,int iRobotID)
	{
		location = new LocationController(hostname, AmberPort);
		driveToPoint = new DriveToPointController(hostname, AmberPort);
		ID = id;	
		RobotID = iRobotID;
		Tasks = new ArrayList<Task>();
		GoToTasks = new ArrayList<Task>();		
	}

	private String[] parseTaskForRobot(String tasks)
	{
		String cut2 = ", ";
		String replace2 = "'";

		String[] TasksList = tasks.split(cut2);

		for (int i = 0; i < TasksList.length; i++) 
			TasksList[i] = TasksList[i].replaceAll(replace2, "");

		return TasksList;
	}

	public void SetTask(String tasks)
	{
		String[] taskList = parseTaskForRobot(tasks);
		Tasks.clear();
		GoToTasks.clear();

		for (int i = 0; i < taskList.length; i++) 
			Tasks.add(new Task(taskList[i]));

		GoToTasks = getGoToTask(Tasks); 
	}

	private List<Point> getPointToGo()
	{
		List<Point> tmpPoint = new  ArrayList<Point>();

		for (int i = 0; i < Tasks.size(); i++) 
		{
			if(Tasks.get(i).Type == TaskType.GoTo)
				tmpPoint.add(new Point(Tasks.get(i).X,Tasks.get(i).Y,R));
		}

		return tmpPoint;	
	}

	public void GetCurrentLocation() throws Exception
	{
		LocationCurrent current = location.GetCurrentLocation();
		X = current.getX();
		Y = current.getY();
		P = current.getP();
		Angle = current.getAngle();
		TimeStamp = current.getTimeStamp();		
	}

	private void GetLocation()
	{
		Location loc = driveToPoint.getCurrentLocation();
		X = loc.getX();
		Y = loc.getY();
		P = loc.getP();
		Angle = loc.getAngle();
		TimeStamp = loc.getTimeStamp();
	}	

	public void SendTargetsList() throws IOException
	{
		List<Point> empty = new ArrayList<>();
		driveToPoint.SetTargets(empty);

		List<Point> targets = getPointToGo();
		driveToPoint.SetTargets(targets);
		startTask = new Date();
		time = countTimeTask(RobotV,X,Y,targets.get(targets.size() - 1).x,targets.get(targets.size() - 1).y,deltaTime);
	}

	public Boolean CompleteTasks() throws Exception	
	{
		List<Point> targets = getPointToGo();
		driveToPoint.RequestVisitedTargets();
		List<Point> visitedTargets = driveToPoint.getVisitedTargets();
		GetLocation();
		markTaskAsEnd(visitedTargets.size());

		if(targets.size() == visitedTargets.size())
		{
			time = 0;
			startTask = null;
			return true;
		}
		else 
			return false;
	}

	public Boolean IsTimeElapsedForTask()
	{
		return false;
	}

	private void markTaskAsEnd(int endTaskNumber)
	{
		for(int i = 0; i < endTaskNumber; i++)
			GoToTasks.get(i).IsEnd = true;				
	}

	private ArrayList<Task> getGoToTask(ArrayList<Task> tasks)
	{
		ArrayList<Task> temp = new ArrayList<>();

		for(int i = 0; i < tasks.size();i++)
		{
			if(tasks.get(i).Type == TaskType.GoTo)
				temp.add(tasks.get(i));
		}

		return temp;
	}

	//	public void ReciveVisitedTargetList() throws Exception
	//	{
	//		List<Point> targets = getPointToGo();
	//		List<Point> visitedTargets = driveToPoint.GetVisitedTargets();
	//		
	//		
	//		if()
	//	}

	private long countTimeForTask(Date start)
	{
		return 0;	
	}


	private long  countTimeTask(double V,double StartX,double StartY,double EndX,double EndY,double delta)
	{
		double distance = Math.sqrt( Math.pow(StartX - EndX, 2) + Math.pow(StartY - EndY, 2));

		return  (new Double((distance / V) + delta)).longValue();

	}

	public ArrayList<Task> GetTasksList()
	{
		return Tasks;		
	}

	public ArrayList<Task> GetGoToTasksList()
	{
		ArrayList<Task> tempTask = new ArrayList<Task>();

		for (int i = 0; i < Tasks.size(); i++) 
		{
			if(Tasks.get(i).Type == TaskType.GoTo)
				tempTask.add(Tasks.get(i));
		}

		return tempTask;
	}

	public void StopRobot() throws IOException
	{
		List<Point> empty = new ArrayList<>();
		driveToPoint.SetTargets(empty);
	}

	public void SetNodeKind(ArrayList<Node> nodes) 
	{
		String nodeTempName;

		for(int i = 0; i < Tasks.size(); i++)
		{
			nodeTempName = getNodeKind(nodes,Tasks.get(i).Name);

			switch (nodeTempName) 
			{
			case "spaceNode":
				Tasks.get(i).Kind = KindTaskType.spaceNode;				
				break;
			case "gateNode":
				Tasks.get(i).Kind = KindTaskType.gateNode;
			default:
				Tasks.get(i).Kind = KindTaskType.UnKnown;
				break;
			}
		}	
	}

	private String getNodeKind(ArrayList<Node> nodes,String nodeName)
	{
		for(int i = 0; i < nodes.size();i++)
		{
			if(nodes.get(i).ID.compareTo(nodeName) == 0)
				return nodes.get(i).Kind;
		}
		return "";
	}

}
