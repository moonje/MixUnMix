package mixUnMix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/***********************************************************************
 * @author Jennifer Moon
 * @author Molly Alger
 * 
 * @version 11/15/2015
 **********************************************************************/
public class Mix implements IMix{

	/** LinkList of Characters Representing a Message (String) **/
	private LinkList message; 
	
	/** A clipboard to save pieces of the message to **/
	private String clipboard;
	
	/** A String of the commands given by the user **/
	private String commands;
	
	
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
		
		
	}
	
	/*******************************************************************
	 * Removes the character at the given position
	 * 
	 * @param position the location of the character to be removed
	 ******************************************************************/
	public void remove(int position){
		
		
	}
	
	/*******************************************************************
	 * Switches the character at one position with the character at
	 * another position
	 * 
	 * @param pos1 the location of one of the characters to be switched
	 * @param pos2 the location of the other character to be switched
	 ******************************************************************/
	public void switchPosition(int pos1, int pos2){
		
		
	}
	
	/*******************************************************************
	 * Saves the commands to a text file
	 * 
	 * @param filename the name of the file to be saved to
	 ******************************************************************/
	public void save(String filename) throws Exception{
		
		PrintWriter out = null;
		try {

			//open and read file
			out = new PrintWriter(new BufferedWriter(new FileWriter
					(filename)));

			//print to file
			for (int i = 0; i < acts.size(); i++){

				

			}

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
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************
	 * 
	 ******************************************************************/
	@Override
	public void setInitialMessage(String message) {
		// TODO Auto-generated method stub
		
	}
}
