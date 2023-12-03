package com.nsg.addon.gui.design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.nsg.addon.ADD_ON;
import com.nsg.addon.rescue.modeling.Element;

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
    		new ImageIcon(ADD_ON.class.getResource("/resource/robot.png")),
    		new ImageIcon(ADD_ON.class.getResource("/resource/searchpoint.png")),
    		new ImageIcon(ADD_ON.class.getResource("/resource/colorblob.png")),
    		new ImageIcon(ADD_ON.class.getResource("/resource/hazard.png"))
    };
    ImageIcon[] wbicons = {
    		new ImageIcon(ADD_ON.class.getResource("/resource/robot.png")),
    		new ImageIcon(ADD_ON.class.getResource("/resource/searchpoint_wb.png")),
    		new ImageIcon(ADD_ON.class.getResource("/resource/colorblob_wb.png")),
    		new ImageIcon(ADD_ON.class.getResource("/resource/hazard_wb.png"))
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
	    		 		
	    		add(iconPanel[r][c]);
	    	}
	    }
	}
	
	public void elemAdd(Point p, Element e) {
		Elem el = iconPanel[p.y][p.x].getElem();
		
		switch(e) {
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
	    Image img = icon.getImage();
	    Image updateImg = img.getScaledInstance(nodeSize, nodeSize, Image.SCALE_SMOOTH);
	    ImageIcon nicon = new ImageIcon(updateImg);
	    
		JLabel l = new JLabel(nicon, SwingConstants.CENTER);
		el.add(l); 
		el.repaint();
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
		
		repaint();
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
	
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				Map map = new Map(4, 4);
				
				frame.setSize(550, 345);
				frame.setPreferredSize(new Dimension(550, 345));
				frame.add(map);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setVisible(true);
				
				map.elemAdd(new Point(2, 2), Element.ROBOT);
				
				try {
					Thread.sleep(3000); // delay 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frame.setVisible(true);

				
				map.elemAdd(new Point(3, 3), Element.COLORBLOB);
				map.elemAdd(new Point(1, 1), Element.NONE);

				frame.setVisible(true);
				
				//map.elemRemove(new Point(2, 2));
				
				frame.setVisible(true);
				
				//map.elemRemove(new Point(3, 3));
				map.elemAdd(new Point(3, 3), Element.HAZARD);
				
			}
		});
	}
}
