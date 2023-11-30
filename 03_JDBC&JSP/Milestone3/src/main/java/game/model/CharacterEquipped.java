package game.model;

public class CharacterEquipped {
    private Character character;
    private GearSlot gearSlot;
    private Item item;
    
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	public GearSlot getGearSlot() {
		return gearSlot;
	}
	public void setGearSlot(GearSlot gearSlot) {
		this.gearSlot = gearSlot;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
    
    
}