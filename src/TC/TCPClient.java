package TC;
import java.io.*;
import java.net.*;


public class TCPClient 
{
   private static int buffSize = 4048;
	
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	
	private char[] buff;

	public TCPClient(String sPlannerIP,int port) throws UnknownHostException, IOException
	{
		clientSocket = new Socket(sPlannerIP, port);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		buff = new char[buffSize];
	}

	protected void finalize() throws IOException
	{
		clientSocket.close();
	}

	public void Send(String message) throws IOException
	{
		outToServer.write(message.getBytes(), 0, message.length());
	}

	public String Recive() throws IOException
	{	
		inFromServer.read(buff, 0, buffSize);
		
		return new String(buff);
	}	
}
