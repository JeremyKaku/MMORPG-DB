package game.model;

import java.util.HashSet;
import java.util.Set;


public class Currency implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int currencyId;
	private String currencyName;
	private int maximumAmount;
	private Integer weeklyCap;
	private boolean availiability;
	private Set<?> characterCurrencies = new HashSet<Object>(0);

	public Currency() {
	}

	public Currency(int currencyId, String currencyName, int maximumAmount, boolean availiability) {
		this.currencyId = currencyId;
		this.currencyName = currencyName;
		this.maximumAmount = maximumAmount;
		this.availiability = availiability;
	}

	public Currency(int currencyId, String currencyName, int maximumAmount, Integer weeklyCap, boolean availiability,
			Set<?> characterCurrencies) {
		this.currencyId = currencyId;
		this.currencyName = currencyName;
		this.maximumAmount = maximumAmount;
		this.weeklyCap = weeklyCap;
		this.availiability = availiability;
		this.characterCurrencies = characterCurrencies;
	}

	public int getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public int getMaximumAmount() {
		return this.maximumAmount;
	}

	public void setMaximumAmount(int maximumAmount) {
		this.maximumAmount = maximumAmount;
	}

	public Integer getWeeklyCap() {
		return this.weeklyCap;
	}

	public void setWeeklyCap(Integer weeklyCap) {
		this.weeklyCap = weeklyCap;
	}

	public boolean isAvailiability() {
		return this.availiability;
	}

	public void setAvailiability(boolean availiability) {
		this.availiability = availiability;
	}

	public Set<?> getCharacterCurrencies() {
		return this.characterCurrencies;
	}

	public void setCharacterCurrencies(Set<?> characterCurrencies) {
		this.characterCurrencies = characterCurrencies;
	}

}
