package com.nhom6;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View extends JFrame {

	private JPanel contentPane, panel, panel_1;
	private String begin;
	private JTextField textField;
	private int countStep = 0; // đếm số bước hiện tại
	private int maxStep = 0;// số bước tối đa do người dùng nhập vào
	private DataInput dataInput;
	private demo demo = new demo();
	private JTextField jTextBeginPoint;
	private JTextField jTextEndPoint;
	private JTextField jTextCurrentPath;
	private String currentPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1433, 794);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		showOpenFile();
		JLabel lblNewLabel = new JLabel("NHÓM 6");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(368, 10, 107, 37);
		contentPane.add(lblNewLabel);

		JList b = new JList();
		b.setSize(0, 0);
		b.setLocation(206, 455);
		b.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(b);

		JLabel CurrentNode = new JLabel("");
		CurrentNode.setHorizontalAlignment(SwingConstants.CENTER);
		CurrentNode.setBounds(165, 550, 45, 35);
		contentPane.add(CurrentNode);

		JLabel jLabelBeginPoint = new JLabel("Đỉnh bắt đầu");
		jLabelBeginPoint.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelBeginPoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLabelBeginPoint.setBounds(49, 205, 134, 26);
		contentPane.add(jLabelBeginPoint);

		jTextBeginPoint = new JTextField();
		jTextBeginPoint.setHorizontalAlignment(SwingConstants.CENTER);
		jTextBeginPoint.setBounds(216, 205, 96, 26);
		contentPane.add(jTextBeginPoint);
		jTextBeginPoint.setColumns(10);

		JLabel jLabelEndPoint = new JLabel("Đỉnh kết thúc");
		jLabelEndPoint.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelEndPoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLabelEndPoint.setBounds(44, 241, 139, 26);
		contentPane.add(jLabelEndPoint);

		jTextEndPoint = new JTextField();
		jTextEndPoint.setHorizontalAlignment(SwingConstants.CENTER);
		jTextEndPoint.setBounds(216, 241, 96, 26);
		contentPane.add(jTextEndPoint);
		jTextEndPoint.setColumns(10);

		panel = new JPanel(new GridLayout());
		panel.setBackground(Color.WHITE);
		panel.setBounds(606, 65, 791, 637);
		contentPane.add(panel);
		panel.setVisible(false);
		JButton createGraph = new JButton("Tạo đồ thị");

		createGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				demo.Display();
				panel.setVisible(true);

			}
		});
		createGraph.setFont(new Font("Tahoma", Font.PLAIN, 14));
		createGraph.setBounds(44, 158, 139, 37);
		contentPane.add(createGraph);

		JButton btnNewButton_3 = new JButton("Xuất file ảnh");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				exportImage(panel);

			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(416, 304, 160, 33);
		contentPane.add(btnNewButton_3);

		JButton btnYourPath = new JButton("Đường đi của bạn");
		btnYourPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentPath = jTextBeginPoint.getText();
				jTextCurrentPath.setText(currentPath);
				if (validation(jTextBeginPoint, jTextEndPoint, dataInput.getMaxDinh())) {
					demo.setColorDefault();
					showYourPath();
				}

			}
		});
		btnYourPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnYourPath.setBounds(226, 304, 160, 33);
		contentPane.add(btnYourPath);

		panel_1 = new JPanel();
		panel_1.setBounds(10, 349, 322, 353);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(210, 10, 96, 35);
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Nhập vào số bước tối đa : \r\n");
		lblNewLabel_5.setBounds(21, 12, 179, 26);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setLabelFor(this);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_6 = new JLabel("Số bước đã đi");
		lblNewLabel_6.setBounds(21, 48, 132, 37);
		panel_1.add(lblNewLabel_6);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel numberCurrentStep = new JLabel("0");
		numberCurrentStep.setBounds(210, 55, 62, 23);
		panel_1.add(numberCurrentStep);
		numberCurrentStep.setHorizontalAlignment(SwingConstants.CENTER);
		numberCurrentStep.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel jLaberCurrentPath = new JLabel("Đường đi của bạn");
		jLaberCurrentPath.setBounds(375, 378, 166, 35);
		contentPane.add(jLaberCurrentPath);
		jLaberCurrentPath.setHorizontalAlignment(SwingConstants.CENTER);
		jLaberCurrentPath.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jTextCurrentPath = new JTextField();
		jTextCurrentPath.setBounds(368, 439, 201, 72);
		contentPane.add(jTextCurrentPath);
		jTextCurrentPath.setColumns(10);

		JButton btnShortestPath = new JButton("Shortest Path");
		btnShortestPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (validation(jTextBeginPoint, jTextEndPoint, dataInput.getMaxDinh())) {
					demo.setColorDefault();
					BFS bfs = new BFS(dataInput);
					bfs.bfs(Integer.parseInt(jTextBeginPoint.getText()));
					int beginPoint = Integer.parseInt(jTextEndPoint.getText());
					currentPath = jTextEndPoint.getText();
					while (beginPoint != Integer.parseInt(jTextBeginPoint.getText())) {
						currentPath = bfs.getP()[beginPoint] + "-->" + currentPath;
						demo.setColorEdge(bfs.getP()[beginPoint] + "" + beginPoint);
						beginPoint = bfs.getP()[beginPoint];
						demo.setColorNode("" + beginPoint);
					}
					demo.setColorNode(jTextEndPoint.getText());
					jTextCurrentPath.setText(currentPath);

				}

			}
		});

		btnShortestPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShortestPath.setBounds(29, 304, 160, 33);
		contentPane.add(btnShortestPath);

		JButton btnNewButton_1 = new JButton("Đi tiếp");
		btnNewButton_1.setBounds(196, 265, 110, 40);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setVisible(true);
		currentPath = jTextBeginPoint.getText();
		btnNewButton_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (btnNewButton_1.isEnabled()) {
					countStep++;

					if (countStep <= maxStep) {
						numberCurrentStep.setText("" + countStep);
						demo.setColorNode(b.getSelectedValue().toString());
						String id = begin + b.getSelectedValue().toString();
						demo.setColorEdge(id);

						if (!dataInput.getDanhSachDinh().get((int) b.getSelectedValue()).isEmpty()) {
							if (b.getSelectedValue().toString().equals(jTextEndPoint.getText())) {
								JOptionPane.showMessageDialog(null, "Bạn đã đi đến đỉnh cuối cùng");
								if (!isContinute()) {
									btnNewButton_1.setEnabled(false);
								}

							}
							currentPath = currentPath + "-->" + b.getSelectedValue();
							jTextCurrentPath.setText(currentPath);
							begin = b.getSelectedValue().toString();
							b.setListData(dataInput.getDanhSachDinh().get(Integer.parseInt(begin)).toArray());
							b.setSelectedIndex(0);
							b.setSize(50, 30 * dataInput.getDanhSachDinh().get(Integer.parseInt(begin)).size());
							CurrentNode.setText(begin);

						} else {
							JOptionPane.showMessageDialog(null,
									"Không có đỉnh kề với đỉnh tiếp theo. Chọn lại đỉnh từ đỉnh trước đó");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Đã đi hết số bước đưa ra");
					}
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnNewButton_2 = new JButton("Bắt đầu");
		btnNewButton_2.setBounds(54, 266, 85, 38);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setEnabled(true);
				countStep = 0;
				numberCurrentStep.setText("0");
				demo.setColorDefault();
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số bước đi ");
				} else {
					String s = textField.getText();
					begin = jTextBeginPoint.getText();
					demo.setColorNode(begin);
					CurrentNode.setText(begin);
					maxStep = Integer.parseInt(s);
					b.setListData(dataInput.getDanhSachDinh().get(Integer.parseInt(begin)).toArray());
					b.setSelectedIndex(0);
					b.setSize(50, 20 * dataInput.getDanhSachDinh().get(Integer.parseInt(begin)).size());

				}

			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_4 = new JLabel("Đỉnh hiện tại :");
		lblNewLabel_4.setBounds(21, 198, 126, 37);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1 = new JLabel("Chọn điểm tiếp theo");
		lblNewLabel_1.setBounds(21, 105, 153, 19);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

	}

	private void exportImage(JPanel jPanel) {
		BufferedImage image = new BufferedImage(jPanel.getWidth(), jPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		jPanel.printAll(g);
		g.dispose();
		try {
			ImageIO.write(image, "jpg", new File("graph.jpg"));
			String currentPathString = System.getProperty("user.dir"); // lấy đường dẫn hiện tại của hệ thống
			File file = new File(currentPathString + "\\graph.jpg");
			Desktop desktop = Desktop.getDesktop();
			if (file.exists())
				desktop.open(file);
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}

	private void showYourPath() {
		panel_1.setVisible(true);
	}

	private void showOpenFile() {
		JLabel fileName = new JLabel("File Name");
		fileName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileName.setHorizontalAlignment(SwingConstants.CENTER);
		fileName.setBounds(216, 81, 96, 44);
		contentPane.add(fileName);
		JButton chooseFile = new JButton("Choose File");
		chooseFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chooseFile.setBounds(44, 81, 139, 44);
		contentPane.add(chooseFile);
		final JFileChooser fileDialog = new JFileChooser();

		chooseFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int returnVal = fileDialog.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileDialog.getSelectedFile();
					fileName.setText("" + file.getName());
					dataInput = new DataInput(fileDialog.getSelectedFile().toString());

					demo.showGraph(dataInput);
					panel.add(demo.getViewPanel());
				} else
					return;

			}

		});
	}

	private boolean validation(JTextField jTextBegin, JTextField jTextEnd, int maxPoint) {

		String begin = jTextBegin.getText();
		for (int i = 0; i < begin.length(); i++) {
			if (begin.charAt(i) < '0' || begin.charAt(i) > '9') {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số (0->9), không nhập kí tự khác");
				return false;
			}
		}
		String end = jTextEnd.getText();
		for (int i = 0; i < end.length(); i++) {
			if (end.charAt(i) < '0' || end.charAt(i) > '9') {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số (0->9), không nhập kí tự khác");
				return false;
			}
		}

		if (Integer.parseInt(end) > maxPoint || Integer.parseInt(begin) > maxPoint) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập điểm nhỏ hơn " + (maxPoint + 1));
			return false;
		}

		else if (jTextBeginPoint.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đỉnh bắt đầu");
			return false;
		} else if (jTextEndPoint.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đỉnh kết thúc");
			return false;
		}

		return true;
	}

	private boolean isContinute() {
		int n = JOptionPane.showConfirmDialog(panel, "Bạn có muốn tiếp tục chọn đường không ?", "Alert",
				JOptionPane.YES_NO_OPTION);
		return n == JOptionPane.YES_OPTION;

	}

}
