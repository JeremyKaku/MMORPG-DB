package game.model;

public class CharacterCurrency {
    private Character character;
    private Currency currency;
    private int weeklyAmount; 
    private int totalAmount;
    
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