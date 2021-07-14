package pkgExceptions;

import eNumExceptions.eGameExceptionType;
import pkgGame.Game;

public class GameException extends Exception {

	private static final long serialVersionUID = 1L;
	private Game game;
	private eGameExceptionType GameExceptionType;
	public GameException(Game game, eGameExceptionType gameExceptionType) {
		super();
		this.game = game;
		GameExceptionType = gameExceptionType;
	}
	public Game getGame() {
		return game;
	}
	public eGameExceptionType getGameExceptionType() {
		return GameExceptionType;
	}
	
	
}
