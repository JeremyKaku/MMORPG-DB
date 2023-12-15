package game.model;

public class CharacterCurrency {
	protected Character character;
	protected Currency currency;
	protected int weeklyAmount;
	protected int totalAmount;

	public CharacterCurrency() {
	}

	public CharacterCurrency(Character character, Currency currency, int weeklyAmount, int totalAmount) {
		this.character = character;
		this.currency = currency;
		this.weeklyAmount = weeklyAmount;
		this.totalAmount = totalAmount;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public int getWeeklyAmount() {
		return weeklyAmount;
	}

	public void setWeeklyAmount(int weeklyAmount) {
		this.weeklyAmount = weeklyAmount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

}