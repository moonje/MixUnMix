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
	 * Tests: LinkList() in LinkList.java
	 ******************************************************************/
	@Test
	public void testLinkList(){
		LinkList<String> link = new LinkList<String>();
		
		assertEquals(link.getTop(), null);
		assertEquals(link.getTail(), null);
	}
	
	/*******************************************************************
	 * Tests: addFirst(e data) in LinkList.java
	 ******************************************************************/
	@Test
	public void testAddFirst(){
		LinkList<String> link = new LinkList<String>();
		link.addFirst("S");
		assertEquals(link.toString(), "S ");
		
		link.addFirst("R");
		assertEquals(link.toString(), "R S ");
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