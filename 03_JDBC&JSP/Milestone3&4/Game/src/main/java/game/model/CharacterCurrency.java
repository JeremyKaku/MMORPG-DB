package game.model;

public class CharacterCurrency implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private CharacterCurrencyId id;
	private Currency currency;
	private GameCharacter gameCharacter;
	private Integer weeklyAmount;
	private int totalAmount;

	public CharacterCurrency() {
	}

	public CharacterCurrency(CharacterCurrencyId id, Currency currency, GameCharacter gameCharacter, int totalAmount) {
		this.id = id;
		this.currency = currency;
		this.gameCharacter = gameCharacter;
		this.totalAmount = totalAmount;
	}

	public CharacterCurrency(CharacterCurrencyId id, Currency currency, GameCharacter gameCharacter,
			Integer weeklyAmount, int totalAmount) {
		this.id = id;
		this.currency = currency;
		this.gameCharacter = gameCharacter;
		this.weeklyAmount = weeklyAmount;
		this.totalAmount = totalAmount;
	}

	public CharacterCurrencyId getId() {
		return this.id;
	}

	public void setId(CharacterCurrencyId id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public GameCharacter getGameCharacter() {
		return this.gameCharacter;
	}

	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	public Integer getWeeklyAmount() {
		return this.weeklyAmount;
	}

	public void setWeeklyAmount(Integer weeklyAmount) {
		this.weeklyAmount = weeklyAmount;
	}

	public int getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

}
