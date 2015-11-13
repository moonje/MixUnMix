package mixUnMix;

/***********************************************************************
 * @author Roger Ferguson
 * @author Jennifer Moon
 * @author Molly Alger
 * 
 * @version 11/13/2015
 **********************************************************************/
public interface IMix {

	   /** processes the given mix command */
	   String processCommand(String command);
		
	   /** set the original message */
	   void setInitialMessage(String message);

	}