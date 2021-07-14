package pkgExceptions;

 
import eNum.eMoveResult;
import pkgGame.Move;
import pkgGame.Player;

public class MoveException extends Exception {

	private static final long serialVersionUID = 1L;
	private Move move;
	private Player currPlayer;
	private Player movePlayer;
	private eMoveResult moveResult;
	public MoveException(Move move, Player currPlayer, Player movePlayer, eMoveResult MoveResult) {
		super();
		this.move = move;
		this.currPlayer = currPlayer;
		this.movePlayer = movePlayer;
		this.moveResult = MoveResult;
	}
	public Move getMove() {
		return move;
	}
	public Player getCurrPlayer() {
		return currPlayer;
	}
	public Player getMovePlayer() {
		return movePlayer;
	}
	public eMoveResult getMoveResult() {
		return moveResult;
	}
 
	
	
}
