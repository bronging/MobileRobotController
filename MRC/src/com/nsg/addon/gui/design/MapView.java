package com.nsg.addon.gui.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SpringLayout;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MapView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapView frame = new MapView();
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
	public MapView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel titlePanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, titlePanel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, titlePanel, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, titlePanel, 24, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, titlePanel, 440, SpringLayout.WEST, contentPane);
		titlePanel.setBounds(0, 0, 450, 30);
		titlePanel.setBorder(null);
		contentPane.add(titlePanel);
		SpringLayout sl_titlePanel = new SpringLayout();
		titlePanel.setLayout(sl_titlePanel);
		
		JLabel lblNewLabel = new JLabel("Mobile Robot Controller");
		sl_titlePanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, titlePanel);
		sl_titlePanel.putConstraint(SpringLayout.WEST, lblNewLabel, 145, SpringLayout.WEST, titlePanel);
		titlePanel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 15));
		
		JPanel contentPanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, contentPanel, 30, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, contentPanel, 0, SpringLayout.WEST, titlePanel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, contentPanel, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, contentPanel, 440, SpringLayout.WEST, contentPane);
		contentPane.add(contentPanel);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JPanel mapPanel = new JPanel();
		mapPanel.setBackground(SystemColor.info);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, mapPanel, 1, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, mapPanel, 1, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, mapPanel, 173, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, mapPanel, 332, SpringLayout.WEST, contentPanel);
		contentPanel.add(mapPanel);
		
		JPanel extraPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, extraPanel, 0, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, extraPanel, 5, SpringLayout.EAST, mapPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, extraPanel, 174, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, extraPanel, 0, SpringLayout.EAST, contentPanel);
		contentPanel.add(extraPanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(SystemColor.window);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, infoPanel, 6, SpringLayout.SOUTH, mapPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, infoPanel, 0, SpringLayout.WEST, mapPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, infoPanel, 0, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, infoPanel, 331, SpringLayout.WEST, mapPanel);
		contentPanel.add(infoPanel);
		
		JPanel btnPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnPanel, 6, SpringLayout.SOUTH, extraPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnPanel, 0, SpringLayout.WEST, extraPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnPanel, 0, SpringLayout.SOUTH, infoPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnPanel, 0, SpringLayout.EAST, extraPanel);
		contentPanel.add(btnPanel);
	}

}
