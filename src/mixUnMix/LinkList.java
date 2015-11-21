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
	 * Switches the node at pos1 with the node at pos2
	 * 
	 * @param pos1, the first position
	 * @param pos1, the second position
	 * @throws IllegalArgumentException
	 ******************************************************************/
	public void switchNodes(int pos1, int pos2) 
			throws IllegalArgumentException{
		
		if (pos1 < 0 || pos2 < 0 || 
				pos1 > count() - 1 || pos2 > count() - 1){
			throw new IllegalArgumentException();
		}
		
		Node<E> temp1 = top;
		
		//Finds the node at position 1
		for (int i = 0; i < pos1; i++){
			temp1 = temp1.getNext();
		}
		Node<E> temp2 = top;
		
		//Finds the node at position 2
		for (int i = 0; i < pos2; i++){
			temp2 = temp2.getNext();
		}
		
		//Switches the data in the nodes
		E data = temp1.getData();
		temp1.setData(temp2.getData());
		temp2.setData(data);
	}
	
	/*******************************************************************
	 * Displays the contents of the LinkList
	 ******************************************************************/
	public void display() {
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
	 * Removes a node at the specified index 
	 * 
	 * @return E, data that was removed
	 * @param position, the index of the Node
	 * @throws IllegalArgumentException
	 ******************************************************************/
	public E removeAtIndex(int position) 
			throws IllegalArgumentException{
		
		E data = null; 
		
		//case 0: no list or index too large
		if (top == null || position >= count() || position < 0){
			throw new IllegalArgumentException();
		}
		
		//case 1: found at first node 
		if (position == 0) {
			data = top.getData();
			top = top.getNext();
			
			//list of one node
			if (top == null){
				tail = top; 	
			}
			return data;
		}
		
		//case 2: found in middle
		
		Node<E> pointer = top;
		
		//Finds the node at the specified index
		for (int i = 0; i < position - 1; i++){
			pointer = pointer.getNext();
		}

		if (pointer.getNext() == null){
			tail = pointer; 
			pointer.setNext(null);
		} else {
			data = pointer.getNext().getData();
			pointer.setNext(pointer.getNext().getNext());
		}
		
		if (data != null)
			return data;
		else 
			throw new IllegalArgumentException();
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
	
	/*******************************************************************
	 * Converts the LinkList information to a String with numbers by 
	 * index 
	 * 
	 * @return string representing the LinkList with numbers
	 ******************************************************************/
	public String toNumbersString() {

		String str = "";
		Node<E> temp = top;
		
		String numbers = "";
		int i = 0; 
		
		//prints the data from each node
		while (temp != null) {
			
			if (i < 10){
				numbers += i + " ";
				str += temp.getData() + " ";
				
			} else if (i >= 10 && i < 100) {
				numbers += i + "  ";
				str += temp.getData() + "  ";
			} else {
				numbers += i + "   ";
				str += temp.getData() + "   ";
			}
			
			temp = temp.getNext();
			i++;
		}
		
		str = numbers + "\n" + str + "\n"; 
		
		return str; 
	}
}