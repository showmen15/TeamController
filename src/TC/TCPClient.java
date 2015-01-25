package TC;
import java.io.*;
import java.net.*;


public class TCPClient 
{
   private static int buffSize = 50000;
	
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	
	private char[] buff;
	private String IP;
	private int Port;
	

	public TCPClient(String sPlannerIP,int port)
	{
		IP =sPlannerIP;
		Port = port;
		
		buff = new char[buffSize];
	}

	protected void finalize() throws IOException
	{
		
	}

	private void initTcpClient(String sPlannerIP,int port) throws UnknownHostException, IOException
	{
		clientSocket = new Socket(sPlannerIP, port);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	private void endTcpClient() throws IOException
	{
		outToServer.close();
		inFromServer.close();		
		clientSocket.close();
	}
	
	public String CommunicateWithPlanner(String message) throws UnknownHostException, IOException, InterruptedException
	{
		String temp;
		
		initTcpClient(IP,Port);
		Send(message);
		temp = Recive();
		endTcpClient();
		
		return temp;
	}
	
	private void Send(String message) throws IOException, InterruptedException
	{
		String sizeMessage = Integer.toString(message.length());
		
		outToServer.write(sizeMessage.getBytes(), 0, sizeMessage.length());
		
		Thread.sleep(1000);
		
		outToServer.write(message.getBytes(), 0, message.length());
	}

	private String Recive() throws IOException
	{	
		inFromServer.read(buff, 0, buffSize);
		
		return new String(buff);
	}	
}
