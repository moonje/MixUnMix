package mixUnMix;

import static org.junit.Assert.*;
import org.junit.Test;

/***********************************************************************
JUnit Tests used to test Node, Mix, LinkList, and UnMix

@author Jennifer Moon
@author Molly Alger
@version 11/15/2015
***********************************************************************/


public class MixUnMixTests {

	
	//Tests: SetInitialMessage() in Mix
	@Test
	public void testSetInitialMessage(){
		Mix m1 = new Mix();
		m1.setInitialMessage("abcdefghijklmnopqrstuvwxyz");
		assertEquals(m1.messageToString(), 
				"a b c d e f g h i j k l m n o p q r s t u v w x y z ");
		
		m1 = new Mix();
		m1.setInitialMessage(" ");
		assertEquals(m1.messageToString(),"  ");
		
		m1 = new Mix(); 
		m1.setInitialMessage("   ");
		assertEquals(m1.messageToString(), "      ");
		
		m1 = new Mix();
		m1.setInitialMessage("! ? ! ? !");
		assertEquals(m1.messageToString(), "!   ?   !   ?   ! ");
		
		//CHECK ME OUT
		m1 = new Mix();
		m1.setInitialMessage("");
		assertEquals(m1.messageToString(), " ");
		
		m1 = new Mix();
		m1.setInitialMessage("	"); //tab
		assertEquals(m1.messageToString(), "	 ");
		
		m1 = new Mix();
		m1.setInitialMessage("| & 1 ~ `' ^%$");
		assertEquals(m1.messageToString(), "|   &   1   ~   ` '   ^ % $ ");
	}
	
}
