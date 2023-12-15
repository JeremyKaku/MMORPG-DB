<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="game.model.Character"%>
<%@ page import="game.model.Weapon"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Character Page</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%
	Character character = (Character) request.getAttribute("character");
	Weapon weapon = (Weapon) request.getAttribute("weapon");
	%>
	<img src="images/go-back-icon.png" title="Back To Player Dashboard"
		class='back-image' onclick="goBack()" alt="Back Icon">
	<h1 class="header-character">${character.firstName}
		${character.lastName}</h1>

	<div class="info">
		<div class="icon" title="I'm very strong!!">
			<a class="my-text" href="javascript:void(0);"
				onclick="redirectToPageCharacterAttribute(${character.characterID})"><img
				src="images/attribute-icon.png" class='img' alt="Attribute Icon">Attribute
				></a>
		</div>
		<div class="icon" title="Show me the money!">
			<a class="my-text" href="javascript:void(0);"
				onclick="redirect(${character.characterID},'/FinalFinal/character/characterCurrency')"><img
				class='img' src="images/currency-icon.png" alt="Currency">Currency
				></a>
		</div>
		<div class="icon" title="Stay Hungry! Stay Foolish!">
			<a class="my-text" href="javascript:void(0);"
				onclick="redirect(${character.characterID},'/FinalFinal/character/characterJob')"><img
				class='img' src="images/job-icon.png" alt="Currency">Job ></a>
		</div>
		<div class="icon" title="Let's see what else I've got.">
			<a class="my-text" href="javascript:void(0);"
				onclick="redirectToPageInventory(${character.characterID})"><img
				class='img' src="images/inventory-icon.png" alt="Currency">Inventory
				></a>
		</div>
	</div>
	<img src="images/character-image.jpg" alt="Character Image"
		id="characterImage">
	<div class="equipment">
		<div class="icon"
			title="
Weapon:${weapon.itemName}
			
MaxStackSize: ${weapon.maxStackSize}
VendorPrice: ${weapon.vendorPrice}
Level: ${weapon.itemLevel}
RequiredLevel: ${weapon.requiredLevel}
AamageDone: ${weapon.damageDone}
AutoAttack: ${weapon.autoAttack}
AttackDelay: ${weapon.attackDelay}">
			<a class="my-text"> <img src="images/weapon-icon.png" class='img'
				alt="Weapon Icon">Main Hand
			</a>
		</div>
		<div class="icon" title="EQUIP">
			<a class="my-text" href="javascript:void(0);"
				onclick="redirectToPageCharacterGear(${character.characterID})"><img
				src="images/gear-icon.png" class='img' alt="Gear Icon">Gear ></a>

		</div>
	</div>
	<script src="script.js"></script>

</body>
</html>

