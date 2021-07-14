package pkgGame;

import java.util.UUID;

public class Player {

	private UUID PlayerID;
	private String PlayerName;
	public Player(String playerName) {
		super();
		this.PlayerID = UUID.randomUUID();
		PlayerName = playerName;
	}
	public String getPlayerName() {
		return PlayerName;
	}
	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}
	public UUID getPlayerID() {
		return PlayerID;
	}
	
	
	
}
