package org.acme.graph.model;

import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class EdgeTest {

	@Test
	public void testInAndOutEdges() {
		Graph graph = new Graph();
		Vertex a = graph.createVertex(new Coordinate(0.0, 0.0), "a");
		Vertex b = graph.createVertex(new Coordinate(1.0, 0.0), "b");
		Edge e = graph.createEdge(a, b, "ab");
		
		ArrayList<Edge> aOutEdges = (ArrayList<Edge>)a.getOutEdges();
		ArrayList<Edge> bInEdges = (ArrayList<Edge>)b.getInEdges();
		assertEquals(aOutEdges.get(0), e);
		assertEquals(bInEdges.get(0), e);
	}
	
	@Test
	public void testGetGeometry() {
		Graph graph = new Graph();
		Vertex a = graph.createVertex(new Coordinate(0.0, 0.0), "a");
		Vertex b = graph.createVertex(new Coordinate(1.0, 0.0), "b");
		Edge e = graph.createEdge(a, b, "ab");
		
		LineString lineString = e.getGeometry();
		assertEquals(new Coordinate(0.0, 0.0), lineString.getCoordinateN(0));
		assertEquals(new Coordinate(1.0, 0.0), lineString.getCoordinateN(1));
	}
	
	@Test
	public void testToString() {
		Graph graph = new Graph();
		Vertex a = graph.createVertex(new Coordinate(0.0, 0.0), "a");
		Vertex b = graph.createVertex(new Coordinate(1.0, 0.0), "b");
		Edge e = graph.createEdge(a, b, "ab");
		
		assertEquals("ab (a->b)", e.toString());
	}
}
