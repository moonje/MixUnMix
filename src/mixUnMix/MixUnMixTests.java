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
    }
    
    /*******************************************************************
	 * Tests: switchPosition(int pos1, int pos2) in Mix.java with
	 * 			incorrect inputs
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
	}
	
	/*******************************************************************
	 * Tests: insert(String c, int position) in Mix.java with incorrect
	 * 	      inputs
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
	 * 		  commands
	 ******************************************************************/
	@Test 
	public void testProcessCommand2(){
		
		String message = "Unable to process command: incorrect format!";
		
		Mix m = new Mix();
		
		//Tests CASE 'B' inputs 
		m.setInitialMessage("AB");
		
		assertEquals(m.processCommand("b 12 0"), message);
		assertEquals(m.processCommand("b aa 0"), message);
		assertEquals(m.processCommand("b"), message);
		assertEquals(m.processCommand("b 12"), message);
		assertEquals(m.processCommand("b b b"), message);
		assertEquals(m.processCommand("b b b b"), message);
		assertEquals(m.processCommand("b 1 0 b"), message);
		assertEquals(m.processCommand("b 0 0 br st q "), message);
		assertEquals(m.processCommand("b  3"), message);
		assertEquals(m.processCommand("b   3"), message);
		assertEquals(m.processCommand("b  0"), message); 
		assertEquals(m.processCommand("b     0"), message);
		assertEquals(m.processCommand("b   "), message);
		assertEquals(m.processCommand("b          "), message);
		
		//Tests CASE 'R' inputs
		assertEquals(m.processCommand("r"), message);
		assertEquals(m.processCommand("r 3"), message);
		assertEquals(m.processCommand("r -1"), message);

		
		//Tests INVALID COMMAND
		message = "Command not found";
		assertEquals(m.processCommand("pizza"), message);
		assertEquals(m.processCommand("B 1 0"), message);
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
		//m6.cut(0, 1);
		//m6.paste(3);
		m6.processCommand("s hello");
		System.out.println(m6.getCommands());
		
		UnMix u6 = new UnMix();
		assertEquals(u6.UnMixUsingFile("hello.txt", "llohe"), 
				"The original message was:\nhello");
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

}