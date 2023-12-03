package com.nsg.addon.gui.design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;


import com.nsg.addon.ADD_ON;
import com.nsg.addon.gui.event.*;

public class View1 extends JPanel {

	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;
	private JTextField mapTextField;
	private JTextField RobotTextField;
	private JTextField spotTextField;
	private JTextField hazardTextField;
	private JTextField colorTextField;
	private JButton btnNewButton;
	

	
	public View1() {
		
		init();
		setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public void init() {	
		
		
		setBounds(100, 100, 450, 300);
		
		setSize(750, 550);
	    
	    ImageIcon backicon1 = new ImageIcon(ADD_ON.class.getResource("/resource/back2.jpg"));
	    Image back1 = backicon1.getImage();
	    Image updateBack1 = back1.getScaledInstance(750, 550, Image.SCALE_SMOOTH);
	    ImageIcon updateBackIcon1 = new ImageIcon(updateBack1);
	    SpringLayout sl_contentPane = new SpringLayout();
	    setLayout(sl_contentPane);
	    
	    JPanel panel_1 = new JPanel();
	    sl_contentPane.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, this);
	    sl_contentPane.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, this);
	    sl_contentPane.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, this);
	    sl_contentPane.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, this);
	    add(panel_1);
	    SpringLayout sl_panel_1 = new SpringLayout();
	    //panel_1.setBackground(new Color(0,0,0,0));
	    //panel_1.setForeground(new Color(0,0,0,0));
	    panel_1.setLayout(sl_panel_1);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(255,0,0,0));
	    //panel.setForeground(new Color(0,0,0,0));
	 
	    sl_panel_1.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, panel_1);
	    sl_panel_1.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, panel_1);
	    sl_panel_1.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, panel_1);
	    sl_panel_1.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, panel_1);
	    panel_1.add(panel);
	    panel.setLayout(null);
	    
	    
	    mapTextField = new JTextField(10);
	    mapTextField.setToolTipText("ex. (m n)");
	    mapTextField.setCaretColor(SystemColor.desktop);
	    mapTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 16));
	    mapTextField.setForeground(new Color(23, 33, 25));
	    mapTextField.setBackground(new Color(247, 254, 254));
	    mapTextField.setSelectionColor(SystemColor.controlHighlight);
	    mapTextField.setHorizontalAlignment(SwingConstants.CENTER);
	    mapTextField.setBounds(420, 138, 200, 33);
	    mapTextField.setBorder(null);
	    mapTextField.setColumns(20);
	    mapTextField.setEditable(true);
	    panel.add(mapTextField);
	    
	    
	    RobotTextField = new JTextField(10);
	    RobotTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 16));
	    RobotTextField.setForeground(new Color(23, 33, 25));
	    RobotTextField.setBackground(new Color(247, 254, 254));
	    RobotTextField.setToolTipText("ex. (5 2)");
	    RobotTextField.setHorizontalAlignment(SwingConstants.CENTER);
	    RobotTextField.setBounds(420, 199, 200, 33);
	    RobotTextField.setBorder(null);
	    RobotTextField.setColumns(30);
	    RobotTextField.setEditable(true);
	    panel.add(RobotTextField);
	    
	    
	    spotTextField = new JTextField(10);
	    spotTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 16));
	    spotTextField.setForeground(new Color(23, 33, 25));
	    spotTextField.setBackground(new Color(247, 254, 254));
	    spotTextField.setToolTipText("ex. ((3 4)(2 5))");
	    spotTextField.setHorizontalAlignment(SwingConstants.CENTER);
	    spotTextField.setBounds(420, 259, 200, 33);
	    spotTextField.setBorder(null);
	    spotTextField.setEditable(true);
	    spotTextField.setColumns(10);
	    panel.add(spotTextField);
	    
	    
	    hazardTextField = new JTextField(10);
	    hazardTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 16));
	    hazardTextField.setForeground(new Color(23, 33, 25));
	    hazardTextField.setBackground(new Color(247, 254, 254));
	    hazardTextField.setToolTipText("ex. ((3 4)(2 5))");
	    hazardTextField.setHorizontalAlignment(SwingConstants.CENTER);
	    hazardTextField.setBounds(420, 323, 200, 33);
	    hazardTextField.setBorder(null);
	    hazardTextField.setEditable(true);
	    hazardTextField.setColumns(10);
	    panel.add(hazardTextField);
	    
	    
	    colorTextField = new JTextField(10);
	    colorTextField.setEditable(true);
	    colorTextField.setToolTipText("ex. ((3 4)(2 5))");
	    colorTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 16));
	    colorTextField.setForeground(new Color(23, 33, 25));
	    colorTextField.setBackground(new Color(247, 254, 254));
	    colorTextField.setHorizontalAlignment(SwingConstants.CENTER);
	    colorTextField.setBounds(420, 383, 200, 33);
	    colorTextField.setBorder(null);
	    colorTextField.setColumns(10);
	    panel.add(colorTextField);
	    
	    
	    btnNewButton = new JButton("");
	    btnNewButton.setBackground(new Color(0,0,0,0));
	    btnNewButton.setBounds(583, 467, 98, 32);
	    btnNewButton.setBorder(null);
	    btnNewButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		String[] str = {mapTextField.getText(), RobotTextField.getText(), spotTextField.getText(), colorTextField.getText(), hazardTextField.getText()};
	    		new EventManager().startView2(str);
	    		//ADD_ON.getInstance().rescue();
	    	}
            
        });
	   
	    
	    
	    panel.add(btnNewButton);
	    
	    
	    JPanel StartPanel = new JPanel();
	    sl_panel_1.putConstraint(SpringLayout.NORTH, StartPanel, 0, SpringLayout.NORTH, panel_1);
	    sl_panel_1.putConstraint(SpringLayout.WEST, StartPanel, 0, SpringLayout.WEST, panel_1);
	    sl_panel_1.putConstraint(SpringLayout.SOUTH, StartPanel, 0, SpringLayout.SOUTH, panel_1);
	    sl_panel_1.putConstraint(SpringLayout.EAST, StartPanel, 0, SpringLayout.EAST, panel_1);
	    panel_1.add(StartPanel);
	    
	    
	    JLabel BackgroundLabel = new JLabel("");
	    BackgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    BackgroundLabel.setForeground(new Color(0,0,0,0));
	    
	    
	    SpringLayout sl_StartPanel = new SpringLayout();
	    sl_StartPanel.putConstraint(SpringLayout.NORTH, BackgroundLabel, 0, SpringLayout.NORTH, StartPanel);
	    sl_StartPanel.putConstraint(SpringLayout.WEST, BackgroundLabel, 0, SpringLayout.WEST, StartPanel);
	    sl_StartPanel.putConstraint(SpringLayout.SOUTH, BackgroundLabel, 0, SpringLayout.SOUTH, StartPanel);
	    sl_StartPanel.putConstraint(SpringLayout.EAST, BackgroundLabel, 0, SpringLayout.EAST, StartPanel);
	    StartPanel.setLayout(sl_StartPanel);

	    StartPanel.add(BackgroundLabel);
	    
	    BackgroundLabel.setIcon(updateBackIcon1);

	}
}


