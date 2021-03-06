package TC;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class TeamController {

	private ArrayList<RobotController> robots;

	private Boolean endWork = false;

	private PlannerController planner;
	private JsonHelper json;
	private ArrayList<Node> nodes; 

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
		String sPath =  "./map/MazeRoboLabFullMap_graph.roson"; //"./map/Test2RobotyReplanowanie.roson"; //"./map/MazeRoboLabFullMap_graph.roson"; //"D:/Desktop/LabiryntMapy/Test4.roson";
		String sIP = "192.168.2.100";//"127.0.0.1";
		int port = 13000;
			
		json = new JsonHelper(sPath);
		nodes = json.GetNodesList();
		
		planner = new  PlannerController(sIP,port); //new PlannerController(URL);

		String  sIPLogger = "192.168.2.101"; //ustawic na szsz
		int sPortLogger = 4321;
		logger = new LoggerUDP(sIPLogger,sPortLogger);
				
		robots = new ArrayList<RobotController>();

		AddRobot("192.168.2.209", "Robot209",1);
		AddRobot("192.168.2.208", "Robot208",2);
		
		
		
		
		//AddRobot("192.168.2.209", "Robot209",2);
		//AddRobot("192.168.2.100", "Robot200",2);
		 //AddRobot("192.168.2.208", "Robot208",1);
		//AddRobot("192.168.2.209", "Robot209",2);
		//AddRobot("192.168.2.203", "Robot203",3);
		//AddRobot("192.168.2.210", "Robot210",4);
		//AddRobot("192.168.2.206", "Robot206",5);
	}

	private void AddRobot(String IP,String RobotID,int iRobotID)
	{
		robots.add(new RobotController(IP, RobotID,iRobotID));		
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
		{
			robots.get(i).SetTask(plansForRobot[i]);
			robots.get(i).SetNodeKind(nodes);
		}
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
	
	private void stopAllRobot() throws IOException
	{
		for (int i = 0; i < robots.size(); i++) 
			robots.get(i).StopRobot();		
	}
	
	private Boolean checkIfRobotGetPoint()
	{
	  ArrayList<Task> temp = robots.get(0).GetTasksList();
	
	  return temp.get(3).IsEnd;	  
	}	
	
	
	private void ManageTaskForRobot()
	{
		robots.get(1).Tasks.add(new Task("goto 2.02 2.4 Node777"));
		robots.get(1).Tasks.add(new Task("goto 2.02 2.6 Node778"));
		robots.get(1).Tasks.add(new Task("goto 1.18 2.88 Node779"));		
		robots.get(1).Tasks.add(new Task("goto 0.550427231222 3.15974024342 Node222"));
		
		robots.get(1).Tasks.add(new Task("goto 0.550089970495 3.3604918387 Node208"));
		robots.get(1).Tasks.add(new Task("goto 0.7 4.08 Node207"));
		
	}
	
	
	public void RunAwaryjne()
	{
		waitTime = 500;

		try
		{
			stopAllRobot();
			logger.SendReset();
			
			
			initRobotsLocation(); //pobranie startowej pozycji robota
			putRobotsInToMap(); //umiesc roboty na mapie 						

			currentJson = json.GetCurrentJSON();  
			currentPlan = planner.GetPlan(currentJson,1); //pobierz plan	
						
			manageTasksToRobot(currentPlan);  //rozdziel zadania na roboty

			sendVisualisation(); //odswiez wizualizacje
			
//			currentPlan = planner.GetPlan(currentJson,2); //pobierz plan	
//			
//			manageTasksToRobot(currentPlan);  //rozdziel zadania na roboty
//
//			logger.SendReset();
//			
//			sendVisualisation(); //odswiez wizualizacje
									
			//json.SetSpaceSearched("Space171"); //ustawianie przeszukanych pomieszczen
			//json.SetSpaceSearched("Space161"); //ustawianie przeszukanych pomieszczen

			//json.GateBlocked("Wall158"); //ustawianie zamknietych drzwi
			
			//json.SetSpaceSearched("Space182"); //ustawianie przeszukanych pomieszczen
			//json.SetSpaceSearched("Space233"); //ustawianie przeszukanych pomieszczen
			
			//sendVisualisation(); //odswiez wizualizacje
						
			//sendVisualisation(); //odswiez wizualizacje

		//	json.GateBlocked("Wall127"); //ustawianie zamknietych drzwi			
		
			
			runGoToPointRobots(); //wyslij zadania do robotowrunGoToPointRobots(); //wyslij zadania do robotow
			
			while(true)
			{
				if(checkIfRobotGetPoint())
				{
					robots.get(0).StopRobot();
					
					robots.get(0).RemoveNotFinishTask();
					
					ManageTaskForRobot();
					
					robots.get(1).SendTargetsList();
		
					logger.SendReset();
					
					sendVisualisation(); //odswiez wizualizacje
					
					while(true)
					{
						Thread.sleep(waitTime); //czekaj na wyniki

						robots.get(1).CompleteTasks();
						
						//checkVisitedTask(); //sprawdz czy robot dotarl do punktow 
						
						sendVisualisation(); //odswiez wizualizacje
					}	
				}
				
				
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
	
	public void Run()
	{
		waitTime = 500;

		try
		{
			stopAllRobot();
			logger.SendReset();
			
			
			initRobotsLocation(); //pobranie startowej pozycji robota
			putRobotsInToMap(); //umiesc roboty na mapie 						
			
			//
			currentJson = json.GetCurrentJSON();  
			currentPlan = planner.GetPlan(currentJson,2); //pobierz plan	
						
			manageTasksToRobot(currentPlan);  //rozdziel zadania na roboty
			
			sendVisualisation(); //ods wiez wizualizacje
			
			//currentPlan = planner.GetPlan(currentJson); //pobierz plan			
						
			//json.SetSpaceSearched("Space171"); //ustawianie przeszukanych pomieszczen
			//json.SetSpaceSearched("Space161"); //ustawianie przeszukanych pomieszczen

			//json.GateBlocked("Wall158"); //ustawianie zamknietych drzwi
			
			//json.SetSpaceSearched("Space182"); //ustawianie przeszukanych pomieszczen
			//json.SetSpaceSearched("Space233"); //ustawianie przeszukanych pomieszczen
			
			//sendVisualisation(); //odswiez wizualizacje
						
			//sendVisualisation(); //odswiez wizualizacje

		//	json.GateBlocked("Wall127"); //ustawianie zamknietych drzwi			
			
//			int i = 0;
//			
//			while(i < 100)
//			{
//			
//			sendVisualisation(); //odswiez wizualizacje
//			sendVisualisation(); //odswiez wizualizacje
//			}

			runGoToPointRobots(); //wyslij zadania do robotow

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
