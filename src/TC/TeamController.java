package TC;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class TeamController {

	private ArrayList<RobotController> robots;

	private Boolean endWork = false;

	private PlannerController planner;
	private JsonHelper json;

	private String currentJson;
	private String currentPlan;

	private ArrayList<BB> box;
	
	private LoggerUDP logger;
	
	//in ms
	private long waitTime;


	public TeamController(String configFile) throws IOException, JSONException
	{
		//config file:
		// ilosc robotow
		// adresacja identyfikatory
		//url REST serwer
		//lokalizacja pliku JASON-a

		String URL = "";
		String sPath = "./map/MazeRoboLabFullMap_graph.roson";
		String sIP = "127.0.0.1";
		int port = 13000;
			
		json = new JsonHelper(sPath);		
		planner = new  PlannerController(sIP,port); //new PlannerController(URL);

		String  sIPLogger = "192.168.2.101"; //ustawic na szsz
		int sPortLogger = 4321;
		logger = new LoggerUDP(sIPLogger,sPortLogger);
				
		robots = new ArrayList<RobotController>();

		AddRobot("192.168.2.202", "Robot202");
		//AddRobot("192.168.2.203", "Robot203");		
	}

	private void AddRobot(String IP,String RobotID)
	{
		robots.add(new RobotController(IP, RobotID));		
	}

	private String[] parseTasksFromPlanner(String tasks)
	{
		String cut1 = "\\],\\["; 
		String replace = "\\[|\\]";

		String[] TasksList = tasks.split(cut1);

		for (int i = 0; i < TasksList.length; i++) 
			TasksList[i] = TasksList[i].replaceAll(replace, "");

		return TasksList;
	}

	private void manageTasksToRobot(String tasks)
	{
		String[] plansForRobot = parseTasksFromPlanner(tasks);

		for (int i = 0; i < plansForRobot.length; i++) 		
			robots.get(i).SetTask(plansForRobot[i]);
	}

	private void initRobotsLocation() throws Exception
	{
		for (int i = 0; i < robots.size(); i++) 
			robots.get(i).GetCurrentLocation();
	}

	private void putRobotsInToMap() throws Exception
	{	
		for (int i = 0; i < robots.size(); i++) 
			json.AddRobot(new MazeRobot(robots.get(i).ID, new Point3D(robots.get(i).X,robots.get(i).Y, 0)));
	}

	private void runGoToPointRobots() throws IOException
	{
		for (int i = 0; i < robots.size(); i++) 
			robots.get(i).SendTargetsList();		
	}

	private void checkVisitedTask() throws Exception
	{
		for (int i = 0; i < robots.size(); i++) 
			robots.get(i).CompleteTasks();
	}
	
	private void sendVisualisation()
	{
		for (int i = 0; i < robots.size(); i++) 
			logger.SendInsert(robots.get(i));
	}
	
	public void Run()
	{
		waitTime = 1000;

		try
		{
			
			initRobotsLocation(); //pobranie startowej pozycji robota
			putRobotsInToMap(); //umiesc roboty na mapie 						

			currentJson = json.GetCurrentJSON();  			
			currentPlan = planner.GetPlan(currentJson); //pobierz plan			

			manageTasksToRobot(currentPlan);  //rozdziel zadania na roboty

			runGoToPointRobots(); //wysli zadania do robotow

			while(true)
			{
				Thread.sleep(waitTime); //czekaj na wyniki

				checkVisitedTask(); //sprawdz czy robot dotarl do punktow 
				
				sendVisualisation(); //odswiez wizualizacje
			}			
		}
		catch(Exception ex)
		{
			System.out.println("Run " + ex);
			ex.printStackTrace();
		}
	}	
}
