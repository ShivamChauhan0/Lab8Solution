package pkgGame;

import java.util.ArrayList;
import java.util.UUID;

import eNum.eMoveResult;

public class ScoreMove {

	private UUID ScoreMoveID;
	private ArrayList<ScoreWord> arrScoreWord = new ArrayList<ScoreWord>();

	public ScoreMove() {
		super();
		ScoreMoveID = UUID.randomUUID();
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

	public int CalculateScoreMove() {
		int totalscore = 0;
		for(ScoreWord w: arrScoreWord) {
			totalscore += w.getScore();
		}
		return totalscore;
	}
	
	public eMoveResult findMoveResult()
	{
		for (ScoreWord SM: arrScoreWord)
		{
			if (SM.getMoveResult() != eMoveResult.GoodMove)
			{
				return SM.getMoveResult();
			}
		}
		return eMoveResult.GoodMove;
	}

}
