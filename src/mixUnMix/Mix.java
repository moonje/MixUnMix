package mixUnMix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/***********************************************************************
 * @author Jennifer Moon
 * @author Molly Alger
 * 
 * @version 11/15/2015
 **********************************************************************/
public class Mix implements IMix{

	/** LinkList of Characters Representing a Message (String) **/
	private LinkList<String> message; 
	
	/** A clip board to save pieces of the message to **/
	private LinkList<String> clipboard;
	
	/** A String of the commands given by the user **/
	private String commands = "";
	
	/** pizza **/
	final String p = "pizza";
	
	/** The Initial Set of Commands **/
	public String cmd = "Q\t\t means QUIT\n"
			+ "b c #\t\t means INSERT char 'c' before position #\n"
			+ "r #\t\t means REMOVE a char at position #\n" +
			"w & #\t\t means SWITCH character at position & with #\n" +
			"s filename\t means SAVE the the set of undo commands" +
			" into the file name 'filename'\n" +
			"x & #\t\t means CUT to the clipboard starting at & and "
			+ "ending at # (inclusive)\n"
			+ "p #\t\t means PASTE from the clipboard, starting at #\n"
			+ "c & #\t\t means COPY to clipboard, starting at & and "
			+ "ending at # (inclusive)";
	
	
	/*******************************************************************
	 * Default constructor for Mix
	 ******************************************************************/
	public Mix(){
		message = new LinkList<String>();
		clipboard = new LinkList<String>();
	}
	
	/*******************************************************************
	 * Quits the program
	 ******************************************************************/
	public void quit(){
		System.exit(0);
	}
	
	/*******************************************************************
	 * Inserts the character c at the given position
	 * 
	 * @param c the character to be inserted
	 * @param position the position to insert the given character
	 ******************************************************************/
	public void insert(char c, int position){
		
		Node<String> pointer = message.getTop();
		
		for (int i = 0; i < position; i++){
			pointer = pointer.getNext();
		}
		
		Node<String> temp = new Node<String>("" + c, pointer.getNext());
		pointer.setNext(temp);
		
		commands = "r" + p + position + "\n" + commands;
	}
	
	/*******************************************************************
	 * Removes the character at the given position
	 * 
	 * @param position the location of the character to be removed
	 ******************************************************************/
	public void remove(int position){
		
		Node<String> pointer = message.getTop();
		
		for (int i = 0; i < position; i++){
			pointer = pointer.getNext();
		}
		
		String data = (String) pointer.getNext().getData();
		
		pointer.setNext(pointer.getNext().getNext());
		
		commands = "b" + p + data + p + position + "\n" + commands;
	}
	
	/*******************************************************************
	 * Switches the character at one position with the character at
	 * another position
	 * 
	 * @param pos1 the location of one of the characters to be switched
	 * @param pos2 the location of the other character to be switched
	 ******************************************************************/
	public void switchPosition(int pos1, int pos2){
		
		Node<String> temp1 = message.getTop();
		
		for (int i = 0; i < pos1; i++){
			temp1 = temp1.getNext();
		}
		
		Node<String> temp2 = message.getTop();
		
		for (int i = 0; i < pos2; i++){
			temp2 = temp2.getNext();
		}
		
		String data = temp1.getData();
		temp1.setData(temp2.getData());
		temp2.setData(data);
		
		commands = "w" + p + pos2 + p + pos1 + "\n" + commands;
	}
	
	/*******************************************************************
	 * Saves the commands to a text file
	 * 
	 * @param filename the name of the file to be saved to
	 ******************************************************************/
	public void save(String filename) throws Exception{
		
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter
					(filename)));

			out.print(commands);

		} catch (IOException e) {
			throw new IOException(); 

		} finally {
			//close file
			out.close();
		}
	}
	
	/*******************************************************************
	 * Cuts the message beginning at one position and ending at another
	 * and then saves the cut to the clipboard
	 * 
	 * @param pos1 the starting position of the cut
	 * @param pos2 the ending position of the cut
	 ******************************************************************/
	public void cut(int pos1, int pos2){
		
		
	}
	
	/*******************************************************************
	 * Pastes whatever is saved to the clipboard into the message at
	 * the given position
	 * 
	 * @param position the location of where to paste the contents of
	 * 			the clipboard
	 ******************************************************************/
	public void paste(int position){
		
		
	}
	
	/*******************************************************************
	 * Copies the message from one position to another position and
	 * saves it to the clipboard
	 * 
	 * @param pos1 the starting position of the copy
	 * @param pos2 the ending position of the copy
	 ******************************************************************/
	public void copy(int pos1, int pos2){
		
		
	}
	
	/*******************************************************************
	 * 
	 ******************************************************************/
	@Override
	public String processCommand(String command) {

		
		
		return null;
	}

	/*******************************************************************
	 * Sets the initial message to a LinkList
	 * 
	 * @param msg the user's message
	 ******************************************************************/
	@Override
	public void setInitialMessage(String msg) {
		String[] characters = msg.split("(?<!^)");
		
		for (int i = 0; i < characters.length; i++){
			//System.out.println(characters[i]);

			message.addAtEnd(characters[i]);
		}
		
	}
	
	
	/************************Jennifer Testing**************************/
	public void display(){
		message.display();
	}
	
	public static void main(String[] args){
		Mix mix = new Mix();
		
//		String msg = "Hello how are you?";
//		String[] characters = msg.split("(?<!^)");
//		System.out.println(Arrays.toString(characters));
		
		
		mix.setInitialMessage("Hello how are you?");
		mix.display();
		
	}
}
