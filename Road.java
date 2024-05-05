
public class Road implements Comparable<Road> {

	private Town end,start;
	private int distance;
	private String roadName;
	
	
	public Road(Town start, Town end, int distance, String roadName)
	{
		this.start = start;
		this.end = end;
		this.distance = distance;
		this.roadName = roadName;
	}
	
	public Road(Town start,Town end, String roadName)
	{
		this(start, end, 1, roadName);
	}
	
	public boolean contains(Town town)
	{
		if (this.start.equals(town) || this.end.equals(town)) {
			return true;
		}
		else
			return false;	
	}
	
	public String getName()
	{
		return this.roadName;
	}
	
	public Town getDestination()
	{
		return this.end;
	}
	
	public Town getSource()
	{
		return this.start;
	}
	
	public int getWeight() {
		return this.distance;
	}
	
	@Override
	public String toString()
	{
		return this.start.toString() + "," + this.end.toString() + "," + this.distance+"," + this.roadName;
	}
	
	
	@Override
	public int compareTo(Road o) {
		
		if(this.roadName.equalsIgnoreCase(o.roadName)) {
			return 0;
		}
		else
			return -1;
	}
	@Override
	public boolean equals(Object r)
	{
		Road temp = (Road)r;
		if (this.start.equals(temp.start) && this.end.equals(temp.end))
		{
			return true;
		}
		else if (this.start.equals(temp.end) && this.end.equals(temp.start))
		{
			return true;
		}
		else
			return false;
	}
	@Override
	public int hashCode()
	{
		return this.roadName.hashCode();
	}

}
