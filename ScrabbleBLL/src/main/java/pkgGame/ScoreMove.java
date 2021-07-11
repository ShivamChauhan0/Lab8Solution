package pkgGame;

import java.util.ArrayList;
import java.util.UUID;

import eNum.eMoveResult;

public class ScoreMove {

	private UUID ScoreMoveID;
	private eMoveResult MoveResult;
	private ArrayList<ScoreWord> arrScoreWord = new ArrayList<ScoreWord>();

	public ScoreMove() {
		super();
		ScoreMoveID = UUID.randomUUID();
	}

	public eMoveResult getMoveResult() {
		return MoveResult;
	}

	public void setMoveResult(eMoveResult moveResult) {
		MoveResult = moveResult;
	}

	public UUID getScoreMoveID() {
		return ScoreMoveID;
	}

	public void AddScoreWord(ScoreWord sw) {
		arrScoreWord.add(sw);
	}

	public ArrayList<ScoreWord> getArrScoreWord() {
		return arrScoreWord;
	}

	public int getScoreMove() {
		return arrScoreWord.stream().map(x -> x.getScore()).reduce(0, Integer::sum);
	}

}
