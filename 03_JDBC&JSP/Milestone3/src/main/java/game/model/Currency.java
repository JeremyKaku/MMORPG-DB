package game.model;

public class Currency {
	protected int currencyId;
	protected String currencyName;
	protected int maximumAmount;
	protected int weeklyCap;
	protected boolean availability;

	public Currency() {
	}

	public Currency(int currencyId) {
		this.currencyId = currencyId;
	}

	public Currency(int currencyId, String currencyName, int maximumAmount, int weeklyCap, boolean availability) {
		this.currencyId = currencyId;
		this.currencyName = currencyName;
		this.maximumAmount = maximumAmount;
		this.weeklyCap = weeklyCap;
		this.availability = availability;
	}

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