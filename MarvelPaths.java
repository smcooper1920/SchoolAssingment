/**
 * 
 */
package hw6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import hw5.*;
import hw6.MarvelParser;
import hw6.MarvelParser.MalformedDataException;
/**
 * Provide a n-degree of separation data model.
 * @author Stephen Cooper
 *
 */
public class MarvelPaths {
	//Set containing all of the characters in a file
	private final Set<String> characters = new HashSet<>();
	//Map containing all of the comics along with their associated list of characters
	private final Map<String, List<String>> books = new HashMap<>();
	//The data structure holding the character and comic info
	private final Graph<String, String> g;
	/**
	 * Rep Invariant: characters != null, books != null, g != null
	 * 
	 * Abstraction Function: g represents characters as nodes and comic books as edges
	 * that link those characters. By traversing the edges we find the shortest path between
	 * characters.
	 */
	
	/**
	 * Constructor that helps provide encapsulation in the process of creating another graph
	 * 
	 * @param g
	 * @requires g != null
	 */
	public MarvelPaths(Graph<String, String> g) {
		this.g = g;
	}

	/**
	 * Base Constructor that fills the books and characters data structures
	 * using the information from marvel.tsv
	 * 
	 * @effects Creates a new Graph by importing data from a .tsv file.
	 * @throws MalformedDataException if the data is not in the format expected
	 */
	public MarvelPaths() {
		this("src/hw6/data/marvel.tsv");
	}
	
	/**
	 * Constructor that fills the books and characters data structures with the desired
	 * files data
	 * 
	 * @param file
	 * @throws MalformedDataException, if the data doesn't match the desired format
	 * @requires file != null and the file exists
	 * @effects characters and books
	 * @modifies Fills characters set with the character file data and fills the books
	 * map with the books as keys and characters as the values
	 */
	public MarvelPaths(String file) {
		try {
			MarvelParser.parseData(file, characters, books);
		} catch (MalformedDataException e) {
			e.printStackTrace();
		}
		g = new Graph<String, String>();
	}
	
	/**
	 * Provide the graph data of the current graph 
	 * 
	 * @requires g!= null
	 * @return a copy of the graph g
	 */
	public Graph<String, String> getGraph() {
		return new Graph<String, String>(g);
	}
	
	/**
	 * Finds the shortest degree of separation between two nodes
	 * 
	 * @param startingString
	 * @param endingString
	 * @return List containing the path from the starting node to the end node
	 * @throws NoSuchElementException if no path is found between the Nodes
	 */
	public List<Edge<String, String>> shortestPath(String startingString, String endingString) throws NoSuchElementException{
		Node<String, String> startingNode = new Node<String, String>(startingString);
		Node<String, String> endingNode = new Node<String, String>(endingString);
		
		Queue<Node<String, String>> children = new LinkedList<Node<String, String>>();
		Map<Node<String, String>, List<Edge<String, String>>> visitedPaths = new HashMap<>();
		
		children.add(startingNode);
		visitedPaths.put(startingNode, new ArrayList<Edge<String, String>>());
		
		while(!children.isEmpty()) {
			Node<String, String> deQdNode = children.remove();
			//Get the Dequeued Node from the Graph g
			deQdNode = g.getNodeFromMap(deQdNode.getName());
			if(deQdNode.equals(endingNode)) {
				return visitedPaths.get(deQdNode);
			}
			//Order the Edges By Character Name then By Comic Name
			Set<Edge<String, String>> deQdNodeEdgeSet = new TreeSet<Edge<String, String>>(deQdNode.getSet());
			for(Edge<String, String> crntDeQNodeEdge : deQdNodeEdgeSet) {
				Node<String, String> crntDeDNodeEdgeEndNode = crntDeQNodeEdge.getEndNode();
				if(!visitedPaths.containsKey(crntDeDNodeEdgeEndNode)) {
					List<Edge<String, String>> deDQNodePath = visitedPaths.get(deQdNode);
					List<Edge<String, String>> appendedPath = new ArrayList<>(deDQNodePath);
					appendedPath.add(crntDeQNodeEdge);
					visitedPaths.put(crntDeDNodeEdgeEndNode, appendedPath);
					children.add(crntDeDNodeEdgeEndNode);
				}
			}
		}
		return new ArrayList<>();
	}
	
	
	/**
	 * Builds the graph that we will use to find paths for later
	 * 
	 * @requires books != null, characters != null
	 * @requires All of the Nodes are in the graph
	 * @effects g, Graph
	 * @modifies Adds all of the characters as Nodes in the graph and Comics as Edges
	 */
	public void buildGraph() {
		//Add all Nodes to the Graph
		for(String s : characters) {
			g.addNodeToGraph(new Node<String, String>(s));
		}
		
		//Add all of the Edges to the Graph
		for(Map.Entry<String, List<String>> entry : books.entrySet()) {
			String currentComicBook = entry.getKey();
			List<String> listOfCharactersInBook = entry.getValue();
			//For each book find each pair of characters in that book
			for(int i = 0; i < listOfCharactersInBook.size()-1; i++) {
				for(int j = i + 1; j < listOfCharactersInBook.size(); j++) {
					String char1 = listOfCharactersInBook.get(i);
					String char2 = listOfCharactersInBook.get(j);
					Node<String, String> char1Node = g.getNodeFromMap(char1);
					Node<String, String> char2Node = g.getNodeFromMap( char2);
					char1Node.addEdge(new Edge<String, String>(char2Node, currentComicBook));
					char2Node.addEdge(new Edge<String, String>(char1Node, currentComicBook));
				}
			}
		}
	}


	public static void main(String[] args) {
		MarvelPaths mPaths = new MarvelPaths();
		mPaths.buildGraph();
//		String startCharacter = "KARNAK [INHUMAN]";
//		String endCharacter = "SPIDER-MAN/PETER PAR";
//		System.out.println(mPaths.getGraph().getNodeFromMap(startCharacter));
//		System.out.println(startCharacter + "  "+ mPaths.shortestPath(startCharacter , endCharacter ).toString());
		System.out.println(args[0] + "  "+ mPaths.shortestPath(args[0] , args[1] ).toString());
	}

}
