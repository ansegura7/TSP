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

import tsp.algorithm.SOMAlgorithm;
import tsp.util.DoublePoint;
import tsp.util.FileManager;

@SuppressWarnings("serial")
public class Environment extends javax.swing.JFrame
{   
    // Main Class variables
    public EnvCanvas envCanvas;
    private FileManager fm;
    private DoublePoint[] doublePoint;
    private Dimension screenSize;
    private int w;
    private int h;
    
    // Algorithms
    private SOMAlgorithm algoSOM;
    
    // GUI objects
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JCheckBox jCheckBox1;
    private DecimalFormat f;
    
    // Creates new form Environment
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
        algoSOM = null;
        f = new DecimalFormat("##.00");
    }
    
    // Method to Initialize the graphic components
    private void initComponents()
    {
    	getContentPane().setBackground(Color.white);
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel("File name:");
        jLabel2 = new javax.swing.JLabel("Elapsed time:");
        jLabel3 = new javax.swing.JLabel("TSP tour length:");
        jLabel4 = new javax.swing.JLabel("Number of points:");
        jLabel5 = new javax.swing.JLabel("Animation:");
        jCheckBox1 = new javax.swing.JCheckBox();
		
        envCanvas = new EnvCanvas(0, 0, w-220, h-50);
        add(envCanvas);

		jPanel1.setBounds(w-220, 0, 210, 310);
		jPanel1.setBackground(Color.white);
		jPanel1.setLayout(null);	
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACTIONS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11),new java.awt.Color(0, 70, 213)));
        add(jPanel1);
        
        // Initialize boton1
        jPanel1.add(jButton1);
        jButton1.setBounds(20, 40, 120, 20);
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton1.setForeground(new java.awt.Color(0, 70, 213));
        jButton1.setText("Load");
        jButton1.setToolTipText("Cargar el archivo inicial");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
		// Initialize boton2
		jPanel1.add(jButton2);
		jButton2.setBounds(20, 70, 120, 20);
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton2.setForeground(new java.awt.Color(0, 70, 213));
        jButton2.setText("Launch MAK");
        jButton2.setToolTipText("Lanzar red neuronal inicial");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
		// Initialize boton3
		jPanel1.add(jButton3);
		jButton3.setBounds(20, 100, 120, 20);
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton3.setForeground(new java.awt.Color(0, 70, 213));
        jButton3.setText("Start");
        jButton3.setToolTipText("Resolver el TSP");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        
        // Initialize boton4
		jPanel1.add(jButton4);
		jButton4.setBounds(20, 130, 120, 20);
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton4.setForeground(new java.awt.Color(0, 70, 213));
        jButton4.setText("Stop");
        jButton4.setToolTipText("Parar la ejecucion del algoritmo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        
        // Initialize label1
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 175, 200, 20);
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(0, 70, 213));
        
        // Initialize label2
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 200, 200, 20);
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setForeground(new java.awt.Color(0, 70, 213));
        
        // Initialize label3
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 225, 200, 20);
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setForeground(new java.awt.Color(0, 70, 213));
        
        // Initialize label4
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 250, 200, 20);
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setForeground(new java.awt.Color(0, 70, 213));
        
        // Initialize label5
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 275, 100, 20);
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setForeground(new java.awt.Color(0, 70, 213));
        
        // Initialize checkBox1
        jPanel1.add(jCheckBox1);
        jCheckBox1.setBounds(90, 275, 40, 20);
        jCheckBox1.setBackground(Color.white);
    }              
    
    // Event - Load TSP data file
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	
        doublePoint = fm.loadFile();
        if (doublePoint != null) {
        	envCanvas.paintGraph(doublePoint, null);
        	System.out.println("   Ploted " + doublePoint.length + " points");
        }
        
        jLabel1.setText("File name: " + fm.getFileName());
        jLabel2.setText("Elapsed time: ");
        jLabel3.setText("TSP tour length: ");
        jLabel4.setText("Number of points: " + doublePoint.length);
    }
    
    // Event - Launch initial tour
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    	if (doublePoint != null)
        {
    		algoSOM = new SOMAlgorithm(this);
    		algoSOM.showInitialSOM(doublePoint);
        }
    }
    
    // Event - Method to solve the TSP
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
        if (doublePoint != null)
        {
        	algoSOM.initAlgorithm(jCheckBox1.isSelected());
            jLabel2.setText("Elapsed time: ");
            jLabel3.setText("TSP tour length: ");
        }
    }
    
    // Event - Stop algorithm execution
    @SuppressWarnings("deprecation")
	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
        if (doublePoint != null)
        	algoSOM.stop();
    }
    
    // Show the final results of the minimum tour
    public void display(long elpTime, double tourLength)
    {
        jLabel2.setText("Elapsed time: " + elpTime + " ms");
        jLabel3.setText("TSP tour length: " + f.format(tourLength * fm.getMaxValue()) + " units");
    }
}
