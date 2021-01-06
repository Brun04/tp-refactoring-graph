package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

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
