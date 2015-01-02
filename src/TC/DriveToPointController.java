package TC;

import java.io.IOException;
import java.util.List;

import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.drivetopoint.DriveToPointProxy;
import pl.edu.agh.amber.drivetopoint.Point;
import pl.edu.agh.amber.drivetopoint.Result;
import pl.edu.agh.amber.drivetopoint.Location;

public class DriveToPointController 
{
	private String Hostname;
	private AmberClient client = null;

	private DriveToPointProxy driveToPointProxy = null;	


	private Point nextTarget;
	public Point getNextTarget() 
	{
		return nextTarget;		
	}

	private List<Point>  nextTargets;
	public List<Point> getNextTargets()
	{
		return nextTargets;
	}

	private Point visitedTarget;
	public Point getVisitedTarget()
	{
		return visitedTarget;
	}

	private List<Point> visitedTargets;
	public List<Point> getVisitedTargets()
	{
		return visitedTargets;
	}

	private Location currentLocation;
	public Location getCurrentLocation()
	{
		return currentLocation; 
	}

	public DriveToPointController(String hostname,int port)
	{
		try
		{
			Hostname = hostname;
			client = new AmberClient(Hostname, port);

			driveToPointProxy = new DriveToPointProxy(client, 0);
		}
		catch (IOException e) 
		{
			System.out.println("Unable to connect to robot: " + e);
		}
	}

	public void SetTargets(List<Point> targets) throws IOException
	{
		driveToPointProxy.setTargets(targets);		
	}

	public void RequestNextTarget() throws Exception
	{
		Result<Point> resultPoint = driveToPointProxy.getNextTarget();
		nextTarget = resultPoint.getResult();
		currentLocation = resultPoint.getLocation();
	}

	public void RequestNextTargets() throws Exception
	{	
		Result<List<Point>> resultPoints = driveToPointProxy.getNextTargets();
		nextTargets = resultPoints.getResult();
		currentLocation = resultPoints.getLocation();
	}

	public void RequestVisitedTarget() throws Exception
	{
		Result<Point> resultPoint = driveToPointProxy.getVisitedTarget();
		visitedTarget = resultPoint.getResult();
		currentLocation = resultPoint.getLocation();
	}

	public void RequestVisitedTargets() throws Exception
	{
		Result<List<Point>> resultPoints = driveToPointProxy.getVisitedTargets();
		visitedTargets = resultPoints.getResult();
		currentLocation = resultPoints.getLocation();
	}
}
