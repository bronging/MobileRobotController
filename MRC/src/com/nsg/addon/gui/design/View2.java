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
import com.nsg.addon.rescue.control.RobotController;
import com.nsg.addon.rescue.modeling.Direction;
import com.nsg.addon.rescue.modeling.Element;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.nsg.addon.gui.design.Map;
import com.nsg.addon.gui.design.Map.Elem;

public class View2 extends JPanel {
	public Map mapPanel;
    
    private JPanel panel;
    private JPanel mapInfoPanel;
    private JLabel mapsizeLabel;
    private JLabel spotLabel;
    private JPanel infoPanel;
    private JLabel voiceRecBtn;
    private JLabel voiceinfo;
    private Point prevRobot;


	private static final long serialVersionUID = 1L;

	public void start() {
		mapPanel.start();	
	}
	
	public void rotateRobot(Point pos, Direction d) {
		mapPanel.iconPanel[mapPanel.rows-1-pos.y][pos.x].getElem().p.setIcon(null);
		ImageIcon icon = new ImageIcon();
		
		switch(d){
		case SOUTH:
			icon = mapPanel.robotIcons[0];
			break;
		case WEST:
			icon = mapPanel.robotIcons[1];
			break;
		case NORTH:
			icon = mapPanel.robotIcons[2];
			break;
		case EAST:
			icon = mapPanel.robotIcons[3];
			break;
		}
		
		Image img = icon.getImage();
	    Image updateImg = img.getScaledInstance(mapPanel.nodeSize, mapPanel.nodeSize, Image.SCALE_SMOOTH);
	    ImageIcon nicon = new ImageIcon(updateImg);	
	    
	    mapPanel.iconPanel[mapPanel.rows - 1 - pos.y][pos.x].getElem().p.setIcon(nicon);
		
	}
	
	public void robotUpdate(Point curr) {
		mapPanel.iconPanel[mapPanel.rows-1-prevRobot.y][prevRobot.x].getElem().p.setIcon(null);
		
		Image img = mapPanel.icons[0].getImage();
	    Image updateImg = img.getScaledInstance(mapPanel.nodeSize, mapPanel.nodeSize, Image.SCALE_SMOOTH);
	    ImageIcon nicon = new ImageIcon(updateImg);	
	    
	    mapPanel.iconPanel[mapPanel.rows - 1 - curr.y][curr.x].getElem().p.setIcon(nicon);
		
		prevRobot = curr;
		
		System.out.println("ROBOT UPDATE ");
		
	}
	
	public void initElem(Point pos, Element e) {
		mapPanel.elemInitAdd(new Point(pos.x, mapPanel.rows - 1 - pos.y), e);
		mapPanel.repaint();
		repaint();
		
		if(e == Element.ROBOT) 
			prevRobot = pos;
	}
	
	public void colorElem(Point pos, Element e) {
		ImageIcon icon = new ImageIcon(); 
		
		switch(e) {
			case ROBOT:
				icon = mapPanel.icons[0]; 
				break;
			case SEARCHPOINT:
				icon = mapPanel.icons[1];
				break;
			case COLORBLOB:
				icon = mapPanel.icons[2];
				break;
			case HAZARD:
				icon = mapPanel.icons[3];
				break;
		}
		
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(mapPanel.nodeSize, mapPanel.nodeSize, Image.SCALE_SMOOTH);
	    ImageIcon nicon = new ImageIcon(updateImg);	
	    mapPanel.iconPanel[mapPanel.rows - 1 - pos.y][pos.x].getElem().p.setIcon(nicon);
		mapPanel.repaint();
		repaint();
	}
	
	public void rescueUpdate(Point pos) {	

		ImageIcon icon  = mapPanel.icons[1];
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(mapPanel.nodeSize, mapPanel.nodeSize, Image.SCALE_SMOOTH);
	    ImageIcon nicon = new ImageIcon(updateImg);	
	    mapPanel.iconPanel[mapPanel.rows - 1 - pos.y][pos.x].getElem().p.setIcon(nicon);
		mapPanel.repaint();
	
    	String s = "탐색지점 (" + pos.x + ", " + pos.y + ") 탐색 완료";
    	setVoiceInfo(s);
    	repaint();
    }
    
    public View2(int m, int n) {   	
    	init(m, n);
    	
    }
	
	/**
	 * Create the frame.
	 */
	public void init(int  m, int n) {
		setBounds(100, 100, 450, 300);
		setSize(750, 550);	
		
		panel = new JPanel();
		add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		mapInfoPanel = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, mapInfoPanel, 20, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, mapInfoPanel, 85, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, mapInfoPanel, -400, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, mapInfoPanel, -600, SpringLayout.EAST, panel);
		mapInfoPanel.setOpaque(false);
		panel.add(mapInfoPanel);
		SpringLayout sl_mapInfoPanel = new SpringLayout();
		mapInfoPanel.setLayout(sl_mapInfoPanel);
		
		mapsizeLabel = new JLabel("3 X 5");
		sl_mapInfoPanel.putConstraint(SpringLayout.NORTH, mapsizeLabel, 0, SpringLayout.NORTH, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.WEST, mapsizeLabel, 0, SpringLayout.WEST, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.SOUTH, mapsizeLabel, -80, SpringLayout.SOUTH, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.EAST, mapsizeLabel, 0, SpringLayout.EAST, mapInfoPanel);
		mapsizeLabel.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		mapsizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mapsizeLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		mapInfoPanel.add(mapsizeLabel);
		
		spotLabel = new JLabel("( 3, 2 )");
		sl_mapInfoPanel.putConstraint(SpringLayout.NORTH, spotLabel, 65, SpringLayout.NORTH, mapInfoPanel);
		spotLabel.setVerticalAlignment(SwingConstants.TOP);
		sl_mapInfoPanel.putConstraint(SpringLayout.WEST, spotLabel, 0, SpringLayout.WEST, mapInfoPanel);
		sl_mapInfoPanel.putConstraint(SpringLayout.SOUTH, spotLabel, 70, SpringLayout.SOUTH, mapInfoPanel);
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
		
		voiceRecBtn = new JLabel("구조 시작");
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
   
    public class Map extends JPanel {

    	private static final long serialVersionUID = 1L;

    	public int rows = 1;
    	public int cols = 1; 
    	
    	int mapPanelWidth = 550;
        int mapPanelHeight = 345;
        int nodeSize;
        
        Elem prevrobot ; 
        
        Elem[][] iconPanel ;

        ImageIcon[] icons = {
        		new ImageIcon(ADD_ON.class.getResource("/resource/robotSOUTH.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/searchpoint.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/colorblob.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/hazard.png"))
        };
        ImageIcon[] wbicons = {
        		new ImageIcon(ADD_ON.class.getResource("/resource/robotSOUTH.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/searchpoint_wb.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/colorblob_wb.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/hazard_wb.png"))
        };
        
        ImageIcon[] robotIcons = {
        		new ImageIcon(ADD_ON.class.getResource("/resource/robotSOUTH.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/robotWEST.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/robotNORTH.png")),
        		new ImageIcon(ADD_ON.class.getResource("/resource/robotEAST.png"))
        };
      
        ImageIcon icon = new ImageIcon(); 
        ImageIcon wbicon = new ImageIcon(); 
        

       
    	public Map(int m, int n) {
    		super();
    		rows = m;
    		cols = n;
    		nodeSize = Math.min(mapPanelWidth / cols, mapPanelHeight / rows);

    		setLayout(new GridLayout(rows, cols, 0, 0));  
    		setOpaque(false);
    		
    		iconPanel = new Elem[rows][cols];
    	    
    	    for(int r = 0; r < rows; r++) {
    	    	for(int c = 0; c < cols; c++) {
    	    		icon = null; 
    	    		iconPanel[r][c] = new Elem(r, c, nodeSize, Element.NONE);
    	    		iconPanel[r][c].setSize(new Dimension(nodeSize, nodeSize));
    	    		iconPanel[r][c].setLayout(new BorderLayout());
    	    		iconPanel[r][c].setOpaque(false);
    	    		iconPanel[r][c].setBackground((c%2==0)? new Color(25,25,25):new Color(200, 200, 200));
    	    		elemAdd(new Point(c, r), Element.NONE);
    	    		add(iconPanel[r][c]);
    	    	}
    	    }
    	}
    	
    	public void start() {
    		ADD_ON.getInstance().execute();
    	}
    	
    	public void elemAdd(Point p, Element e) {
    		Elem el = iconPanel[p.y][p.x].getElem();
    		
    		boolean flag = true;
    		switch(e) {
    			case NONE:
    				flag = false;
    				break;
    			case ROBOT:
    				icon = icons[0]; 
    				break;
    			case SEARCHPOINT:
    				icon = icons[1];
    				break;
    			case COLORBLOB:
    				icon = icons[2];
    				break;
    			case HAZARD:
    				icon = icons[3];
    				break;
    		}
    		
    		if(flag) {
    			Image img = icon.getImage();
        	    Image updateImg = img.getScaledInstance(nodeSize, nodeSize, Image.SCALE_SMOOTH);
        	    ImageIcon nicon = new ImageIcon(updateImg);
        	    
        		JLabel l = new JLabel(nicon, SwingConstants.CENTER);
        		el.add(l); 
    		}
    		else {
    			JLabel l = new JLabel();
    			el.add(l);
    		}
    		el.repaint();
    		
    		repaint();
    	}
    	
    	public void robotUpdate(Point curr) {
    		
    		prevrobot.remove(prevrobot.p);
    		
    		Elem el = iconPanel[curr.y][curr.x].getElem();
    		icon = icons[0];
    	
    		Image img = icon.getImage();
    	    Image updateImg = img.getScaledInstance(nodeSize, nodeSize, Image.SCALE_SMOOTH);
    	    ImageIcon nicon = new ImageIcon(updateImg);
    	    
    		JLabel l = new JLabel(nicon, SwingConstants.CENTER);
    		el.add(l); 
    		el.repaint();
    		
    		prevrobot = el;
    	}
    	
    	public void rescueUpdate(Point p) {
    		Elem el = iconPanel[p.y][p.x].getElem();
    		el.remove(el.p);
    		
    		icon = icons[1];
    		Image img = icon.getImage();
    	    Image updateImg = img.getScaledInstance(nodeSize, nodeSize, Image.SCALE_SMOOTH);
    	    ImageIcon nicon = new ImageIcon(updateImg);
    	    
    		JLabel l = new JLabel(nicon, SwingConstants.CENTER);
    		el.add(l); 
    		
    		icon = icons[0];
    		img = icon.getImage();
    	    updateImg = img.getScaledInstance(nodeSize, nodeSize, Image.SCALE_SMOOTH);
    	    nicon = new ImageIcon(updateImg);
    	    
    		l = new JLabel(nicon, SwingConstants.CENTER);
    		el.add(l); 
    		el.repaint();
    	
    		
    	}
    	
    	public void elemInitAdd(Point p, Element e) {
    		Elem el = iconPanel[p.y][p.x].getElem();
    		
    		switch(e) {
    			case ROBOT:
    				wbicon = wbicons[0];
    				prevrobot = el;
    				break;
    			case SEARCHPOINT:
    				wbicon = wbicons[1];
    				break;
    			case COLORBLOB:
    				wbicon = wbicons[2];
    				break;
    			case HAZARD:
    				wbicon = wbicons[3];
    				break;
    		}
    	    Image img = wbicon.getImage();
    	    Image updateImg = img.getScaledInstance(nodeSize, nodeSize, Image.SCALE_SMOOTH);
    	    ImageIcon nicon = new ImageIcon(updateImg);
    	    
    		JLabel l = new JLabel(nicon, SwingConstants.CENTER);
    		el.add(l); 
    	}
    	
    	public void elemRemove(Point p) {
    		Elem el = iconPanel[p.y][p.x].getElem();
    		el.remove(el.p);
    	}
    	
    	public class Elem extends JPanel {
    		int r, c; //map 배열상의 좌표 
    		int x, y; //왼쪽 상단 좌표 
    		int nodeSize; 
    		Element elem; 
    		ImageIcon icon;
    		JLabel p = null;
    		
    		public Elem(int r, int c, int size, Element e) {
    			this.r = r; 
    			this.c = c; 
    			this.nodeSize = size; 
    			this.elem = e; 
    			
    			this.x = r * nodeSize; 
    			this.y = c * nodeSize;  
    		}
    		
    		public Elem getElem() {
    			return this; 
    		}
    		
    		@Override
    		public void paintComponent(Graphics g) {
    			int m = nodeSize / 2; 
    			g.setColor(new Color(0,0,0));
    			g.drawLine(m, 0, m, nodeSize);
    			g.drawLine(0,  m,  nodeSize,  m);
    		}
    		
    		@Override
    		public Component add(Component c) {
    			p = (JLabel) c; 
    			return super.add(c); 
    		}
    		
    		@Override
    		public void remove(Component c) {
    			p = null;
    			super.remove(c);
    		}
    	}

    }
}



