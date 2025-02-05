package org.acme.graph.routing;

import static org.junit.Assert.assertEquals;

import org.acme.graph.TestGraphFactory;
import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests fonctionnels sur DijkstraPathFinder
 * 
 * @author MBorne
 *
 */
public class DijkstraRegressTest {

	private Graph graph;

	private DijkstraPathFinder finder;

	@Before
	public void setUp() throws Exception {
		this.graph = TestGraphFactory.createGraph01();
		this.finder = new DijkstraPathFinder();
	}

	@Test
	public void testABFound() {
		Path path = finder.findPath(graph.findVertex("a"), graph.findVertex("b"));
		assertEquals(1, path.getPathLen());
	}

	@Test
	public void testBANotFound() {
		Path path = finder.findPath(graph.findVertex("b"), graph.findVertex("a"));
		assertEquals(0, path.getPathLen());
	}

	@Test
	public void testACFoundWithCorrectOrder() {
		Path path = finder.findPath(graph.findVertex("a"), graph.findVertex("c"));
		assertEquals(2, path.getPathLen());

		int index = 0;
		{
			Edge edge = path.getEdgeN(index++);
			assertEquals("a", edge.getSource().getId());
			assertEquals("b", edge.getTarget().getId());
		}
		{
			Edge edge = path.getEdgeN(index++);
			assertEquals("b", edge.getSource().getId());
			assertEquals("c", edge.getTarget().getId());
		}
	}
	
	@Test
	public void testBBNotFound() {
		Path path = finder.findPath(graph.findVertex("b"), graph.findVertex("b"));
		assertEquals(0, path.getPathLen());
	}
}
