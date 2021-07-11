package pkgGame;

import java.util.ArrayList;
import java.util.UUID;

import eNum.eMoveResult;
import pkgCore.Dictionary;
import pkgCore.Word;

public class ScoreWord {

	private UUID ScoreWordID;
	private eMoveResult MoveResult;
	private int Score;
	private Word word;
	private String strWord;
	private Dictionary dictionary;
	private ArrayList<Space> tiles;
	private Board board;
	
	public ScoreWord(Dictionary Dictionary, ArrayList<Space> Tiles, Board Board, eMoveResult MoveResult) {
		this.ScoreWordID = UUID.randomUUID();
		this.dictionary = Dictionary;
		this.tiles = Tiles;
		this.board = Board;
	}
	
	public eMoveResult getMoveResult() {
		return MoveResult;
	}
	public void setMoveResult(eMoveResult moveResult) {
		MoveResult = moveResult;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public UUID getScoreWordID() {
		return ScoreWordID;
	}	
	public void SetWord(String strWord)
	{
		this.word = dictionary.findWord(strWord);
	}
	public boolean isWord()
	{
		return this.word != null;
	}
	public ArrayList<Space> getTiles() {
		return tiles;
	}
	

	
}
