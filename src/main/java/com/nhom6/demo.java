package com.nhom6;

import java.nio.file.AccessDeniedException;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.Viewer.ThreadingModel;;

public class demo {

	Graph graph = new SingleGraph("Đồ thị nhóm 6");
	Viewer viewer = new SwingViewer(graph, ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
	ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);

	public demo() {

	}

	public void showGraph(DataInput dataInput) {
		System.setProperty("org.graphstream.ui", "swing");
		graph.setAttribute("ui.stylesheet", styleSheet);
		graph.setAutoCreate(true);
		graph.setStrict(false);

		for (int i = 0; i < dataInput.getDanhSachDinh().size(); i++) {
			for (Integer arrays : dataInput.getDanhSachDinh().get(i)) {
				String s = "" + i + arrays;
				graph.addEdge(s, "" + i, "" + arrays, true);
			}
		}
		
		for (Node node : graph) {
			node.setAttribute("label", node.getId());
		}

	}

	protected String styleSheet = "node {" + "	shape: circle;" + "	size: 20px;" + "	fill-mode: plain;"
			+ "	fill-color: white;" + "	stroke-mode: plain;" + "	stroke-color: grey;" + "	stroke-width: 1px;" +

			"}" + "node.marked {" + "	fill-color: red;" + "}" + "node.default {" + "	fill-color: white;" + "}"
			+ "edge.marked {" + "	fill-color: green;" + "}"+ "edge.default {" + "	fill-color: black;" + "}" ;

	public void Display() {

		viewer.enableAutoLayout();

	}

	public void setColorNode(String id) {

		graph.getNode(id).setAttribute("ui.class", "marked");

	}

	public void setColorEdge(String id) {

		graph.getEdge(id).setAttribute("ui.class", "marked");

	}

	public void setColorDefault() {
		
		for (Node node : graph) {
			node.setAttribute("ui.class", "default");
		}
		graph.edges().forEach(e -> e.setAttribute("ui.class", "default"));

	}

	public ViewPanel getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(ViewPanel viewPanel) {
		this.viewPanel = viewPanel;
	}

}