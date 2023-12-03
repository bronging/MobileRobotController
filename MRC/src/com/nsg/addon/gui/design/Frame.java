package com.nsg.addon.gui.design;


import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

import com.nsg.addon.gui.event.*;
import com.nsg.addon.ADD_ON;
import com.nsg.addon.gui.design.View1;
import com.nsg.addon.gui.design.View2;

@SuppressWarnings("unused")
public class Frame extends JFrame{
	
	private View1 view1;
	public View2 view2;
	
	public Frame() {
		setType(Type.UTILITY);
		setTitle("MobileRobotController");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		
		setSize(750, 550);
	}
	
	public void setView1() {
		view1 = new View1();
		view1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(view1);
		setVisible(true);
	}
	
	public void setView2(int m, int n) {
		view2 = new View2(m, n);
		setContentPane(view2);
		view2.setVisible(true);
		setVisible(true);
	}	
}