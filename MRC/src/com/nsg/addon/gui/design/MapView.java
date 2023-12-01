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
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

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
		setType(Type.UTILITY);
		setTitle("MobileRobotController");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 455);
		setSize(750, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255,0,0,0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel contentPanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, contentPanel, 30, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, contentPanel, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, contentPanel, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, contentPanel, 0, SpringLayout.EAST, contentPane);
		contentPanel.setBackground(new Color(255,0,0,0));
		contentPane.add(contentPanel);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JPanel mapPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, mapPanel, 30, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, mapPanel, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, mapPanel, 400, SpringLayout.NORTH, contentPanel);
		mapPanel.setBackground(new Color(255,0,0,0));
		contentPanel.add(mapPanel);
		
		JPanel extraPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, extraPanel, 30, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, extraPanel, 610, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, extraPanel, 300, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, mapPanel, -6, SpringLayout.WEST, extraPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, extraPanel, 0, SpringLayout.EAST, contentPanel);
		extraPanel.setBackground(new Color(255,0,0,0));
		contentPanel.add(extraPanel);
		
		JPanel infoPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, infoPanel, 3, SpringLayout.SOUTH, mapPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, infoPanel, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, infoPanel, -10, SpringLayout.SOUTH, contentPanel);
		SpringLayout sl_mapPanel = new SpringLayout();
		mapPanel.setLayout(sl_mapPanel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setDisplayedMnemonic('0');
		lblNewLabel_2.setBackground(new Color(255, 0, 0));
		ImageIcon icon3 = new ImageIcon(
	            MapView.class.getResource("/resource/map3.png")
	        );
	    Image img3 = icon3.getImage();
	    Image updateImg3 = img3.getScaledInstance(610, 370, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon3 = new ImageIcon(updateImg3);
	    lblNewLabel_2.setIcon(updateIcon3);
		sl_mapPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 0, SpringLayout.NORTH, mapPanel);
		sl_mapPanel.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, mapPanel);
		sl_mapPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, mapPanel);
		sl_mapPanel.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, mapPanel);
		mapPanel.add(lblNewLabel_2);
		infoPanel.setBackground(new Color(255,0,0,0));
		contentPanel.add(infoPanel);
		
		JPanel btnPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnPanel, 100, SpringLayout.SOUTH, extraPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnPanel, 610, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnPanel, -10, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnPanel, 0, SpringLayout.EAST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, infoPanel, -6, SpringLayout.WEST, btnPanel);
		SpringLayout sl_infoPanel = new SpringLayout();
		infoPanel.setLayout(sl_infoPanel);
		
		JLabel lblNewLabel_41 = new JLabel("");
		lblNewLabel_41.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_41.setBackground(new Color(255,0,0,0));
		
		ImageIcon icon2 = new ImageIcon(
	            MapView.class.getResource("/resource/info2.png")
	        );
	    Image img2 = icon2.getImage();
	    Image updateImg2 = img2.getScaledInstance(580, 65, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon2 = new ImageIcon(updateImg2);
	    lblNewLabel_41.setIcon(updateIcon2);
		sl_infoPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_41, 0, SpringLayout.NORTH, infoPanel);
		sl_infoPanel.putConstraint(SpringLayout.WEST, lblNewLabel_41, 0, SpringLayout.WEST, infoPanel);
		sl_infoPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_41, 0, SpringLayout.SOUTH, infoPanel);
		sl_infoPanel.putConstraint(SpringLayout.EAST, lblNewLabel_41, 0, SpringLayout.EAST, infoPanel);
		infoPanel.add(lblNewLabel_41);
		SpringLayout sl_extraPanel = new SpringLayout();
		extraPanel.setLayout(sl_extraPanel);
		
//		JLabel lblNewLabel_3 = new JLabel("");
//		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
//		sl_extraPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 0, SpringLayout.NORTH, extraPanel);
//		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
//		lblNewLabel_3.setBackground(new Color(210, 233, 231));
//		sl_extraPanel.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, extraPanel);
//		sl_extraPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, 0, SpringLayout.SOUTH, extraPanel);
//		sl_extraPanel.putConstraint(SpringLayout.EAST, lblNewLabel_3, 0, SpringLayout.EAST, extraPanel);
//		ImageIcon icon1 = new ImageIcon(
//	            MapView.class.getResource("/resource/extra.png")
//	        );
//	    Image img1 = icon1.getImage();
//	    Image updateImg1 = img1.getScaledInstance(120, 250, Image.SCALE_SMOOTH);
//	    ImageIcon updateIcon1 = new ImageIcon(updateImg1);
//	    lblNewLabel_3.setIcon(updateIcon1);
//		extraPanel.add(lblNewLabel_3);
//		
		
		btnPanel.setBackground(new Color(255,0,0,0));
		contentPanel.add(btnPanel);
		SpringLayout sl_btnPanel = new SpringLayout();
		btnPanel.setLayout(sl_btnPanel);
		
		
		JLabel lblNewLabel_5 = new JLabel("");
		ImageIcon icon = new ImageIcon(
	            MapView.class.getResource("/resource/btn2.png")
	        );
	    Image img = icon.getImage();
	    Image updateImg = img.getScaledInstance(120, 65, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon = new ImageIcon(updateImg);
	    lblNewLabel_5.setIcon(updateIcon);
	        
		sl_btnPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 0, SpringLayout.NORTH, btnPanel);
		sl_btnPanel.putConstraint(SpringLayout.WEST, lblNewLabel_5, 0, SpringLayout.WEST, btnPanel);
		sl_btnPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_5, 0, SpringLayout.SOUTH, btnPanel);
		sl_btnPanel.putConstraint(SpringLayout.EAST, lblNewLabel_5, 0, SpringLayout.EAST, btnPanel);
		btnPanel.add(lblNewLabel_5);
		
		JPanel titlePanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, titlePanel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, titlePanel, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, titlePanel, 50, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, titlePanel, 0, SpringLayout.EAST, contentPane);
		titlePanel.setBackground(new Color(255,0,0,0));
		titlePanel.setBounds(0, 0, 450, 30);
		titlePanel.setBorder(null);
		contentPane.add(titlePanel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		//lblNewLabel.setIcon(new ImageIcon(MapView.class.getResource("/resource/title.png")));
		lblNewLabel.setBounds(18, 6, 250, 35);
		ImageIcon icon5 = new ImageIcon(
	            MapView.class.getResource("/resource/title2.png")
	        );
	    Image img5 = icon5.getImage();
	    Image updateImg5 = img5.getScaledInstance(250, 40, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon5 = new ImageIcon(updateImg5);
	    titlePanel.setLayout(null);
	    //lblNewLabel.setIcon(new ImageIcon(MapView.class.getResource("/resource/title.bmp")));
	    lblNewLabel.setIcon(updateIcon5);

		titlePanel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, contentPane);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		//lblNewLabel_1.setIcon(new ImageIcon(MapView.class.getResource("/resource/frame2.jpg")));
		ImageIcon icon4 = new ImageIcon(
	            MapView.class.getResource("/resource/frame10.jpeg")
	        );
	    Image img4 = icon4.getImage();
	    Image updateImg4 = img4.getScaledInstance(750, 550, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon4 = new ImageIcon(updateImg4);
	    lblNewLabel_1.setIcon(updateIcon4);
	}

}
