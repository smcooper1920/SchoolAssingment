package hw5;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>Node</b> represents a single node in the graph and is composed of the
 * name of the node and the list of outgoing edges with their accompanied
 * weights.
 * 
 * Generics: NodeName will be the type of the unique name associatede with each Node
 * EdgeLabel is associated further with the Edges class but is needed here since each Node posseses a set of outgoing edges
 */
public class Node<NodeName extends Comparable<NodeName>, EdgeLabel extends Comparable<EdgeLabel>> implements Comparable<Node<NodeName, EdgeLabel>>{
	
	// The name of the Node
	private final NodeName name;
	// Set containing all of the outgoing edges of this Node
	private final Set<Edge<EdgeLabel, NodeName>> edgeSet = new HashSet<Edge<EdgeLabel, NodeName>>();
	
	/**
	 * Rep Invariant: name != null, edgeSet != null, edgeList.size >= 0,
	 * for each k: 0 <= k < edgeSet.size -> edgeSet[k] != null
	 * 
	 * Abstraction Function: A Node consists of a name, and a set of 
	 * Edges which direct to the ending node and weight required to get
	 * from the current node to the next node.
	 */
	public Node(NodeName name) {
		this.name = name;
	}
	
	public Node(Node<NodeName, EdgeLabel> node1) {
		this.name = node1.getName();
	}


	/**
	 * Provide specialized output for test driver class
	 * 
	 * @return s, String in the format NodeName(EdgeLabel)
	 */
	public String testDriverOutput() {
		String s = "";
		for(Edge<EdgeLabel, NodeName> e : edgeSet) {
			s += (" " + name + "(" + e.getLabel() + ")");
		}
		return s;
	}
	
	
	
	/**
	 * Getter method for the edgeSet Set
	 * 
	 * @return Set with all of the edges associated with the current Node
	 */
	public Set<Edge<EdgeLabel, NodeName>> getSet(){
		return new HashSet<Edge<EdgeLabel, NodeName>>(edgeSet);
	}
	
	/**
	 * Provide a visual representation of the current Node
	 * 
	 * @return String, showing the name of the Node and the connected edges
	 */
	@Override
	public String toString() {
		return ("startPoint: " + name + "\n" + "edgeSet: " + edgeSet.toString() + "\n");
	}
	
	/**
	 * Add an Edge to the set of edges leaving the current Node
	 * 
	 * @param newEdge, The Edge we are adding to the edgeSet
	 * @requires newEdge != null
	 * @effects this.edgeSet
	 * @modifies edgeSet[0...k] -> edgeSet[0..k] : newEdge
	 */
	public void addEdge(Edge<EdgeLabel, NodeName> newEdge) {
		edgeSet.add(newEdge);
	}
	
	
	/**
	 * Getter method for the field name
	 * 
	 * @requres name != null, this != null
	 * @return name
	 */
	public NodeName getName() {
		return name;
	}
	
	
	/**
	 * Checks if the current Node is equal to the passed in obj
	 * 
	 * @param obj, the Object we are comparing
	 * @requires obj != null
	 * @return true if the Node names are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Node<?, ?>)) {
			return false;
		}
		Node<?, ?> n = (Node<?, ?>) obj;
		return this.name.equals(n.name);
	}
	
	/**
	 * Create a hash code value for Node objects
	 * 
	 * @requires this != null
	 * @return the hashcode based on the Node name
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	/**
	 * Compare two different node values to find the larger
	 * 
	 * @param n, the node we are comparing
	 * @return 0 if equal, >0 if this>n and <0 if this<n
	 */
	@Override
	public int compareTo(Node<NodeName, EdgeLabel> n) {
		return (this.name).compareTo(n.name);
	}

}