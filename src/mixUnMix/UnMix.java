package mixUnMix;

import java.io.*;

import java.util.Scanner;

/***********************************************************************
 * @author Jennifer Moon
 * @author Molly Alger
 * 
 * @version 12/2/2015
 **********************************************************************/
public class UnMix implements IUnMix{
	LinkList<String> message;
		
	/*******************************************************************
	 * Default constructor for UnMix
	 ******************************************************************/
	public UnMix(){
		message = new LinkList<String>();
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
		
		UnMix um = new UnMix();
		//Mix mix = new Mix();
		
		um.setInitialMessage(userMessage);
		
		if (filename.substring(filename.lastIndexOf(".") + 1).equals
				("txt")){
		
			try {
				Scanner fileReader = new Scanner (new File(filename));
				
				while (fileReader.hasNextLine()){
					String text = fileReader.nextLine();
					um.processCommand(text);
				}
				
				fileReader.close();
				
				return "The original message was:\n" + 
					um.messageToString();
				
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
	 * Sets the initial message to a LinkList
	 * 
	 * @param msg the user's message
	 ******************************************************************/
	public void setInitialMessage(String msg) {
		
		String[] characters = msg.split("(?<!^)");
		
		for (int i = 0; i < characters.length; i++){
			message.addAtEnd(characters[i]);
		}
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
		
		String data = message.removeAtIndex(position);
		
		if (data != null){
			
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
		
			
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/*******************************************************************
	 * Processes the command
	 * 
	 * @param command, the command
	 * @return String WHAT DO IT DOOOOO
	 * @throws IllegalArgumentException
	 ******************************************************************/
	public String processCommand(String command) 
			throws IllegalArgumentException, 
			UnsupportedOperationException{
		
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
				if (com.length != 2){
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
						switchPosition(pos1, pos2);
						
					} catch (Exception e) {
						throw new IllegalArgumentException(); 
					}
					
				} else {
					throw new IllegalArgumentException(); 
				}
				break; 
			
	
			//Command doesn't exist 
			default:
				throw new UnsupportedOperationException();
			}
			
		} catch (UnsupportedOperationException e)  {
			throw new UnsupportedOperationException();
			
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		return message.toNumbersString();
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
		
		scanner.close();
	}
}