package game.model;

public class Attribute {
	protected int attributeID;
	protected String attributesName;

	public Attribute() {
	}

	public Attribute(int attributeID, String attributesName) {
		this.attributeID = attributeID;
		this.attributesName = attributesName;
	}

	public Attribute(int attributeID) {
		this.attributeID = attributeID;
	}

	/** Getters and setters. */

	public int getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public String getAttributesName() {
		return attributesName;
	}

	public void setAttributesName(String attributesName) {
		this.attributesName = attributesName;
	}
}