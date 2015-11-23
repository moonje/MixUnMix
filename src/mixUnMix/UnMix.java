package mixUnMix;

import java.io.*;

import java.util.Scanner;

/***********************************************************************
 * @author Jennifer Moon
 * @author Molly Alger
 * 
 * @version 11/15/2015
 **********************************************************************/
public class UnMix implements IUnMix{

	/** LinkList of characters representing a message (String) **/
	private LinkList<String> message; 
	
	/** The clipboard **/
	private LinkList<String> clipboard;
	
	/*******************************************************************
	 * Default constructor for Mix
	 ******************************************************************/
	public UnMix(){
		message = new LinkList<String>();
		clipboard = new LinkList<String>();
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
		try { 
			message.removeAtIndex(position);
		} catch (Exception e) {
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
			
		} catch (Exception e) {
			throw new IllegalArgumentException();
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
		
		clipboard.deleteAll();
		clipboard.addFirst(message.getAtIndex(pos1));
		
		int count = pos2 - pos1;
		
		for(int i = 1; i <= count; i++){
			clipboard.addBeforeIndex(i, message.getAtIndex(pos1 + i));
		}
		
		for(int i = 0; i <= count; i++){
			message.removeAtIndex(pos1);
		}
	}
	
	/*******************************************************************
	 * Pastes whatever is saved to the clipboard into the message at
	 * the given position
	 * 
	 * @param position the location of where to paste the contents of
	 * 			the clipboard
	 ******************************************************************/
	public void paste(int position){
		for(int i = clipboard.count() - 1; i >= 0; i--){
			message.addBeforeIndex(position + 1, 
					clipboard.getAtIndex(i));
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
	}
	
	/*******************************************************************
	 * Processes the user's command
	 * 
	 * @param command, the user's command
	 * @throws IllegalArgumentException
	 ******************************************************************/
	public void processCommand(String command) 
			throws IllegalArgumentException {
		
		try{
			String[] com = command.split(" ");
			
			switch (com[0]) {
			
			//insert char c before position #
			case "b":
				
				//user put in incorrectly formatted command
				if (com[1].length() > 1){
					throw new IllegalArgumentException();
					
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
								throw new IllegalArgumentException();
							}
							
						//Character input is not a string
						} else {
							
							if (com.length == 3){
								index = Integer.parseInt(com[2]);
								
							//User put in too much
							} else {
								throw new IllegalArgumentException();
							}
						}
						
						insert(com[1], index);
						
					} catch (Exception e) {
						throw new IllegalArgumentException();
					}
				}
				break; 
			
			//remove a char at position #
			case "r":
				
				//user put in incorrectly formatted command
				if (com.length != 2 || com[1].length() > 1){
					throw new IllegalArgumentException();
					
				} else {
					try {
						int index = Integer.parseInt(com[1]);
						remove(index);
						
					} catch (Exception e) {
						throw new IllegalArgumentException(); 
					}
				}
				break; 
			
			//switch characters at position & and #
			case "w":
				
				if (com.length == 3){
					try {
						int pos1 = Integer.parseInt(com[1]);
						int pos2 = Integer.parseInt(com[2]);
						message.switchNodes(pos1, pos2);
						
					} catch (Exception e) {
						throw new IllegalArgumentException(); 
					}
					
				} else {
					throw new IllegalArgumentException(); 
				}
				break; 
				
			//cut to the clipboard, starting at & to # (inclusive)
			case "x":
				
				//user put in incorrectly formatted command
				if (com.length != 3){
					throw new IllegalArgumentException();
					
				} else {
					try {
						int start = Integer.parseInt(com[1]);
						int end = Integer.parseInt(com[2]);
						cut(start, end);
						
					//user didn't put in numbers
					} catch (Exception e) {
						throw new IllegalArgumentException();
					}
				}
				break; 	
				
			//paste from clipboard, starting at #
			case "p":
				
				if(com.length != 3){
					throw new IllegalArgumentException();
					
				} else {
					try{
						int pos1 = Integer.parseInt(com[1]);
						paste(pos1);
					} catch (Exception e){
						throw new IllegalArgumentException();
					}
				}
				break; 
				
			//copy to clipboard, starting at & to # (inclusive)
			case "c":
				
				//user put in incorrectly formatted command
				if (com.length != 3){
					throw new IllegalArgumentException();
					
				} else {
					try {
						int start = Integer.parseInt(com[1]);
						int end = Integer.parseInt(com[2]);
						copy(start, end);
						
					//user didn't put in numbers
					} catch (Exception e) {
						throw new IllegalArgumentException();
					}
				}
				break; 
				
			//Command doesn't exist 
			default:
				throw new IllegalArgumentException();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/*******************************************************************
	 * Sets the initial message to a LinkList
	 * 
	 * @param msg the user's message
	 ******************************************************************/
	public void setMessage(String msg) {
		
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
	 * UnMixes the user's message used the given file
	 * 
	 * @param filename, the name of the file
	 * @param userMessage, the user's message to unmix 
	 * @return the decoded message
	 ******************************************************************/
	@Override
	public String UnMixUsingFile(String filename, String userMessage) 
			throws IllegalArgumentException{
		
		setMessage(userMessage);
		System.out.println(messageToString());
		
		//Ensures the user is reading from a .txt file
		if (filename.substring(filename.lastIndexOf(".") + 1).equals(
				"txt")) {

			try {
				Scanner fileReader = new Scanner(new File(filename));

				while (fileReader.hasNextLine()) {
					String text = fileReader.nextLine();
					processCommand(text);
				}
				
				fileReader.close();
				
				return "The original message was:\n" + 
						messageToString(); 

			} catch (FileNotFoundException e) {
				return "WARNING! File not found!";
			
			} catch (Exception e) {
				return "WARNING! Unable to load file!";
			}
			
		} else {
			return "WARNING! Only able to open .txt files!";
		}
	}
	
	/*******************************************************************
	 * Main method used to run the UnMix program in the console
	 ******************************************************************/
	public static void main (String args[]){
		UnMix unmix = new UnMix();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter in the mixed up message:");
		String userMessage = scanner.nextLine();
		
		System.out.println("Enter in the file to unmix the message:");
		String filename = scanner.nextLine();
		
		System.out.println(unmix.UnMixUsingFile(filename, userMessage));
	}
}