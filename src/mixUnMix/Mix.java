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
	
	/** Generic Error Message **/
	final String error = "Unable to process command: incorrect format!";
	
	/*******************************************************************
	 * Default constructor for Mix
	 ******************************************************************/
	public Mix(){
		message = new LinkList<String>();
		clipboard = new LinkList<String>();
	}
	
	/*******************************************************************
	 * Returns the string of commands 
	 * 
	 * @return String of the commands
	 ******************************************************************/
	public String getCommands(){
		return commands; 
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
	public void insert(String c, int position){
		
		message.addBeforeIndex(position, c);

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
	 * @return String telling the user whether or not the file saved
	 ******************************************************************/
	public String save(String filename){
		
		PrintWriter out = null;
		String update = "";
		
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter
					(filename)));

			out.print(commands);
			
			update = "File saved successfully!";

		} catch (IOException e) {
			update = "WARNING: File was not saved successfully!";

		} finally {
			//close file
			out.close();
		}
		
		return update; 
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
	 * Processes the user's command
	 * 
	 * @param command, the user's command
	 * @return String WHAT DO IT DOOOOO
	 ******************************************************************/
	@Override
	public String processCommand(String command) {
		
		try{
			String[] com = command.split(" ");
			
			switch (com[0]) {
			case "Q":
				//quit
				break; 
				
			case "b":
				//insert char c before position #
				if (com.length != 3 || com[1].length() > 1){
					return error;
					
				} else {
					try {
						int index = Integer.parseInt(com[2]);
						insert(com[1], index);
						
					} catch (Exception e) {
						return error; 
					}
				}
				break; 
				
			case "r":
				//remove a char at position #
				break; 
				
			case "w":
				//switch characters at position & and #
				break; 
				
			case "s":
				//save the commands to the filename 
				break; 
				
			case "x":
				//cut to the clipboard, starting at & to # (inclusive)
				break; 
				
			case "p":
				//paste from clipboard, starting at #
				break; 
				
			case "c":
				//copy to clipboard, starting at & to # (inclusive)
				break; 
				
			default:
				return "Command not found";
			}
		} catch (Exception e) {
			return error;
		}

		return "Something useful, like probably the updated LinkList";
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
			message.addAtEnd(characters[i]);
		}
	}
	
	/*******************************************************************
	 * Display message (LinkList)
	 ******************************************************************/
	public void displayMessage(){
		System.out.println(message.toString());
	}
	
	/*******************************************************************
	 * Converts message (LinkList) to a String
	 * 
	 * @return String representing message
	 ******************************************************************/
	public String messageToString(){
		return message.toString();
	}
	
	/*******************************************************************
	 * Displays clipboard (LinkList)
	 ******************************************************************/
	public void displayClipboard(){
		System.out.println(clipboard.toString());
	}
	
	/*******************************************************************
	 * Converts clipboard (LinkList) to a String
	 * 
	 * @return String representing message
	 ******************************************************************/
	public String clipboardToString(){
		return message.toString();
	}
}
