import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

public class Graph implements GraphInterface<Town, Road> {
	
	  ArrayList<Town> towns;
	private ArrayList<LinkedList<Road>> roadList;
	
	static Town[] prevTowns;
	 
	public Graph()
	{
		this.towns= new ArrayList<>();
		this.roadList = new ArrayList<>();
	}
	
    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		if(sourceVertex == null || destinationVertex == null)
			return null;
		int sourceIndex;
		int destinationIndex;
		
		if(towns.contains(sourceVertex) && towns.contains(destinationVertex))
		{
			sourceIndex = towns.indexOf(sourceVertex);
			destinationIndex = towns.indexOf(destinationVertex);
		}
		else 
			return null; 
		
		for (Road r : roadList.get(sourceIndex)) {
			
			for(Road t : roadList.get(destinationIndex))
			{
				if (r.equals(t))
					return r;
			}
		}
		
		return null;
		
		
	}
    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		int sourceIndex;
		int destinationIndex;
		
		if(sourceVertex == null || destinationVertex == null)
			throw new NullPointerException("Once of the specified vertices is null");
		
		if((!(towns.contains(sourceVertex)) || (!towns.contains(destinationVertex))))
			throw new IllegalArgumentException("One of the vertex is not in the ArrayList");
		else {
			 sourceIndex = towns.indexOf(sourceVertex);
			 destinationIndex = towns.indexOf(destinationVertex);
		}
		
		
		Road r = new Road(sourceVertex,destinationVertex, weight, description);
		Road t = new Road(destinationVertex,sourceVertex, weight, description);
		
		
		roadList.get(sourceIndex).add(r);
		roadList.get(destinationIndex).add(t);
		
		sourceVertex.addTownToAdjList(destinationVertex);
		destinationVertex.addTownToAdjList(sourceVertex);
		
		return r;
			
	}
    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		if(v == null)
			throw new NullPointerException("specified vertex is null");
		
		if(towns.isEmpty() == true)
		{
			towns.add(v);
			roadList.add(new LinkedList<Road>());
			return true;
		}
		if(towns.contains(v)) // already exists 
			return false;
		
		else
		{
			towns.add(v);
			roadList.add(new LinkedList<Road>());
			return true;
		}			
	}
	
    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		return(sourceVertex.adjTo(destinationVertex));
	}
	   /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */

	@Override
	public boolean containsVertex(Town v) {
		
		return towns.contains(v);
	}
    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		Set<Road> roadSet = new HashSet<>();
	
		for ( int i =0 ; i < towns.size(); i++)
		{
			for ( Road r : roadList.get(i))
			{
				if(!(roadSet.contains(r)))
				{
					roadSet.add(r);
				}
			}
		}
	
		return roadSet;
	}
	   /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */ 
	@Override
	public Set<Road> edgesOf(Town vertex) {
		
		if(!towns.contains(vertex))
			throw new IllegalArgumentException("Vertex not found in graph");
		if(vertex == null)
			throw new NullPointerException("Vertex is null");
		
		Set<Road> roadSet = new HashSet<>();
		int indexOfVertex = towns.indexOf(vertex);
		for ( Road r : roadList.get(indexOfVertex))
		{
			roadSet.add(r);
		}
		return roadSet;
	}
    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		if(sourceVertex == null || destinationVertex == null)
			return null;
		int sourceIndex;
		int destinationIndex;
		
		Road r = new Road (sourceVertex, destinationVertex,weight, description);
		
		if(towns.contains(sourceVertex) && towns.contains(destinationVertex))
		{
			sourceIndex = towns.indexOf(sourceVertex);
			destinationIndex = towns.indexOf(destinationVertex);
		}
		else 
			return null; 
		
		for (Road t : roadList.get(sourceIndex)) {
			
			if(t.equals(r)){
				roadList.get(sourceIndex).remove(t);
				roadList.get(destinationIndex).remove(t);
				sourceVertex.removeAdjList(destinationVertex);
				destinationVertex.removeAdjList(sourceVertex);
				return t;
			}
		}
		
		return null;
	}
    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		if(v == null)
			return false;
		if(!(towns.contains(v)))
			return false;
		// removing all edges connected to the vertex
		for( Town t : v.adjList) {
			
			Road r = this.getEdge(v, t);
					
			this.removeEdge(t, v, r.getWeight(), r.getName());
			
		}
		if(!(v.adjList.isEmpty())) {
			Town j =v.adjList.get(0);
			Road r = this.getEdge(v, j);
			this.removeEdge(v,j,r.getWeight(),r.getName());
		}			
		
		//remove vertex
		towns.remove(v);
		return true;
	}
    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		Set<Town> townSet= new HashSet<>();
		for (Town t : towns)
		{
			townSet.add(t);
		}
		return townSet;
		
	}
	 /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> dummyArray = new ArrayList<>();
		ArrayList<String> returnArray = new ArrayList<>();
		
		dijkstraShortestPath(sourceVertex);
		
		Town currentTown = destinationVertex;
		while(!currentTown.equals(sourceVertex))
		{
			int indexOfCurrentTown = towns.indexOf(currentTown);
			Town t = prevTowns[indexOfCurrentTown];
			if(t==null)
			{
				break;
			}
			Road r = this.getEdge(currentTown, t);
			String s = t.getName() + " via " + r.getName()+ " to "+ currentTown.getName() +" "+ r.getWeight()+" mi";
			dummyArray.add(s);
			currentTown =t;
		}
		for(int i = dummyArray.size()-1; i>-1;i--)
		{
			returnArray.add(dummyArray.get(i));
		}
		
		return returnArray;
		
	}
/**  
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		//unvisited set
		Set<Town> unvisitedTowns = new HashSet<Town>();
		for (Town t : towns)
		{
			unvisitedTowns.add(t);
		}
		// visited set
		Set<Town> visitedTowns = new HashSet<Town>(); 
		// tracking shortest distance
		int[] distanceArray = new int[towns.size()];
		int indexOfSource = towns.indexOf(sourceVertex);
		
		for(int i =0; i<distanceArray.length;i++)
		{
			if(i==indexOfSource)
				distanceArray[i] = 0;
			
			distanceArray[i]= Integer.MAX_VALUE;
		}
		// previous vertex arrayList
			prevTowns = new Town[towns.size()];
		
		//visit Queue
		LinkedList<Town> visitQueue = new LinkedList<>();
		//visitQueue.remove()
		visitQueue.add(sourceVertex);
		
		while(!(visitQueue.isEmpty()))
		{
			for(Town t : visitQueue.peek().adjList)
			{
				int indexOfTown = towns.indexOf(visitQueue.peek());
				int indexOfTargetTown = towns.indexOf(t);
				
				Road r = this.getEdge(visitQueue.peek(), t);
				if(distanceArray[indexOfTown]+r.getWeight() < distanceArray[indexOfTargetTown])
				{
					distanceArray[indexOfTargetTown] = distanceArray[indexOfTown]+r.getWeight();
					prevTowns[indexOfTargetTown] = towns.get(indexOfTown);
					visitQueue.add(t);
				}
				
			}
			visitedTowns.add(visitQueue.remove());
		}
		
		
	
		
			
			
		}
		
		
		
	}
	


