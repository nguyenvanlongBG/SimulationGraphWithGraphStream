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

import javax.imageio.ImageIO;
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

	private JPanel contentPane, panel;
	private String begin;
	private JTextField textField;
	private int countStep = 0; // đếm số bước hiện tại
	private int maxStep = 0;// số bước tối đa do người dùng nhập vào
	private DataInput dataInput;
	private demo demo = new demo();
	private JTextField jTextBeginPoint;
	private JTextField jTextEndPoint;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		setBounds(100, 100, 930, 726);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		showOpenFile();
		JLabel lblNewLabel = new JLabel("NHÓM 6");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(267, 10, 107, 37);
		contentPane.add(lblNewLabel);
		
		

		
		
		
		JList b = new JList();
		b.setLocation(206, 377);
		b.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(b);

		JLabel lblNewLabel_1 = new JLabel("Chọn điểm tiếp theo");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(23, 377, 153, 19);
		contentPane.add(lblNewLabel_1);

		JLabel CurrentNode = new JLabel("");
		CurrentNode.setBounds(161, 473, 45, 35);
		contentPane.add(CurrentNode);

		JLabel lblNewLabel_5 = new JLabel("Nhập vào số bước tối đa : \r\n");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setLabelFor(this);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(23, 288, 179, 26);
		contentPane.add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(216, 286, 96, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Số bước đã đi");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(23, 330, 132, 37);
		contentPane.add(lblNewLabel_6);

		JLabel numberCurrentStep = new JLabel("0");
		numberCurrentStep.setHorizontalAlignment(SwingConstants.CENTER);
		numberCurrentStep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberCurrentStep.setBounds(216, 328, 62, 23);
		contentPane.add(numberCurrentStep);
		
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
		panel.setBounds(408, 81, 478, 519);
		contentPane.add(panel);
		panel.setVisible(false);

		JLabel lblNewLabel_4 = new JLabel("Đỉnh hiện tại :");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(23, 471, 126, 37);
		contentPane.add(lblNewLabel_4);
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

		JButton btnNewButton_1 = new JButton("Đi tiếp");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				countStep++;
				if (countStep <= maxStep) {
					numberCurrentStep.setText("" + countStep);
					demo.setColorNode(b.getSelectedValue().toString());
					String id = begin + b.getSelectedValue().toString();
					demo.setColorEdge(id);
					

					if (!dataInput.getDanhSachDinh().get((int) b.getSelectedValue()).isEmpty()) {
						begin = b.getSelectedValue().toString();
						b.setListData(dataInput.getDanhSachDinh().get((int) b.getSelectedValue()).toArray());
						b.setSelectedIndex(0);
						b.setSize(50, 30 * dataInput.getDanhSachDinh().get((int) b.getSelectedValue()).size());
						CurrentNode.setText(b.getSelectedValue().toString());
					} else {
						JOptionPane.showMessageDialog(null, "Không có đỉnh kề với đỉnh tiếp theo. Chọn lại đỉnh từ đỉnh trước đó");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Đã đi hết số bước đưa ra");
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(190, 536, 110, 40);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Bắt đầu");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(jTextBeginPoint.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đỉnh bắt đầu");
				}
				else if(jTextEndPoint.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đỉnh kết thúc");
				}
				else if(textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số bước đi ");
				}
				else {
					String s = textField.getText();
					begin =jTextBeginPoint.getText();
					demo.setColorNode(begin);
					CurrentNode.setText(begin);
					maxStep = Integer.parseInt(s);
					b.setListData(dataInput.getDanhSachDinh().get(1).toArray());
					b.setSelectedIndex(0);
					b.setSize(50, 20 * dataInput.getDanhSachDinh().get(1).size());

				}

			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(44, 537, 85, 38);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Xuất file ảnh");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				exportImage(panel);

			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(91, 606, 153, 37);
		contentPane.add(btnNewButton_3);
		
		

		

		

	}

	private void exportImage(JPanel jPanel) {
		BufferedImage image = new BufferedImage(jPanel.getWidth(), jPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		jPanel.printAll(g);
		g.dispose();
		try {
			ImageIO.write(image, "jpg", new File("graph.jpg"));
			File file = new File("C:\\Users\\Quang Ha\\eclipse-workspace\\BaiTap\\graph.jpg");
			Desktop desktop = Desktop.getDesktop();
			if (file.exists())
				desktop.open(file);
			// ImageIO.write(image, "png", new File("Paint.png"));
		} catch (IOException exp) {
			exp.printStackTrace();
		}
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
				}
				else return;

			}
		});
	}
}
