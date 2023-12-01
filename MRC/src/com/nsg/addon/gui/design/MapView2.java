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

public class MapView2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapView2 frame = new MapView2();
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
	public MapView2() {
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
		ImageIcon icon3 = new ImageIcon(
	            MapView.class.getResource("/resource/map3.png")
	        );
	    Image img3 = icon3.getImage();
	    Image updateImg3 = img3.getScaledInstance(610, 370, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon3 = new ImageIcon(updateImg3);
		
		ImageIcon icon2 = new ImageIcon(
	            MapView.class.getResource("/resource/info2.png")
	        );
	    Image img2 = icon2.getImage();
	    Image updateImg2 = img2.getScaledInstance(580, 65, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon2 = new ImageIcon(updateImg2);
		ImageIcon icon = new ImageIcon(
	            MapView.class.getResource("/resource/btn2.png")
	        );
	    Image img = icon.getImage();
	    Image updateImg = img.getScaledInstance(120, 65, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon = new ImageIcon(updateImg);
		ImageIcon icon5 = new ImageIcon(
	            MapView.class.getResource("/resource/title2.png")
	        );
	    Image img5 = icon5.getImage();
	    Image updateImg5 = img5.getScaledInstance(250, 40, Image.SCALE_SMOOTH);
	    ImageIcon updateIcon5 = new ImageIcon(updateImg5);
		
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
