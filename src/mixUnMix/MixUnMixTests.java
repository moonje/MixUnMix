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
	
	/*******************************************************************
	 * Tests: insert(String c, int position) in Mix.java
	 ******************************************************************/
	@Test
	public void testInsert(){
		Mix m = new Mix(); 
		
		m.insert("A", 0);
		assertEquals(m.messageToString(), "A ");
		assertEquals(m.getCommands(), "rpizza0\n");
		
		m.insert("B", 1);
		assertEquals(m.messageToString(), "A B ");
		assertEquals(m.getCommands(), "rpizza1\nrpizza0\n");
	}
	
	/*******************************************************************
	 * Tests: processCommand(String command) in Mix.java
	 ******************************************************************/
	@Test
	public void testProcessCommand(){
		
		//Tests correct CASE 'B' inputs 
		Mix m = new Mix();
		m.setInitialMessage("13");
		
		m.processCommand("b 0 0");
		assertEquals(m.messageToString(), "0 1 3 ");
		
		m.processCommand("b 2 2");
		assertEquals(m.messageToString(), "0 1 2 3 ");
		
		m.processCommand("b A 3");
		assertEquals(m.messageToString(), "0 1 2 A 3 ");
	}
	
	/*******************************************************************
	 * Tests: processCommand(String command) in Mix.java with improper 
	 * 		  commands
	 ******************************************************************/
	@Test 
	public void testProcessCommand2(){
		String message = "Unable to process command: incorrect format!";
		
		//Tests CASE 'B' inputs 
		Mix m = new Mix();
		m.setInitialMessage("AB");
		
		assertEquals(m.processCommand("b 12 0"), message);
		assertEquals(m.processCommand("b aa 0"), message);
		assertEquals(m.processCommand("b"), message);
		assertEquals(m.processCommand("b 12"), message);
		assertEquals(m.processCommand("b b b"), message);

		
		//Tests INVALID COMMAND
		message = "Command not found";
		assertEquals(m.processCommand("pizza"), message);
		assertEquals(m.processCommand("B 1 0"), message);
	}
	
	/*******************************************************************
	 * Tests: addBeforeIndex(int index, E data) in LinkList.java
	 ******************************************************************/
	@Test
	public void testAddBeforeIndex(){
		
		LinkList<String> link = new LinkList<String>();
		
		link.addBeforeIndex(0, "A");
		assertEquals(link.toString(), "A ");
		
		link.addBeforeIndex(1, "B");
		assertEquals(link.toString(), "A B ");
		
		link.addBeforeIndex(1, "C");
		assertEquals(link.toString(), "A C B ");
		
		link.addBeforeIndex(0, "D");
		assertEquals(link.toString(), "D A C B ");
		
		link.addBeforeIndex(4, "E");
		assertEquals(link.toString(), "D A C B E ");
		
		link.addBeforeIndex(2, "F");
		assertEquals(link.toString(), "D A F C B E ");
	}
	
	/*******************************************************************
	 * Tests: addBeforeIndex(int index, E data) in LinkList.java for 
	 *        index less than zero 
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testAddBeforeIndex2(){
		
		LinkList<String> link = new LinkList<String>();
		link.addBeforeIndex(-1, "C");
	}
	
	/*******************************************************************
	 * Tests: addBeforeIndex(int index, E data) in LinkList.java for 
	 *       index greater than the size of the LinkList
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testAddBeforeIndex3(){
		
		LinkList<String> link = new LinkList<String>();
		link.addBeforeIndex(10, "C");
	}
	
	/*******************************************************************
	 * Tests: SetInitialMessage(String msg) in Mix
	 ******************************************************************/
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
		m1.setInitialMessage("| & 1 ~ `' ");
		assertEquals(m1.messageToString(), "|   &   1   ~   ` '   ");
	}	
}