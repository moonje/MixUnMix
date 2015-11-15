package mixUnMix;

/***********************************************************************
 * @author Jennifer Moon
 * @author Molly Alger
 * 
 * @version 11/15/2015
 **********************************************************************/
public class Mix implements IMix{

	/** LinkList of Characters Representing a Message (String) **/
	private LinkList message; 
	
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
