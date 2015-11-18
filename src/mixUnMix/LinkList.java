package mixUnMix;

/***********************************************************************
 * @author Roger Ferguson
 * @author Jennifer Moon
 * @author Molly Alger
 * 
 * @version 11/13/2015
 **********************************************************************/
public class LinkList<E> {
	
	/** The First Node in the LinkList **/
	private Node<E> top; 
	
	/** The Tail Node in the LinkList **/
	private Node<E> tail;

	/*******************************************************************
	 * Default constructor used to instantiate the LinkList
	 ******************************************************************/
	public LinkList() {
		top = null;   // 0000
		tail = null;  // 0000
	}

	/*******************************************************************
	 * Adds a new first node to the LinkList
	 * 
	 * @param data, the type of the data the Node will contain
	 ******************************************************************/
	public void addFirst (E data) {
		//Old way
		//top = new Node<E> (data, top);
		
		//Case 0: No list
		if (top == null){
			top = new Node<E>(data, top);
			tail = top; 
		}
		
		//Case 1: There is a list 
		else {
			top = new Node<E>(data,top);
		}
	}
	
	/*******************************************************************
	 * Returns the top Node
	 * 
	 * @return the top node
	 ******************************************************************/
	public Node<E> getTop(){
		return top; 
	}
	
	/*******************************************************************
	 * Returns the tail Node
	 * 
	 * @return the tail node
	 ******************************************************************/
	public Node<E> getTail(){
		return tail; 
	}
	
	/*******************************************************************
	 * Displays the contents of the LinkList
	 ******************************************************************/
	public void display() {

//		String str = "";
//		Node<E> temp = top;
//		
//		//prints the data from each node
//		while (temp != null) {
//			str += temp.getData() + "\n";
//			temp = temp.getNext();
//		}
//		
		System.out.println(this.toString()); 
	}
	
	/*******************************************************************
	 * Converts the LinkList information to a String
	 * 
	 * @return string representing the LinkList
	 ******************************************************************/
	public String toString() {

		String str = "";
		Node<E> temp = top;
		
		//prints the data from each node
		while (temp != null) {
			str += temp.getData() + " ";
			temp = temp.getNext();
		}
		
		return str; 
	}

	/*******************************************************************
	 * Returns the number of Nodes in the LinkList
	 * 
	 * @return the number of Nodes in the LinkList
	 ******************************************************************/
	public int count() {
		int count = 0;

		Node<E> temp = top;
		
		//counts each node in the list
		while (temp != null) {
			count++;
			temp = temp.getNext();
		}

		return count;
	}

	/*******************************************************************
	 * Adds a new Node to the end of the LinkList
	 * 
	 * @param data, the type of data the Node will contain
	 ******************************************************************/
	public void addAtEnd (E data) {
		
		//Case 0: Empty list
		if (top == null) {
			top = new Node<E> (data, top);
			tail = top; 
		
		//Case 1: List with items
		} else {
			tail.setNext(new Node<E>(data, null));
			tail = tail.getNext();
		}
	} 
	
	/*******************************************************************
	 * Adds a new Node with given data at the given index
	 * 
	 * @param index, the position at which the node will be added
	 * @param data, the data to be added
	 * @throws IllegalArgumentException
	 ******************************************************************/
	public void addBeforeIndex(int index, E data) 
			throws IllegalArgumentException{
		
		//index out of bounds
		if (index < 0 || count() < index){
			throw new IllegalArgumentException();
		
		//First node
		} else if (index == 0){
			addFirst(data);
			
		//Last node
		} else if (index == count()){
			addAtEnd(data);
		
		//some other index 
		} else {
			Node<E> temp = top;

			for (int i = 0; i < index - 1; i++){
				temp = temp.getNext();
			}
			
			Node<E> node = new Node<E>(data, temp.getNext());
			temp.setNext(node);	
		}	
	}

	/*******************************************************************
	 * Removes a specified Node from the LinkList
	 * 
	 * @param data, the Node being removed 
	 * @return whether or not the Node was deleted
	 ******************************************************************/
	public boolean delete (E data) {

		//case 0: no list
		if (top == null) 
			return false;
		
		//case 1: found at first node 
		if (top.getData().equals(data)) {
			top = top.getNext();
			
			//list of one node
			if (top == null){
				tail = top; 	
			}

			return true;
		}
		
		//case 2: found in middle
		Node<E> temp = top;
		
		while (temp.getNext() != null) {
			
			if (temp.getNext().getData().equals(data)) {
				
				temp.setNext(temp.getNext().getNext());
				
				if (temp.getNext() == null){
					tail = temp;
				}
				
				return true;
			}
			
			temp = temp.getNext();		
		}
		
		//case 3: data not found
		return false;
	}

//	/*******************************************************************
//	 * Main Method used to test the LinkList
//	 * 
//	 * Provided by Professor Ferguson; we will need to use JUnit testing
//	 ******************************************************************/
//	public static void main (String[] args){
//		LinkList<String> list = new LinkList<String>();
//		
//		list.addAtEnd("pizza5");
//		list.addFirst("pizza1");
//		list.addFirst("pizza2");
//		list.addFirst("pizza3");
//		list.addAtEnd("pizza4");
//
//		list.display();
//		
//		list.delete("pizza1");
//		
//		System.out.println("-------");
//		list.display();
//		
//
//		list.addAtEnd("pizza11");
//		list.addFirst("pizza3");
//		list.addFirst("pizza4");
//		list.addFirst("pizza5");
//		list.addFirst("pizza6");
//		list.addFirst("pizza7");
//		list.addFirst("pizza8");
//		list.addAtEnd("pizza9");
//
//		System.out.println("-------");
//		list.display();
//
//	}
}