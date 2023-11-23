package game.model;

import java.util.HashSet;
import java.util.Set;


public class Quality implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String itemQuality;
	private Set<?> customizedWeapons = new HashSet<Object>(0);
	private Set<?> customizedGears = new HashSet<Object>(0);

	public Quality() {
	}

	public Quality(String itemQuality) {
		this.itemQuality = itemQuality;
	}

	public Quality(String itemQuality, Set<?> customizedWeapons, Set<?> customizedGears) {
		this.itemQuality = itemQuality;
		this.customizedWeapons = customizedWeapons;
		this.customizedGears = customizedGears;
	}

	public String getItemQuality() {
		return this.itemQuality;
	}

	public void setItemQuality(String itemQuality) {
		this.itemQuality = itemQuality;
	}

	public Set<?> getCustomizedWeapons() {
		return this.customizedWeapons;
	}

	public void setCustomizedWeapons(Set<?> customizedWeapons) {
		this.customizedWeapons = customizedWeapons;
	}

	public Set<?> getCustomizedGears() {
		return this.customizedGears;
	}

	public void setCustomizedGears(Set<?> customizedGears) {
		this.customizedGears = customizedGears;
	}

}
