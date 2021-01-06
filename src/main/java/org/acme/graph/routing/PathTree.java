package org.acme.graph.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathTree {
	private static final Logger log = LogManager.getLogger(PathTree.class);
	
	private Map<Vertex, PathNode> nodes;
	
	public PathTree(Graph graph, Vertex source) {
		this.nodes = new HashMap<Vertex, PathNode>();
		log.trace("PathTree({})", source);
		for (Vertex vertex : graph.getVertices()) {
			PathNode node = new PathNode();
			node.setCost(source == vertex ? 0.0 : Double.POSITIVE_INFINITY);
			node.setReachingEdge(null);
			node.setVisited(false);
			this.nodes.put(vertex, node);
		}
	}
	
	
	/**
	 * Récupère le chemin en remontant les relations incoming edge
	 * 
	 * @param destination
	 * @return
	 */
	protected List<Edge> getPath(Vertex destination) {
		List<Edge> result = new ArrayList<Edge>();

		Edge current = this.getNode(destination).getReachingEdge();
		do {
			result.add(current);
			current = this.getNode(current.getSource()).getReachingEdge();
		} while (current != null);

		Collections.reverse(result);
		return result;
	}
	
	public PathNode getNode(Vertex vertex) {
		return this.nodes.get(vertex);
	}
}
