package com.nhom6;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

    public class demo {
    	public static void main(String args[]) {
    		System.setProperty("org.graphstream.ui", "swing"); 
    		Graph graph = new SingleGraph("example");
    		graph.display();
    		Node A = graph.addNode( "A" );
    		Node B = graph.addNode( "B" );
    		Node C = graph.addNode( "C" );
    		Node D = graph.addNode( "D" );
    		Node E = graph.addNode( "E" );
    		graph.setAutoCreate(true);

    		graph.addEdge( "AB1", "A", "B", true );
    		graph.addEdge( "AB2", "B", "A", true );
    		graph.addEdge( "BC", "B", "C",true );
    		graph.addEdge( "CD", "C", "D", true );
    		graph.addEdge( "DA", "D", "A",true );
    		graph.addEdge( "DE", "D", "E", true );
    		graph.addEdge( "EB", "E", "B", true );
    		graph.addEdge( "BB", "B", "B", true );
    		

    		A.setAttribute("label", "A");
    	
    		B.setAttribute("label", "B");
    		C.setAttribute("label", "C");
    		D.setAttribute("label", "D");
    		E.setAttribute("label", "E");
    	
    	}
    
    }