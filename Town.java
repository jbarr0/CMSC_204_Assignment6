import java.util.LinkedList;

public class Town implements Comparable<Town> {

	
	private String name;
	 LinkedList<Town> adjList;
	
	public Town(String name)
	{
		this.name = name;
		adjList = new LinkedList<Town>();
	}
	// copy constructor 
	public Town(Town anotherTown)
	{
		this.name = anotherTown.name;
		this.adjList=anotherTown.adjList; // double check this
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public void addTownToAdjList(Town t)
	{
		this.adjList.add(t);
	}
	public void removeAdjList(Town t) {
		this.adjList.remove(t);
	}
	
	public boolean adjTo(Town t)
	{
		return this.adjList.contains(t);
	}
	
	@Override
	public int compareTo(Town o) {
		if(this.name.equalsIgnoreCase(o.name))
			return 0;
		else
		 return -1;
	}
	@Override
	public boolean equals(Object o)
	{
		Town temp = (Town)o;
		return this.name.equalsIgnoreCase(temp.name);
	}
	@Override
	public int hashCode()
	{
		return this.name.hashCode();
	}

	@Override
	public String toString()
	{
		return this.name;
	}
}
