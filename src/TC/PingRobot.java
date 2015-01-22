package TC;

import java.io.IOException;
import java.net.InetAddress;

public class PingRobot
{
	private static int timeout = 3000; 
	
	///Return false when "Host is NOT reachable"
	///Return true when "Host is reachable"	
	public static Boolean Ping(String ipAddress) throws IOException
	{
	    InetAddress inet = InetAddress.getByName(ipAddress);

	    return inet.isReachable(timeout);		
	}	
}
