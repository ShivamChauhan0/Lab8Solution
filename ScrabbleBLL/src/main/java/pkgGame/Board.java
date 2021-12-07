package pkgGame;

import java.util.ArrayList;
import java.util.Collections;

import eNum.eBonusType;
import eNum.eMoveResult;
import eNumExceptions.eDrawExceptionType;
import pkgExceptions.DrawException;

public class Board {

	private Space[][] puzzle;
	private ArrayList<BonusSquare> bonuses = new ArrayList<BonusSquare>();
	private ArrayList<Move> movesMade = new ArrayList<Move>();
	private ArrayList<Letter> tileBag = new ArrayList<Letter>();

	/**
	 * Board - Set the bonus squares. Create the TileBag, Shuffle the Tilebag
	 * 
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 */
	public Board() {
		SetBonus();
		puzzle = new Space[15][15];
		for (int Row = 0; Row < 15; Row++) {
			for (int Col = 0; Col < 15; Col++) {
				Space s = new Space(findBonus(Row, Col), Row, Col);
				puzzle[Row][Col] = s;
			}
		}

		CreateTileBag();
		Collections.shuffle(tileBag);
	}

	public Space[][] getPuzzle() {
		return puzzle;
	}

	public void SetSpace(Space s) {
		this.getPuzzle()[s.getRow()][s.getCol()] = s;
	}

	protected ArrayList<Move> getMovesMade() {
		return movesMade;
	}

	protected ArrayList<Letter> drawLetter(int cnt) throws DrawException {
		ArrayList<Letter> drawLetters = new ArrayList<Letter>();
		for (int i = 0; i < cnt; i++) {
			drawLetters.add(drawLetter());
		}
		return drawLetters;
	}

	/**
	 * drawLetter - Draw a letter from the tileBag and return it. If the tileBag is
	 * empty, throw a DrawException
	 * 
	 * @author BRG
	 * @version Lab #8
	 * @since Lab #8
	 * @return
	 * @throws DrawException
	 */
	protected Letter drawLetter() throws DrawException {
		if (tileBag.isEmpty())
			throw new DrawException("The tile bag is empty");
		
		//get random letter from tilebag
		int randomIndex = (int)(Math.random() * tileBag.size());
		Letter randomLetter = getTileBag().get(randomIndex);
		
		//remove letter from tilebag
		tileBag.remove(randomIndex);
		
		return randomLetter();
	} 
	/**
	 * getTileBag - return the current state of the board's tilebag
	 * 
	 * @author BRG
	 * @version Lab #8
	 * @since Lab #8
	 * @return - ArrayList<Letter> of remaining tiles
	 */
	private ArrayList<Letter> getTileBag() {
		return this.tileBag;
	}

	/**
	 * RemoveLettersFromTileBag - Remove letter(s) from the tileBag
	 * 
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * @param removeLetters
	 */
	private void RemoveLettersFromTileBag(ArrayList<Letter> removeLetters) {
		this.tileBag.remove(removeLetters);
		removeLetters.clear();
		}
	/**
	 * CreateTileBag - Create the tile bag in the initial Board creation
	 * 
	 * @author BRG
	 * @version Lab #8
	 * @since Lab #8
	 */
	private void CreateTileBag() {
		AddLetterToTileBag('a',9);
		AddLetterToTileBag('b',2);
		AddLetterToTileBag('c',2);
		AddLetterToTileBag('d',4);
		AddLetterToTileBag('e',12);
		AddLetterToTileBag('f',2);
		AddLetterToTileBag('g',3);
		AddLetterToTileBag('h',2);
		AddLetterToTileBag('i',9);
		AddLetterToTileBag('j',1);
		AddLetterToTileBag('k',1);
		AddLetterToTileBag('l',4);
		AddLetterToTileBag('m',2);
		AddLetterToTileBag('n',6);
		AddLetterToTileBag('o',8);
		AddLetterToTileBag('p',2);
		AddLetterToTileBag('q',1);
		AddLetterToTileBag('r',6);
		AddLetterToTileBag('s',4);
		AddLetterToTileBag('t',6);
		AddLetterToTileBag('u',4);
		AddLetterToTileBag('v',2);
		AddLetterToTileBag('w',2);
		AddLetterToTileBag('x',1);
		AddLetterToTileBag('y',2);
		AddLetterToTileBag('z',1);
		AddLetterToTileBag(' ',2);
	}

	/**
	 * AddLetterToTileBag - Add a single letter to the tilebag.
	 * 
	 * @author BRG
	 * @version Lab #8
	 * @since Lab #8
	 */
	 private void AddLetterToTileBag(Character c, int num) {
		 Letter d = new Letter(c);
		 for (int i =0; i < num; i++) {
		 this.tileBag.add(d);
		 }
		 }


	public boolean isAnySpaceUsed(ArrayList<Space> spaces) {
		for (Space s : spaces) {
			if (this.getPuzzle()[s.getRow()][s.getCol()].getLetter() != null)
				return true;
		}
		return false;
	}

	public boolean isAnySpaceAdjacentOrStar(ArrayList<Space> spaces) {

		ArrayList<Space> spacesAdjacent = new ArrayList<Space>();

		// Check for Star
		for (Space s : spaces) {
			if (this.getPuzzle()[s.getRow()][s.getCol()].getBonusSquare() != null
					&& this.getPuzzle()[s.getRow()][s.getCol()].getBonusSquare().getBonusType() == eBonusType.Star)
				return true;
		}

		for (Space s : spaces) {
			spacesAdjacent.addAll(findAdacentSpaces(s));
		}

		for (Space s : spacesAdjacent) {
			if (s.getLetter() != null)
				return true;
		}

		return false;
	}

	public static ArrayList<Space> findAdacentSpaces(Board b, Space s) {
		ArrayList<Space> spaces = new ArrayList<Space>();
		if (b == null)
			return null;
		if (s == null)
			return null;

		int iRow = s.getRow();
		int iCol = s.getCol();

		int iRowMin = 0;
		int iColMin = 0;
		int iColMax = b.getPuzzle()[0].length;
		int iRowMax = b.getPuzzle().length;

		// left
		if (s.getCol() > iColMin) {
			Space s1 = b.getPuzzle()[s.getRow()][s.getCol() - 1];
			spaces.add(s1);
		}
		if (s.getCol() < iColMax) {
			Space s2 = b.getPuzzle()[s.getRow()][s.getCol() + 1];
			spaces.add(s2);
		}
		if (s.getRow() > iRowMin) {
			Space s3 = b.getPuzzle()[s.getRow() - 1][s.getCol()];
			spaces.add(s3);
		}
		if (s.getRow() < iRowMax) {
			Space s4 = b.getPuzzle()[s.getRow() + 1][s.getCol()];
			spaces.add(s4);
		}
		return spaces;
	}

	public ArrayList<Space> findAdacentSpaces(Space s) {
		ArrayList<Space> spaces = new ArrayList<Space>();
		if (this.puzzle == null)
			return null;
		if (s == null)
			return null;

		int iRow = s.getRow();
		int iCol = s.getCol();

		int iRowMin = 0;
		int iColMin = 0;
		int iColMax = this.getPuzzle()[0].length;
		int iRowMax = this.getPuzzle().length;

		// left
		if (s.getCol() > iColMin) {
			Space s1 = this.getPuzzle()[s.getRow()][s.getCol() - 1];
			spaces.add(s1);
		}
		if (s.getCol() < iColMax) {
			Space s2 = this.getPuzzle()[s.getRow()][s.getCol() + 1];
			spaces.add(s2);
		}
		if (s.getRow() > iRowMin) {
			Space s3 = this.getPuzzle()[s.getRow() - 1][s.getCol()];
			spaces.add(s3);
		}
		if (s.getRow() < iRowMax) {
			Space s4 = this.getPuzzle()[s.getRow() + 1][s.getCol()];
			spaces.add(s4);
		}
		return spaces;
	}

	protected void MakeMove(Move m) {
		if (m.ValidateMove().findMoveResult() != eMoveResult.GoodMove) {

		}

		for (Space s : m.getTiles()) {
			// If the space is a bonus space, turn it off
			if (this.puzzle[s.getRow()][s.getCol()].getBonusSquare() != null) {
				this.puzzle[s.getRow()][s.getCol()].getBonusSquare().setUsed(true);
			}

			// Record the space
			this.SetSpace(s);
		}
		// Add the move to the history
		movesMade.add(m);
	}

	private BonusSquare findBonus(int Row, int Col) {
		for (BonusSquare bs : this.bonuses) {
			if (bs.getRow() == Row && bs.getCol() == Col) {
				return bs;
			}
		}
		return null;
	}

	private void SetBonus() {
		bonuses.add(new BonusSquare(0, 0, eBonusType.TripleWord));
		bonuses.add(new BonusSquare(0, 7, eBonusType.TripleWord));
		bonuses.add(new BonusSquare(0, 14, eBonusType.TripleWord));
		bonuses.add(new BonusSquare(7, 0, eBonusType.TripleWord));
		bonuses.add(new BonusSquare(7, 14, eBonusType.TripleWord));
		bonuses.add(new BonusSquare(14, 0, eBonusType.TripleWord));
		bonuses.add(new BonusSquare(14, 7, eBonusType.TripleWord));
		bonuses.add(new BonusSquare(14, 14, eBonusType.TripleWord));

		bonuses.add(new BonusSquare(0, 3, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(0, 11, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(2, 6, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(2, 8, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(3, 0, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(3, 7, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(3, 14, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(6, 2, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(6, 6, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(6, 8, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(6, 12, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(7, 3, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(7, 11, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(8, 2, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(8, 6, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(8, 8, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(8, 12, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(11, 0, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(11, 7, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(11, 14, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(12, 6, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(12, 8, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(14, 3, eBonusType.DoubleLetter));
		bonuses.add(new BonusSquare(14, 11, eBonusType.DoubleLetter));

		bonuses.add(new BonusSquare(1, 5, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(1, 9, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(5, 1, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(5, 5, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(5, 9, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(5, 13, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(9, 1, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(9, 5, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(9, 9, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(9, 13, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(13, 5, eBonusType.TripleLetter));
		bonuses.add(new BonusSquare(13, 9, eBonusType.TripleLetter));

		bonuses.add(new BonusSquare(1, 1, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(1, 13, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(2, 2, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(2, 12, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(3, 3, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(3, 11, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(4, 4, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(4, 10, eBonusType.DoubleWord));

		bonuses.add(new BonusSquare(10, 4, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(10, 10, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(11, 3, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(11, 11, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(12, 2, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(12, 12, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(13, 1, eBonusType.DoubleWord));
		bonuses.add(new BonusSquare(13, 13, eBonusType.DoubleWord));

		bonuses.add(new BonusSquare(7, 7, eBonusType.Star));
	}

}
