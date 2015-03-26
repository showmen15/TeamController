package TC;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task 
{
	private String OrgTask;

	public String TeskID;
	
	public double X;
	public double Y;
	public TaskType Type;
	public String Name;
	public KindTaskType Kind;
	
	public Boolean IsEnd;

	public Task(String sTask)
	{
		String[] taskList = parseTask(sTask);
		InitTaskObject(taskList);
		OrgTask = sTask;
		TeskID = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		IsEnd = false;
		Kind = KindTaskType.UnKnown;
	}

	private String[] parseTask(String sTask)
	{
		String cut3 = " ";

		String[] TasksList = sTask.split(cut3);

		return TasksList;
	}

	private void InitTaskObject(String[] taskList)
	{
		switch (taskList[0]) 
		{
		case "goto":
			Type = TaskType.GoTo;
			X = Double.parseDouble(taskList[1]);
			Y = Double.parseDouble(taskList[2]);
			Name = taskList[3];		
			break;
		case "search":
			Type = TaskType.Search;
			Name = taskList[1];	
		default:
			Type = TaskType.UnKnown;
			break;
		}
	}

	public enum TaskType { UnKnown,GoTo,Search };
	
	public enum KindTaskType { UnKnown,spaceNode,gateNode}
}
