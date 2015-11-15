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

	/*******************************************************************
	 * Default constructor used to instantiate the LinkList
	 ******************************************************************/
	public LinkList() {
		top = null;   // 0000
	}

	/*******************************************************************
	 * Adds a new first node to the LinkList
	 * 
	 * @param data, the type of the data the Node will contain
	 ******************************************************************/
	public void addfirst (E data) {
		top = new Node<E> (data, top);
	}

	/*******************************************************************
	 * Displays the contents of the LinkList
	 ******************************************************************/
	public void display() {

		Node<E> temp = top;
		
		//prints the data from each node
		while (temp != null) {
			System.out.println (temp.getData());
			temp = temp.getNext();
		}
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

		//case 0: empty list
		if (top == null) {
			top = new Node<E> (data, top);
		
		//case 1: list with items
		} else {
			
			Node<E> temp = top;
			
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}

			Node<E> temp2 = new Node<E>();
			temp2.setData(data);
			temp2.setNext(null);

			temp.setNext(temp2);
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
		
		//case 1: delete the first node
		if (top.getData().equals(data)) {
			top = top.getNext();
			return true;
		}
		
		Node<E> temp = top;
		
		while(!temp.getData().equals(data)){
			
			//case 2: delete a node from the middle
			if(temp.getNext().getData().equals(data)){
				temp.setNext(temp.getNext().getNext());
				return true;
			}
			
			//case 3: delete a node from the end
			if(temp.getNext() == null){
				temp.setNext(null);
				return true;
			}
			
			temp = temp.getNext();
			
		}
		
		
		//case 4: multiple occurrences of the data
		
		
		//case 5: data not found
		return false;
		 
	}

	/*******************************************************************
	 * 
	 ******************************************************************/
	public void deleteHalfWay() {

		//case 0: empty list
		
		
		//case 1: 
		
	}

	/*******************************************************************
	 * Main Method used to test the LinkList
	 * 
	 * Provided by Professor Ferguson; we will need to use JUnit testing
	 ******************************************************************/
	public static void main (String[] args){
		LinkList<String> list = new 
				LinkList<String>();
		
		list.addAtEnd("pizza5");
		list.addfirst("pizza1");
		list.addfirst("pizza2");
		list.addfirst("pizza3");
		list.addAtEnd("pizza4");

		list.display();
		
		list.delete("pizza1");
		list.display();
		

		//		list.addAtEnd("pizza11");

		//		list.addfirst("pizza3");
		//		list.addfirst("pizza4");
		//		list.addfirst("pizza5");
		//		list.addfirst("pizza6");
		//		list.addfirst("pizza7");
		//		list.addfirst("pizza8");
		//		list.addAtEnd("pizza9");

		//list.display();

	}
}