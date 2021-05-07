package com.nhom6;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1060, 691);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DataInput dataInput = new DataInput();
		//System.setProperty("org.graphstream.ui", "swing");
		Graph graph = new MultiGraph("embedded");
		Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		// ...
		ViewPanel view = (ViewPanel) viewer.addDefaultView(false);   // false indicates "no JFrame".
		// ...
		viewer.enableAutoLayout();

		graph.setAttribute("ui.stylesheet", styleSheet);
		graph.setAutoCreate(true);
		graph.setStrict(false);
		//graph.display(false);

		for (ArrayList<Integer> arrays : dataInput.getDanhSachDinh()) {
			for (int i = 1; i < arrays.size(); i++) {
				String s = "" + arrays.get(0) + arrays.get(i);
				graph.addEdge(s, "" + arrays.get(0), "" + arrays.get(i), true);
			}
		}

		for (Node node : graph) {
			node.setAttribute("label", node.getId());
		}
		//frame.getContentPane().add((Component) view);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(444, 144, 537, 375);
		panel.setSize(500, 500);
		frame.getContentPane().add(panel);
		
		panel.add(view);
		
		
		
	
		
	}
	
	protected String styleSheet = "node {" + "	shape: circle;" + "	size: 20px;" + "	fill-mode: plain;"
			+ "	fill-color: white;" + "	stroke-mode: plain;" + "	stroke-color: grey;" + "	stroke-width: 1px;" +

			"}" + "node.marked {" + "	fill-color: red;" + "}";
}
