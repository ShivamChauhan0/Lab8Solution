package pkgUtil;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pkgGame.Player;

public class CircuilarLinkedListTest {

	@Test
	public void CircularLinkedListTest1() {
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		Player p3 = new Player("Jim");
		
	    CircularLinkedList<Player> cll = new CircularLinkedList<Player>();

	    cll.addNode(p1);
	    cll.addNode(p2);
	    cll.addNode(p3);
	    
	    assertTrue(cll.containsNode(p2));
	    assertEquals(p1,cll.getNextNode(p3));
	    assertEquals(p2,cll.getNextNode(p1));
	    assertEquals(p3,cll.getNextNode(p2));
	}
	@Test
	public void CircularLinkedListTest2() {
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		Player p3 = new Player("Jim");
		
	    CircularLinkedList<Player> cll = new CircularLinkedList<Player>();

	    cll.addNode(p1);
	    cll.addNode(p2);
	    cll.addNode(p3);
	    
	    assertTrue(cll.containsNode(p2));
	    assertEquals(p1,cll.getNextNode(p3));
	    assertEquals(p2,cll.getNextNode(p1));
	    assertEquals(p3,cll.getNextNode(p2));
	    
	    cll.delete(p2);
	    
	    assertEquals(p1,cll.getNextNode(p3));
	    assertEquals(p3,cll.getNextNode(p1));  
	}
	
	
	@Test
	public void CircularLinkedListTest3() {
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		Player p3 = new Player("Jim");
		
	    CircularLinkedList<Player> cll = new CircularLinkedList<Player>();

	    cll.addNode(p1);
	    cll.addNode(p2);
	    cll.addNode(p3);
	    
	    assertEquals(p3,cll.getCurrent());
	    cll.getNext();
	    assertEquals(p1,cll.getCurrent());
	    cll.getNext();
	    assertEquals(p2,cll.getCurrent());
	    cll.getNext();
	    assertEquals(p3,cll.getCurrent());
	    cll.getNext();
	    assertEquals(p1,cll.getCurrent());
	    
	}

}

