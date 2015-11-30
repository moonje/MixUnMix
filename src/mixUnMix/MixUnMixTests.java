package mixUnMix;

import static org.junit.Assert.*;
import org.junit.Test;

/***********************************************************************
JUnit Tests used to test Node, Mix, LinkList, and UnMix

@author Jennifer Moon
@author Molly Alger
@version 12/2/2015
***********************************************************************/

public class MixUnMixTests {
	
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
			+ "ending at # (inclusive)\n"
			+ "a & \t\t appends the message with character &\n"
			+ "h\t\t displays this message again\n";
	
	/*******************************************************************
	 * Tests: switchNodes(int pos1, int pos2) in LinkList.java
	 ******************************************************************/
    @Test
    public void testSwitchNodes(){
    	
    	LinkList<String> link = new LinkList<String>();
    	
    	link.addAtEnd("p");
    	link.addAtEnd("i");
    	link.addAtEnd("z");
    	link.addAtEnd("z");
    	link.addAtEnd("a");
    	
    	link.switchNodes(0, 1);
    	assertEquals(link.toString(), "ipzza");
    	
    	link.switchNodes(1, 0);
    	assertEquals(link.toString(), "pizza");
    	
    	link.switchNodes(0, 4);
    	assertEquals(link.toString(), "aizzp");
    }
    
	/*******************************************************************
	 * Tests: switchNodes(int pos1, int pos2) in LinkList.java for 
	 *        negative param 1
	 ******************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testSwitchNodes2(){
    	
    	LinkList<String> link = new LinkList<String>();
    	
    	link.addAtEnd("p");
    	link.addAtEnd("i");
    	link.addAtEnd("z");
    	link.addAtEnd("z");
    	link.addAtEnd("a");
    	
    	link.switchNodes(-1, 0);
    }
    
	/*******************************************************************
	 * Tests: switchNodes(int pos1, int pos2) in LinkList.java for 
	 *        negative param 2
	 ******************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testSwitchNodes3(){
    	
    	LinkList<String> link = new LinkList<String>();
    	
    	link.addAtEnd("p");
    	link.addAtEnd("i");
    	link.addAtEnd("z");
    	link.addAtEnd("z");
    	link.addAtEnd("a");
    	
    	link.switchNodes(0, -1);
    }
    
	/*******************************************************************
	 * Tests: switchNodes(int pos1, int pos2) in LinkList.java for 
	 *        negative param 1 & param 2
	 ******************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testSwitchNodes4(){
    	
    	LinkList<String> link = new LinkList<String>();
    	
    	link.addAtEnd("p");
    	link.addAtEnd("i");
    	link.addAtEnd("z");
    	link.addAtEnd("z");
    	link.addAtEnd("a");
    	
    	link.switchNodes(-1, -1);
    }
    
	/*******************************************************************
	 * Tests: switchNodes(int pos1, int pos2) in LinkList.java for 
	 *        too large param 1
	 ******************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testSwitchNodes5(){
    	
    	LinkList<String> link = new LinkList<String>();
    	
    	link.addAtEnd("p");
    	link.addAtEnd("i");
    	link.addAtEnd("z");
    	link.addAtEnd("z");
    	link.addAtEnd("a");
    	
    	link.switchNodes(5, 3);
    }
    
	/*******************************************************************
	 * Tests: switchNodes(int pos1, int pos2) in LinkList.java for 
	 *        too large param 2
	 ******************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testSwitchNodes6(){
    	
    	LinkList<String> link = new LinkList<String>();
    	
    	link.addAtEnd("p");
    	link.addAtEnd("i");
    	link.addAtEnd("z");
    	link.addAtEnd("z");
    	link.addAtEnd("a");
    	
    	link.switchNodes(2, 5);
    }
    
	/*******************************************************************
	 * Tests: switchPosition(int pos1, int pos2) in Mix.java
	 ******************************************************************/
    @Test
    public void testSwitchPosition(){
    	
    	Mix m = new Mix();
    	
    	m.setInitialMessage("pizza");
    	m.switchPosition(1, 3);
    	assertEquals(m.messageToString(), "pzzia");
    	assertEquals(m.getCommands(), "w 3 1\n");
    	
    	Mix m2 = new Mix();
    	m2.setInitialMessage("This is a test.");
    	m2.switchPosition(0, 4);
    	assertEquals(m2.messageToString(), 
    			" hisTis a test.");
    	assertEquals(m2.getCommands(), "w 4 0\n");
    	
    	Mix m3 = new Mix();
    	m3.setInitialMessage("Hello");
    	m3.switchPosition(2, 3);
    	assertEquals(m3.messageToString(), "Hello");
    	assertEquals(m3.getCommands(), "w 3 2\n");
    	m3.switchPosition(0, 4);
    	assertEquals(m3.messageToString(), "oellH");
    	assertEquals(m3.getCommands(), 
    			"w 4 0\nw 3 2\n");
    	
    	Mix m4 = new Mix();
    	m4.setInitialMessage("pizza");
    	
    	m4.switchPosition(3, 1);
    	assertEquals(m4.messageToString(), "pzzia");
    }
    
    /*******************************************************************
	 * Tests: switchPosition(int pos1, int pos2) in Mix.java with
	 * 			too large a position
	 ******************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testSwitchPosition2(){
    	
    	Mix m = new Mix();
    	m.setInitialMessage("pizza");
    	
    	m.switchPosition(1, 100);
    }
    
	/*******************************************************************
	 * Tests: insert(String c, int position) in Mix.java
	 ******************************************************************/
	@Test
	public void testInsert(){
		
		Mix m = new Mix(); 
		
		m.insert("A", 0);
		assertEquals(m.messageToString(), "A");
		assertEquals(m.getCommands(), "r 0\n");
		
		m.insert("B", 1);
		assertEquals(m.messageToString(), "AB");
		assertEquals(m.getCommands(), "r 1\nr 0\n");
		
		Mix m2 = new Mix();
		m2.setInitialMessage("HELLO");
		m2.insert("m", 3);
		assertEquals(m2.messageToString(), "HELmLO");
		assertEquals(m2.getCommands(), "r 3\n");
		
		Mix m3 = new Mix();
		m3.setInitialMessage("HELLO");
		m3.insert("m", 5);
		assertEquals(m3.messageToString(), "HELLOm");
		assertEquals(m3.getCommands(), "r 5\n");
	}
	
	/*******************************************************************
	 * Tests: insert(String c, int position) in Mix.java with incorrect
	 * 	      inputs (trying to insert more than a character)
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testInsertErrors(){
		
		Mix m = new Mix();
		
		m.insert("QUACK", 12);
	}
	
	/*******************************************************************
	 * Tests: processCommand(String command) in Mix.java
	 ******************************************************************/
	@Test
	public void testProcessCommand(){
		
		//Tests CASE 'H' inputs
		//We're going to let them get away with extra spaces after 
		//the 'h', 'cause they're probably pretty lost and we feel
		//bad for them.
		Mix m = new Mix();
		assertEquals(m.processCommand("h"), cmd);
		assertEquals(m.processCommand("h "), cmd);
		assertEquals(m.processCommand("h             "), cmd);
		
		//Tests correct CASE 'B' inputs 
		m.setInitialMessage("13");
		
		m.processCommand("b 0 0");
		assertEquals(m.messageToString(), "013");
		
		m.processCommand("b 2 2");
		assertEquals(m.messageToString(), "0123");
		
		m.processCommand("b A 3");
		assertEquals(m.messageToString(), "012A3");
		
		m.processCommand("b   3");
		assertEquals(m.messageToString(), "012 A3");
		
		//Tests correct CASE 'R' inputs
		m = new Mix();
		m.setInitialMessage("pizza");
		
		m.processCommand("r 1");
		assertEquals(m.messageToString(), "pzza");
		
		m.processCommand("r 0");
		assertEquals(m.messageToString(), "zza");
		
		m.processCommand("r 2");
		assertEquals(m.messageToString(), "zz");
		
		m.processCommand("r 0");
		assertEquals(m.messageToString(), "z");
		
		m.processCommand("r 0");
		assertEquals(m.messageToString(), "");
		
		//Tests correct CASE 'W' inputs
		m = new Mix();
		m.setInitialMessage("switch twitch");
		m.switchPosition(0, 7);
		assertEquals(m.messageToString(), "twitch switch");
		
		m = new Mix();
		m.setInitialMessage("Jennifer is cool");
		m.switchPosition(12, 15);
		assertEquals(m.messageToString(), "Jennifer is looc");
		m.switchPosition(14, 15);
		assertEquals(m.messageToString(), "Jennifer is loco");
	}
	
	/*******************************************************************
	 * Tests: processCommand(String command) in Mix.java with improper 
	 * 		  commands and commands that don't exist 
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand2(){
		
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("w 20 2");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand3(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b 12 0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand4(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b aa 0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand5(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand6(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b 12");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand7(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b b b");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand8(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b 1 0 b");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand10(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b 0 0 br st q ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand11(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b  3");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand12(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b   3");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand13(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b  0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand14(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b     0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand15(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b   ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand16(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("r");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand17(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b          ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand18(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("r 3");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand19(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("r -1");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand20(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("pizza");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand21(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("B 1 0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommand22(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("x 0 5");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand23(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("t");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand24(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("q");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand25(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("A");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand26(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("B");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand27(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("X");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand28(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("P");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand29(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("C");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand30(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand31(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("S");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand32(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("R");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommand33(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("H");
	}
	
	/*******************************************************************
	 * Tests: remove(int position) in Mix.java
	 ******************************************************************/
	@Test
	public void testRemove(){
		
		Mix m = new Mix();
		m.setInitialMessage("PIZZA pizza");
		
		m.remove(0);
		assertEquals(m.messageToString(), "IZZA pizza");
		assertEquals(m.getCommands(), "b P 0\n");
		
		m.remove(1);
		assertEquals(m.messageToString(), "IZA pizza");
		assertEquals(m.getCommands(), "b Z 1\nb P 0\n");
		
		m.remove(5);
		assertEquals(m.messageToString(), "IZA pzza");
		assertEquals(m.getCommands(), "b i 5\nb Z 1\nb P 0\n");
		
		m.remove(7);
		assertEquals(m.messageToString(), "IZA pzz");
		assertEquals(m.getCommands(), "b a 7\nb i 5\nb Z 1\nb P 0\n");
	}
	
	/*******************************************************************
	 * Tests: copy(int pos1, int pos2) in Mix.java
	 ******************************************************************/
	@Test
	public void testCopy(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.copy(0, 1);
		assertEquals(m.clipboardToString(), "He");
		assertEquals(m.messageToString(), "Hello");
		
		Mix m2 = new Mix();
		m2.setInitialMessage("PIZZA IS GOOD");
		m2.copy(0, 4);
		assertEquals(m2.clipboardToString(), "PIZZA");
		assertEquals(m2.messageToString(), "PIZZA IS GOOD");
	}
	
	/*******************************************************************
	 * Tests: copy(int pos1, int pos2) in Mix.java with negative
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCopy2(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.copy(-1, 1);
		
	}
	
	/*******************************************************************
	 * Tests: copy(int pos1, int pos2) in Mix.java with negative
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCopy3(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.copy(1, -1);
		
	}
	
	/*******************************************************************
	 * Tests: copy(int pos1, int pos2) in Mix.java with too large
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCopy4(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.copy(8, 5);
		
	}
	
	/*******************************************************************
	 * Tests: copy(int pos1, int pos2) in Mix.java with too large
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCopy5(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.copy(1, 8);
		
	}
	
	/*******************************************************************
	 * Tests: copy(int pos1, int pos2) in Mix.java with larger first
	 * 			input
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCopy6(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.copy(4, 1);
		
	}
	
	/*******************************************************************
	 * Tests: copy(int pos1, int pos2) in Mix.java with trying to copy
	 * 			an empty message
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCopy7(){
		Mix m = new Mix();
		
		m.setInitialMessage("pizza");
		m.cut(0, 4);
		m.copy(0, 4);
	}
	
	/*******************************************************************
	 * Tests: cut(int pos1, int pos2) in Mix.java
	 ******************************************************************/
	@Test
	public void testCut(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.cut(0, 1);
		assertEquals(m.clipboardToString(), "He");
		assertEquals(m.messageToString(), "llo");
		
		Mix m2 = new Mix();
		m2.setInitialMessage("PIZZA IS GOOD");
		m2.cut(0, 4);
		assertEquals(m2.clipboardToString(), "PIZZA");
		assertEquals(m2.messageToString(), " IS GOOD");
		
		Mix m3 = new Mix();
		m3.setInitialMessage("HIHIHI");
		m3.cut(0, 0);
		assertEquals(m3.clipboardToString(), "H");
		assertEquals(m3.messageToString(), "IHIHI");
		
	}
	
	/*******************************************************************
	 * Tests: cut(int pos1, int pos2) in Mix.java with negative 
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCut2(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.cut(-1, 1);
		
	}
	
	/*******************************************************************
	 * Tests: cut(int pos1, int pos2) in Mix.java with too large
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCut3(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.cut(7, 1);
		
	}
	
	/*******************************************************************
	 * Tests: cut(int pos1, int pos2) in Mix.java with negative
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCut4(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.cut(1, -1);
		
	}
	
	/*******************************************************************
	 * Tests: cut(int pos1, int pos2) in Mix.java with too large
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCut5(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.cut(1, 8);
		
	}
	
	/*******************************************************************
	 * Tests: cut(int pos1, int pos2) in Mix.java with first position
	 *        larger than the second 
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCut6(){
		Mix m = new Mix();
		
		m.setInitialMessage("We can't do this!");
		m.cut(3, 1);
	}
	
	/*******************************************************************
	 * Tests: cut(int pos1, int pos2) in Mix.java with trying to cut
	 * 			an empty message
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testCut7(){
		Mix m = new Mix();
		
		m.setInitialMessage("pizza");
		m.cut(0, 4);
		m.cut(0, 4);
	}
	
	/*******************************************************************
	 * Tests: paste(int pos1) in Mix.java
	 ******************************************************************/
	@Test
	public void testPaste(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.cut(0, 1);
		assertEquals(m.clipboardToString(), "He");
		m.paste(3);
		assertEquals(m.messageToString(), "lloHe");
		
		m.cut(0, 1);
		assertEquals(m.clipboardToString(), "ll");
		m.paste(2);
		assertEquals(m.messageToString(), "oHlle");
		
		m.copy(0, 1);
		assertEquals(m.clipboardToString(), "oH");
		m.paste(0);
		assertEquals(m.messageToString(), "oHoHlle");
		
		m.copy(0, 0);
		assertEquals(m.clipboardToString(), "o");
		m.paste(7);
		assertEquals(m.messageToString(), "oHoHlleo");
		
	}
	
	/*******************************************************************
	 * Tests: paste(int pos1, int pos2) in Mix.java with nothing in
	 * 			the clipboard
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testPaste2(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.paste(0);
		
	}
	
	/*******************************************************************
	 * Tests: paste(int pos1, int pos2) in Mix.java with too large
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testPaste3(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.copy(0, 4);
		m.paste(6);
		
	}
	
	/*******************************************************************
	 * Tests: paste(int pos1, int pos2) in Mix.java with negative
	 * 			position
	 ******************************************************************/
	@Test (expected=IllegalArgumentException.class)
	public void testPaste4(){
		
		Mix m = new Mix();
		
		m.setInitialMessage("Hello");
		m.copy(0, 4);
		m.paste(-1);
		
	}
	
	/*******************************************************************
	 * Tests: removeAtIndex(int position) in LinkList.java
	 ******************************************************************/
	@Test
	public void testRemoveAtIndex(){
		
		LinkList<String> link = new LinkList<String>();
		link.addFirst("P");
		link.addAtEnd("i");
		link.addAtEnd("z");
		link.addAtEnd("s");
		link.addAtEnd("a");
		
		link.removeAtIndex(0);
		assertEquals(link.toString(), "izsa");
		
		link.removeAtIndex(1);
		assertEquals(link.toString(), "isa");
		
		link.removeAtIndex(1);
		assertEquals(link.toString(), "ia");
		
		link.removeAtIndex(1);
		assertEquals(link.toString(), "i");
		
		link.removeAtIndex(0);
		assertEquals(link.toString(), "");
	}
	
	/*******************************************************************
	 * Tests: removeAtIndex(int position) in LinkList.java with too 
	 *        too large of an index
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveAtIndex2(){
		
		LinkList<String> link = new LinkList<String>();
		link.removeAtIndex(0);
	}
	
	/*******************************************************************
	 * Tests: removeAtIndex(int position) in LinkList.java with a
	 *        negative index
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveAtIndex3(){
		
		LinkList<String> link = new LinkList<String>();
		link.removeAtIndex(-1);
	}
	
	/*******************************************************************
	 * Tests: addBeforeIndex(int index, E data) in LinkList.java
	 ******************************************************************/
	@Test
	public void testAddBeforeIndex(){
		
		LinkList<String> link = new LinkList<String>();
		
		link.addBeforeIndex(0, "A");
		assertEquals(link.toString(), "A");
		
		link.addBeforeIndex(1, "B");
		assertEquals(link.toString(), "AB");
		
		link.addBeforeIndex(1, "C");
		assertEquals(link.toString(), "ACB");
		
		link.addBeforeIndex(0, "D");
		assertEquals(link.toString(), "DACB");
		
		link.addBeforeIndex(4, "E");
		assertEquals(link.toString(), "DACBE");
		
		link.addBeforeIndex(2, "F");
		assertEquals(link.toString(), "DAFCBE");
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
	 * Tests: addFirst(E data) in LinkList.java 
	 ******************************************************************/
	@Test
	public void testAddFirst(){
		
		LinkList<String> link = new LinkList<String>();
		link.addFirst("A");
		assertEquals(link.toString(), "A");
		
		link.addFirst("B");
		assertEquals(link.toString(), "BA");
		
		link.addFirst("C");
		assertEquals(link.toString(), "CBA");
		
		link.addFirst("D");
		assertEquals(link.toString(), "DCBA");
	}
	
	/*******************************************************************
	 * Tests: count() in LinkList.java 
	 ******************************************************************/
	@Test
	public void testCount(){
		
		LinkList<String> link = new LinkList<String>();
		int test = link.count();
		assertEquals(test, 0);
		
		link.addFirst("A");
		test = link.count();
		assertEquals(test, 1);
		
		link.addFirst("A");
		test = link.count();
		assertEquals(test, 2);
		
	}
	
	/*******************************************************************
	 * Tests: addAtEnd(E data) in LinkList.java 
	 ******************************************************************/
	@Test
	public void testAddAtEnd(){
		
		LinkList<String> link = new LinkList<String>();
		link.addAtEnd("A");
		assertEquals(link.toString(), "A");
		
		link.addAtEnd("B");
		assertEquals(link.toString(), "AB");
		
		link.addAtEnd("C");
		assertEquals(link.toString(), "ABC");
		
		link.addAtEnd("D");
		assertEquals(link.toString(), "ABCD");
		
	}
	
	/*******************************************************************
	 * Tests: delete(E data) in LinkList.java 
	 ******************************************************************/
	@Test
	public void testDeleteNode(){
		
		LinkList<String> link = new LinkList<String>();
		assertEquals(link.delete("A"), false);
		
		link.addAtEnd("A");
		assertEquals(link.delete("A"), true);
		int test = link.count();
		assertEquals(test, 0);
		
		link.addAtEnd("A");
		link.addAtEnd("A");
		link.addAtEnd("A");
		link.addAtEnd("A");
		link.addAtEnd("B");
		assertEquals(link.delete("A"), true);
		test = link.count();
		assertEquals(test, 4);
		assertEquals(link.toString(), "AAAB");
		
		assertEquals(link.delete("B"), true);
		test = link.count();
		assertEquals(test, 3);
		assertEquals(link.toString(), "AAA");
		
		assertEquals(link.delete("B"), false);
	}
	
	/*******************************************************************
	 * Tests: deleteAll() in LinkList.java 
	 ******************************************************************/
	@Test
	public void testDeleteAllNodes(){
		
		LinkList<String> link = new LinkList<String>();
		link.addAtEnd("A");
		link.deleteAll();
		assertEquals(link.count(), 0);
		
	}
	
	/*******************************************************************
	 * Tests: SetInitialMessage(String msg) in Mix
	 ******************************************************************/
	@Test
	public void testSetInitialMessage(){
		
		Mix m1 = new Mix();
		m1.setInitialMessage("abcdefghijklmnopqrstuvwxyz");
		assertEquals(m1.messageToString(), 
				"abcdefghijklmnopqrstuvwxyz");
		
		m1 = new Mix();
		m1.setInitialMessage(" ");
		assertEquals(m1.messageToString()," ");
		
		m1 = new Mix(); 
		m1.setInitialMessage("   ");
		assertEquals(m1.messageToString(), "   ");
		
		m1 = new Mix();
		m1.setInitialMessage("! ? ! ? !");
		assertEquals(m1.messageToString(), "! ? ! ? !");
		
		//CHECK ME OUT
		m1 = new Mix();
		m1.setInitialMessage("");
		assertEquals(m1.messageToString(), "");
		
		m1 = new Mix();
		m1.setInitialMessage("	"); //tab
		assertEquals(m1.messageToString(), "	");
		
		m1 = new Mix();
		m1.setInitialMessage("| & 1 ~ `' ");
		assertEquals(m1.messageToString(), "| & 1 ~ `' ");
	}
	
	/*******************************************************************
	 * Tests: UnMixUsingFile(String filename, String userMessage) in
	 * 			UnMix
	 ******************************************************************/
	@Test
	public void testUnMixUsingFile(){
		
		Mix m = new Mix();
		m.setInitialMessage("Pizza");
		m.insert("M", 0);
		assertEquals(m.messageToString(), "MPizza");
		assertEquals(m.getCommands(), "r 0\n");
		m.save("test");
		
		UnMix u = new UnMix();
		assertEquals(u.UnMixUsingFile("test.txt", "MPizza"),
							"The original message was:\nPizza");
		
		Mix m2 = new Mix();
		m2.setInitialMessage("Hello");
		m2.save("test2");
		
		UnMix u2 = new UnMix();
		assertEquals(u2.UnMixUsingFile("test2.txt", "Hello"),
							"The original message was:\nHello");
		
		Mix m3 = new Mix();
		m3.setInitialMessage("Hello World");
		m3.remove(0);
		assertEquals(m3.messageToString(), "ello World");
		assertEquals(m3.getCommands(), "b H 0\n");
		m3.insert("J", 0);
		assertEquals(m3.messageToString(), "Jello World");
		assertEquals(m3.getCommands(), "r 0\nb H 0\n");
		m3.save("test3");
		
		UnMix u3 = new UnMix();
		assertEquals(u3.UnMixUsingFile("test3.txt", "Jello World"),
				"The original message was:\nHello World");
		
		Mix m4 = new Mix();
		m4.setInitialMessage("This is a really long string! "
				+ "1234567890");
		m4.remove(4);
		m4.cut(0, 3);
		m4.save("jennifertest2");
		UnMix u4 = new UnMix();
		assertEquals(u4.UnMixUsingFile("jennifertest2.txt", 
				"is a really long string! 1234567890"), 
				"The original message was:\nThis is a really long"
				+ " string! 1234567890");
		
		Mix m5 = new Mix();
		m5.setInitialMessage("empty");
		m5.remove(0);
		m5.remove(0);
		m5.remove(0);
		m5.remove(0);
		m5.remove(0);
		m5.save("nothing");
		UnMix u5 = new UnMix();
		assertEquals(u5.UnMixUsingFile("nothing.txt", ""), 
				"The original message was:\nempty");
		
		Mix m6 = new Mix();
		m6.setInitialMessage("hello");
		m6.processCommand("x 0 1");
		m6.processCommand("p 3");
		m6.processCommand("s hello");
		
		UnMix u6 = new UnMix();
		assertEquals(u6.UnMixUsingFile("hello.txt", "llohe"), 
				"The original message was:\nhello");
		
		Mix m7 = new Mix();
		m7.setInitialMessage("1234567890!@#$%^&*()_+=-`");
		m7.processCommand("r 0");
		m7.processCommand("a 0");
		m7.processCommand("s m7");
		
		UnMix u7 = new UnMix();
		assertEquals(u7.UnMixUsingFile("m7.txt", 
				"234567890!@#$%^&*()_+=-`0"),
				"The original message was:\n1234567890!@#$%^&*()_+=-`");
		
		Mix m8 = new Mix();
		m8.setInitialMessage("This message is going to be deleted "
				+ "and made again");
		m8.processCommand("x 0 49");
		m8.processCommand("a 1");
		m8.processCommand("c 0 0");
		m8.processCommand("p 0");
		m8.processCommand("p 0");
		m8.processCommand("p 0");
		m8.processCommand("x 0 3");
		m8.processCommand("s deleted");
		
		UnMix u8 = new UnMix();
		assertEquals(u8.UnMixUsingFile("deleted.txt", ""),
				"The original message was:\nThis message is going "
				+ "to be deleted and made again");
	}
	
	/*******************************************************************
	 * Tests: UnMixUsingFile(String filename, String userMessage) in
	 * 			UnMix with file issues
	 ******************************************************************/
	@Test
	public void testUnMixUsingFile2(){
		
		UnMix u = new UnMix();
		assertEquals(u.UnMixUsingFile("test6.txt", "NO"), 
				"WARNING! File not found!");
		
		assertEquals(u.UnMixUsingFile("test6", "NO"), 
				"WARNING! Only able to open .txt files!");
		
		assertEquals(u.UnMixUsingFile("hello.txt", "NO"), 
				"WARNING! Unable to load file!");
	}
	
	/*******************************************************************
	 * Tests: allen(String c) in Mix.java which adds a character to
	 * 			the end of the message
	 ******************************************************************/
	@Test
	public void testAllen(){
		
		Mix m = new Mix();
		m.setInitialMessage("Hello");
		m.allen("!");
		assertEquals(m.messageToString(), "Hello!");
		assertEquals(m.getCommands(), "r 5\n");
		
	}
	
	/*******************************************************************
	 * FERGUSOR TEST
	 ******************************************************************/
    @Test 
    public void testUndoProcessCommand() {
        	// Creates the file for testing, 
    		// this code could be removed if the file already exist
    		Mix message = new Mix();
        	message.setInitialMessage ("This is a secret message");
        	message.processCommand("b a 0");
        	String userMessage = message.messageToString();
        	message.processCommand("s testIt");
       
        	UnMix unMessage = new UnMix();
        	String original = unMessage.UnMixUsingFile ("testIt.txt", 
        			userMessage);
        	assertEquals(original, "The original message was:\n"
        			+ "This is a secret message");
    }
    
    /*******************************************************************
	 * Tests: switchPosition(int pos1, int pos2) in UnMix.java
	 ******************************************************************/
    @Test
    public void testSwitchPositionUM(){
    	
    	UnMix m = new UnMix();
    	
    	m.setInitialMessage("pizza");
    	m.switchPosition(1, 3);
    	assertEquals(m.messageToString(), "pzzia");
    	
    	UnMix m2 = new UnMix();
    	m2.setInitialMessage("This is a test.");
    	m2.switchPosition(0, 4);
    	assertEquals(m2.messageToString(), 
    			" hisTis a test.");
    	
    	UnMix m3 = new UnMix();
    	m3.setInitialMessage("Hello");
    	m3.switchPosition(2, 3);
    	assertEquals(m3.messageToString(), "Hello");
    	m3.switchPosition(0, 4);
    	assertEquals(m3.messageToString(), "oellH");
    	
    	UnMix m4 = new UnMix();
    	m4.setInitialMessage("pizza");
    	
    	m4.switchPosition(3, 1);
    	assertEquals(m4.messageToString(), "pzzia");
    }
    
    /*******************************************************************
	 * Tests: switchPosition(int pos1, int pos2) in UnMix.java with
	 * 			too large a position
	 ******************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testSwitchPositionUM2(){
    	
    	UnMix m = new UnMix();
    	m.setInitialMessage("pizza");
    	
    	m.switchPosition(1, 100);
    }
    
	/*******************************************************************
	 * Tests: insert(String c, int position) in UnMix.java
	 ******************************************************************/
	@Test
	public void testInsertUM(){
		
		UnMix m = new UnMix(); 
		
		m.insert("A", 0);
		assertEquals(m.messageToString(), "A");
		
		m.insert("B", 1);
		assertEquals(m.messageToString(), "AB");
		
		UnMix m2 = new UnMix();
		m2.setInitialMessage("HELLO");
		m2.insert("m", 3);
		assertEquals(m2.messageToString(), "HELmLO");
		
		UnMix m3 = new UnMix();
		m3.setInitialMessage("HELLO");
		m3.insert("m", 5);
		assertEquals(m3.messageToString(), "HELLOm");
	}
	
	/*******************************************************************
	 * Tests: insert(String c, int position) in UnMix.java with incorrect
	 * 	      inputs (trying to insert more than a character)
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testInsertErrorsUM(){
		
		UnMix m = new UnMix();
		
		m.insert("QUACK", 12);
	}
	
	/*******************************************************************
	 * Tests: processCommand(String command) in UnMix.java
	 ******************************************************************/
	@Test
	public void testProcessCommandUM(){

		UnMix m = new UnMix();

		//Tests correct CASE 'B' inputs 
		m.setInitialMessage("13");
		
		m.processCommand("b 0 0");
		assertEquals(m.messageToString(), "013");
		
		m.processCommand("b 2 2");
		assertEquals(m.messageToString(), "0123");
		
		m.processCommand("b A 3");
		assertEquals(m.messageToString(), "012A3");
		
		m.processCommand("b   3");
		assertEquals(m.messageToString(), "012 A3");
		
		//Tests correct CASE 'R' inputs
		m = new UnMix();
		m.setInitialMessage("pizza");
		
		m.processCommand("r 1");
		assertEquals(m.messageToString(), "pzza");
		
		m.processCommand("r 0");
		assertEquals(m.messageToString(), "zza");
		
		m.processCommand("r 2");
		assertEquals(m.messageToString(), "zz");
		
		m.processCommand("r 0");
		assertEquals(m.messageToString(), "z");
		
		m.processCommand("r 0");
		assertEquals(m.messageToString(), "");
		
		//Tests correct CASE 'W' inputs
		m = new UnMix();
		m.setInitialMessage("switch twitch");
		m.switchPosition(0, 7);
		assertEquals(m.messageToString(), "twitch switch");
		
		m = new UnMix();
		m.setInitialMessage("Jennifer is cool");
		m.switchPosition(12, 15);
		assertEquals(m.messageToString(), "Jennifer is looc");
		m.switchPosition(14, 15);
		assertEquals(m.messageToString(), "Jennifer is loco");
	}
	
	/*******************************************************************
	 * Tests: processCommand(String command) in UnMix.java with improper 
	 * 		  commands and commands that don't exist 
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM2(){
		
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("w 20 2");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM3(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b 12 0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM4(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b aa 0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM5(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM6(){
		Mix m = new Mix();
		m.setInitialMessage("AB");
		m.processCommand("b 12");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM7(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b b b");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM8(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b 1 0 b");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM10(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b 0 0 br st q ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM11(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b  3");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM12(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b   3");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM13(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b  0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM14(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b     0");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM15(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b   ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM16(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("r");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM17(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("b          ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM18(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("r 3");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessCommandUM19(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("r -1");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM20(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("pizza");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM21(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("B 1 0");
	}
	
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM23(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("t");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM24(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("q");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM25(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("A");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM26(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("B");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM27(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("X");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM28(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("P");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM29(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("C");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM30(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("");
	}
	
	
	@Test (expected = UnsupportedOperationException.class)
	public void testProcessCommandUM32(){
		UnMix m = new UnMix();
		m.setInitialMessage("AB");
		m.processCommand("R");
	}
	
	/*******************************************************************
	 * Tests: remove(int position) in UnMix.java
	 ******************************************************************/
	@Test
	public void testRemoveUM(){
		
		UnMix m = new UnMix();
		m.setInitialMessage("PIZZA pizza");
		
		m.remove(0);
		assertEquals(m.messageToString(), "IZZA pizza");
		
		m.remove(1);
		assertEquals(m.messageToString(), "IZA pizza");
		
		m.remove(5);
		assertEquals(m.messageToString(), "IZA pzza");
		
		m.remove(7);
		assertEquals(m.messageToString(), "IZA pzz");
	}
	
	/*******************************************************************
	 * Tests: quit(int position) in Mix.java
	 ******************************************************************/
	@Test(expected=IllegalArgumentException.class)
	public void testQuit(){
		
		Mix m = new Mix();
		m.processCommand("Q ANYTHING");
	}

}