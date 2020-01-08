/*
 * Node.java
 *
 * Created on June 10, 2007, 01:03 PM
 * Created by Andres Segura
 *
 */

package tsp.util;

// Class Node of the DoubleLinkedList class
public class Node
{
    // Class variables
    public int keyName;
    public int victories;
    public double x;
    public double y;
    public Node next;
    public Node previous;
    public boolean assigned;
	
    // First constructor of Node class
    public Node(int key, int victories, double x, double y)
    {
    	this.keyName = key;
		this.victories = victories;
		this.x = x;
		this.y = y;
		this.next = this;
		this.previous = this;
        this.assigned = false;
    }
	
    // Second constructor of Node class
    public Node(int key, DoublePoint dblPoint)
    {
    	this(key, 0, dblPoint.x, dblPoint.y);
    }
    
}
