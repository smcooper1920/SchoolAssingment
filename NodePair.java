package hw7;

import hw5.Node;
/**
 * @author Stephen Cooper
 *<b>NodePair</b> represents an Immutable pair of Nodes
 */
public class NodePair<NodeName extends Comparable<NodeName>, EdgeLabel extends Comparable<EdgeLabel>> {

	
	private final Node<NodeName, EdgeLabel> node1;
	private final Node<NodeName, EdgeLabel> node2;
	/**
	 * Rep Invariant: node1!=null, node2!=null
	 * 
	 * Abstraction Function: A pair of nodes that stored together
	 */
	public NodePair(Node<NodeName, EdgeLabel> node1, Node<NodeName, EdgeLabel> node2) {
		this.node1 = node1;
		this.node2 = node2;
	}
	
	
	/**
	 * Returns a copy of node1
	 * 
	 * @return node1
	 */
	public Node<NodeName, EdgeLabel> getNode1(){
		return new Node<NodeName, EdgeLabel>(node1);
	}
	
	/**
	 * Returns a copy of node2
	 * 
	 * @return node2
	 */
	public Node<NodeName, EdgeLabel> getNode2(){
		return new Node<NodeName, EdgeLabel>(node2);
	}
	
	/* 
	 * Compare this versus another object for equality
	 * 
	 * @param obj, An object that will be compared against this for equality
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof NodePair<?, ?>)) {
			return false;
		}
		NodePair<?, ?> np = (NodePair<?, ?>) obj;
		boolean same = this.node1.equals(np.node1) && this.node2.equals(np.node2);
		boolean opp = this.node1.equals(np.node2) && this.node2.equals(np.node1);
		return same || opp;
	}
	
	/* 
	 * Get the hashcode for this
	 * 
	 * @return int, which is the hashcode for this object
	 */
	@Override
	public int hashCode() {
		return this.node1.hashCode()*31 + this.node2.hashCode()*31;
	}
	
	

}
