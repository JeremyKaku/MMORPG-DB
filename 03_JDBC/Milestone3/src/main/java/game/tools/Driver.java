package game.tools;

import game.dal.*;
import game.model.*;
import game.model.Character;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class Driver {

	public static void displayPlayersAndCharacters() throws SQLException {
		PlayerDao playerDao = PlayerDao.getInstance();
		CharacterDao characterDao = CharacterDao.getInstance();
		List<Player> players = playerDao.getAllPlayers();
		if (players != null && !players.isEmpty()) {
			for (Player player : players) {
				System.out.println("\nPlayer: " + player.getPlayerName());
				List<Character> characters = characterDao.getAllCharactersByPlayer(player);
				if (characters != null && !characters.isEmpty()) {
					System.out.println("Characters:");
					for (Character character : characters) {
						System.out.println("  - " + character.getFirstName() + " " + character.getLastName());
						System.out.println(" \t- " + character.getWeapon().getItemName());
					}
				} else {
					System.out.println("No characters found for this player.");
				}
				System.out.println();
			}
		} else {
			System.out.println("No players found.");
		}
	}

	static void displayCustomizedGear(CustomizedGear customizedGear) {
		if (customizedGear != null) {
			System.out.println("Customized Gear -> ");
			System.out.println("Item ID: " + customizedGear.getItemID());
			System.out.println("Item Name: " + customizedGear.getItemName());
			System.out.println("Max Stack Size: " + customizedGear.getMaxStackSize());
			System.out.println("Vendor Price: " + customizedGear.getVendorPrice());
			System.out.println("Item Level: " + customizedGear.getItemLevel());
			System.out.println("Gear Slot: " + customizedGear.getGearSlot().getGearSlotId());
			System.out.println("Required Level: " + customizedGear.getRequiredLevel());
			System.out.println("Defense Rating: " + customizedGear.getDefenseRating());
			System.out.println("Magic Defense Rating: " + customizedGear.getMagicDefenseRating());
			System.out.println("Item Quality: " + customizedGear.getItemQuality());
			System.out.println("Customized Condition: " + customizedGear.getCustomizedCondition());
			System.out.println("Dye Color: " + customizedGear.getDyeColor());
			System.out.println("Maker: " + customizedGear.getMaker());
			System.out.println();
		} else {
			System.out.println("Customized Gear not found!");
		}
	}

	static void displayCustomizedWeapon(CustomizedWeapon customizedWeapon) {
		if (customizedWeapon != null) {
			System.out.println("Customized Weapon -> ");
			System.out.println("Item ID: " + customizedWeapon.getItemID());
			System.out.println("Item Name: " + customizedWeapon.getItemName());
			System.out.println("Max Stack Size: " + customizedWeapon.getMaxStackSize());
			System.out.println("Vendor Price: " + customizedWeapon.getVendorPrice());
			System.out.println("Item Level: " + customizedWeapon.getItemLevel());
			System.out.println("Required Level: " + customizedWeapon.getRequiredLevel());
			System.out.println("Damage Done: " + customizedWeapon.getDamageDone());
			System.out.println("Auto Attack: " + customizedWeapon.getAutoAttack());
			System.out.println("Attack Delay: " + customizedWeapon.getAttackDelay());
			System.out.println("Item Quality: " + customizedWeapon.getItemQuality());
			System.out.println("Customized Condition: " + customizedWeapon.getCustomizedCondition());
			System.out.println("Dye Color: " + customizedWeapon.getDyeColor());
			System.out.println("Maker: " + customizedWeapon.getMaker());
			System.out.println();
		} else {
			System.out.println("Customized Weapon not found!");
		}
	}

	private static void displayInventory(Inventory inventory) {
		if (inventory != null) {
			System.out.println("Inventory ->");
			System.out.println("Character ID: " + inventory.getCharacter().getCharacterID());
			System.out.println("Inventory Slot ID: " + inventory.getInventorySlotId());
			System.out.println("Quantity: " + inventory.getQuantity());
			System.out.println("Item ID: " + inventory.getItem().getItemID());
			System.out.println("Item Name: " + inventory.getItem().getItemName());
			System.out.println("Max Stack Size: " + inventory.getItem().getMaxStackSize());
			System.out.println();
		} else {
			System.out.println("Inventory not found!");
		}
	}

	private static void displayCurrency(Currency currency) {
		if (currency != null) {
			System.out.println("Currency:");
			System.out.println("Currency ID: " + currency.getCurrencyId());
			System.out.println("Currency Name: " + currency.getCurrencyName());
			System.out.println("Maximum Amount: " + currency.getMaximumAmount());
			System.out.println("Weekly Cap: " + currency.getWeeklyCap());
			System.out.println("Availability: " + currency.isAvailability());
			System.out.println();
		} else {
			System.out.println("Currency not found!");
		}
	}

	private static void displayCharacterCurrency(CharacterCurrency characterCurrency) {
		if (characterCurrency != null) {
			System.out.println("CharacterCurrency:");
			System.out.println("Character: " + characterCurrency.getCharacter().getFirstName());
			System.out.println("Currency: " + characterCurrency.getCurrency().getCurrencyName());
			System.out.println("Weekly Amount: " + characterCurrency.getWeeklyAmount());
			System.out.println("Total Amount: " + characterCurrency.getTotalAmount());
			System.out.println();
		} else {
			System.out.println("CharacterCurrency not found!");
		}
	}

	public static void main(String[] args) throws SQLException {

		// Colours for displaying
		String reset = "\u001B[0m";
		String red = "\u001B[31m";
		String green = "\u001B[32m";
		String yellow = "\u001B[33m";

		// Player
		PlayerDao playerDao = PlayerDao.getInstance();
		Player riddhi = new Player("Riddhi Gohil", "gr@gmail.com", "");
		Player yashvi = new Player("Yashvi Garg", "yg@gmail.com", "");
		Player gaurav = new Player("Gaurav Jurani", "jg@gmail.com", "");
		Player xue = new Player("Xue Han", "hx@gmail.com", "");
		Player jing = new Player("Jing Guo", "gj@gmail.com", "");
		riddhi = playerDao.create(riddhi);
		yashvi = playerDao.create(yashvi);
		gaurav = playerDao.create(gaurav);
		xue = playerDao.create(xue);
		jing = playerDao.create(jing);

		// Weapon
		WeaponDao weaponDao = WeaponDao.getInstance();
		Weapon sword = new Weapon("Sword", 1, 10, 5, 1, 20, BigDecimal.valueOf(5.75), BigDecimal.valueOf(6.9));
		Weapon axe = new Weapon("Axe", 1, 15, 7, 2, 25, BigDecimal.valueOf(1.5), BigDecimal.valueOf(2.9));
		Weapon bow = new Weapon("Bow", 1, 12, 6, 1, 18, BigDecimal.valueOf(5.75), BigDecimal.valueOf(6.9));
		Weapon dagger = new Weapon("Dagger", 1, 8, 4, 1, 15, BigDecimal.valueOf(5.75), BigDecimal.valueOf(6.9));
		Weapon hammer = new Weapon("Hammer", 1, 18, 8, 3, 30, BigDecimal.valueOf(5.75), BigDecimal.valueOf(6.9));
		Weapon wand = new Weapon("Wand", 1, 10, 5, 2, 22, BigDecimal.valueOf(5.75), BigDecimal.valueOf(6.9));
		Weapon gun = new Weapon("Gun", 1, 40, 5, 4, 22, BigDecimal.valueOf(5.75), BigDecimal.valueOf(6.9));
		sword = weaponDao.create(sword);
		axe = weaponDao.create(axe);
		bow = weaponDao.create(bow);
		dagger = weaponDao.create(dagger);
		hammer = weaponDao.create(hammer);
		wand = weaponDao.create(wand);
		gun = weaponDao.create(gun);
		
		// Characters
		CharacterDao characterDao = CharacterDao.getInstance();
		Character kratos = new Character("King", "Kratos", riddhi, sword);
		Character croft = new Character("Lara", "Croft", riddhi, gun);
		Character panther = new Character("Pink", "Panther", riddhi, hammer);
		Character mario = new Character("Super", "Mario", yashvi, dagger);
		Character luigi = new Character("Baby", "Luigi", yashvi, bow);
		Character gates = new Character("Synyster", "Gates", gaurav, wand);
		Character agent = new Character("Agent", "47", gaurav, axe);
		Character freeman = new Character("Gordon", "Freeman", gaurav, gun);
		Character yuna = new Character("Yin", "Yuna", xue, axe);
		Character scorpion = new Character("Scorpion", "2", jing, hammer);
		Character kong = new Character("Donkey", "Kong", jing, dagger);
		Character ryu = new Character("Ru", "Ryu", jing, sword);
		kratos = characterDao.create(kratos);
		croft = characterDao.create(croft);
		panther = characterDao.create(panther);
		mario = characterDao.create(mario);
		luigi = characterDao.create(luigi);
		gates = characterDao.create(gates);
		agent = characterDao.create(agent);
		freeman = characterDao.create(freeman);
		yuna = characterDao.create(yuna);
		scorpion = characterDao.create(scorpion);
		kong = characterDao.create(kong);
		ryu = characterDao.create(ryu);

		// All Players and their Characters
		System.out.println("\n\n=========================================\n\n");
		System.out.print(" All Players, All their Characters and their Main Hand Weapon -> ");
		displayPlayersAndCharacters();

		// Delete the player
		System.out.println("\n\n=========================================\n\n");
		System.out.println(red + "Deleting a Player..." + reset);
		xue = playerDao.delete(xue);
		System.out.println(red + "Player deleted." + reset);
		System.out.println(red + "\nDeleting a Character..." + reset);
		scorpion = characterDao.delete(scorpion);
		System.out.println(red + "Character deleted." + reset);
		Weapon newWeapon = new Weapon(gun.getItemID());
		characterDao.updateWeapon(gates, newWeapon);
		System.out.println("\nCharacter's weapon updated successfully.");
		System.out.print("\nUpdated Players and Characters -> ");
		displayPlayersAndCharacters();
		System.out.println("\n\n=========================================\n\n");

		int characterIDToRetrieve = 601;
		Character retrievedCharacter = characterDao.getCharacterByID(characterIDToRetrieve);
		if (retrievedCharacter != null) {
			System.out.format(green + "Retrieved Character for ID:%s -> %s %s", characterIDToRetrieve,
					retrievedCharacter.getFirstName(), retrievedCharacter.getLastName() + reset);
		} else {
			System.out.format("Character not found for ID:%s.", characterIDToRetrieve);
		}
		System.out.println("\n\n=========================================\n\n");

		// Attribute instances.
		AttributeDao attributeDao = AttributeDao.getInstance();
		Attribute strength = new Attribute(1, "Strength");
		Attribute agility = new Attribute(2, "Agility");
		Attribute intelligence = new Attribute(3, "Intelligence");
		strength = attributeDao.create(strength);
		agility = attributeDao.create(agility);
		intelligence = attributeDao.create(intelligence);

		// CharacterAttribute instances.
		CharacterAttributeDao charAttributeDao = CharacterAttributeDao.getInstance();
		CharacterAttribute kratosStrength = new CharacterAttribute(strength, kratos, "50");
		CharacterAttribute kratosAgility = new CharacterAttribute(agility, kratos, "20");
		CharacterAttribute kratosIntel = new CharacterAttribute(intelligence, kratos, "220");
		CharacterAttribute croftAgility = new CharacterAttribute(agility, croft, "35");
		kratosStrength = charAttributeDao.create(kratosStrength);
		kratosAgility = charAttributeDao.create(kratosAgility);
		kratosIntel = charAttributeDao.create(kratosIntel);
		croftAgility = charAttributeDao.create(croftAgility);

		// 2. Attributes
		Attribute retrievedAttribute = attributeDao.getAttributeByID(strength.getAttributeID());
		System.out.println(green + "Retrieved attribute -> \n\tID:" + retrievedAttribute.getAttributeID() + "\n\tName:"
				+ retrievedAttribute.getAttributesName() + reset);
		System.out.println("\n\n=========================================\n\n");

		// Update attribute name
		Attribute updatedAttribute = attributeDao.updateAttributeName(strength, "Power");
		System.out.println(yellow + "Updated attribute: \n\tID:" + updatedAttribute.getAttributeID() + "\n\tName:"
				+ updatedAttribute.getAttributesName() + reset);
		System.out.println("\n\n=========================================\n\n");

		// Delete attribute
		attributeDao.deleteAttribute(agility);
		System.out.println(red + "Attribute deleted." + reset);
		System.out.println("\n\n=========================================\n\n");

		// Get attributes by name
		List<Attribute> intelligenceAttributes = attributeDao.getAttributesByName("Intel");
		System.out.println(green + "Attributes with name 'Intel'-> " + reset);
		for (Attribute a : intelligenceAttributes) {
			System.out.format("\n \n\tAttributeID:%s \n\tAttribute Name:%s ", a.getAttributeID(),
					a.getAttributesName());
		}
		System.out.println("\n\n=========================================\n\n");

		System.out.println("Get All Character Attributes For Kratos -> ");
		List<CharacterAttribute> allAttributes = charAttributeDao.getAllCharacterAttributes(kratos.getCharacterID());
		for (CharacterAttribute a : allAttributes) {
			System.out.format("\nCharacter: \n\t-Name:%s %s \n\t-Attribute Name: %s \n\t-Attribute Value: %s",
					a.getCharacter().getFirstName(), a.getCharacter().getLastName(),
					a.getAttribute().getAttributesName(), a.getAtrributeValue());
		}
		System.out.println("\n\n=========================================\n\n");

		System.out.println(green + "\nRetrieving Weapon by ID -> " + gun.getItemID() + reset);
		Weapon retrievedWeapon = weaponDao.getWeaponByID(gun.getItemID());
		System.out.println("Retrieved Weapon: " + retrievedWeapon.getItemName());
		System.out.println("\n\n=========================================\n\n");

		// Item DAO
		ItemDao itemDao = ItemDao.getInstance();
		Item healthPotion = new Item("Health Potion", 10, 5);
		Item manaPotion = new Item("Mana Potion", 10, 7);
		Item antidote = new Item("Antidote", 5, 8);
		Item elixir = new Item("Elixir", 5, 15);

		healthPotion = itemDao.create(healthPotion);
		manaPotion = itemDao.create(manaPotion);
		antidote = itemDao.create(antidote);
		elixir = itemDao.create(elixir);

		// Create GearSlot
		GearSlotDao gearSlotDao = GearSlotDao.getInstance();
		GearSlot mainHand = new GearSlot("Main-Hand");
		GearSlot head = new GearSlot("Head");
		GearSlot offHand = new GearSlot("Off-Hand");
		GearSlot body = new GearSlot("Body");
		GearSlot earring = new GearSlot("Earring");
		GearSlot hands = new GearSlot("Hands");
		GearSlot wrist = new GearSlot("Wrist");
		GearSlot legs = new GearSlot("Legs");
		GearSlot ring = new GearSlot("Ring");
		GearSlot feet = new GearSlot("Feet");

		mainHand = gearSlotDao.create(mainHand);
		head = gearSlotDao.create(head);
		offHand = gearSlotDao.create(offHand);
		body = gearSlotDao.create(body);
		earring = gearSlotDao.create(earring);
		hands = gearSlotDao.create(hands);
		wrist = gearSlotDao.create(wrist);
		legs = gearSlotDao.create(legs);
		ring = gearSlotDao.create(ring);
		feet = gearSlotDao.create(feet);

		Item helmet = new Item("Helmet", 10, 5);
		Item jewellery = new Item("Jewellery", 10, 7);
		Item armor = new Item("Antidote", 5, 8);
		Item magicRobe = new Item("Elixir", 5, 15);
		
		GearDao gearDao = GearDao.getInstance();
		Gear steelHelmet = new Gear(helmet.getItemID(),"Steel Helmet", 3, 20, 5, head, 3, 10, 5);
		Gear leatherArmor = new Gear(healthPotion.getItemID(), "Leather Armor", 2, 15, 3, body, 2, 8, 3);
		Gear ironShield = new Gear(armor.getItemID(), "Iron Shield", 2, 25, 6, hands, 5, 15, 8);
		Gear steelChestplate = new Gear(armor.getItemID(), "Steel Chestplate", 3, 25, 7, body, 5, 12, 6);
		Gear leatherGloves = new Gear(armor.getItemID(), "Leather Gloves", 2, 12, 4, hands, 3, 6, 2);
		Gear bronzeHelmet = new Gear(helmet.getItemID(), "Bronze Helmet", 3, 18, 4, head, 2, 8, 4);
		Gear magicalWand = new Gear(armor.getItemID(), "Magical Wand", 1, 35, 10, mainHand, 10, 20, 8);
		Gear silverRing = new Gear(jewellery.getItemID(), "Silver Ring", 1, 15, 3, ring, 1, 4, 2);

		steelHelmet = gearDao.create(steelHelmet);
		leatherArmor = gearDao.create(leatherArmor);
		ironShield = gearDao.create(ironShield);
		magicRobe = gearDao.create(magicRobe);
		steelChestplate = gearDao.create(steelChestplate);
		leatherGloves = gearDao.create(leatherGloves);
		bronzeHelmet = gearDao.create(bronzeHelmet);
		magicalWand = gearDao.create(magicalWand);
		silverRing = gearDao.create(silverRing);

		// Get all Items
		List<Item> allItems = itemDao.getAllItems();
		System.out.println("All Items:");
		for (Item i : allItems) {
			System.out.format("\n \n\tItemID:%s \n\tItem Name:%s \n\tMax Stack Size:%s \n\tVendor Prize:%s",
					i.getItemID(), i.getItemName(), i.getMaxStackSize(), i.getVendorPrice());
		}
		System.out.println("\n\n=========================================\n\n");

		// MiscellaneousItem
		MiscellaneousItemDao miscellaneousItemDao = MiscellaneousItemDao.getInstance();
		MiscellaneousItem apkalluEgg = new MiscellaneousItem(3101, "Apkallu Egg", 1, 60, "A special Egg.");
		MiscellaneousItem ancientArtifact = new MiscellaneousItem(3201, "Ancient Artifact", 1, 50,
				"A mysterious artifact from ancient times.");
		MiscellaneousItem gemstone = new MiscellaneousItem(3301, "Gemstone", 5, 20,
				"A precious gemstone with magical properties.");
		MiscellaneousItem strangeKey = new MiscellaneousItem(3401, "Strange Key", 1, 10,
				"A key that seems to unlock unknown doors.");
		apkalluEgg = miscellaneousItemDao.create(apkalluEgg);
		ancientArtifact = miscellaneousItemDao.create(ancientArtifact);
		gemstone = miscellaneousItemDao.create(gemstone);
		strangeKey = miscellaneousItemDao.create(strangeKey);

		// Get MiscellaneousItem by ID
		int apkalluEggId = apkalluEgg.getItemID();
		MiscellaneousItem retrievedApkalluEgg = miscellaneousItemDao.getMiscellaneousItemByID(apkalluEggId);
		System.out.println(green + "Retrieved MiscellaneousItem: " + retrievedApkalluEgg.getItemName() + reset);
		System.out.println(green + "Description: " + retrievedApkalluEgg.getItemDescription() + reset);
		System.out.println("\n\n=========================================\n\n");

		// Update MiscellaneousItem description
		String newApkalluEggDescription = "A rare and valuable egg from the Apkallu species.";
		retrievedApkalluEgg = miscellaneousItemDao.updateMiscellaneousItemDescription(retrievedApkalluEgg,
				newApkalluEggDescription);
		System.out.println(yellow + "Updated MiscellaneousItem: " + retrievedApkalluEgg.getItemName()
				+ "\nNew Description: " + retrievedApkalluEgg.getItemDescription() + reset);
		System.out.println("\n\n=========================================\n\n");

		// ConsumableItem
		ConsumableItemDao consumableItemDao = ConsumableItemDao.getInstance();
		ConsumableItem pancake1 = new ConsumableItem(5000, "Giant Potato Pancakes", 5, 15, 1,
				"Increases health and happiness");
		ConsumableItem pancake2 = new ConsumableItem(5101, "Special Pancake", 3, 12, 2,
				"Provides a temporary speed boost");
		ConsumableItem pancake3 = new ConsumableItem(5202, "Magic Pancake", 1, 25, 3, "Grants magical abilities");
		pancake1 = consumableItemDao.create(pancake1);
		pancake2 = consumableItemDao.create(pancake2);
		pancake3 = consumableItemDao.create(pancake3);

		// Update the description of a ConsumableItem
		ConsumableItem updatedPancake = consumableItemDao.updateConsumableItemDescription(pancake1,
				"Now with extra magic!");
		System.out.println(yellow + "Updated ConsumableItem description: " + reset);
		System.out.format(
				"\n \n\tItemID:%s \n\tItem Name:%s \n\tMax Stack Size:%s \n\tVendor Price:%s \n\tLevel:%s \n\tDescription:%s",
				updatedPancake.getItemID(), updatedPancake.getItemName(), updatedPancake.getMaxStackSize(),
				updatedPancake.getVendorPrice(), updatedPancake.getItemLevel(), updatedPancake.getItemDescription());
		System.out.println("\n\n=========================================\n\n");

		// Delete a ConsumableItem
		pancake2 = consumableItemDao.delete(pancake2);
		System.out.println(red + "ConsumableItem deleted." + reset);
		System.out.println("\n\n=========================================\n\n");

		// Customised Gear and Weapon
		CustomizedGearDao customizedGearDao = CustomizedGearDao.getInstance();
		CustomizedWeaponDao customizedWeaponDao = CustomizedWeaponDao.getInstance();
		CustomizedGear customizedGear = new CustomizedGear(1111, "Enchanted Armor", 1, 100, 10, body, 5, 50, 20, "high",
				90, "Blue", "Master Smith");
		CustomizedWeapon customizedWeapon = new CustomizedWeapon(2222, "Fire Sword", 1, 150, 15, 10, 30,
				BigDecimal.valueOf(5.75), BigDecimal.valueOf(6.9), "normal", 80, "Red", "Master Blacksmith");
		customizedGear = customizedGearDao.create(customizedGear);
		customizedWeapon = customizedWeaponDao.create(customizedWeapon);

		// Retrieve by ID
		CustomizedGear retrievedCustomizedGear = customizedGearDao.getCustomizedGearByID(customizedGear.getItemID());
		CustomizedWeapon retrievedCustomizedWeapon = customizedWeaponDao
				.getCustomizedWeaponByID(customizedWeapon.getItemID());

		displayCustomizedGear(retrievedCustomizedGear);
		displayCustomizedWeapon(retrievedCustomizedWeapon);
		System.out.println("\n\n=========================================\n\n");

		// Inventory DAO
		InventoryDao inventoryDao = InventoryDao.getInstance();
		Inventory inventory = new Inventory();
		inventory.setCharacter(kratos);
		inventory.setInventorySlotId(1);
		inventory.setQuantity(5);
		inventory.setItem(sword);
		inventory = inventoryDao.create(inventory);

		// Retrieve by ID
		Inventory retrievedInventory = inventoryDao.getInventoryByID(kratos.getCharacterID(),
				inventory.getInventorySlotId());
		displayInventory(retrievedInventory);
		System.out.println("\n\n=========================================\n\n");

		// CharacterEquipped DAO
		CharacterEquippedDao characterEquippedDao = CharacterEquippedDao.getInstance();
		CharacterEquipped characterEquipped = new CharacterEquipped();
		characterEquipped.setCharacter(mario);
		characterEquipped.setGearSlot(mainHand);
		characterEquipped.setGear(steelHelmet);
		characterEquipped = characterEquippedDao.create(characterEquipped);

		// Retrieve by IDs
		CharacterEquipped retrievedCharacterEquipped = characterEquippedDao
				.getCharacterEquippedByIds(mario.getCharacterID(), mainHand.getGearSlotId());
		System.out.println("CharacterEquipped -> ");
		System.out.println("Character ID: " + retrievedCharacterEquipped.getCharacter().getCharacterID());
		System.out.println("Gear Slot ID: " + retrievedCharacterEquipped.getGearSlot().getGearSlotId());
		System.out.println("Item ID: " + retrievedCharacterEquipped.getGear().getItemID());
		System.out.println("\n\n=========================================\n\n");

		// Job DAO
		JobDao jobDao = JobDao.getInstance();
		Job warrior = new Job(1, "Warrior", true);
		Job knight = new Job(2, "Knight", true);
		Job archer = new Job(3, "Archer", true);
		warrior = jobDao.create(warrior);
		knight = jobDao.create(knight);
		archer = jobDao.create(archer);

		// GearJob DAO
		GearJobDao gearJobDao = GearJobDao.getInstance();
		GearJob gearJob = new GearJob();
		gearJob.setGear(ironShield);
		gearJob.setJob(knight);
		gearJob = gearJobDao.create(gearJob);

		// WeaponJob DAO
		WeaponJobDao weaponJobDao = WeaponJobDao.getInstance();
		WeaponJob weaponJob = new WeaponJob();
		weaponJob.setWeapon(dagger);
		weaponJob.setJob(warrior);
		weaponJob = weaponJobDao.create(weaponJob);

		GearJob retrievedGearJob = gearJobDao.getGearJobByIds(ironShield.getItemID(), knight.getJobId());
		System.out.println("GearJob:");
		System.out.println("Gear ID: " + retrievedGearJob.getGear().getItemID());
		System.out.println("Gear Name: " + retrievedGearJob.getGear().getItemName());
		System.out.println("Job ID: " + retrievedGearJob.getJob().getJobId());
		System.out.println("\n\n=========================================\n\n");

		// Retrieve by IDs
		WeaponJob retrievedWeaponJob2 = weaponJobDao.getWeaponJobByIds(dagger.getItemID(), warrior.getJobId());
		System.out.println("WeaponJob:");
		System.out.println("Weapon ID: " + retrievedWeaponJob2.getWeapon().getItemID());
		System.out.println("Weapon Name: " + retrievedWeaponJob2.getWeapon().getItemName());
		System.out.println("Job ID: " + retrievedWeaponJob2.getJob().getJobId());
		System.out.println("\n\n=========================================\n\n");

		// CharacterJob DAO
		CharacterJobDao characterJobDao = CharacterJobDao.getInstance();
		CharacterJob characterJob = new CharacterJob();
		characterJob.setCharacter(mario);
		characterJob.setJob(archer);
		characterJob.setJobLevel(5);
		characterJob.setExperiencePoint(1000);
		characterJob.setCurrentJob(true);
		characterJob = characterJobDao.insertCharacterJob(characterJob);

		// Retrieve by IDs
		CharacterJob retrievedCharacterJob = characterJobDao.getCharacterJobByIds(mario.getCharacterID(),
				archer.getJobId());
		System.out.println("CharacterJob:");
		System.out.println("Character Name: " + retrievedCharacterJob.getCharacter().getFirstName() + " "
				+ retrievedCharacterJob.getCharacter().getLastName());
		System.out.println("Job Name: " + retrievedCharacterJob.getJob().getJobName());
		System.out.println("Exp Points: " + retrievedCharacterJob.getExperiencePoint());
		System.out.println("Job Level: " + retrievedCharacterJob.getJobLevel());
		System.out.println("\n\n=========================================\n\n");

		// Currency
		CurrencyDao currencyDao = CurrencyDao.getInstance();
		Currency gold = new Currency();
		gold.setCurrencyName("Gold");
		gold.setMaximumAmount(10000);
		gold.setWeeklyCap(2000);
		gold.setAvailability(true);
		gold = currencyDao.create(gold);
		displayCurrency(gold);
		System.out.println("\n\n=========================================\n\n");

		// Character Currency
		CharacterCurrencyDao characterCurrencyDao = CharacterCurrencyDao.getInstance();
		CharacterCurrency kratosGold = new CharacterCurrency();
		kratosGold.setCharacter(kratos);
		kratosGold.setCurrency(gold);
		kratosGold.setWeeklyAmount(500);
		kratosGold.setTotalAmount(2000);
		kratosGold = characterCurrencyDao.create(kratosGold);
		displayCharacterCurrency(kratosGold);
		System.out.println("\n\n=========================================\n\n");

		WeaponBonusDao weaponBonusDao = WeaponBonusDao.getInstance();
		WeaponBonus weaponBonus = new WeaponBonus(sword, strength, 10);
		weaponBonus = weaponBonusDao.create(weaponBonus);
		System.out.println("WeaponBonus created successfully.");

		System.out.format(green + "\nRetrieving WeaponBonus by ID: %s ", weaponBonus.getItem().getItemID() + reset);
		WeaponBonus retrievedWeaponBonus = weaponBonusDao.getWeaponBonusByID(weaponBonus.getItem().getItemID(),
				weaponBonus.getAttribute().getAttributeID());
		System.out.format(
				green + "\nRetrieved WeaponBonus -> \n\tWeapon Name:%s \n\tAttribute Name: %s \n\tBonus Value: %s ",
				retrievedWeaponBonus.getItem().getItemName(), retrievedWeaponBonus.getAttribute().getAttributesName(),
				retrievedWeaponBonus.getBonusValue() + reset);
		System.out.println("\n\n=========================================\n\n");

		System.out.println(red + "Deleting WepaonBonus..." + reset);
		weaponBonus = weaponBonusDao.delete(weaponBonus);
		System.out.println(red + "Deleted." + reset);
		System.out.println("\n\n=========================================\n\n");

	}
}
