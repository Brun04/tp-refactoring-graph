package org.acme.graph.routing;

import java.util.Collection;

import org.acme.graph.model.Edge;
import org.acme.graph.model.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Utilitaire pour le calcul du plus court chemin dans un graphe
 * 
 * @author MBorne
 *
 */
public class DijkstraPathFinder {

	private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);
	
	private PathTree pathTree;

	public DijkstraPathFinder() {
	}

	/**
	 * Calcul du plus court chemin entre une origine et une destination
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public Path findPath(Vertex origin, Vertex destination) {
		log.info("findPath({},{})...",origin,destination);
		Vertex current;
		this.pathTree = new PathTree(origin);
		while ((current = findNextVertex()) != null) {
			visit(current);
			if (pathTree.isReached(destination)) {
				log.info("findPath({},{}) : path found",origin,destination);
				return new Path(pathTree.getPath(destination));
			}
		}
		log.info("findPath({},{}) : path not found",origin,destination);
		return new Path();
	}

	/**
	 * Parcourt les arcs sortants pour atteindre les sommets avec le meilleur coût
	 * 
	 * @param vertex
	 */
	private void visit(Vertex vertex) {
		log.trace("visit({})", vertex);
		Collection<Edge> outEdges = vertex.getOutEdges();
		/*
		 * On étudie chacun des arcs sortant pour atteindre de nouveaux sommets ou
		 * mettre à jour des sommets déjà atteint si on trouve un meilleur coût
		 */
		for (Edge outEdge : outEdges) {
			Vertex reachedVertex = outEdge.getTarget();
			/*
			 * Convervation de arc permettant d'atteindre le sommet avec un meilleur coût
			 * sachant que les sommets non atteint ont pour coût "POSITIVE_INFINITY"
			 */
			double newCost = pathTree.getNode(vertex).getCost() + outEdge.getCost();
			PathNode reachedNode = pathTree.getOrCreateNode(reachedVertex);
			if (newCost < reachedNode.getCost()) {
				reachedNode.setCost(newCost);
				reachedNode.setReachingEdge(outEdge);
			}
		}
		/*
		 * On marque le sommet comme visité
		 */
		pathTree.getNode(vertex).setVisited(true);
	}

	/**
	 * Recherche le prochain sommet à visiter. Dans l'algorithme de Dijkstra, ce
	 * sommet est le sommet non visité le plus proche de l'origine du calcul de plus
	 * court chemin.
	 * 
	 * @return
	 */
	private Vertex findNextVertex() {
		double minCost = Double.POSITIVE_INFINITY;
		Vertex result = null;
		for (Vertex vertex : pathTree.getReachedVertices()) {
			PathNode node = pathTree.getOrCreateNode(vertex);
			// sommet déjà visité?
			if (node.isVisited()) {
				continue;
			}
			// sommet non atteint?
			if (node.getCost() == Double.POSITIVE_INFINITY) {
				continue;
			}
			// sommet le plus proche de la source?
			if (node.getCost() < minCost) {
				result = vertex;
			}
		}
		return result;
	}
	
	

}
