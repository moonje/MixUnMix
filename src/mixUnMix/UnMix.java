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
	//private LinkList message; 
		
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
		
		Mix mix = new Mix();
		mix.setInitialMessage(userMessage);
		
		//Ensures the user is reading from a .txt file
		if (filename.substring(filename.lastIndexOf(".") + 1).equals(
				"txt")) {

			try {
				Scanner fileReader = new Scanner(new File(filename));


				while (fileReader.hasNextLine()) {
					
					String text = fileReader.nextLine();
					String [] part = text.split("pizza");
					
					if (part[0].equals("r")){
						
						mix.remove(Integer.parseInt(part[1]));
						
					} else if (part[0].equals("b")){

						mix.insert(part[1], Integer.parseInt(part[2]));
						
					} else if (part[0].equals("w")){
			
						mix.switchPosition(Integer.parseInt(part[1]),
											Integer.parseInt(part[2]));
						
					}else if (part[0].equals("x")) {
					
					//add more relevant things here.
					}else if (part[0].equals("p")) {
						
						
					}else if (part[0].equals("c")) {
						
						
					}
				}
				
				
				fileReader.close();
				
				return mix.messageToString(); 

			} catch (FileNotFoundException e) {
				return "WARNING! File not found!";
			
			} catch (Exception e) {
				return "WARNING! Unable to load file!";
			}
			
		} else {
			return "WARNING! Only able to open .txt files!";
		}

		//return null;
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
		
		System.out.println("The original message was:");
		System.out.println(unmix.UnMixUsingFile(filename, userMessage));
	}
}