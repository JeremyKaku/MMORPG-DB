package game.model;

public class Player {
	protected int playerID;
	protected String playerName;
	protected String email;
	protected String playerPassword;

	public Player() {
	}

	public Player(int playerID, String playerName, String email, String playerPassword) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.email = email;
		this.playerPassword = playerPassword;
	}
	
	public Player(String playerName, String email, String playerPassword) {
		this.playerName = playerName;
		this.email = email;
		this.playerPassword = playerPassword;
	}

	public Player(int playerID) {
		this.playerID = playerID;
	}

	/** Getters and setters. */

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

}