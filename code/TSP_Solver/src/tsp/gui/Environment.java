/*
 * Environment.java
 *
 * Created on June 10, 2007, 01:02 PM
 * Created by Andres Segura
 * 
 */

package tsp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;

import tsp.algorithm.TspAlgorithm;
import tsp.algorithm.SOMAlgorithm;
import tsp.util.DoubleLinkedList;
import tsp.util.DoublePoint;
import tsp.util.FileManager;
import tsp.util.Node;

// GUI class of the solution
@SuppressWarnings("serial")
public class Environment extends javax.swing.JFrame
{
    // GUI objects
	private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jCombo1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane jScrollPane1; 
    public EnvCanvas envCanvas;
    
    // Main Class variables
    private FileManager fm;
    private DoublePoint[] doublePoint;
    private Dimension screenSize;
    private int w;
    private int h;
    private DecimalFormat df;
    
    // Algorithms
    private TspAlgorithm tspAlgo;
    
    // Creates new Environment GUI
    public Environment()
    {
        super("TSP Solver");
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        w = screenSize.width;
        h = screenSize.height;
        setBounds(0, 0, w, h);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(3);
        initComponents();
        
        // Initialize class variables
        fm = new FileManager();
        doublePoint = null;
        tspAlgo = null;
        df = new DecimalFormat("0.00");
    }
    
    // Method to Initialize the graphic components
    private void initComponents()
    {
    	getContentPane().setBackground(Color.white);
    	jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCombo1 = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel("Load TSP file");
        jLabel2 = new javax.swing.JLabel("Animation");
        jLabel3 = new javax.swing.JLabel("File name:");
        jLabel4 = new javax.swing.JLabel("Number of points:");
        jLabel5 = new javax.swing.JLabel("TSP tour length:");
        jLabel6 = new javax.swing.JLabel("Elapsed time:");
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane(jTextArea1);
        
        // Initialize panel1
     	jPanel1.setBounds(6, 0, w-227, h-35);
     	jPanel1.setBackground(Color.white);
     	jPanel1.setLayout(null);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TSP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11),new java.awt.Color(0, 70, 213)));
        add(jPanel1);
        
        // Initialize panel2
		jPanel2.setBounds(w-220, 0, 208, 280);
		jPanel2.setBackground(Color.white);
		jPanel2.setLayout(null);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACTIONS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11),new java.awt.Color(0, 70, 213)));
        add(jPanel2);
        
        // Initialize scrollPane
        jScrollPane1.setBounds(w-220, 290, 208, h-325);
        jScrollPane1.setBackground(Color.white);
        jPanel1.setLayout(null);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SOLUTION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11),new java.awt.Color(0, 70, 213)));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(jScrollPane1);
        
        // Initialize canvas
        envCanvas = new EnvCanvas(3, 11, w-236, h-51);
        jPanel1.add(envCanvas);
        
        // Initialize boton1
        jPanel2.add(jButton1);
        jButton1.setBounds(105, 40, 80, 20);
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton1.setForeground(new java.awt.Color(0, 70, 213));
        jButton1.setText("Seach");
        jButton1.setToolTipText("Load TSP file.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
		// Initialize boton2
		jPanel2.add(jButton2);
		jButton2.setBounds(20, 100, 75, 20);
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton2.setForeground(new java.awt.Color(0, 70, 213));
        jButton2.setText("Solve");
        jButton2.setToolTipText("Solve TSP problem");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        // Initialize boton3
		jPanel2.add(jButton3);
		jButton3.setBounds(105, 100, 80, 20);
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton3.setForeground(new java.awt.Color(0, 70, 213));
        jButton3.setText("Pause");
        jButton3.setToolTipText("Pause/Resume algorithm execution.");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        
		// Initialize combo1
		jPanel2.add(jCombo1);
		jCombo1.setBounds(20, 70, 165, 20);
		jCombo1.setFont(new java.awt.Font("Tahoma", 0, 11));
		jCombo1.addItem("SOM Algorithm");
		jCombo1.setToolTipText("Select algorithm.");
        
        // Initialize checkBox1
        jPanel2.add(jCheckBox1);
        jCheckBox1.setBounds(101, 128, 40, 20);
        jCheckBox1.setBackground(Color.white);
		
        // Initialize label1
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 40, 100, 20);
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12));
        
        // Initialize label2
        jPanel2.add(jLabel2);
        jLabel2.setBounds(20, 128, 100, 20);
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
		
        // Initialize label3
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 165, 200, 20);
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setForeground(new java.awt.Color(0, 70, 213));
        
        // Initialize label4
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 190, 200, 20);
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel4.setForeground(new java.awt.Color(0, 70, 213));
        
        // Initialize label5
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 215, 200, 20);
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel5.setForeground(new java.awt.Color(0, 70, 213));
        
        // Initialize label6
        jPanel2.add(jLabel6);
        jLabel6.setBounds(20, 240, 200, 20);
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel6.setForeground(new java.awt.Color(0, 70, 213));
    }              
    
    // Event - Load TSP data file
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	System.out.println(">> Start TSP Solver");
    	DoublePoint[] dp = fm.loadFile();
    	
        if (dp != null) {
        	this.doublePoint = dp;
        	
        	envCanvas.paintGraph(doublePoint, null);
        	jButton2.setEnabled(true);
            jLabel3.setText("File name: " + fm.getFileName().toUpperCase());
            jLabel4.setText("Number of points: " + doublePoint.length);
            jLabel5.setText("TSP tour length: ");
            jLabel6.setText("Elapsed time: ");
            jTextArea1.setText("");
            System.out.println("   Ploted " + doublePoint.length + " points");
        }
    }
    
    // Event - Method to solve the TSP
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
    	// Validation
        if (doublePoint == null)
        	return;
    	
        // Select algorithm
        if (jCombo1.getSelectedIndex() == 0) {
    		tspAlgo = new SOMAlgorithm(this, jCheckBox1.isSelected());
    	}
        
        // Clean GUI controls
        jButton1.setEnabled(false);
    	jButton2.setEnabled(false);
    	jButton3.setEnabled(true);
        jLabel5.setText("TSP tour length: ");
        jLabel6.setText("Elapsed time: ");
        jTextArea1.setText("");
        
    	// Set data and start algorithm
    	tspAlgo.init(doublePoint);
		tspAlgo.start();
    }
    
    // Event - Pause/Resume algorithm execution
    @SuppressWarnings("deprecation")
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
    	// Validation
        if (doublePoint == null)
        	return;
        
        String currAction = jButton3.getText();
        if (currAction == "Pause") {
        	jButton3.setText("Resume");
        	tspAlgo.suspend();
        }
        else if (currAction == "Resume") {
        	jButton3.setText("Pause");
        	tspAlgo.resume();
        }
        	
    }
    
    // Show the final results of the minimum tour
    public void displayResults()
    {
    	double tourLength = tspAlgo.getTourLength() * fm.getFactorValue();
    	long elapsedTime = tspAlgo.getElapsedTime();
    	DoubleLinkedList solution = tspAlgo.getSolution();
    	
    	// Solution KPIs
        jLabel5.setText("TSP tour length: " + df.format(tourLength) + " uom");
        jLabel6.setText("Elapsed time: " + elapsedTime + " ms");
        System.out.println("   Algorithm results: Tour length: " + tourLength + " UoM, Elapsed time: " + elapsedTime + " ms");

    	// Solution Tour
        StringBuilder sb = new StringBuilder();
        Node n = solution.getLast();
        do {
        	sb.append(" Node " + n.keyName + "\t x: " + df.format(n.x) + ", y: " + df.format(n.y) + "\n");
        	n = n.next;
        }
        while (n != solution.getLast());
        jTextArea1.setText(sb.toString());
        
        // Update buttons enable status
    	jButton1.setEnabled(true);
    	jButton2.setEnabled(true);
    	jButton3.setEnabled(false);
    }
}
