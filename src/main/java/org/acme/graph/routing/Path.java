package org.acme.graph.routing;

import java.util.ArrayList;
import java.util.List;

import org.acme.graph.model.Edge;

public class Path {
	/**
	 * List of edges which composed the path.
	 */
	private List<Edge> edges;
	
	public Path() {
		this.edges = new ArrayList<Edge>();
	}
	
	public Path(List<Edge> edges) {
		this.edges = edges;
	}
	
	public List<Edge> getEdges() {
		return this.edges;
	}
	
	public int getPathLen() {
		return this.edges.size();
	}
	
	public Edge getEdgeN(int n) {
		return this.getEdges().get(n);
	}

}
