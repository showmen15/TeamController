package TC;

public class MazeRobot 
{
	public String ID;
	public String Type;
	public String Name;
	public Point3D Position;
	public Point3D arrow;
	public double Probability;
	public  float Height;
	
	public MazeRobot(String id, Point3D position) 
	{
		this.Name = 
		this.ID = id;
		this.Position = position;
		
		this.Height = 0;
		this.Type = "SomeType";
		this.arrow = this.Position;
	}    
}