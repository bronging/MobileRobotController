package com.nsg.addon.gui.design;

import com.nsg.addon.gui.event.EventManager;
import com.nsg.addon.rescue.modeling.Element;
import com.nsg.addon.ADD_ON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesignUI2 extends JFrame {
    private JPanel mapPanel;
    private JTextField rowField;
    private JTextField startField;

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private JTextField spotField;
    private JTextField colorField;
    private JTextField hazardField;
    private JLabel voiceLabel;
    
    private int cols;
    private int rows;
    
    boolean icon = false; 

    public DesignUI2() {
        initialize();
    }

    public void initialize() {
    	mapPanel = new JPanel() {
    		private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(icon) {
                	drawEdges(g);
                    drawIcons(g);  
                    icon = false;
                }
            }
    	};
    	
    	mapPanel.setBounds(0, 36, 700, 495);
    	mapPanel.setLayout(null);
    	
    	getContentPane().add(mapPanel);
    	
        setSize(947, 651);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("MobileRobotController");

        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(0, 0, 931, 37);
        inputPanel.setLayout(null);
        
        
        
        
        JLabel mapLabel = new JLabel("Map");
        mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mapLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
        mapLabel.setBounds(172, 9, 55, 15);
        inputPanel.add(mapLabel);
        rowField = new JTextField(5);
        rowField.setBounds(234, 6, 60, 21);
        inputPanel.add(rowField);

        JLabel startLabel = new JLabel("Start");
        startLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
        startLabel.setBounds(301, 9, 55, 15);
        inputPanel.add(startLabel);
        startField = new JTextField(5);
        startField.setBounds(363, 6, 60, 21);
        inputPanel.add(startField);

        JButton createGraphButton = new JButton("\uC9C0\uB3C4 \uC0DD\uC131");
        createGraphButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        createGraphButton.setBounds(817, 5, 99, 23);
        
        createGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
        
                    createGraph();
                    icon = true; 
                    mapPanel.repaint();
                    repaint();
                   
                } catch (Exception ex) {
                    ex.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
                }
                System.out.flush(); // 강제로 출력을 플러시
            }
        });
        
        getContentPane().setLayout(null);
        inputPanel.add(createGraphButton);

        getContentPane().add(inputPanel);
        
        JLabel title = new JLabel("MobileRobotController");
        title.setFont(new Font("Dialog", Font.PLAIN, 15));
        title.setBounds(7, 9, 158, 15);
        inputPanel.add(title);
        
        spotField = new JTextField(5);
        spotField.setBounds(492, 6, 60, 21);
        inputPanel.add(spotField);
        
        JLabel spotLabel = new JLabel("Spot");
        spotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        spotLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
        spotLabel.setBounds(430, 9, 55, 15);
        inputPanel.add(spotLabel);
        
        colorField = new JTextField(5);
        colorField.setBounds(621, 6, 60, 21);
        inputPanel.add(colorField);
        
        JLabel colorLabel = new JLabel("Color");
        colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        colorLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
        colorLabel.setBounds(559, 9, 55, 15);
        inputPanel.add(colorLabel);
        
        hazardField = new JTextField(5);
        hazardField.setBounds(750, 6, 60, 21);
        inputPanel.add(hazardField);
        
        JLabel hazardLabel = new JLabel("Hazard");
        hazardLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
        hazardLabel.setBounds(688, 9, 55, 15);
        inputPanel.add(hazardLabel);

        
        
        JPanel voiceRecPanel = new JPanel();
        voiceRecPanel.setBounds(0, 530, 931, 82);
        getContentPane().add(voiceRecPanel);
        
        JButton voiceRecButton = new JButton("음성 인식");
        //voiceRecButton.addActionListener(new EventManager());
        
        JLabel voiceLabel = new JLabel("New label");
        voiceRecPanel.add(voiceLabel);
        voiceRecPanel.add(voiceRecButton);
        
        JPanel aroundInformationPanel = new JPanel();
        aroundInformationPanel.setBounds(701, 152, 230, 237);
        getContentPane().add(aroundInformationPanel);

        
    }
 

    private void createGraph() {
    	
    	nodes = new ArrayList<>();
        edges = new ArrayList<>();
        
        
        try {
            String inputText = rowField.getText();
            
            // 문자열 배열을 Integer 배열로 변환
            Integer[] numbers = extractNumber(inputText);
            
            rows = numbers[1] + 1;
            cols = numbers[0] + 1;
            
            nodes.clear();
            edges.clear();


            int mapPanelWidth = 700;
            int mapPanelHeight = 495;
            
            int nodeSize = Math.min(mapPanelWidth / cols, mapPanelHeight / rows);

            // 각 셀에 대한 노드 생성
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Node node = new Node(j, i, j * nodeSize, i * nodeSize, nodeSize);
                    nodes.add(node);

                }
            }

            // 각 노드에 대해 오른쪽과 아래쪽으로 엣지를 그림
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j < cols - 1; j++) {
                    edges.add(new Edge(nodes.get(i * cols + j), nodes.get(i * cols + j + 1)));  // 현재 위치의 노드와 오른쪽에 위치한 노드를 연결하는 엣지를 생성
                    edges.add(new Edge(nodes.get(i * cols + j), nodes.get((i + 1) * cols + j)));  // 현재 위치의 노드와 아래에 위치한 노드를 연결하는 엣지를 생성
                }
            }

            // 가장 오른쪽 열에 대한 아래쪽 엣지 추가
            for (int i = 0; i < rows - 1; i++) {
                edges.add(new Edge(nodes.get(i * cols + cols - 1), nodes.get((i + 1) * cols + cols - 1)));
            }

            // 가장 아래 행에 대한 오른쪽 엣지 추가
            for (int j = 0; j < cols - 1; j++) {
                edges.add(new Edge(nodes.get((rows - 1) * cols + j), nodes.get((rows - 1) * cols + j + 1)));
            }

            revalidate();
            repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for rows and cols.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void drawEdges(Graphics g) {
        for (Edge edge : edges) {
            g.drawLine(
                    edge.getSource().getCenterX(), edge.getSource().getCenterY(),
                    edge.getTarget().getCenterX(), edge.getTarget().getCenterY()
            );
        }
    }

    /**초기 지도에 아이콘 표시*/
    public void drawIcons(Graphics g){       
        String startText = startField.getText();
        String spotText = spotField.getText();
        String colorText = colorField.getText();
        String hazardText = hazardField.getText();
        

        Integer[] startinput = extractNumber(startText);   //(1 2) -> [1,2]
        Integer[] spotinput = extractNumber(spotText);   // ((45 1)(4 6)) -> [45, 1, 4, 6]
        Integer[] colorinput = extractNumber(colorText);
        Integer[] hazardinput = extractNumber(hazardText);
        
        Element e = null;
        Point p = new Point();
              
        // 탐색 위치 그리기 - 회색
        for (int i = 0; i < spotinput.length - 1; i += 2) {
            int x = spotinput[i];
            int y = spotinput[i + 1];
            
            p.x = x;
            p.y = y;          
            e = Element.SEARCHPOINT;
            
            drawIconAtNode(p, e, g);  // 좌표에 이미지 그리기
        }
        
        // 중요지점 위치 그리기 - 회색
        for (int i = 0; i < colorinput.length - 1; i += 2) {
            int x = colorinput[i];
            int y = colorinput[i + 1];
            
            p.x = x;
            p.y = y;          
            e = Element.COLORBLOB;
            
            drawIconAtNode(p, e, g);  // 좌표에 이미지 그리기
        }
        
        // 위험지점 위치 그리기 - 회색
        for (int i = 0; i < hazardinput.length - 1; i += 2) {
            int x = hazardinput[i];
            int y = hazardinput[i + 1];
            
            p.x = x;
            p.y = y;          
            e = Element.HAZARD;
            
            drawIconAtNode(p, e, g);  // 좌표에 이미지 그리기
        }
        
        // 시작 위치 그리기
        for (int i = 0; i < startinput.length -1 ; i += 2) {
            int x = startinput[i];
            int y = startinput[i + 1];
            
            p.x = x;
            p.y = y;          
            e = Element.ROBOT;
            drawIconAtNode(p, e, g);  // 좌표에 이미지 그리기
        }
    }   
   
    
    /**((45 1)(4 6)) -> [45, 1, 4, 6]*/
    private Integer[] extractNumber(String input) {
        // 괄호와 공백을 제거
        String cleanedInput = input.replaceAll("[()]", "");

        // 쉼표를 기준으로 숫자 분리
        String[] numberStrings = cleanedInput.split("\\s+");

        // 문자열 배열을 Integer 배열로 변환
        List<Integer> numbersList = new ArrayList<>();
        for (String numberString : numberStrings) {
            try {
                // 문자열을 숫자로 변환
                int number = Integer.parseInt(numberString);
                numbersList.add(number);
            } catch (NumberFormatException e) {
                // 숫자로 변환할 수 없는 문자열이라면 무시
            }
        }

        // Integer 리스트를 Integer 배열로 변환
        Integer[] numbers = new Integer[numbersList.size()];
        return numbersList.toArray(numbers);
    }
    
    
    public void drawIconAtNode(Point p, Element e, Graphics g) {
        int pointX = p.x;
        int pointY = rows - 1 - p.y; // 왼쪽 위 (0,0)좌표계에서 오른쪽 아래 (0,0)좌표계로!
        // 해당 좌표에 대한 노드를 찾기
        Node selectedNode = findNodeByCoordinates(pointX, pointY);
        
        System.out.println(selectedNode.x);
        System.out.println(selectedNode.y);
        System.out.println(e);
        ImageIcon icon3 = new ImageIcon();
        
        switch(e) {
            case ROBOT: 
                icon3 = new ImageIcon(ADD_ON.class.getResource("/resource/robot.png"));
                //g.drawRect(selectedNode.getCenterX(), selectedNode.getCenterY(), 20, 20);                
                break;
            case SEARCHPOINT:
            	icon3 = new ImageIcon(ADD_ON.class.getResource("/resource/searchpoint.png"));
                //g.drawOval(selectedNode.getCenterX(), selectedNode.getCenterY(), 20, 20);                
                break;
            case COLORBLOB:
            	icon3 = new ImageIcon(MapView.class.getResource("/resource/colorblob.png"));
                //g.drawRect(selectedNode.getCenterX(), selectedNode.getCenterY(), 10, 10);                
                break;
            case HAZARD:
            	icon3 = new ImageIcon(MapView.class.getResource("/resource/hazard.png"));
                //g.drawOval(selectedNode.getCenterX(), selectedNode.getCenterY(), 10, 10);                
                break;
            default:
                break;
        }
        
        if (selectedNode != null) {
           // 이미지의 크기를 노드 크기에 맞게 조절
           
           // JLabel 대신 직접 그래픽스에 이미지를 그림
           g.drawImage(icon3.getImage(), selectedNode.getCenterX()-20, selectedNode.getCenterY()-20, 40, 40, null);
        }  
        // 이미지 추가 후 mapPanel을 다시 그리도록 요청
        
    }
    
    private Node findNodeByCoordinates(int x, int y) {
        for (Node node : nodes) {
            if (node.x == x && node.y == y) {
                return node;
            }
        }
        return null; // 해당 좌표에 해당하는 노드를 찾지 못한 경우
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DesignUI2().setVisible(true);
            }
        });
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

    public class Edge {
        private Node source;
        private Node target;

        public Edge(Node source, Node target) {
            this.source = source;
            this.target = target;
        }

        public Node getSource() {
            return source;
        }

        public Node getTarget() {
            return target;
        }
    }

    public void showVoiceRst(String s) {
       voiceLabel.setText(s);
    }

}