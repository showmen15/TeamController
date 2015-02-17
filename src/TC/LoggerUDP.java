package TC;

import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class LoggerUDP 
{
	private DatagramSocket clientSocket; 
	private InetAddress IPAddress;
	private int port;


	LoggerUDP(String IP,int iPort)
	{
		this.port = iPort;

		try 
		{
			clientSocket = new DatagramSocket();
			IPAddress = InetAddress.getByName(IP);

		} 
		catch (SocketException e) 
		{
			e.printStackTrace();
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
	}

	protected void finalize()
	{
		clientSocket.close();
	}

	public void SendInsert(RobotController robot)
	{
		String tempOperation = "#insert;";
		String tempParssedRobot = parseRobot(robot);

		send(tempOperation + tempParssedRobot);
	}

	public void SendUpdate(RobotController robot)
	{
		String tempOperation = "#update;";
		String tempParssedRobot = parseRobot(robot);

		send(tempOperation + tempParssedRobot);
	}	

	
	public void SendReset()
	{
		String tempOperation = "#reset;";
	
		send(tempOperation);
	}
	
	private String parseRobot(RobotController robot)
	{
		String tempRobot = "";

		tempRobot += robot.ID.toString() + ";" + robot.X + ";" + robot.Y + ";" + robot.Angle + ";";

		ArrayList<Task> tempTasks = robot.GetGoToTasksList();  //GetTasksList();
		Task itemTask; 

		for(int i = 0; i < tempTasks.size();i++)
		{
			itemTask = tempTasks.get(i);
			tempRobot += itemTask.TeskID + ";"	+  itemTask.X + ";"	+  itemTask.Y + ";"	+ itemTask.Name + ";" + itemTask.IsEnd + ";";
		}

		return tempRobot;		
	}

	private void send(String message)
	{
		try 
		{
			byte[]   sendData = message.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

			clientSocket.send(sendPacket);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
