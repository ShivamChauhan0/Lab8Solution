package pkgGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import eNum.eMoveResult;
import pkgCore.Dictionary;
import pkgCore.Word;

public class Move {

	private Board b;
	private HashSet<Space> tiles = new HashSet<Space>();
	private Dictionary d = new Dictionary();
	private ScoreMove scoremove = new ScoreMove();
	public Move(Board B) {
		this.b = B;
	}

	public void AddTile(Space s) {
		tiles.add(s);
	}

	public void SetTiles(ArrayList<Space> spaces) {
		tiles.clear();
		tiles.addAll(spaces);
	}

	public ScoreMove getScoremove() {
		return scoremove;
	}

	public eMoveResult ValidateMove() {

		

		HashMap<Integer, Integer> hmRow = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> hmCol = new HashMap<Integer, Integer>();
		ArrayList<Space> spaces = new ArrayList<Space>(this.tiles);
		ArrayList<String> StringWords = new ArrayList<String>();
		ArrayList<Word> PossibleWords = new ArrayList<Word>();
		ArrayList<Space> moveSpaces;
		Space minSpace = null;
		Space maxSpace = null;
		Space minBoardSpace = null;
		Space maxBoardSpace = null;

		eMoveResult moveResult;
		int iRowCnt = 0;
		int iColCnt = 0;

		// Is board valid (not null)?
		if (b == null)
			return eMoveResult.MissingBoard;

		// What if there are no tiles?
		if (tiles.size() == 0)
			return eMoveResult.NoTiles;

		// Area all tiles either vertical or horizontal?
		for (int i = 0; i < spaces.size(); i++) {
			if (hmRow.get(spaces.get(i).getRow()) == null)
				iRowCnt = 1;
			else
				iRowCnt = hmRow.get(spaces.get(i).getRow()) + 1;

			if (hmCol.get(spaces.get(i).getCol()) == null)
				iColCnt = 1;
			else
				iColCnt = hmCol.get(spaces.get(i).getCol()) + 1;
			hmRow.put(spaces.get(i).getRow(), iRowCnt);
			hmCol.put(spaces.get(i).getCol(), iColCnt);
		}
		if (hmRow.size() == 1)
			moveResult = eMoveResult.RowMove;
		else if (hmCol.size() == 1)
			moveResult = eMoveResult.ColMove;
		else {
			moveResult = eMoveResult.NotALine;
			return moveResult;
		}

		// Is each tile moving to a spot that doesn't have an existing Letter?
		if (b.isAnySpaceUsed(spaces))
			return eMoveResult.SpaceUsed;

		// Is at least one tile touching an existing tile or star?
		if (!b.isAnySpaceAdjacentOrStar(spaces)) {
			return eMoveResult.NoAdjacentOrStar;
		}

		switch (moveResult) {
		case ColMove:
			minSpace = spaces.stream().min(Comparator.comparing(Space::getRow))
					.orElseThrow(NoSuchElementException::new);

			maxSpace = spaces.stream().max(Comparator.comparing(Space::getRow))
					.orElseThrow(NoSuchElementException::new);

			minBoardSpace = FindMinMaxSpace(b, minSpace, minSpace.getRow(), minSpace.getCol() - 1, moveResult, 0, -1);

			maxBoardSpace = FindMinMaxSpace(b, maxSpace, maxSpace.getRow(), maxSpace.getCol() + 1, moveResult, 0, 1);

			break;

		case RowMove:
			minSpace = spaces.stream().min(Comparator.comparing(Space::getCol))
					.orElseThrow(NoSuchElementException::new);
			maxSpace = spaces.stream().max(Comparator.comparing(Space::getCol))
					.orElseThrow(NoSuchElementException::new);

			minBoardSpace = FindMinMaxSpace(b, minSpace, minSpace.getRow(), minSpace.getCol() - 1, moveResult, 0, -1);

			maxBoardSpace = FindMinMaxSpace(b, maxSpace, maxSpace.getRow(), maxSpace.getCol() + 1, moveResult, 0, 1);

			break;
		}

		// See if there's a space in the tiles/board
		if (spaceInWord(b, moveResult, minSpace, maxSpace)) {
			return eMoveResult.SpaceInWord;
		}

		//System.out.println("Good word so far");

		// Find initial word minBoardSpace - maxBoardSpace
		String strInitialWord = findInitialWord(b, minBoardSpace, maxBoardSpace, moveResult);
		moveSpaces = findInitialWordSpaces(b, minBoardSpace, maxBoardSpace, moveResult);
		//ScoreWord SWinitial = new ScoreWord(d, moveSpaces, b, moveResult);
		//SM.AddScoreWord(SWinitial);
		
		scoremove.AddScoreWord(new ScoreWord(d,moveSpaces,b,moveResult));
		
		
		//System.out.println(strInitialWord);
		StringWords.add(strInitialWord);

		// Find every subsequent word. If RowMove, check every column to see if there
		// is a word made from minSpace to maxSpace.

		for (Space s : this.tiles) {

			switch (moveResult) {
			case ColMove:
				minBoardSpace = FindMinMaxSpace(b, s, s.getRow(), s.getCol() +1, (moveResult == eMoveResult.ColMove ? eMoveResult.RowMove : eMoveResult.ColMove), 0, -1);
				maxBoardSpace = FindMinMaxSpace(b, s, s.getRow(), s.getCol() -1, (moveResult == eMoveResult.ColMove ? eMoveResult.RowMove : eMoveResult.ColMove), 0, -1);
				break;
			case RowMove:				
				minBoardSpace = FindMinMaxSpace(b, s, s.getRow()-1, s.getCol() , (moveResult == eMoveResult.ColMove ? eMoveResult.RowMove : eMoveResult.ColMove), 0, -1);
				maxBoardSpace = FindMinMaxSpace(b, s, s.getRow()+1, s.getCol() , (moveResult == eMoveResult.ColMove ? eMoveResult.RowMove : eMoveResult.ColMove), 0, -1);
			}
			moveSpaces = findInitialWordSpaces(b, minBoardSpace, maxBoardSpace, (moveResult == eMoveResult.ColMove ? eMoveResult.RowMove : eMoveResult.ColMove));
			scoremove.AddScoreWord(new ScoreWord(d,moveSpaces,b,moveResult));

			String strWord = findSubsequentWords(b, minBoardSpace, maxBoardSpace, moveResult);
			if (strWord != null)
				StringWords.add(findSubsequentWords(b, minBoardSpace, maxBoardSpace, moveResult));
		}

		// All the words are found, time to score the words
		for (String strWord : StringWords) {
			//ScoreWord SW = new ScoreWord(d, spaces, b);

			Word w = d.findWord(strWord);
			if (w == null)
				return eMoveResult.NotAWord;
			else
				PossibleWords.add(w);
		}

		// If any of the above is NOTAWORD, return NOTAWORD

		// Check each tile. Is every word (horizontal or vertical) a valid word? If it's
		// valid,
		// determine the score of the word.

		return eMoveResult.GoodMove;
	}

	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("incomplete-switch")
	private Space FindMinMaxSpace(Board b, Space MinMaxSpace, int Row, int Col, eMoveResult MoveResult, int Boundary,
			int IncreaseDecrease) {
		switch (MoveResult) {
		case RowMove:
			if (Row == Boundary)
				if (b.getPuzzle()[Boundary][Col].getLetter() == null)
					return MinMaxSpace;
				else
					return b.getPuzzle()[Boundary][Col];
			else if (Row != Boundary) {
				if (b.getPuzzle()[Row][Col].getLetter() == null) {
					// There's no letter here, return minSpace
					return MinMaxSpace;
				} else if (b.getPuzzle()[Row][Col].getLetter() != null) {
					// Board has a space, back up one space
					return FindMinMaxSpace(b, b.getPuzzle()[Row][Col], Row, Col + IncreaseDecrease, MoveResult,
							Boundary, IncreaseDecrease);
				}
			}
			break;
		case ColMove:
			if (Col == Boundary)
				if (b.getPuzzle()[Row][Boundary].getLetter() == null)
					return MinMaxSpace;
				else
					return b.getPuzzle()[Row][Boundary];
			else if (Col != Boundary) {
				if (b.getPuzzle()[Row][Col].getLetter() == null) {
					// There's no letter here, return minSpace
					return MinMaxSpace;
				} else if (b.getPuzzle()[Row][Col].getLetter() != null) {
					// Board has a space, back up one space
					return FindMinMaxSpace(b, b.getPuzzle()[Row][Col], Row + IncreaseDecrease, Col, MoveResult,
							Boundary, IncreaseDecrease);
				}
			}
			break;
		}
		return MinMaxSpace;
	}

	@SuppressWarnings("incomplete-switch")
	private boolean spaceInWord(Board b, eMoveResult MoveResult, Space minSpace, Space maxSpace) {

		switch (MoveResult) {
		case ColMove:
			for (int Row = minSpace.getRow(); Row < maxSpace.getRow(); Row++) {
				if (FindSpace(b, Row, minSpace.getCol()).getLetter() == null)
					return true;
			}
			break;
		case RowMove:
			for (int Col = minSpace.getCol(); Col < maxSpace.getCol(); Col++) {
				if (FindSpace(b, minSpace.getRow(), Col).getLetter() == null)
					return true;
			}
			break;
		}
		return false;
	}

	private Space FindSpace(Board b, int Row, int Col) {
		for (Space s : this.tiles) {
			if (s.getRow() == Row && s.getCol() == Col)
				return s;
		}
		return b.getPuzzle()[Row][Col];
	}

	@SuppressWarnings("incomplete-switch")
	private String findInitialWord(Board b, Space minSpace, Space maxSpace, eMoveResult MoveResult) {
		StringBuilder sb = new StringBuilder();
		switch (MoveResult) {
		case ColMove:
			for (int Row = minSpace.getRow(); Row < maxSpace.getRow() + 1; Row++) {
				Space s = FindSpace(b, Row, minSpace.getCol());
				sb.append(s.getLetter().getChLetter());
			}
			break;
		case RowMove:
			for (int Col = minSpace.getCol(); Col < maxSpace.getCol() + 1; Col++) {
				Space s = FindSpace(b, minSpace.getRow(), Col);
				sb.append(s.getLetter().getChLetter());
			}
			break;
		}
		return sb.toString();
	}

	@SuppressWarnings("incomplete-switch")
	private ArrayList<Space> findInitialWordSpaces(Board b, Space minSpace, Space maxSpace, eMoveResult MoveResult) {
		ArrayList<Space> SpacesFound = new ArrayList<Space>();
		switch (MoveResult) {
		case ColMove:
			for (int Row = minSpace.getRow(); Row < maxSpace.getRow() + 1; Row++) {
				SpacesFound.add(FindSpace(b, Row, minSpace.getCol()));
			}
			break;
		case RowMove:
			for (int Col = minSpace.getCol(); Col < maxSpace.getCol() + 1; Col++) {
				SpacesFound.add(FindSpace(b, minSpace.getRow(), Col));
			}
			break;
		}
		return SpacesFound;
	}

	private String findSubsequentWords(Board b, Space minSpace, Space maxSpace, eMoveResult MoveResult) {
//		Space minBoardSpace = null;
//		Space maxBoardSpace = null;
		StringBuilder sb = new StringBuilder();
		switch (MoveResult) {
		case ColMove:
			break;
		case RowMove:
			if (maxSpace.getRow() - minSpace.getRow() == 0)
				// There are no words, single space
				return null;
			for (int iLetter = minSpace.getRow(); iLetter < maxSpace.getRow() + 1; iLetter++) {
				Space s = FindSpace(b, iLetter, minSpace.getCol());
				sb.append(s.getLetter().getChLetter());
			}
			return sb.toString();
		}

		return null;
	}

	private void MakeMove() {
		if (this.ValidateMove() == eMoveResult.GoodMove) {
		}
	}

}
