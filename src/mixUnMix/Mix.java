package mixUnMix;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

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
			+ "ending at # (inclusive)\n"
			+ "h\t\t displays this message again\n";
	
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
		
		try {
			message.addBeforeIndex(position, c);
			
			commands = "r" + " " + position + "\n" + commands;

		} catch (IllegalArgumentException e){
			throw new IllegalArgumentException();
		}
	}
	
	/*******************************************************************
	 * Removes the character at the given position
	 * 
	 * @param position the location of the character to be removed
	 * @throws IllegalArgumentException
	 ******************************************************************/
	public void remove(int position) throws IllegalArgumentException{
		
		String data = message.removeAtIndex(position);
		
		if (data != null){
			commands = "b" + " " + data + " " + position + "\n" + commands;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/*******************************************************************
	 * Switches the character at one position with the character at
	 * another position
	 * 
	 * @param pos1 the location of one of the characters to be switched
	 * @param pos2 the location of the other character to be switched
	 * @throws IllegalArgumentException
	 ******************************************************************/
	public void switchPosition(int pos1, int pos2) 
			throws IllegalArgumentException{
		
		try {
			message.switchNodes(pos1, pos2);
			commands = "w" + " " + pos2 + " " + pos1 + "\n" + commands;
			
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
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
					(filename + ".txt")));

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
	public void cut(int pos1, int pos2) throws IllegalArgumentException{
		
		if (pos1 < 0 || pos2 < 0 || pos1 > message.count() ||
				pos2 > message.count()){
			throw new IllegalArgumentException();
		}
		
		clipboard.deleteAll();
		clipboard.addFirst(message.getAtIndex(pos1));
		
		int count = pos2 - pos1;
		
		for(int i = 1; i <= count; i++){
			clipboard.addBeforeIndex(i, message.getAtIndex(pos1 + i));
		}
		
		for(int i = 0; i <= count; i++){
			commands = "b " + message.removeAtIndex(pos1) + 
					" " + pos1 + "\n" + commands;
		}
		
		//ADD TO COMMANDS
		//commands = "b " +  commands;
	}
	
	/*******************************************************************
	 * Pastes whatever is saved to the clipboard into the message at
	 * the given position
	 * 
	 * @param position the location of where to paste the contents of
	 * 			the clipboard
	 * @throws IllegalArgumentException
	 ******************************************************************/
	public void paste(int position) throws IllegalArgumentException{
		
		if (clipboard.getTop() == null){
			throw new IllegalArgumentException();
		}
		
		if (position > clipboard.count() + 1 || position < 0){
			throw new IllegalArgumentException();
		}
		
		for(int i = clipboard.count() - 1; i >= 0; i--){
			message.addBeforeIndex(position, 
					clipboard.getAtIndex(i));
			
			//CHECK ME OUT
			//ADD TO COMMANDS
			commands = "r " + (position + 1 + i) + "\n" + commands; 
		}
	}
	
	/*******************************************************************
	 * Copies the message from one position to another position and
	 * saves it to the clipboard
	 * 
	 * @param pos1 the starting position of the copy
	 * @param pos2 the ending position of the copy
	 ******************************************************************/
	public void copy(int pos1, int pos2){
		
		clipboard.deleteAll();
		clipboard.addFirst(message.getAtIndex(pos1));
		
		int count = pos2 - pos1;
		
		for(int i = 1; i <= count; i++){
			clipboard.addBeforeIndex(i, message.getAtIndex(pos1 + i));
		}
		
		//ADD TO COMMANDS: no need to, right? 
	}
	
	/*******************************************************************
	 * Processes the user's command
	 * 
	 * @param command, the user's command
	 * @return String WHAT DO IT DOOOOO
	 ******************************************************************/
	@Override
	public String processCommand(String command) {
		
		String returnString = "";
		
		try{
			String[] com = command.split(" ");
			
			switch (com[0]) {
			
			case "h":
				if (com.length == 1)
					return cmd;
				else
					returnString = error;
			
			//quit
			case "Q":
				quit();
				break; 
			
			//insert char c before position #
			case "b":
				
				//user put in incorrectly formatted command
				if (com[1].length() > 1){
					returnString = error;
					
				} else {
					try {
						int index = -1; 
						
						//Character input is a space
						if (command.charAt(1) == ' ' &&
								command.charAt(2) == ' '){
							
							if (com.length == 4){
								index = Integer.parseInt(com[3]);
								com[1] = " ";
								
							//User put in too much
							} else {
								returnString = error;
							}
							
						//Character input is not a string
						} else {
							
							if (com.length == 3){
								index = Integer.parseInt(com[2]);
								
							//User put in too much
							} else {
								returnString = error;
							}
						}
						
						insert(com[1], index);
						
					} catch (Exception e) {
						returnString = error; 
					}
				}
				break; 
			
			//remove a char at position #
			case "r":
				
				//user put in incorrectly formatted command
				if (com.length != 2){
					returnString = error;
					
				} else {
					try {
						int index = Integer.parseInt(com[1]);
						remove(index);
						
					} catch (Exception e) {
						returnString = error; 
					}
				}
				break; 
			
			//switch characters at position & and #
			case "w":
				
				if (com.length == 3){
					try {
						int pos1 = Integer.parseInt(com[1]);
						int pos2 = Integer.parseInt(com[2]);
						switchPosition(pos1, pos2);
						
					} catch (Exception e) {
						returnString = error; 
					}
					
				} else {
					returnString = error; 
				}
				break; 
			
			//save the commands to the filename 	
			case "s":
				if (com.length == 2){
					
					try {
						return save (com[1]) + "\nYour mixed message is"
								+ " " + message.toString() + "\n";
						
					} catch (Exception e) {
						returnString = error;
					}
				} else {
					returnString = error;
				}
				
			//cut to the clipboard, starting at & to # (inclusive)
			case "x":
				
				if(com.length == 3){
					try{
						int pos1 = Integer.parseInt(com[1]);
						int pos2 = Integer.parseInt(com[2]);
						cut(pos1, pos2);
						
					} catch (Exception e){
						returnString = error;
					}
					
				} else {
					returnString = error;
				}
				
				break; 
				
			//paste from clipboard, starting at #
			case "p":
				if(com.length == 2){
					try{
						int pos1 = Integer.parseInt(com[1]);
						paste(pos1);
						
					} catch (Exception e){
						returnString = error;
					}
				} else {
					returnString = error;
				}
				break; 
				
			//copy to clipboard, starting at & to # (inclusive)
			case "c":
				if(com.length == 3){
					try{
						int pos1 = Integer.parseInt(com[1]);
						int pos2 = Integer.parseInt(com[2]);
						copy(pos1, pos2);
					} catch (Exception e){
						
						returnString = error;
					}
				}
				break; 
				
			//Command doesn't exist 
			default:
				returnString = "Command not found";
			}
		} catch (Exception e) {
			returnString = error;
		}

		return returnString;
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
		return clipboard.toString();
	}
	
	/*******************************************************************
	 * Returns a string with the numbers of the nodes and the 
	 * data of the nodes
	 * 
	 * @return String representing message
	 ******************************************************************/
	public String toNumbersString(){
		return message.toNumbersString();
	}
	
	/*******************************************************************
	 * Takes in user inputs 
	 ******************************************************************/
	public static void main (String args[]){
		Mix mix = new Mix();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter initial message:");
		String userMessage = scanner.nextLine();
		
		mix.setInitialMessage(userMessage);
		
		System.out.print(mix.cmd);
		System.out.println("Message is:");
		System.out.println(mix.toNumbersString());
		System.out.println("Command:");

		String input = "";
		
	    while ((input = scanner.nextLine()) != "Q") {
	    	System.out.println(mix.processCommand(input));
	      
	    	System.out.println(mix.toNumbersString());
	    	System.out.println("Command:");
	    }
	}
}