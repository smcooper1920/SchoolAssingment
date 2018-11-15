package hw5;

/**
 * <b>Edge</b> represents a pointer that originates at the front node and reaches
 *  the end node and possesses a weight.
 *  
 *  Generics: EdgeLabel is the weight of the incoming directed edge
 *  NodeName is the name of the Node associated with a particular directed edge
 */
public class Edge<EdgeLabel extends Comparable<EdgeLabel>, NodeName extends Comparable<NodeName>> implements Comparable<Edge<EdgeLabel, NodeName>>{
	//The end node that the edge is pointing to
	private final Node<NodeName, EdgeLabel> endNode;
	//The corresponding weight of the Edge
	private final EdgeLabel label;
	
	/**
	 * Rep Invariant: endNode != null, weight != null
	 * 
	 * Abstraction Function: Edge acts like a pointer from the starting node 
	 * (the node containing the Edge) to the endNode. The weight is the "weight" 
	 * of the edge (generally, how much effort it takes to get from one node to another)
	 */
	public Edge(Node<NodeName, EdgeLabel> endNode, EdgeLabel label) {
		this.endNode = endNode;
		this.label = label;
	}


	/**
	 * Provide a visual representation of the current Edge
	 * 
	 * @return String, showing the endNode name and weight
	 */
	@Override
	public String toString() {
		return ("Weight: " + label + " endPoint: " + ((Node<?,?>) endNode).getName());
	}
	
	/**
	 * Getter method to provide access to the endNode method
	 * 
	 * @requires endNode != null
	 * @return endNode, the node being pointed to by the edge
	 */
	public Node<NodeName, EdgeLabel> getEndNode() {
		return endNode;
	}
	
	/**
	 * Getter method for the field name
	 * 
	 * @requres weight != null, this != null
	 * @return weight
	 */
	public EdgeLabel getLabel() {
		return label;
	}
	
	/**
	 * Checks if the current Edge is equal to the passed in obj
	 * 
	 * @param obj, the Object we are comparing
	 * @requires obj != null
	 * @return true if the Nodes and weights are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Edge<?,?>)) {
			return false;
		}
		Edge<?,?> e = (Edge<?,?>) obj;
		return this.endNode.equals(e.endNode) && this.label.equals(e.label);
	}
	
	/**
	 * Create a hash code value for Edge objects
	 * 
	 * @requires this != null
	 * @return the hashcode based on the Node and Weight
	 */
	@Override
	public int hashCode() {
		return this.endNode.hashCode() * 31 + this.label.hashCode();
	}

	/**
	 * Compare two different edges to find the larger
	 * 
	 * @param o, the node we are comparing
	 * @return 0 if equal, >0 if this>n and <0 if this<n
	 */
	@Override
	public int compareTo(Edge<EdgeLabel, NodeName> o) {
		if(this.endNode.compareTo(o.endNode) == 0) {
			return this.label.compareTo(o.label);
		}
		return this.endNode.compareTo(o.endNode);
	}
}
