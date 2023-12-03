package com.nsg.addon.gui.design;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.nsg.addon.ADD_ON;
import com.nsg.addon.gui.event.EventManager;
import com.nsg.addon.rescue.modeling.Element;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.nsg.addon.gui.design.Map;

public class View2 extends JPanel {
	public Map mapPanel;
    
    private JPanel panel;
    private JPanel mapInfoPanel;
    private JLabel mapsizeLabel;
    private JLabel spotLabel;
    private JPanel infoPanel;
    private JLabel voiceRecBtn;
    private JLabel voiceinfo;
  


	private static final long serialVersionUID = 1L;

	public void robotUpdate(Point prev, Point curr) {
		mapPanel.robotUpdate(new Point(prev.x, mapPanel.rows - 1 - prev.y), 
				new Point(curr.x, mapPanel.rows - 1 - curr.y));
		System.out.println("ROBOT UPDATE ");
		repaint();
	}
	
	public void initElem(Point pos, Element e) {
		mapPanel.elemInitAdd(new Point(pos.x, mapPanel.rows - 1 - pos.y), e);
		repaint();
	}
	
	public void colorElem(Point pos, Element e) {
		mapPanel.elemAdd(new Point(pos.x, mapPanel.rows - 1 - pos.y), e);
		repaint();
	}
	
	public void rescueUpdate(Point pos) {	
		mapPanel.rescueUpdate(new Point(pos.x, mapPanel.rows - 1 - pos.y));

    	String s = "탐색지점 (" + pos.x + ", " + pos.y + ") 탐색 완료";
    	setVoiceInfo(s);
    	repaint();
    }
    
    
	
	/**
	 * Create the frame.
	 */
	public View2(int  m, int n) {
		setBounds(100, 100, 450, 300);
		setSize(750, 550);	
		
		panel = new JPanel();
		add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		mapInfoPanel = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, mapInfoPanel, 20, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, mapInfoPanel, 85, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, mapInfoPanel, -435, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, mapInfoPanel, -600, SpringLayout.EAST, panel);
		mapInfoPanel.setOpaque(false);
		panel.add(mapInfoPanel);
		SpringLayout sl_mapInfoPanel = new SpringLayout();
		mapInfoPanel.setLayout(sl_mapInfoPanel);
		
		mapsizeLabel = new JLabel("3 X 5");
		sl_mapInfoPanel.putConstraint(SpringLayout.NORTH, mapsizeLabel, 0, SpringLayout.NORTH, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.WEST, mapsizeLabel, 0, SpringLayout.WEST, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.SOUTH, mapsizeLabel, -45, SpringLayout.SOUTH, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.EAST, mapsizeLabel, 0, SpringLayout.EAST, mapInfoPanel);
		mapsizeLabel.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		mapsizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mapsizeLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		mapInfoPanel.add(mapsizeLabel);
		
		spotLabel = new JLabel("( 3, 2 )");
		sl_mapInfoPanel.putConstraint(SpringLayout.NORTH, spotLabel, 50, SpringLayout.NORTH, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.WEST, spotLabel, 0, SpringLayout.WEST, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.SOUTH, spotLabel, 0, SpringLayout.SOUTH, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.EAST, spotLabel, 0, SpringLayout.EAST, mapInfoPanel);
		spotLabel.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		spotLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mapInfoPanel.add(spotLabel);
	
		
		//map size : 
		mapPanel = new Map(n, m);
		int nl = mapPanel.nodeSize;
		
		sl_panel.putConstraint(SpringLayout.WEST, mapPanel, 180, SpringLayout.WEST, panel);		
		sl_panel.putConstraint(SpringLayout.NORTH, mapPanel, 115, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, mapPanel, -90, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, mapPanel, (180+nl*(n)), SpringLayout.WEST, panel);
		mapPanel.setOpaque(false);
 
		
    	panel.add(mapPanel);
    	
		infoPanel = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, infoPanel, 460, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, infoPanel, 160, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, infoPanel, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, infoPanel, 0, SpringLayout.EAST, panel);
		infoPanel.setOpaque(false);
		panel.add(infoPanel);
		
		voiceRecBtn = new JLabel("음성인식");
		voiceRecBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventManager.controlVoiceRec(e);
				EventManager.showVoiceRst(new Point(3, 4), Element.HAZARD);
			}
		});
		SpringLayout sl_infoPanel = new SpringLayout();
		sl_infoPanel.putConstraint(SpringLayout.NORTH, voiceRecBtn, 20, SpringLayout.NORTH, infoPanel);
		sl_infoPanel.putConstraint(SpringLayout.WEST, voiceRecBtn, 480, SpringLayout.WEST, infoPanel);
		sl_infoPanel.putConstraint(SpringLayout.SOUTH, voiceRecBtn, -30, SpringLayout.SOUTH, infoPanel);
		sl_infoPanel.putConstraint(SpringLayout.EAST, voiceRecBtn, -25, SpringLayout.EAST, infoPanel);
		
		voiceRecBtn.setForeground(new Color(47, 61, 48));
		voiceRecBtn.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		voiceRecBtn.setHorizontalAlignment(SwingConstants.CENTER);
		
		voiceinfo = new JLabel();
		sl_infoPanel.putConstraint(SpringLayout.NORTH, voiceinfo, 20, SpringLayout.NORTH, infoPanel);
		sl_infoPanel.putConstraint(SpringLayout.SOUTH, voiceinfo, -30, SpringLayout.SOUTH, infoPanel);
		voiceinfo.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		voiceinfo.setForeground(Color.DARK_GRAY);
		voiceinfo.setHorizontalAlignment(SwingConstants.LEFT);
		sl_infoPanel.putConstraint(SpringLayout.WEST, voiceinfo, 25, SpringLayout.WEST, infoPanel);
		sl_infoPanel.putConstraint(SpringLayout.EAST, voiceinfo, -135, SpringLayout.EAST, infoPanel);
		voiceinfo.setBackground(Color.ORANGE);
		
		infoPanel.setLayout(sl_infoPanel);
		infoPanel.add(voiceinfo);
		infoPanel.add(voiceRecBtn);
		
		JPanel background2 = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, background2, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, background2, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, background2, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, background2, 0, SpringLayout.EAST, panel);
		panel.add(background2);
		
		
		ImageIcon backicon2 = new ImageIcon(MapView.class.getResource("/resource/back1.jpg"));
	    Image back2 = backicon2.getImage();
	    Image updateBack2 = back2.getScaledInstance(750, 550, Image.SCALE_SMOOTH);
	    ImageIcon updateBackIcon2 = new ImageIcon(updateBack2);
	    SpringLayout sl_contentPane = new SpringLayout();
	    sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, this);
	    sl_contentPane.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, this);
	    sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, this);
	    sl_contentPane.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, this);
	    setLayout(sl_contentPane);
		SpringLayout sl_background2 = new SpringLayout();
		background2.setLayout(sl_background2);
		
		JLabel lblNewLabel = new JLabel("");
		sl_background2.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, background2);
		sl_background2.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, background2);
		sl_background2.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, background2);
		sl_background2.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, background2);
		lblNewLabel.setIcon(updateBackIcon2);
		background2.add(lblNewLabel);
		
		
	}
	
	 
	public void showView2() {
		setVisible(true);
	}
	
	
    
    public void setVoiceInfo(String s) {
    	voiceinfo.setText(s);
    }
	
    
    
    

    public void setMapLabel(int m, int n) {
    	String s = m + " X " + n;
    	mapsizeLabel.setText(s);
    }
    
    

    public void setSpotLabel(Point[] pos) {
    	String s = "";
    	
    	for(int i = 0; i < pos.length && pos[i] != null; i++) 
    		s += "( " + pos[i].x + ", " + pos[i].y + " )\n";
    	spotLabel.setText(s);
    }
    class JPanelSquare extends JPanel{
    	
    	int column;
    	int row;
    	String state;
    	JLabel piece = null;
    		
    	public JPanelSquare(int row, int column, String state) {
    		this.column = column;
    		this.row = row;
    		this.state = state;
    	}	
    	
    	public int getColumn() {
    		return column;
    	}
    	
    	public int getRow() {
    		return row;
    	}
    	
    	@Override
    	public Component add(Component c) {
    		piece = (JLabel) c;
    		return super.add(c);
    	}
    	
        @Override
        public void remove(Component comp) {
            piece = null;
            super.remove(comp);
        }

    	public JLabel getPiece() {
    		return piece;
    	}
    	
    	public void setPiece() {
    		
    	}
    	
    	public void setState(String state) {
    		this.state = state;
    	}
    	
    	public String getState() {
    		return state;
    	}
    		
    }
    public class Node {
        public int x;
        public int y;
        public int xloc;   //실제 좌표 위치
        public int yloc;
        public int size;
        public ImageIcon nodeImage;

        public Node(int x, int y, int xloc, int yloc, int size) {
            this.x = x;
            this.y = y;
            this.xloc = xloc;
            this.yloc = yloc;
            this.size = size;
        }

        public int getCenterX() {
            return xloc + size / 2;
        }

        public int getCenterY() {
            return yloc + size / 2;
        }
        
        public void setNodeImage(ImageIcon image) {
           this.nodeImage = image;
        }
    }

    }



