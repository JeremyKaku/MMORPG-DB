package game.model;

import java.util.HashSet;
import java.util.Set;

public class Player implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int playerId;
	private String playerName;
	private String email;
	private String playerPassword;
	private Set<?> gameCharacters = new HashSet<Object>(0);

	public Player() {
	}

	public Player(int playerId, String playerName, String email, String playerPassword) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.email = email;
		this.playerPassword = playerPassword;
	}

	public Player(int playerId, String playerName, String email, String playerPassword, Set<?> gameCharacters) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.email = email;
		this.playerPassword = playerPassword;
		this.gameCharacters = gameCharacters;
	}

	public Player(String playerName, String email, String playerPassword) {
		this.playerName = playerName;
		this.email = email;
		this.playerPassword = playerPassword;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlayerPassword() {
		return this.playerPassword;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

	public Set<?> getGameCharacters() {
		return this.gameCharacters;
	}

	public void setGameCharacters(Set<?> gameCharacters) {
		this.gameCharacters = gameCharacters;
	}

}
