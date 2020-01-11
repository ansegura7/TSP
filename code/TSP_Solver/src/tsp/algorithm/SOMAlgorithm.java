/*
 * SOMAlgorithm.java
 *
 * Created on June 10, 2007, 03:11 PM
 * Created by Andres Segura
 *
 */

package tsp.algorithm;

import java.util.Hashtable;
import java.util.Random;

import tsp.gui.Environment;
import tsp.gui.PaintProcess;
import tsp.util.DoubleLinkedList;
import tsp.util.DoublePoint;
import tsp.util.Node;

// Self organizing map (SOM) class
public class SOMAlgorithm extends TspAlgorithm
{
    // Class constants
    private final static int MAX_ITERATIONS = 1500;
    private final static double LAMBDA = 0.2d;
    private final static int PAINT_WAIT = 50;
    
    // Class main variables
    private Environment env;
    private DoublePoint[] doublePoint;
    private DoubleLinkedList graph;
    private int nPoints; 
    private Random rnd = new Random();
    
    // Other variables
    private Hashtable<Integer, Integer> cache;
    private boolean display;
    private long elapsedTime;
    
    /** Creates a new instance of SOMAlgorithm */
    public SOMAlgorithm()
    {
        this(null, false);
    }
    
    /** Creates a new instance of SOMAlgorithm */
    public SOMAlgorithm(Environment env, boolean b)
    {
    	this.env = env;
        this.doublePoint = null;
        this.graph = null;
        this.nPoints = 0;        
        this.cache = null;
        this.display = b;
        this.elapsedTime = 0l;
    }
    
    // Function based on the point cloud, sets the initial SOM
    public void init(DoublePoint[] points)
    {
        doublePoint = points;
        nPoints = points.length;
        cache = new Hashtable<Integer, Integer>();
        
        setInitialPoints();
    }
    
    // This is the method that solves the TSP problem using SOM algorithm
    public void solve()
    {
        if (doublePoint != null) {
        	elapsedTime = System.nanoTime();
        	
        	int nIter;
        	while (graph.size() < nPoints * 2)
            {
        		nIter = (int)(graph.size() * MAX_ITERATIONS / nPoints);
                for (int i = 0; i < nIter; i++)
                	updateSOM();
                
                addNodes();
                if (display)
                	paint();
            }
        	
        	finish();
        	elapsedTime = ((System.nanoTime() - elapsedTime) / 1000000);
        	
        	// Show final results
            paint();
            if (env != null)
            	env.displayResults();
        }
    }
    
    // Method that calculates the length of the tour
    public double getTourLength()
    {
    	double tour = 0.0d;
    	Node node = graph.getLast();
    	
    	do
    	{
    		tour += getEuclideanDistance(node, node.next);
            node = node.next;
    	}
    	while (node != graph.getLast());
    	
    	return tour;
    }
    
    // Returns the time it took for the algorithm to resolve the TSP
    public int getElapsedTime()
    {
    	return (int)this.elapsedTime;
    }
    
    // Method that looks for the initial position of the SOM
    private void setInitialPoints()
    {
        if (doublePoint == null)
            return;
        
        double cx = 0;
        double cy = 0;
        for (int i = 0; i < nPoints; i++)
        {
            cx += doublePoint[i].x;
            cy += doublePoint[i].y;
        }
        cx /= nPoints;
        cy /= nPoints;
        
        // Search limit nodes
        DoublePoint nNorth = new DoublePoint(cx, cy * 0.8);
        DoublePoint nEast = new DoublePoint(cx * 1.2, cy);
        DoublePoint nSouth = new DoublePoint(cx, cy * 1.2);
        DoublePoint nWest = new DoublePoint(cx * 0.8, cy);
        
        // Save graph data
        graph = new DoubleLinkedList();
        graph.rightInsert(new Node(0, nNorth), null);
        graph.rightInsert(new Node(1, nEast), graph.get(0));
        graph.rightInsert(new Node(2, nSouth), graph.get(1));
        graph.rightInsert(new Node(3, nWest), graph.get(2));
        
        // Show SOM
        if (display)
        	paint();
    }
    
    // Method in charge of drawing. Use a separate thread to make the drawings
    private void paint()
    {
    	if (env != null) {
	        new PaintProcess(env.envCanvas, doublePoint, graph);
	        try { sleep(PAINT_WAIT); } catch(Exception e) { System.out.println(" - Error: " + e.getMessage()); }
    	}
    }
    
    // Method that update the SOM
    private void updateSOM()
    {
        int winnerNode = -1;
        int targetNode = rnd.nextInt(nPoints);
        
        if (cache.containsKey(targetNode)) {
        	winnerNode = cache.get(targetNode);
        	cache.remove(targetNode);
        }
        else
        {
            double winnerDist = Double.MAX_VALUE;
            double localDist = 0.0d;
            Node node = graph.getLast();
            
            // Search for the node closest to that impulse
            do
            {
            	localDist = getEuclideanDistance(doublePoint[targetNode], node);
                if (localDist < winnerDist)
                {
                	winnerDist = localDist;  
                    winnerNode = node.keyName;
                }
                node = node.next;
            }
            while (node != graph.getLast());
            
            cache.put(targetNode, winnerNode);
        }
        
        // Move the winning node and give it its prize
        graph.get(winnerNode).victories++;
        closeupPhase(winnerNode, targetNode);
    }
    
    // Method of the network nodes insertion
    private void addNodes()
    {   
    	Node winner = null;
        Node node = graph.getLast();
        int maxVictories = 0;
        
        do
        {
            if (node.victories > maxVictories)
            {
            	maxVictories = node.victories;
                winner = node;
            }
            node = node.next;
        }
        while (node != graph.getLast());
        
        winner.victories = 0;
        
        // A new node is inserted to the right
        double x = (winner.x + winner.next.x) / 2;
        double y = (winner.y + winner.next.y) / 2;
        graph.rightInsert(new Node(graph.size(), 0, x, y), winner);
        
        // A new node is inserted to the left
        x = (winner.x + winner.previous.x) / 2;
        y = (winner.y + winner.previous.y) / 2;
        graph.leftInsert(new Node(graph.size(), 0, x, y), winner);
    }
    
    // This method ends the TSP. Check which stimuli need a new node
    private void finish()
    {
    	// Local variables
        double distUP = 0.0d;
        double dist = 0.0d;
        int key = 0;
        Node node = null;
        
        for (int i = 0; i < nPoints; i++)
        {
            if (cache.containsKey(i) && !graph.get(cache.get(i)).assigned) {
            	key = cache.get(i);
            }
            else
            {
                node = graph.getLast();
                distUP = Double.MAX_VALUE;
                do
                {
                    if (!node.assigned)
                    {
                        dist = getEuclideanDistance(doublePoint[i], node);
                        if (dist < distUP)
                        {
                            distUP = dist;
                            key = node.keyName;
                        }
                    }
                    node = node.next;
                }
                while (node != graph.getLast());
            }
            
            // Update
            node = graph.get(key);
            node.x = doublePoint[i].x;
            node.y = doublePoint[i].y;
            node.assigned = true;
        }
        
        // Finally, remove isolated nodes from the graph
        removeIsolatedNodes();
    }
    
    // Method that eliminate isolated nodes
    private void removeIsolatedNodes()
    {
        Node currNode = graph.getLast();
        Node auxNode = null;
        int n = graph.size();
        
        for (int i = 0; i <= n; i++)
        {
        	auxNode = currNode.next;
            if (!currNode.assigned)
            	graph.remove(currNode);
            currNode = auxNode;
        }
        
    }

    // This method brings the winning node and its neighbors closer to the stimulus
    private void closeupPhase(int key, int ix)
    {
        Node node = graph.get(key);
        
        // Update target node position
        node.x += LAMBDA * (doublePoint[ix].x - node.x);
        node.y += LAMBDA * (doublePoint[ix].y - node.y);
        
        // Update right neighbor node position
        node.previous.x += 0.4 * LAMBDA * (doublePoint[ix].x - node.previous.x);
        node.previous.y += 0.4 * LAMBDA * (doublePoint[ix].y - node.previous.y);
        
        // Update left neighbor node position
        node.next.x += 0.4 * LAMBDA * (doublePoint[ix].x - node.next.x);
        node.next.y += 0.4 * LAMBDA * (doublePoint[ix].y - node.next.y);
    }
    
    // Returns the Euclidean distance between 2 points in the plane
    private double getEuclideanDistance(Node a, Node b) {
    	return getEuclideanDistance(new DoublePoint(a.x, a.y), b);
    }
    
    private double getEuclideanDistance(DoublePoint a, Node b) {
    	if (a != null && b != null)
    		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    	
    	return Double.MAX_VALUE;
    }
    
}
