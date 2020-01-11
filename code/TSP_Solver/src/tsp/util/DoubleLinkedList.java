/*
 * DoubleLinkedList.java
 *
 * Created on June 10, 2007, 01:03 PM
 * Created by Andres Segura
 * 
 */

package tsp.util;

// Double Linked List class
public class DoubleLinkedList
{
    // Class variables
    private Node lastNode;
    private int nNodes;
	
    // Class constructor
    public DoubleLinkedList()
    {
    	lastNode = null;
        nNodes = 0;
    }
    
    // Returns if the list is empty or not
    public boolean isEmpty()
    {
        return (lastNode == null);
    }
    
    // Returns the number of nodes in the list
    public int size()
    {
        return nNodes;
    }
    
    // Returns the last selected node
    public Node getLast()
    {
    	return lastNode;
    }

    // Search for a node from its key name
    public Node get(int key)
    {
		Node n = getLast();
		while (n.next != lastNode && n.keyName != key)
			n = n.next;
		
		if (n.keyName == key)
            return n;
		
		return null;
    }
	
    // Insert a node to the right of node p 
    public void rightInsert(Node n, Node p)
    {
        if (isEmpty()) {
            lastNode = n;
        }
		else
		{
            p.next.previous = n;
            n.next = p.next;
            p.next = n;
            n.previous = p;
		}
        nNodes++;
    }
    
    // Insert a node to the left of node p
    public void leftInsert(Node n, Node p)
    {
        if (isEmpty()) {
        	lastNode = n;
        }
        else
        {
            p.previous.next = n;
            n.previous = p.previous;
            p.previous = n;
            n.next = p;
        }
        nNodes++;
    }
    
    // Delete a node from the list
    public void remove(Node node)
    {
        if (node == lastNode)
            lastNode = node.next;
        
        node.previous.next = node.next;
        node.next.previous = node.previous;
        node = null;
        nNodes--;
    }
}
