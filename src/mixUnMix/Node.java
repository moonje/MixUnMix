package mixUnMix;

/***********************************************************************
 * @author Roger Ferguson
 * @author Jennifer Moon
 * @author Molly Alger
 * 
 * @version 12/2/2015
 **********************************************************************/
public class Node<E> {

	/** The data in the Node **/
	public E data;

	/** Next Node in the LinkList **/
	public Node<E> next;

	/*******************************************************************
	 * Constructor used to instantiate the Node, given its data and 
	 * the next Node
	 * 
	 * @param data, the data in the Node
	 * @param next, the next Node
	 ******************************************************************/
	public Node(E data, Node<E> next) {
		this.data = data;
		this.next = next;
	}

	/*******************************************************************
	 * Default constructor to instantiate the Node
	 ******************************************************************/
	public Node() {
			data = null; 
			next = null; 
	}

	/*******************************************************************
	 * Returns the data in the Node
	 * 
	 * @return the data in the Node
	 ******************************************************************/
	public E getData() {
		return data;
	}

	/*******************************************************************
	 * Sets the data in the Node to specified information 
	 * 
	 * @param data, the data to be put in the Node
	 ******************************************************************/
	public void setData(E data) {
		this.data = data;
	}

	/*******************************************************************
	 * Returns the next Node
	 * 
	 * @return the next node in the LinkList
	 ******************************************************************/
	public Node<E> getNext() {
		return next;
	}

	/*******************************************************************
	 * Sets the next Node
	 * 
	 * @param next, the next node 
	 ******************************************************************/
	public void setNext(Node<E> next) {
		this.next = next;
	}
}