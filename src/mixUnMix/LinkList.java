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

		if (top == null) {
			top = new Node<E> (data, top);
			
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

		if (top == null) 
			return false;
		
		if (top.getData().equals(data)) {
			top = top.getNext();
			return true;
		}
		
		// not done yet.
		
		
		
		return true;
		 
	}

	/*******************************************************************
	 * 
	 ******************************************************************/
	public void deleteHalfWay() {


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