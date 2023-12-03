package com.nsg.addon.gui.design;

import com.nsg.addon.gui.event.EventManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DesignUI extends JFrame {
    private JPanel mapPanel;
    private JTextField rowField;
    private JTextField startField;

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private JTextField spotField;
    private JTextField colorField;
    private JTextField hazardField;

    public DesignUI() {
        initialize();
    }

    private void initialize() {
        setSize(947, 651);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("MobileRobotController");

        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(0, 0, 931, 37);
        inputPanel.setLayout(null);
        JLabel mapLabel = new JLabel("Map");
        mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mapLabel.setFont(new Font("���� ��� Semilight", Font.PLAIN, 17));
        mapLabel.setBounds(172, 9, 55, 15);
        inputPanel.add(mapLabel);
        rowField = new JTextField(5);
        rowField.setBounds(234, 6, 60, 21);
        inputPanel.add(rowField);

        JLabel startLabel = new JLabel("Start");
        startLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startLabel.setFont(new Font("���� ��� Semilight", Font.PLAIN, 17));
        startLabel.setBounds(301, 9, 55, 15);
        inputPanel.add(startLabel);
        startField = new JTextField(5);
        startField.setBounds(363, 6, 60, 21);
        inputPanel.add(startField);

        JButton createGraphButton = new JButton("\uC9C0\uB3C4 \uC0DD\uC131");
        createGraphButton.setFont(new Font("���� ��� Semilight", Font.PLAIN, 14));
        createGraphButton.setBounds(817, 5, 99, 23);
        createGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGraph();
            }
        });
        getContentPane().setLayout(null);
        inputPanel.add(createGraphButton);

        getContentPane().add(inputPanel);
        
        JLabel title = new JLabel("MobileRobotController");
        title.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
        title.setBounds(7, 9, 158, 15);
        inputPanel.add(title);
        
        spotField = new JTextField(5);
        spotField.setBounds(492, 6, 60, 21);
        inputPanel.add(spotField);
        
        JLabel spotLabel = new JLabel("Spot");
        spotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        spotLabel.setFont(new Font("���� ��� Semilight", Font.PLAIN, 17));
        spotLabel.setBounds(430, 9, 55, 15);
        inputPanel.add(spotLabel);
        
        colorField = new JTextField(5);
        colorField.setBounds(621, 6, 60, 21);
        inputPanel.add(colorField);
        
        JLabel colorLabel = new JLabel("Color");
        colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        colorLabel.setFont(new Font("���� ��� Semilight", Font.PLAIN, 17));
        colorLabel.setBounds(559, 9, 55, 15);
        inputPanel.add(colorLabel);
        
        hazardField = new JTextField(5);
        hazardField.setBounds(750, 6, 60, 21);
        inputPanel.add(hazardField);
        
        JLabel hazardLabel = new JLabel("Hazard");
        hazardLabel.setFont(new Font("���� ��� Semilight", Font.PLAIN, 17));
        hazardLabel.setBounds(688, 9, 55, 15);
        inputPanel.add(hazardLabel);

        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawEdges(g);
            }
        };
        mapPanel.setBounds(0, 36, 700, 495);
        getContentPane().add(mapPanel);
        
        JPanel voiceRecPanel = new JPanel();
        voiceRecPanel.setBounds(0, 530, 931, 82);
        getContentPane().add(voiceRecPanel);
        
        JButton jb = new JButton("���� ����");
        //jb.addActionListener(new EventManager());
        voiceRecPanel.add(jb);
        
        JPanel aroundInformationPanel = new JPanel();
        aroundInformationPanel.setBounds(701, 152, 230, 237);
        getContentPane().add(aroundInformationPanel);

        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    private void createGraph() {
        try {
            String inputText = rowField.getText();
            
            // ��ȣ�� ���� ����
            String cleanedInput = inputText.replaceAll("[()]", "");

            // ��ǥ�� �������� ���� �и�
            String[] input = cleanedInput.split(" ");

            if (input.length != 2) {
                JOptionPane.showMessageDialog(this, "Map�� (4 5)���·� �Է����ּ���.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int rows = Integer.parseInt(input[0]);
            int cols = Integer.parseInt(input[1]);

            mapPanel.removeAll();
            mapPanel.setLayout(new GridLayout(rows, cols));

            nodes.clear();
            edges.clear();

            int mapPanelWidth = mapPanel.getWidth();
            int mapPanelHeight = mapPanel.getHeight();

            int nodeSize = Math.min(mapPanelWidth / cols, mapPanelHeight / rows);

            // �� ���� ���� ��� ����
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Node node = new Node(j * nodeSize, i * nodeSize, nodeSize);
                    nodes.add(node);
                }
            }

            // �� ��忡 ���� �����ʰ� �Ʒ������� ������ �׸�
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j < cols - 1; j++) {
                    edges.add(new Edge(nodes.get(i * cols + j), nodes.get(i * cols + j + 1))); // ���������� ����
                    edges.add(new Edge(nodes.get(i * cols + j), nodes.get((i + 1) * cols + j))); // �Ʒ������� ����
                }
            }

            // ���� ������ ���� ���� �Ʒ��� ���� �߰�
            for (int i = 0; i < rows - 1; i++) {
                edges.add(new Edge(nodes.get(i * cols + cols - 1), nodes.get((i + 1) * cols + cols - 1)));
            }

            // ���� �Ʒ� �࿡ ���� ������ ���� �߰�
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DesignUI().setVisible(true);
            }
        });
    }

    private class Node {
        private int x;
        private int y;
        private int size;

        public Node(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public int getCenterX() {
            return x + size / 2;
        }

        public int getCenterY() {
            return y + size / 2;
        }
    }

    private class Edge {
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
}
