package org.acme.graph.cli;

import java.io.File;

import org.acme.graph.io.GraphReader;
import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Vertex;
import org.acme.graph.routing.DijkstraPathFinder;
import org.acme.graph.routing.Path;

/**
 * 
 * Application en ligne de commande pour le calcul d'un plus chemin entre deux
 * points.
 * 
 * @author MBorne
 *
 */
public class FindPath {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.out.println("Usage : <path> <source> <target>");
			System.out.println("Vous pouvez configurer eclipse avec l'une des options suivante :");
			System.out.println("${project_loc}/src/test/resources/graph/01.xml a c");
			System.out.println("${project_loc}/src/test/resources/route500/idf/troncon_route.shp 1 1000");
			System.exit(1);
		}
		// Chargement du graph...
		File graphFile = new File(args[0]);
		if (!graphFile.exists()) {
			System.err.println("file not found : " + graphFile.getAbsolutePath());
		}
		Graph graph = GraphReader.read(graphFile);

		// Récupération source et target
		Vertex source = graph.findVertex(args[1]);
		if (source == null) {
			System.err.println("vertex " + args[1] + " not found");
		}
		Vertex target = graph.findVertex(args[2]);
		if (target == null) {
			System.err.println("vertex " + args[2] + " not found");
		}

		DijkstraPathFinder pathFinder = new DijkstraPathFinder(graph);
		Path pathEdges = pathFinder.findPath(source, target);
		if (pathEdges.getPathLen() == 0) {
			System.err.println("path not found");
			return;
		}
		System.out.println("Chemin trouvé : ");
		for (Edge pathEdge : pathEdges.getEdges()) {
			System.out.println(pathEdge.getSource().getId() + " -> " + pathEdge.getTarget().getId());
		}
	}
}
