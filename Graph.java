package hw5;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Stephen Cooper
 *<b>Graph</b> represents an Immutable directed labeled multigraph composed of nodes and 
 * directed edges with their associated weights. 
 * 
 * Generics: EdgeLabel is the weight of the incoming directed edge
 *  NodeName is the name of the Node associated with a particular directed edge
 */
public class Graph<NodeName extends Comparable<NodeName>, EdgeLabel extends Comparable<EdgeLabel>> {
	// Maps the name of a node to the Node itself
	private final Map<NodeName, Node<NodeName, EdgeLabel>> nodeMap;
	
	/**
	 * Rep Invariant: nodeMap != null
	 * 
	 * Abstraction Function: A graph stored in nodeMap where the key
	 * is the name of the node and the value is the Node.
	 */
	public Graph() {
		nodeMap = new HashMap<NodeName, Node<NodeName, EdgeLabel>>();
	}
	
	
	/**
	 * Secondary Constructor added to create a new graph to provide encapsulation
	 * of another graph.
	 * 
	 * @param g, the graph to be copied
	 * @requires g != null
	 */
	public Graph(Graph<NodeName, EdgeLabel> g) {
		nodeMap = g.getGraphsNodeMap();
	}

	/**
	 * Return a copy of the Graph in it's current state
	 * 
	 * @return A copy of the nodeMap
	 */
	public Map<NodeName, Node<NodeName, EdgeLabel>> getGraphsNodeMap(){
		return new HashMap<NodeName, Node<NodeName, EdgeLabel>>(nodeMap);
	}
	
	/**
	 * Gets the Node object corresponding to the Node name
	 * 
	 * @param s
	 * @requires s != null, Node must be in nodeMap
	 * @return the Node corresponding to the Node name
	 */
	public Node<NodeName, EdgeLabel> getNodeFromMap(NodeName s) {
		return nodeMap.get(s);
	}
	
	/**
	 * Adds a node to the Graph if the node doesn't exist in the Graph
	 * 
	 * @param n
	 * @requires n != null
	 * @effects nodeMap
	 * @modifies Adds the Node n to the HashMap nodeMap
	 */
	public void addNodeToGraph(Node<NodeName, EdgeLabel> n) {
		if(!nodeMap.containsKey(n.getName())) {
			nodeMap.put(n.getName(), n);
		}
	}
	
	/**
	 * Adds an Edge to a specified Node as long as it's not a duplicate meaning
	 * same endNode and Weight
	 * 
	 * @param n
	 * @param e
	 * @requires n != null, e != null
	 * @effects n
	 * @modifies Adds Edge e to the Set edgeSet for the specified Node n
	 */
	public void addEdgeToNode(Node<NodeName, EdgeLabel> n, Edge<EdgeLabel, NodeName> e) {
		if(nodeMap.containsKey(n.getName())) {
			n.addEdge(e);
		}
	}

	
	/**
	 * Provide a visual representation of the current Graph
	 * 
	 * @return String, showing all of the Node and Edge values in the Graph
	 */
	@Override
	public String toString() {
		String str = "";
		for(Node<NodeName, EdgeLabel> n : nodeMap.values()) {
			str += n.toString();
		}
		return str;
	}
	
	/**
	 * Check for whether the Representation Invariant holds
	 */
	@SuppressWarnings("unused")
	private void checkRep() {
		assert !this.nodeMap.equals(null);
		assert !this.nodeMap.containsKey(null);
		assert !this.nodeMap.containsValue(null);
	}
	
//	public static void main(String[] args) {
//		Graph g = new Graph();
//		g.addNodeToGraph(new Node("A"));
//		g.addNodeToGraph(new Node("B"));
//		g.addNodeToGraph(new Node("D"));
//		Node A = g.getNodeFromMap("A");
//		Node B = g.getNodeFromMap("B");
//		Node D = g.getNodeFromMap("D");
//		Edge C = new Edge(B,"5");
//		Edge E = new Edge(B, "10");
//		Edge F = new Edge(B, "6");
//		Edge G = new Edge(D,"3");
//		g.addEdgeToNode(A, C);
//		g.addEdgeToNode(A, G);
//		g.addEdgeToNode(A, E);
//		g.addEdgeToNode(A, F);
//		g.addEdgeToNode(B, G);
//		System.out.println(g.toString());
//		
//		for(Edge e : A.getSet()) {
//			System.out.println(e.getEndNode().toString());
//		}
//	}
}
