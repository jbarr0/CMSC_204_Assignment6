import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {
	
	Graph graph;
	
	TownGraphManager()
	{
		graph = new Graph();
	}
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Set<Town>towns = graph.vertexSet();
		
		Town town = null;
		Town t2 = null;
		for ( Town t : towns)
		{
			if(t.getName().equals(town1))
				town=t;
			
			if(t.getName().equals(town2))
				t2=t;
		}
		if(graph.addEdge(town, t2, weight, roadName) != null) {
			return true;
		}	
		else
			return false;	
	}
	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Town town = new Town(town1);
		Town town3 = new Town(town2);
		Road r = graph.getEdge(town, town3);
		return r.getName();
	}
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		Town newTown = new Town(v);
		
		return (graph.addVertex(newTown));
		
		
	}
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {
		Town town = new Town(name);
		int i =graph.towns.indexOf(town);
		return graph.towns.get(i);
	}
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		Town town = new Town(v);
		return (graph.containsVertex(town));
			 
	}
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town town = new Town(town1);
		Town town3 = new Town(town2);
		Road r = graph.getEdge(town, town3);
		if(!(r == null))
			return true;
		else 
			return false;
		
	}
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		Set<Road> s =graph.edgeSet();
		ArrayList<String> arrReturn = new ArrayList<>();
		for(Road r : s)
		{
			arrReturn.add(r.getName());
		}
		Collections.sort(arrReturn);
		return arrReturn;
		
	}
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town town = new Town(town1);
		Town town3 = new Town(town2);
		Road r = graph.getEdge(town, town3);
		r = graph.removeEdge(town, town3, r.getWeight(), r.getName());
		if(!(r==null))
			return true;
		else
			return false;
		
	}
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		Town town = new Town(v);
		return graph.removeVertex(town);
				
	}
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		Set<Town>s=graph.vertexSet();
		ArrayList<String> arr = new ArrayList<>();
		for (Town t : s)
		{
			arr.add(t.getName());
		}
		Collections.sort(arr);
		return arr;
	}
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town t1=null,t2 = null;
		Set<Town> s = graph.vertexSet();
		
		for(Town t : s)
		{
			if(t.getName().equals(town1))
				t1 = t;
			if(t.getName().equals(town2))
				t2=t;
			
		}
		
		
		ArrayList<String>st = graph.shortestPath(t1, t2);
		return st;
	}

}
