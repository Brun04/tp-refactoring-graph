package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * 
 * Un graphe matérialisé par une liste de sommets et d'arcs
 * 
 * @author MBorne
 *
 */
public class Graph {
	/**
	 * Liste des sommets
	 */
	private List<Vertex> vertices = new ArrayList<Vertex>();

	/**
	 * Liste des arcs
	 */
	private List<Edge> edges = new ArrayList<Edge>();

	/**
	 * Récupération de la liste sommets
	 * 
	 * @return
	 */
	public List<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * Recherche d'un sommet par identifiant
	 * 
	 * @param id
	 * @return
	 */
	public Vertex findVertex(String id) {
		for (Vertex vertex : vertices) {
			if (vertex.getId().equals(id)) {
				return vertex;
			}
		}
		return null;
	}

	/**
	 * Recherche d'un sommet par coordonnées
	 * 
	 * @param coordinate
	 * @return
	 */
	public Vertex findVertex(Coordinate coordinate) {
		for (Vertex vertex : vertices) {
			Coordinate candidate = vertex.getCoordinate();
			if (candidate != null && candidate.equals(coordinate)) {
				return vertex;
			}
		}
		return null;
	}

	/**
	 * Récupération de la liste des arcs
	 * 
	 * @return
	 */
	public List<Edge> getEdges() {
		return edges;
	}

	/**
	 * Create a vertex and add it to the graph.
	 * @param coordinate
	 * @param id
	 * @return
	 */
	public Vertex createVertex(Coordinate coordinate, String id) {
		assert(coordinate != null);
		Vertex v = new Vertex();
		v.setCoordinate(coordinate);
		v.setId(id);
		this.vertices.add(v);
		return v;
	}
	
	/**
	 * Create a new edge and add it to the graph.
	 * @param source
	 * @param target
	 * @param id
	 * @return
	 */
	public Edge createEdge(Vertex source, Vertex target, String id) {
		assert(source != null && target != null);
		Edge e = new Edge(source, target);
		e.setId(id);
		this.edges.add(e);
		return e;
	}

}
