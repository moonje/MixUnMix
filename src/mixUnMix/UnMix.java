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
	private LinkList message; 
		
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
		
		//Ensures the user is reading from a .txt file
		if (filename.substring(filename.lastIndexOf(".") + 1).equals(
				"txt")) {

			try {
				Scanner fileReader = new Scanner(new File(filename));


				while (fileReader.hasNextLine()) {
					
					String text = fileReader.nextLine();
					String [] part = text.split("pizza");
					
					if (part[0].equals("r")){
						//user inserted something, we're going to remove it
						//call appropriate method
						
					} else if (part[0].equals("b")){
						//user removed something, we're going to put it back in
						//call appropriate method
						
					} else if (part[0].equals("w")){
						//User switched two items, we're going to switch them back
						//call appropriate method
						
					} /* else if (other things) { */
					
					//add more relevant things here.
					
				}
				
				//return the correct string 
				
				fileReader.close();

			} catch (FileNotFoundException e) {
				return "WARNING! File not found!";
			
			} catch (Exception e) {
				return "WARNING! Unable to load file!";
			}
			
		} else {
			return "WARNING! Only able to open .txt files!";
		}

		return null;
	}
	
	/*******************************************************************
	 * Main method used to run the UnMix program in the console
	 ******************************************************************/
	public static void main (String args[]){
		UnMix unmix = new UnMix();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter in the mixed up message:");
		String userMessage = scanner.next();
		
		System.out.println("Enter in the file to unmix the message:");
		String filename = scanner.next();
		
		System.out.println("The original message was:");
		System.out.println(unmix.UnMixUsingFile(filename, userMessage));
	}
}