package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eNum.eMoveResult;

public class MoveLab7Test {

//	@Test
//	public void TestMovesNotAdjacent1() {
//		
//		Board b = new Board();
//		Space s1 = new Space(new Letter('A'),0,0);
//		Space s2 = new Space(new Letter('X'),0,1);
//		Move m = new Move(b);
//		m.AddTile(s1);
//		m.AddTile(s2);
//		eMoveResult eMove = m.ValidateMove();
//
//		assertEquals(eMoveResult.NoAdjacentOrStar, eMove);
//		
//	}
//
//	public void TestMovesNotInLine1() {
//		
//		Board b = new Board();
//		Space s1 = new Space(new Letter('A'),0,0);
//		Space s2 = new Space(new Letter('X'),4,3);
//		Move m = new Move(b);
//		m.AddTile(s1);
//		m.AddTile(s2);
//		eMoveResult eMove = m.ValidateMove();
//
//		assertEquals(eMoveResult.NotALine, eMove);
//		
//	}
//	
//	public void TestMoveNoTiles1() {
//		
//		Board b = new Board();
//		Move m = new Move(b);
//		eMoveResult eMove = m.ValidateMove();
//
//		assertEquals(eMoveResult.NoTiles, eMove);
//		
//	}	
//	
//	@Test
//	public void TestSpaceInWord() {
//		
//		Board b = new Board();
//		Space s1 = new Space(new Letter('T'),7,6);
//		Space s2 = new Space(new Letter('H'),7,7);
//		Space s3 = new Space(new Letter('E'),7,8);
//		b.getPuzzle()[7][6] = s1;
//		b.getPuzzle()[7][7] = s2;
//		b.getPuzzle()[7][8] = s3;		
//		Move m = new Move(b);		
//		m.AddTile(new Space(new Letter('E'),7,9));
//		m.AddTile(new Space(new Letter('E'),7,11));		
//		eMoveResult eMove = m.ValidateMove();		
//		assertEquals(eMoveResult.SpaceInWord, eMove);
//	}
	
//	@Test
//	public void TestLegalMove1() {
//		
//		//	Empty board, add the first word
//		Board b = new Board();
//		Space s1 = new Space(new Letter('A'),7,7);
//		Space s2 = new Space(new Letter('X'),7,8);
//		Space s3 = new Space(new Letter('E'),7,9);
//		Move m = new Move(b);
//		m.AddTile(s1);
//		m.AddTile(s2);
//		m.AddTile(s3);
//		eMoveResult eMove = m.ValidateMove();
//		assertEquals(eMoveResult.GoodMove, eMove);
//		
//	}
//	
//	@Test
//	public void TestLegalMove2() {
//		
//		//	Board has tiles, 
//		Board b = new Board();
//		Space s1 = new Space(new Letter('T'),7,6);
//		Space s2 = new Space(new Letter('H'),7,7);
//		Space s3 = new Space(new Letter('E'),7,8);
//		b.getPuzzle()[7][6] = s1;
//		b.getPuzzle()[7][7] = s2;
//		b.getPuzzle()[7][8] = s3;
//		
//		Space s4 = new Space(new Letter('E'),7,9);
//		Move m = new Move(b);
//		m.AddTile(s4);
//		
//		eMoveResult eMove = m.ValidateMove();		
//		assertEquals(eMoveResult.GoodMove, eMove);
//	}
	
	
//	@Test
//	public void TestBadWord1() {
//		
//		//	Board has tiles, 
//		Board b = new Board();
//		b.SetSpace(new Space(new Letter('R'),7,7));
//		b.SetSpace(new Space(new Letter('A'),7,8));
//		b.SetSpace(new Space(new Letter('I'),7,9));
//		b.SetSpace(new Space(new Letter('N'),7,10));		
//		
//		Move m = new Move(b);
//		m.AddTile(new Space(new Letter('F'),8,6));
//		m.AddTile(new Space(new Letter('A'),8,7));
//		m.AddTile(new Space(new Letter('D'),8,8));
//		
//		eMoveResult eMove = m.ValidateMove();		
//		assertEquals(eMoveResult.NotAWord, eMove);
//	}	
	
	@Test
	public void TestLegalMove3() {
		
		//	Board has tiles, 
		Board b = new Board();
		b.SetSpace(new Space(new Letter('R'),7,7));
		b.SetSpace(new Space(new Letter('A'),7,8));
		b.SetSpace(new Space(new Letter('I'),7,9));
		b.SetSpace(new Space(new Letter('N'),7,10));		
		
		Move m = new Move(b);
		m.AddTile(new Space(new Letter('F'),8,6));
		m.AddTile(new Space(new Letter('E'),8,7));
		m.AddTile(new Space(new Letter('D'),8,8));
		
		eMoveResult eMove = m.ValidateMove();		
		assertEquals(eMoveResult.GoodMove, eMove);
		
		ScoreMove sm = m.getScoremove();
		for (ScoreWord sw : sm.getArrScoreWord())
		{
			for (Space s: sw.getTiles())
			{
				System.out.print(s.getLetter().getChLetter());
				
			}
			System.out.println("");
		}
	}	
	
	
	


}
