package TC;

import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.location.LocationProxy;
import pl.edu.agh.amber.location.LocationCurrent;

import java.io.IOException;

public class LocationController 
{
	public double X;
	public double Y;
	public double P;
	public double Alfa;
	public double TimeStamp;
	
	private AmberClient client = null;
	private String Hostname;
	private LocationProxy locationProxy = null;	

	public LocationController(String hostname,int port)
	{
		try
		{
			Hostname = hostname;
			client = new AmberClient(Hostname, port);
			locationProxy = new LocationProxy(client, 0);
		}
		catch (IOException e) 
		{
			System.out.println("Unable to connect to robot: " + e);
		}
	}	

	protected void finalize () 
	{
		realiseResource();
	}

	private void realiseResource()
	{
		if(client != null)
		{
			client.terminate();
			client = null;
		}			
	}

	public LocationCurrent GetCurrentLocation()
	{
		try 
		{
			LocationCurrent lok = locationProxy.getCurrentLocation();
			lok.waitAvailable();
			return lok;

		} 
		catch (Exception e) 
		{
			System.out.println("Error in sending a command: " + e);
			e.printStackTrace();
		}
		return null;
	}

	public void LoadMap(String map)
	{
		try 
		{
			//podmiana mapy na robotach.
		} 
		catch (Exception e) 
		{
			System.out.println("Error in sending a command: " + e);
			e.printStackTrace();
			throw e;
		}		
	}	
}
