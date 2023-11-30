package game.model;

public class Currency {
    private int currencyId;
    private String currencyName;
    private int maximumAmount;
    private int weeklyCap; 
    private boolean availability;
	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public int getMaximumAmount() {
		return maximumAmount;
	}
	public void setMaximumAmount(int maximumAmount) {
		this.maximumAmount = maximumAmount;
	}
	public int getWeeklyCap() {
		return weeklyCap;
	}
	public void setWeeklyCap(int weeklyCap) {
		this.weeklyCap = weeklyCap;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}