package com.nsg.addon.gui.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JButton;

public class MainView {

	private JFrame frame;
	private JTextField mapField;
	private JTextField startField;
	private JTextField spotField;
	private JTextField hazardField;
	private JTextField colorblobField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
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
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		frame.getContentPane().add(titlePanel);
		titlePanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Mobile Robot Controller");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 15));
		titlePanel.add(lblNewLabel);
		
		JPanel formPanel = new JPanel();
		formPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		frame.getContentPane().add(formPanel);
		formPanel.setLayout(new GridLayout(0, 2, 10, 3));
		
		JLabel MapLabel = new JLabel("Map");
		MapLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		formPanel.add(MapLabel);
		
		mapField = new JTextField();
		mapField.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(mapField);
		mapField.setColumns(10);
		
		JLabel StartLabel = new JLabel("Start");
		StartLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		formPanel.add(StartLabel);
		
		startField = new JTextField();
		startField.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(startField);
		startField.setColumns(10);
		
		JLabel SpotLabel = new JLabel("Spot");
		SpotLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		formPanel.add(SpotLabel);
		
		spotField = new JTextField();
		spotField.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(spotField);
		spotField.setColumns(10);
		
		JLabel HazardLabel = new JLabel("Hazard");
		HazardLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		formPanel.add(HazardLabel);
		
		hazardField = new JTextField();
		hazardField.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(hazardField);
		hazardField.setColumns(10);
		
		JLabel ColorBlobLabel = new JLabel("ColorBlob");
		ColorBlobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		formPanel.add(ColorBlobLabel);
		
		colorblobField = new JTextField();
		colorblobField.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(colorblobField);
		colorblobField.setColumns(10);
		
		JPanel btnPanel = new JPanel();
		frame.getContentPane().add(btnPanel);
		
		JButton createBtn = new JButton("create");
		btnPanel.add(createBtn);
	}

}
