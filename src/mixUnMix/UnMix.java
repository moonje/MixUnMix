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
		
		if (filename.substring(filename.lastIndexOf(".") + 1).equals
				("txt")){
		
			try {
				Scanner fileReader = new Scanner (new File(filename));
				
				while (fileReader.hasNextLine()){
					String text = fileReader.nextLine();
					mix.processCommand(text);
				}
				
				fileReader.close();
				
				return "The original message was:\n" + 
					mix.messageToString();
				
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
		
		scanner.close();
	}
}