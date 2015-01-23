package TC;

public class BB 
{
	public String BBName;
	
	public double x_start, x_end;
	public double y_start, y_end;
	
	public BB(double xs,double xe,double ys,double ye)
	{
		x_start = xs;
		x_end = xe;
		
		y_start = ys;		
		y_end = ye;
	}
	
	public void initBB(double xs,double xe,double ys,double ye)
	{
		x_start = Math.min(x_start, xs);
		x_end = Math.max(x_end, xe);
		
		y_start = Math.min(y_start, ys);		
		y_end = Math.max(y_end, ye);
	}
}
